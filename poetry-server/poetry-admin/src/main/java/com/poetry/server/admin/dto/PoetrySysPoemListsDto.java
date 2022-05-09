package com.poetry.server.admin.dto;

import com.poetry.common.annotation.CheckDate;
import com.poetry.db.entity.PoetrySysPoems;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class PoetrySysPoemListsDto extends AbstractDto<String> {
    @Size(max = 64)
    private String poemListId;
    @Size(max = 255)
    @NotBlank
    private String poemListTitle;
    @Size(max = 255)
    @NotBlank
    private String poemListDesc;

    public String getPoemListImage() {
        return poemListImage;
    }

    public void setPoemListImage(String poemListImage) {
        this.poemListImage = poemListImage;
    }

    @NotBlank
    private String poemListImage;
    public Set<PoetrySysPoems> getPoems() {
        return poems;
    }

    public void setPoems(Set<PoetrySysPoems> poems) {
        this.poems = poems;
    }

    private Set<PoetrySysPoems> poems;

    public PoetrySysPoemListsDto() {
    }

    public void setPoemListId(String poemListId) {
        this.poemListId = poemListId;
    }

    public String getPoemListId() {
        return this.poemListId;
    }

    public void setPoemListTitle(String poemListTitle) {
        this.poemListTitle = poemListTitle;
    }

    public String getPoemListTitle() {
        return this.poemListTitle;
    }

    public void setPoemListDesc(String poemListDesc) {
        this.poemListDesc = poemListDesc;
    }

    public String getPoemListDesc() {
        return this.poemListDesc;
    }


}