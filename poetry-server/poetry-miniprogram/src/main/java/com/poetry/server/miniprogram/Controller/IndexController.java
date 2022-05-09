package com.poetry.server.miniprogram.Controller;

import com.poetry.common.consts.enums.PoemsType;
import com.poetry.common.response.SuccessResponse;
import com.poetry.server.miniprogram.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class IndexController {
    @Autowired
    private IndexService indexService;
    @GetMapping("/api/poetry/rhesis")
    public SuccessResponse getAllRhesisToBanner(){
        return indexService.getAllRhesisToBanner();
    }
    @GetMapping("/api/poetry/poemLists/random")
    public SuccessResponse getRandomPoemList(){
        return indexService.getRandomPoemList();
    }

    @GetMapping("/api/poetry/dynasty")
    public SuccessResponse getAllDynasty(){
        return indexService.getAllDynasty();
    }
    @GetMapping("/api/poetry/poem/list")
    public SuccessResponse getPoemsList(String id, int type){
        return indexService.getPoemsList(id, type);
    }
    @GetMapping("/api/poetry/poets/list")
    public SuccessResponse getPoetsList(String id){
        return indexService.getPoetsList(id);
    }
    @GetMapping("/api/poetry/rhesis/list")
    public SuccessResponse getAllRhesis(){
        return indexService.getAllRhesis();
    }


}
