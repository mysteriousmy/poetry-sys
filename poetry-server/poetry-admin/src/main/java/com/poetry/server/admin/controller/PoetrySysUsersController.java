package com.poetry.server.admin.controller;

import com.poetry.common.annotation.JwtToken;
import com.poetry.common.response.SuccessResponse;
import com.poetry.server.admin.dto.PoetrySysUsersDto;
import com.poetry.server.admin.service.PoetrySysUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/poetry/admins/users")
@Slf4j
public class PoetrySysUsersController {
    @Autowired
    private PoetrySysUsersService usersService;
    @JwtToken
    @GetMapping("")
    public SuccessResponse getAllUsers(){
        return usersService.getAllUsers();
    }

    @JwtToken
    @GetMapping("/{id}")
    public SuccessResponse getUsersById(@PathVariable("id") String id){
        return usersService.getUsersById(id);
    }

    @JwtToken
    @PostMapping("")
    public SuccessResponse saveUsers(@RequestBody @Validated PoetrySysUsersDto usersDto){
        return usersService.saveUsers(usersDto);
    }

    @JwtToken
    @PutMapping("")
    public SuccessResponse updateUsers(@RequestBody @Validated PoetrySysUsersDto usersDto){
        return usersService.updateUsers(usersDto);
    }

    @JwtToken
    @DeleteMapping("/{id}")
    public SuccessResponse deleteUsers(@PathVariable("id") String id){
        return usersService.deleteUsersById(id);
    }
}
