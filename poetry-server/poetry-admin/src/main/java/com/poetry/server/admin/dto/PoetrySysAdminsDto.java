package com.poetry.server.admin.dto;

import com.poetry.common.annotation.CheckDate;
import com.poetry.common.annotation.CheckEmail;
import com.poetry.common.consts.enums.AdminsType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PoetrySysAdminsDto extends AbstractDto<String> {
    public String getAdminsId() {
        return adminsId;
    }

    public void setAdminsId(String adminsId) {
        this.adminsId = adminsId;
    }

    private String adminsId;
    @Size(max = 30)
    @NotBlank
    private String adminsName;
    @Size(max = 20)
    private String adminsPassword;
    @CheckEmail
    @Size(max = 255)
    @NotBlank
    private String adminsEmail;

    private AdminsType adminsType;

    public PoetrySysAdminsDto() {
    }


    public void setAdminsName(String adminsName) {
        this.adminsName = adminsName;
    }

    public String getAdminsName() {
        return this.adminsName;
    }

    public void setAdminsPassword(String adminsPassword) {
        this.adminsPassword = adminsPassword;
    }

    public String getAdminsPassword() {
        return this.adminsPassword;
    }

    public void setAdminsEmail(String adminsEmail) {
        this.adminsEmail = adminsEmail;
    }

    public String getAdminsEmail() {
        return this.adminsEmail;
    }

    public void setAdminsType(AdminsType adminsType) {
        this.adminsType = adminsType;
    }

    public AdminsType getAdminsType() {
        return this.adminsType;
    }

}