package com.healog.mvc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.healog.mvc.model.dao")
public class DBconfig {

}
