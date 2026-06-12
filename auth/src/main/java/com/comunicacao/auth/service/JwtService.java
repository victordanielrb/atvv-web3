package com.comunicacao.auth.service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.comunicacao.auth.domain.AuthUser;
import com.comunicacao.auth.dto.TokenValidationResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String rawSecret;
	private final long expirationSeconds;
	private byte[] signingKey;

	public JwtService(@Value("${jwt.secret}") String rawSecret,
			@Value("${jwt.expiration-seconds}") long expirationSeconds) {
		this.rawSecret = rawSecret;
		this.expirationSeconds = expirationSeconds;
	}

	@PostConstruct
	void init() {
		byte[] decoded;
		try {
			decoded = Decoders.BASE64.decode(rawSecret);
		} catch (DecodingException exception) {
			decoded = rawSecret.getBytes(StandardCharsets.UTF_8);
		}
		this.signingKey = decoded;
	}

	public String gerarToken(AuthUser user, List<String> roles) {
		Instant now = Instant.now();
		return Jwts.builder()
				.setSubject(user.getEmail())
				.claim("user_id", user.getUserId())
				.claim("loja_id", user.getLojaId())
				.claim("roles", roles)
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plusSeconds(expirationSeconds)))
				.signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS256)
				.compact();
	}

	@SuppressWarnings("unchecked")
	public TokenValidationResponse validarToken(String authorizationHeader) {
		String token = authorizationHeader.replace("Bearer ", "").trim();
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(signingKey))
				.build()
				.parseClaimsJws(token)
				.getBody();

		Long userId = toLong(claims.get("user_id"));
		Long lojaId = toLong(claims.get("loja_id"));
		List<String> roles = (List<String>) claims.get("roles");
		return new TokenValidationResponse(true, claims.getSubject(), userId, lojaId, roles);
	}

	private Long toLong(Object value) {
		if (value instanceof Integer) {
			return ((Integer) value).longValue();
		}
		if (value instanceof Long) {
			return (Long) value;
		}
		return Long.valueOf(String.valueOf(value));
	}
}
