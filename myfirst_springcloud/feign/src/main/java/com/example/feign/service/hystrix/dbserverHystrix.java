package com.example.feign.service.hystrix;

import com.example.feign.service.dbservice;
import org.springframework.stereotype.Component;

/*
这是熔断类
 */
@Component
public class dbserverHystrix implements dbservice {
    @Override
    public String gettest() {
        return "dbservice服务暂时不可用";
    }
}
