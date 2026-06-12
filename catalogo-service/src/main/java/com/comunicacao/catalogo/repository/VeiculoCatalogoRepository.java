package com.comunicacao.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.catalogo.domain.VeiculoCatalogo;

public interface VeiculoCatalogoRepository extends JpaRepository<VeiculoCatalogo, Long> {
	List<VeiculoCatalogo> findByLojaId(Long lojaId);
}
