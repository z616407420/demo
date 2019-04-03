package com.example.demo.controller;

import com.example.demo.util.RedisUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = "基础数据接口")
@RestController
@RequestMapping(value = "/basic")
public class BasicDataController{

    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping(value = "/set", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> set(String key,String msg,Long time) {
        boolean set = redisUtil.set(key, msg,time);
        return ResponseEntity.ok(key);
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> get(String key) {
        Object o = redisUtil.get(key);
        return ResponseEntity.ok(o);
    }

    @RequestMapping(value = "/getExpire", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<?> getExpire(String key) {
        Long o = redisUtil.getExpire(key);
        return ResponseEntity.ok(o);
    }




}
