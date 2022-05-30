package com.poetry.server.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.poetry.common.consts.enums.AdminsType;
import com.poetry.common.consts.enums.ErrorCode;
import com.poetry.common.exception.CustomException;
import com.poetry.common.response.SuccessResponse;
import com.poetry.common.util.AesUtil;
import com.poetry.common.util.EmailUtil;
import com.poetry.common.util.JwtUtil;
import com.poetry.db.entity.PoetrySysAdmins;
import com.poetry.db.entity.PoetrySysRhesis;
import com.poetry.db.repository.PoetrySysAdminsRepository;
import com.poetry.server.admin.dto.EmailLoginDto;
import com.poetry.server.admin.dto.PoetrySysAdminLoginDto;
import com.poetry.server.admin.dto.PoetrySysAdminsDto;
import com.poetry.server.admin.dto.SysInitDto;
import com.poetry.server.admin.service.SysAdminsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.util.*;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class SysAdminsServiceImpl implements SysAdminsService {
    @Autowired
    private PoetrySysAdminsRepository repository;
    @Autowired
    private EmailUtil emailUtil;

    private static Map<String, String> emailVerifyCode = new HashMap<>();
    @Override
    public Object login(PoetrySysAdminLoginDto adminsDto) throws RuntimeException {
        String adminName = adminsDto.getAdminsName();
        String adminPassword = AesUtil.Encrypt(adminsDto.getAdminsPassword());
        PoetrySysAdmins admins = repository.findByAdminsNameAndAdminsPassword(adminName, adminPassword);
        if(admins == null){
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getCode(),"用户名或密码错误！");
        }
        return generateJwt(admins);
    }

    @Override
    public SuccessResponse  pubEmailVerifyCode(String email) {
        PoetrySysAdmins admins = repository.findByAdminsEmail(email);
        if(admins == null){
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getCode(),"未找到该管理员的邮箱！");
        }
        String value = UUID.randomUUID().toString().substring(0,6);
        emailUtil.sendVerifyCode(email, value);
        emailVerifyCode.put(email + admins.adminsId, value);
        return new SuccessResponse(true);
    }

    @Override
    public Object emailLogin(EmailLoginDto emailLoginDto) {
        String adminEmail = emailLoginDto.getAdminsEmail();
        String verifyCode = emailLoginDto.getVerifyCode();
        PoetrySysAdmins admins = repository.findByAdminsEmail(adminEmail);
        if(admins == null){
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getCode(),"未找到该管理员的邮箱！可能已被删除！");
        }
        String now_vc = emailVerifyCode.get(adminEmail + admins.adminsId);
        System.out.println(now_vc);
        if(!verifyCode.equals(now_vc)){
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getCode(), "验证码错误！");
        }
        return generateJwt(admins);
    }

    @Override
    public Boolean autoLogin() throws Exception {
        return null;
    }

    @Override
    public Boolean sysHasInit() throws Exception {
        List<PoetrySysAdmins> admins = repository.findByAdminsType(AdminsType.SUPER_ADMIN);
        return admins != null && admins.size() != 0;
    }

    @Override
    public SuccessResponse sysInit(SysInitDto sysInitDto) throws Exception {
        List<PoetrySysAdmins> admins = repository.findByAdminsType(AdminsType.SUPER_ADMIN);

        if(admins != null && admins.size() > 0){
            throw new CustomException(ErrorCode.DATA_NOT_FOUND.getCode(), "系统已经初始化，不可重复初始化！");
        }
        PoetrySysAdmins admin = new PoetrySysAdmins();
        admin.adminsName = sysInitDto.getUsername();
        admin.adminsEmail = sysInitDto.getEmail();
        admin.adminsPassword = AesUtil.Encrypt(sysInitDto.getPassword());
        admin.adminsType = AdminsType.SUPER_ADMIN;
        repository.saveAndFlush(admin);
        return new SuccessResponse(true);
    }

    @Override
    public SuccessResponse getAllAdmins() {
        List<PoetrySysAdmins> admins = repository.findByAdminsType(AdminsType.NORMAL_ADMIN);
        return new SuccessResponse(admins);
    }

    @Override
    public SuccessResponse getAdminsById(String id) {
        PoetrySysAdmins admins = repository.findByAdminsId(id);
        return new SuccessResponse(admins);
    }

    @Override
    public SuccessResponse saveAdmins(PoetrySysAdminsDto adminsDto) {
        PoetrySysAdmins admins = new PoetrySysAdmins();
        admins.adminsName = adminsDto.getAdminsName();
        admins.adminsEmail = adminsDto.getAdminsEmail();
        admins.adminsType = AdminsType.NORMAL_ADMIN;
        if(adminsDto.getAdminsPassword() == null || adminsDto.getAdminsPassword().trim().length() == 0){
            admins.adminsPassword = AesUtil.Encrypt("12345678");
        }else{
            admins.adminsPassword = AesUtil.Encrypt(adminsDto.getAdminsPassword());
        }
        PoetrySysAdmins temp = repository.findByAdminsNameOrAdminsEmail(admins.adminsName,admins.adminsEmail);
        if(temp!=null){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), "该管理员或邮箱已存在！");
        }
        repository.save(admins);
        return new SuccessResponse(admins);
    }

    @Override
    public SuccessResponse updateAdmins(PoetrySysAdminsDto adminsDto) {
        PoetrySysAdmins admins = repository.findByAdminsId(adminsDto.getAdminsId());
        admins.adminsName = adminsDto.getAdminsName();
        admins.adminsEmail = adminsDto.getAdminsEmail();
        admins.adminsPassword = AesUtil.Encrypt(adminsDto.getAdminsPassword());
        repository.saveAndFlush(admins);
        return new SuccessResponse(admins);
    }



    @Override
    public SuccessResponse deleteAdminsById(String id) {
        if(id == null || id.length() == 0){
            throw new CustomException(ErrorCode.INVALID_PARAM.getCode(), ErrorCode.INVALID_PARAM.getSemantics());
        }
        int result = repository.deleteByAdminsIdAndAdminsTypeNot(id, AdminsType.SUPER_ADMIN);

        return new SuccessResponse(result);
    }

    private static JSONObject generateJwt(PoetrySysAdmins admins){
        JSONObject jsonObject = new JSONObject();
        String token = JwtUtil.sign(admins.adminsId);
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", admins.adminsName);
        userInfo.put("userpwd", admins.adminsPassword);
        jsonObject.put("token", token);
        jsonObject.put("user", userInfo);
        return jsonObject;
    }
}
