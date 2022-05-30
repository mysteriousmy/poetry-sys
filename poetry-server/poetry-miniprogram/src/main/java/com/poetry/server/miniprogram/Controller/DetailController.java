package com.poetry.server.miniprogram.Controller;

import com.poetry.common.response.SuccessResponse;
import com.poetry.server.miniprogram.Service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/poetry")
public class DetailController {
    @Autowired
    private DetailService detailService;
    @GetMapping("/poemList/detail/{id}")
    public SuccessResponse getDetail(@PathVariable("id") String id){
        return detailService.getDetail(id);
    }
    @GetMapping("/poets/detail/{id}")
    public SuccessResponse getPoetsDetail(@PathVariable("id")String id, String openid){
        return detailService.getPoetsDetail(id,openid);
    }
    @GetMapping("/rhesis/detail/{id}")
    public SuccessResponse getRhesisDetail(@PathVariable("id") String id, String openid){
        return detailService.getRhesisDetail(id,openid);
    }
    @GetMapping("/poem/detail/{id}")
    public SuccessResponse getPoemDetail(@PathVariable("id") String id, String openid){
        return detailService.getPoemDetail(id,openid);
    }
}
