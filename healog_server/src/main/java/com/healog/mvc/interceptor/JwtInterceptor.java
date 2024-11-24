 package com.healog.mvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.healog.mvc.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	private final String HEADER_AUTH = "access-token";
	private JwtUtil jwtUtil;
	
	public JwtInterceptor(JwtUtil jwtUtil){
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getMethod().equals("OPTIONS")) return true;
		
		String token = request.getHeader(HEADER_AUTH);
		if(token!=null) {
			try {
				jwtUtil.vaildate(token);
				return true;				
			} catch(Exception e){

			}
		}
		throw new Exception("유효하지 않은 접근입니다.");
	}
}
