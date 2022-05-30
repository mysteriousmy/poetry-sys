package com.poetry.server.admin.controller;

import com.poetry.common.annotation.JwtToken;
import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysDynastyDto;
import com.poetry.server.admin.service.PoetrySysDynastyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/poetry/admins/dynasty")
@Slf4j
public class PoetrySysAdminDynastyController {
    @Autowired
    private PoetrySysDynastyService dynastyService;
    @JwtToken
    @GetMapping("")
    public SuccessResponse getAllDynasty(){
        return dynastyService.getAllDynasty();
    }

    @JwtToken
    @GetMapping("/{id}")
    public SuccessResponse getDynastyById(@PathVariable("id") String id){
        return dynastyService.getDynastyById(id);
    }

    @JwtToken
    @PostMapping("")
    public SuccessResponse saveDynasty(@RequestBody @Validated PoetrySysDynastyDto dynastyDto){
        return dynastyService.saveDynasty(dynastyDto);
    }

    @JwtToken
    @PutMapping("")
    public SuccessResponse updateDynasty(@RequestBody @Validated PoetrySysDynastyDto dynastyDto){
        return dynastyService.updateDynasty(dynastyDto);
    }

    @JwtToken
    @DeleteMapping("/{id}")
    public SuccessResponse deleteDynasty(@PathVariable("id") String id){
        return dynastyService.deleteDynastyById(id);
    }
    @JwtToken
    @PostMapping("/query")
    public SuccessResponse queryDynastyByWords(Map<String, Object> queryMap){
        return dynastyService.queryDynastyByWords(queryMap);
    }
}
