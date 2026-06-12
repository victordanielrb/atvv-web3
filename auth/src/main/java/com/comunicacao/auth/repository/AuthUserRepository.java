package com.comunicacao.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.auth.domain.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

	Optional<AuthUser> findByEmail(String email);
}
