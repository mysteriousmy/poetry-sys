package com.poetry.server.miniprogram.Controller;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.miniprogram.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/poetry/search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @GetMapping("/history/{openid}")
    public SuccessResponse getSearchHistory(@PathVariable("openid") String openid){
        return searchService.getSearchHistory(openid);
    }
    @GetMapping("")
    public SuccessResponse getSearchData(String word, int type, String openid){
        return searchService.getSearchData(word, type, openid);
    }
}
