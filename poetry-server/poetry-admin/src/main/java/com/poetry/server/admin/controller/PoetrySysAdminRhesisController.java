package com.poetry.server.admin.controller;

import com.poetry.common.annotation.JwtToken;
import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysRhesisDto;
import com.poetry.server.admin.service.PoetrySysRhesisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/poetry/admins/rhesis")
@Slf4j
public class PoetrySysAdminRhesisController {
    @Autowired
    private PoetrySysRhesisService rhesisService;
    @JwtToken
    @GetMapping("")
    public SuccessResponse getAllRhesis(){
        return rhesisService.getAllRhesis();
    }

    @JwtToken
    @GetMapping("/{id}")
    public SuccessResponse getRhesisById(@PathVariable("id") String id){
        return rhesisService.getRhesisById(id);
    }

    @JwtToken
    @PostMapping("")
    public SuccessResponse saveRhesis(@RequestBody @Validated PoetrySysRhesisDto rhesisDto){
        return rhesisService.saveRhesis(rhesisDto);
    }

    @JwtToken
    @PutMapping("")
    public SuccessResponse updateRhesis(@RequestBody @Validated PoetrySysRhesisDto rhesisDto){
        return rhesisService.updateRhesis(rhesisDto);
    }

    @JwtToken
    @DeleteMapping("/{id}")
    public SuccessResponse deleteRhesis(@PathVariable("id") String id){
        return rhesisService.deleteRhesisById(id);
    }
}
