package com.comunicacao.venda.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comunicacao.venda.domain.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	List<Venda> findByLojaIdAndDataVendaBetweenOrderByDataVendaDesc(Long lojaId, LocalDate inicio, LocalDate fim);
}
