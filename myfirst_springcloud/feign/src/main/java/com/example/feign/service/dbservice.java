package com.example.feign.service;

import com.example.feign.service.hystrix.dbserverHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "dbservice",fallback = dbserverHystrix.class)       //指定目标节点服务器在eureka中注册的名称，并指定熔断类
public interface dbservice {
    @GetMapping("/gettest")             //指定节点服务器接口地址
    public String gettest();
}
