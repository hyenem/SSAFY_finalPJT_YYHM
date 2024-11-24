package com.healog.mvc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.healog.mvc.interceptor.JwtInterceptor;

@Configuration
@MapperScan(basePackages = {
	    "com.healog.mvc.account.model.dao",
	    "com.healog.mvc.diary.model.dao",
	    "com.healog.mvc.feedback.model.dao",
	    "com.healog.mvc.subscription.model.dao",
})
public class WebConfig implements WebMvcConfigurer {
	
	private JwtInterceptor jwtInterceptor;
	public WebConfig(JwtInterceptor jwtInterceptor) {
		this.jwtInterceptor = jwtInterceptor;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH");
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /uploads/profile/** 요청을 실제 파일 경로로 매핑
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:src/main/resources/static/img/");
    }
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
//		.excludePathPatterns("/trainer/**", "/user/**",  "/swagger-ui/**", "/v3/api-docs/**", "/check", "/trainer**");
//	}

}
