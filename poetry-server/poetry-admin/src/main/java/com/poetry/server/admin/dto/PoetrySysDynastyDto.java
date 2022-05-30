package com.poetry.server.admin.dto;

import com.poetry.common.annotation.CheckDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class PoetrySysDynastyDto extends AbstractDto<String> {
    @Size(max = 64)
    private String dynastyId;
    @Size(max = 50)
    @NotBlank(message = "朝代名称不能为空！")
    private String dynastyName;
    @Size(max = 255,message = "描述信息超标！")
    private String dynastyDesc;

    public PoetrySysDynastyDto() {
    }

    public void setDynastyId(String dynastyId) {
        this.dynastyId = dynastyId;
    }

    public String getDynastyId() {
        return this.dynastyId;
    }

    public void setDynastyName(String dynastyName) {
        this.dynastyName = dynastyName;
    }

    public String getDynastyName() {
        return this.dynastyName;
    }

    public void setDynastyDesc(String dynastyDesc) {
        this.dynastyDesc = dynastyDesc;
    }

    public String getDynastyDesc() {
        return this.dynastyDesc;
    }
}