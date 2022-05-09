package com.poetry.server.admin.controller;

import com.poetry.common.annotation.JwtToken;
import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysPoetsDto;
import com.poetry.server.admin.service.PoetrySysPoetsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/poetry/admins/poets")
@Slf4j
public class PoetrySysAdminPoetsController {
    @Autowired
    private PoetrySysPoetsService poetService;
    @JwtToken
    @GetMapping("")
    public SuccessResponse getAllPoets(){
        return poetService.getAllPoets();
    }

    @JwtToken
    @GetMapping("/{id}")
    public SuccessResponse getPoetsById(@PathVariable("id") String id){
        return poetService.getPoetsById(id);
    }

    @JwtToken
    @PostMapping("")
    public SuccessResponse savePoets(@RequestBody @Validated PoetrySysPoetsDto poetDto){
        return poetService.savePoets(poetDto);
    }

    @JwtToken
    @PutMapping("")
    public SuccessResponse updatePoets(@RequestBody @Validated PoetrySysPoetsDto poetDto){
        return poetService.updatePoets(poetDto);
    }

    @JwtToken
    @DeleteMapping("/{id}")
    public SuccessResponse deletePoets(@PathVariable("id") String id){
        return poetService.deletePoetsById(id);
    }
}
