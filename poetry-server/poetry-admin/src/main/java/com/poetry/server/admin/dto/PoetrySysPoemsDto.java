package com.poetry.server.admin.dto;

import com.poetry.common.annotation.CheckDate;
import com.poetry.common.consts.enums.PoemsType;
import com.poetry.db.entity.PoetrySysPoets;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class PoetrySysPoemsDto extends AbstractDto<String> {
    @Size(max = 64)
    private String poemId;
    @Size(max = 255)
    @NotBlank
    private String poemTitle;
    @NotNull
    private PoemsType poemsType;
    @Size(max = 255)
    @NotBlank
    private String poemContent;
    private String poemTranslates;
    private String poemAnnotation;
    private String poemAppreciation;
    private String poemCreateScene;

    public String getPoetsId() {
        return poetsId;
    }

    public void setPoetsId(String poetsId) {
        this.poetsId = poetsId;
    }

    @NotBlank
    private String poetsId;

    public PoetrySysPoemsDto() {
    }

    public void setPoemId(String poemId) {
        this.poemId = poemId;
    }

    public String getPoemId() {
        return this.poemId;
    }

    public void setPoemTitle(String poemTitle) {
        this.poemTitle = poemTitle;
    }

    public String getPoemTitle() {
        return this.poemTitle;
    }

    public void setPoemsType(PoemsType poemsType) {
        this.poemsType = poemsType;
    }

    public PoemsType getPoemsType() {
        return this.poemsType;
    }

    public void setPoemContent(String poemContent) {
        this.poemContent = poemContent;
    }

    public String getPoemContent() {
        return this.poemContent;
    }

    public void setPoemTranslates(String poemTranslates) {
        this.poemTranslates = poemTranslates;
    }

    public String getPoemTranslates() {
        return this.poemTranslates;
    }

    public void setPoemAnnotation(String poemAnnotation) {
        this.poemAnnotation = poemAnnotation;
    }

    public String getPoemAnnotation() {
        return this.poemAnnotation;
    }

    public void setPoemAppreciation(String poemAppreciation) {
        this.poemAppreciation = poemAppreciation;
    }

    public String getPoemAppreciation() {
        return this.poemAppreciation;
    }

    public void setPoemCreateScene(String poemCreateScene) {
        this.poemCreateScene = poemCreateScene;
    }

    public String getPoemCreateScene() {
        return this.poemCreateScene;
    }


}