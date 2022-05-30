package com.poetry.server.admin.dto;

import com.poetry.common.annotation.CheckDate;
import com.poetry.db.entity.PoetrySysDynasty;
import com.poetry.db.entity.PoetrySysPoems;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class PoetrySysPoetsDto extends AbstractDto<String> {
    @Size(max = 64)
    private String poetsId;
    @Size(max = 255)
    @NotBlank
    private String poetsName;
    private String poetsDesc;
    @NotBlank
    private String poetsPhoto;

    public String getDynastyId() {
        return dynastyId;
    }

    public void setDynastyId(String dynastyId) {
        this.dynastyId = dynastyId;
    }

    private String dynastyId;


    public PoetrySysPoetsDto() {
    }

    public void setPoetsId(String poetsId) {
        this.poetsId = poetsId;
    }

    public String getPoetsId() {
        return this.poetsId;
    }

    public void setPoetsName(String poetsName) {
        this.poetsName = poetsName;
    }

    public String getPoetsName() {
        return this.poetsName;
    }

    public void setPoetsDesc(String poetsDesc) {
        this.poetsDesc = poetsDesc;
    }

    public String getPoetsDesc() {
        return this.poetsDesc;
    }

    public void setPoetsPhoto(String poetsPhoto) {
        this.poetsPhoto = poetsPhoto;
    }

    public String getPoetsPhoto() {
        return this.poetsPhoto;
    }


}