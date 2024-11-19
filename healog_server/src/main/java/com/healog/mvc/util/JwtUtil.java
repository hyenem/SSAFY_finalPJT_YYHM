package com.healog.mvc.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.healog.mvc.account.model.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "SSAFY_Final_PJT_127_HMYY_Healog_Secretkey";
	private SecretKey  secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

	public String createToken(User loginUser) {
		Date exp = new Date(System.currentTimeMillis()+1000*10); //1시간 유효
		return Jwts.builder().header().add("typ", "JWT").and()
				.claim("loginUser", loginUser)
				.expiration(exp)
				.signWith(secretKey).compact();
	}
	
	public Jws<Claims> vaildate(String token){
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
	}
}
