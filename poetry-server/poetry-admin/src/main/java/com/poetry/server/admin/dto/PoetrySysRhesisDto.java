package com.poetry.server.admin.dto;

import com.poetry.common.annotation.CheckDate;
import com.poetry.db.entity.PoetrySysPoems;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class PoetrySysRhesisDto extends AbstractDto<String> {
    @Size(max = 64)
    private String rhesisId;
    @Size(max = 255)
    @NotBlank
    private String rhesisContent;
    private String rhesisBackground;

    public String getPoemId() {
        return poemId;
    }

    public void setPoemId(String poemId) {
        this.poemId = poemId;
    }
    @NotBlank
    private String poemId;

    public PoetrySysRhesisDto() {
    }

    public void setRhesisId(String rhesisId) {
        this.rhesisId = rhesisId;
    }

    public String getRhesisId() {
        return this.rhesisId;
    }

    public void setRhesisContent(String rhesisContent) {
        this.rhesisContent = rhesisContent;
    }

    public String getRhesisContent() {
        return this.rhesisContent;
    }

    public void setRhesisBackground(String rhesisBackground) {
        this.rhesisBackground = rhesisBackground;
    }

    public String getRhesisBackground() {
        return this.rhesisBackground;
    }

}