package com.demo.spring.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.demo.spring.detail.UserDetail;
import com.demo.spring.exception.JwtTokenMalformedException;
import com.demo.spring.exception.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUltil {

	private static final String JWT_SECRECT_KEY = "LEN";
	private static final long TOKEN_VALIDITY = 30 * 60 * 1000;

	public UserDetail getUserFromToken(final String token) throws Exception {
		Claims body = Jwts.parser().setSigningKey(JWT_SECRECT_KEY).parseClaimsJws(token).getBody();

		UserDetail userDetail = new UserDetail();
		userDetail.setUsername(body.getSubject());

		Set<String> roles = Arrays.asList(body.get("roles").toString().split(",")).stream().map(r -> new String(r))
				.collect(Collectors.toSet());

		userDetail.setRoles(roles);
		return userDetail;
	}

	public String generateToken(UserDetail u) {
		Claims claims = Jwts.claims().setSubject(u.getUsername());
		claims.put("roles", u.getRoles());
		long nowMillis = System.currentTimeMillis();
		long expiredMilis = nowMillis + TOKEN_VALIDITY;
		Date expired = new Date(expiredMilis);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(expired)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRECT_KEY).compact();
	}

	public void validateToken(final String token) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRECT_KEY).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}
}
