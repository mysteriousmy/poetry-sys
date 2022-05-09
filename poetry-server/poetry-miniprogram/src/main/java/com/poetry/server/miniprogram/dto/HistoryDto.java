package com.poetry.server.miniprogram.dto;

import com.poetry.db.entity.PoetrySysPoems;
import com.poetry.db.entity.PoetrySysPoets;
import com.poetry.db.entity.PoetrySysRhesis;
import lombok.Data;

import java.util.List;

@Data
public class HistoryDto {
    public List<PoetrySysPoems> poems;
    public List<PoetrySysRhesis> rheses;
    public List<PoetrySysPoets> poets;
}
