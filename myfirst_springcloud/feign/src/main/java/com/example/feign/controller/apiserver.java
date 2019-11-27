package com.example.feign.controller;

import com.example.feign.service.dbservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiserver {
    @Autowired
    dbservice dbservice;

    @GetMapping("/testfeign")
    public String testfeign(){
        return "testfeign:"+dbservice.gettest();
    }
}
