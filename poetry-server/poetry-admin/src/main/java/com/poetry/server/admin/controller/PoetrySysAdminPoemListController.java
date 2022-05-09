package com.poetry.server.admin.controller;

import com.poetry.common.annotation.JwtToken;
import com.poetry.common.response.SuccessResponse;
import com.poetry.db.entity.PoetrySysPoems;
import com.poetry.server.admin.dto.PoetrySysPoemListsDto;
import com.poetry.server.admin.service.PoetrySysPoemListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/poetry/admins/poemList")
@Slf4j
public class PoetrySysAdminPoemListController {
    @Autowired
    private PoetrySysPoemListService poemListService;
    @JwtToken
    @GetMapping("")
    public SuccessResponse getAllPoemList(){
        return poemListService.getAllPoemList();
    }

    @JwtToken
    @GetMapping("/{id}")
    public SuccessResponse getPoemListById(@PathVariable("id") String id){
        return poemListService.getPoemListById(id);
    }

    @JwtToken
    @PostMapping("")
    public SuccessResponse savePoemList(@RequestBody @Validated PoetrySysPoemListsDto PoemListDto){
        return poemListService.savePoemList(PoemListDto);
    }

    @JwtToken
    @PutMapping("")
    public SuccessResponse updatePoemList(@RequestBody @Validated PoetrySysPoemListsDto PoemListDto){
        return poemListService.updatePoemList(PoemListDto);
    }

    @JwtToken
    @DeleteMapping("/{id}")
    public SuccessResponse deletePoemList(@PathVariable("id") String id){
        return poemListService.deletePoemListById(id);
    }
    @JwtToken
    @PostMapping("/{id}")
    public SuccessResponse savePoemListPoems(@PathVariable("id")String id, Set<PoetrySysPoems> poems){
        return poemListService.savePoemListPoems(id, poems);
    }
    @JwtToken
    @PostMapping("/query")
    public SuccessResponse queryPoemListsByWords(Map<String, Object> queryMap){
        return poemListService.queryPoemListByWords(queryMap);
    }
}
