package com.poetry.server.admin.dto;

import com.poetry.common.annotation.CheckDate;
import com.poetry.db.entity.PoetrySysUsers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class PoetrySysUserCollectDto extends AbstractDto<String> {
    @Size(max = 64)
    @NotBlank
    private String userCollectId;
    private PoetrySysUsers sysUsers;
    @CheckDate
    private Date createTime;
    @CheckDate
    private Date updateTime;

    public PoetrySysUserCollectDto() {
    }

    public void setUserCollectId(String userCollectId) {
        this.userCollectId = userCollectId;
    }

    public String getUserCollectId() {
        return this.userCollectId;
    }

    public void setSysUsers(PoetrySysUsers sysUsers) {
        this.sysUsers = sysUsers;
    }

    public PoetrySysUsers getSysUsers() {
        return this.sysUsers;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }
}