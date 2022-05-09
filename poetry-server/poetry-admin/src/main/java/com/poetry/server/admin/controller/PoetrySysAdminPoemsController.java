package com.poetry.server.admin.controller;

import com.poetry.common.annotation.JwtToken;
import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysPoemsDto;
import com.poetry.server.admin.service.PoetrySysPoemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/poetry/admins/poems")
@Slf4j
public class PoetrySysAdminPoemsController {
    @Autowired
    private PoetrySysPoemsService poemsService;
    @JwtToken
    @GetMapping("")
    public SuccessResponse getAllPoems(){
        return poemsService.getAllPoems();
    }

    @JwtToken
    @GetMapping("/{id}")
    public SuccessResponse getPoemsById(@PathVariable("id") String id){
        return poemsService.getPoemsById(id);
    }

    @JwtToken
    @PostMapping("")
    public SuccessResponse savePoems(@RequestBody @Validated PoetrySysPoemsDto PoemsDto){
        return poemsService.savePoems(PoemsDto);
    }

    @JwtToken
    @PutMapping("")
    public SuccessResponse updatePoems(@RequestBody @Validated PoetrySysPoemsDto PoemsDto){
        return poemsService.updatePoems(PoemsDto);
    }

    @JwtToken
    @DeleteMapping("/{id}")
    public SuccessResponse deletePoems(@PathVariable("id") String id){
        return poemsService.deletePoemsById(id);
    }

    @JwtToken
    @PostMapping("/query")
    public SuccessResponse queryPoemByWords(Map<String, Object> queryMap){
        return poemsService.queryPoemByWords(queryMap);
    }
}
