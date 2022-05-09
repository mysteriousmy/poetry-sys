package com.poetry.server.admin.controller;


import com.poetry.common.annotation.JwtToken;
import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.EmailLoginDto;
import com.poetry.server.admin.dto.PoetrySysAdminLoginDto;
import com.poetry.server.admin.dto.PoetrySysAdminsDto;
import com.poetry.server.admin.dto.SysInitDto;
import com.poetry.server.admin.service.SysAdminsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/poetry/admins")
@Slf4j
public class PoetrySysAdminsController {
    @Autowired
    private SysAdminsService adminsService;
    @PostMapping("/login")
    public SuccessResponse adminLogin(@RequestBody @Validated PoetrySysAdminLoginDto loginDto) throws Exception {
        return new SuccessResponse(adminsService.login(loginDto));
    }
    @GetMapping("/hasInit")
    public SuccessResponse hasInit() throws Exception {
        return new SuccessResponse(adminsService.sysHasInit());
    }
    @GetMapping("/pubEmail")
    public SuccessResponse pubEmail(String email) throws Exception{
        adminsService.pubEmailVerifyCode(email);
        return new SuccessResponse(true);
    }
    @PostMapping("/init")
    public SuccessResponse sysInit(@Validated @RequestBody SysInitDto sysInitDto) throws Exception {
        return adminsService.sysInit(sysInitDto);
    }
    @PostMapping("/emailLogin")
    public SuccessResponse emailLogin(@RequestBody @Validated EmailLoginDto emailLoginDto) throws Exception{
        return new SuccessResponse(adminsService.emailLogin(emailLoginDto));
    }
    @JwtToken
    @GetMapping("")
    public SuccessResponse getAllAdmins(){
        return adminsService.getAllAdmins();
    }

    @JwtToken
    @GetMapping("/{id}")
    public SuccessResponse getAdminsById(@PathVariable("id") String id){
        return adminsService.getAdminsById(id);
    }

    @JwtToken
    @PostMapping("")
    public SuccessResponse saveAdmins(@RequestBody @Validated PoetrySysAdminsDto rhesisDto){
        return adminsService.saveAdmins(rhesisDto);
    }

    @JwtToken
    @PutMapping("")
    public SuccessResponse updateAdmins(@RequestBody @Validated PoetrySysAdminsDto rhesisDto){
        return adminsService.updateAdmins(rhesisDto);
    }

    @JwtToken
    @DeleteMapping("/{id}")
    public SuccessResponse deleteAdmins(@PathVariable("id") String id){
        return adminsService.deleteAdminsById(id);
    }
}