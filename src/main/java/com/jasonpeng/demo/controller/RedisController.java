package com.jasonpeng.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.jasonpeng.demo.service.RedisService;
import com.jasonpeng.demo.util.common.CodeEnum;
import com.jasonpeng.demo.util.common.ResultBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/redis/save")
    public JSONObject save(@RequestParam String key, @RequestParam String value) {
        redisService.save(key, value);
        return ResultBuilder.buildSimple(CodeEnum.SUCCESS.code, CodeEnum.SUCCESS.message);
    }

    @GetMapping("/redis/get")
    public JSONObject get(@RequestParam String key) {
        return ResultBuilder.build(CodeEnum.SUCCESS.code, CodeEnum.SUCCESS.message, redisService.get(key));
    }

    @GetMapping("/redis/delete")
    public JSONObject delete(@RequestParam String key) {
        redisService.delete(key);
        return ResultBuilder.buildSimple(CodeEnum.SUCCESS.code, CodeEnum.SUCCESS.message);
    }
}
