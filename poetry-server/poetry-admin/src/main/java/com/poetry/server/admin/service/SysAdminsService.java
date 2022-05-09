package com.poetry.server.admin.service;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.EmailLoginDto;
import com.poetry.server.admin.dto.PoetrySysAdminLoginDto;
import com.poetry.server.admin.dto.PoetrySysAdminsDto;
import com.poetry.server.admin.dto.SysInitDto;

public interface SysAdminsService {
    Object login(PoetrySysAdminLoginDto loginDto) throws Exception;
    SuccessResponse  pubEmailVerifyCode(String email);
    Object emailLogin(EmailLoginDto emailLoginDto);
    Boolean autoLogin() throws Exception;
    Boolean sysHasInit() throws Exception;
    SuccessResponse  sysInit(SysInitDto sysInitDto) throws Exception;

    SuccessResponse getAllAdmins();

    SuccessResponse getAdminsById(String id);

    SuccessResponse saveAdmins(PoetrySysAdminsDto rhesisDto);

    SuccessResponse updateAdmins(PoetrySysAdminsDto rhesisDto);

    SuccessResponse deleteAdminsById(String id);
}
