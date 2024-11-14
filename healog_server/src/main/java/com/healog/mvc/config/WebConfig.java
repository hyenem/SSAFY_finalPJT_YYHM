package com.healog.mvc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {
	    "com.healog.mvc.account.model.dao",
	    "com.healog.mvc.diary.model.dao",
	    "com.healog.mvc.feedback.model.dao",
	    "com.healog.mvc.subscription.model.dao"
public class WebConfig {

}
