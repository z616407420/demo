package com.example.demo.controller;

import com.example.demo.RedisDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisDemoApplication.class)
public class BasicDataControllerTest {

    @Autowired
    private BasicDataController basicDataController;


    @Test
    public void set() {

        basicDataController.set("name","哈哈",0L);

    }

    @Test
    public void get() {
        ResponseEntity<?> name = basicDataController.get("name");
    }

    @Test
    public void getExpire() {
        basicDataController.getExpire("name");
    }
}