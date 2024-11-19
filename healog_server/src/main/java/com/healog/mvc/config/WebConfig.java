package com.healog.mvc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = {
	    "com.healog.mvc.account.model.dao",
	    "com.healog.mvc.diary.model.dao",
	    "com.healog.mvc.feedback.model.dao",
	    "com.healog.mvc.subscription.model.dao",
})
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH");
	}
}
