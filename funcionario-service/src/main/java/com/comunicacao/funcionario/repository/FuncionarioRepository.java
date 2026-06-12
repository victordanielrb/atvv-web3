package com.comunicacao.funcionario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.funcionario.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	List<Funcionario> findByLojaIdOrderByIdAsc(Long lojaId);
}
