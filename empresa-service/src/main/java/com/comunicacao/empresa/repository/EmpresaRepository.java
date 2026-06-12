package com.comunicacao.empresa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.empresa.domain.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	Optional<Empresa> findByLojaId(Long lojaId);
}
