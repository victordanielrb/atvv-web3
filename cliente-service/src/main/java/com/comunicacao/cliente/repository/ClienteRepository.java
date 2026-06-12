package com.comunicacao.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.cliente.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByLojaId(Long lojaId);
}
