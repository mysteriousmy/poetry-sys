package com.poetry.server.miniprogram.Controller;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.miniprogram.Service.UserService;
import com.poetry.server.miniprogram.dto.CollectDto;
import com.poetry.server.miniprogram.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/poetry/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/doLogin")
    public SuccessResponse login(@RequestBody @Validated UserDto userDto){
        return userService.doLogin(userDto);
    }
    @PostMapping("/check")
    public SuccessResponse check(@RequestBody @Validated UserDto userDto){
        return userService.doCheck(userDto);
    }
    @GetMapping("/check/collect/{id}")
    public SuccessResponse checkCollect(@PathVariable("id") String id, String openid){
        return userService.checkCollect(id, openid);
    }
    @PostMapping("/collect")
    public SuccessResponse collect(@RequestBody @Validated CollectDto dto){
        return userService.collect(dto);
    }
    @GetMapping("/collect/list")
    public  SuccessResponse getCollect(int type, String openid){
        return userService.getCollectByType(type, openid);
    }
    @GetMapping("/history/list")
    public SuccessResponse getHistory(String openid){
        return userService.getHistoryByUser(openid);
    }
}
