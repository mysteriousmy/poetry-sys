package com.poetry.server.admin.dto;

import com.poetry.common.annotation.CheckDate;
import com.poetry.common.consts.enums.UsersGender;
import com.poetry.db.entity.PoetrySysSearchHistory;
import com.poetry.db.entity.PoetrySysUserCollect;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class PoetrySysUsersDto extends AbstractDto<String> {
    @Size(max = 64)
    @NotBlank
    private String userId;
    @Size(max = 64)
    @NotBlank
    private String openId;
    @Size(max = 50)
    @NotBlank
    private String userName;
    private String userPhoto;
    @NotNull
    private UsersGender userGender;
    private List<PoetrySysUserCollect> collectList;
    private List<PoetrySysSearchHistory> searchHistoryList;
    @CheckDate
    private Date createTime;
    @CheckDate
    private Date updateTime;

    public PoetrySysUsersDto() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserPhoto() {
        return this.userPhoto;
    }

    public void setUserGender(UsersGender userGender) {
        this.userGender = userGender;
    }

    public UsersGender getUserGender() {
        return this.userGender;
    }

    public void setCollectList(java.util.List<com.poetry.db.entity.PoetrySysUserCollect> collectList) {
        this.collectList = collectList;
    }

    public java.util.List<com.poetry.db.entity.PoetrySysUserCollect> getCollectList() {
        return this.collectList;
    }

    public void setSearchHistoryList(java.util.List<com.poetry.db.entity.PoetrySysSearchHistory> searchHistoryList) {
        this.searchHistoryList = searchHistoryList;
    }

    public java.util.List<com.poetry.db.entity.PoetrySysSearchHistory> getSearchHistoryList() {
        return this.searchHistoryList;
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