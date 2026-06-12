package com.comunicacao.venda.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.comunicacao.venda.domain.TipoVendaItem;
import com.comunicacao.venda.domain.Venda;
import com.comunicacao.venda.repository.VendaRepository;

@Service
public class VendaSeedService {

	private final VendaRepository repository;

	public VendaSeedService(VendaRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	void seed() {
		if (repository.count() > 0) {
			return;
		}

		Venda servico = new Venda();
		servico.setLojaId(1L);
		servico.setFuncId(101L);
		servico.setCliId(201L);
		servico.setItemId(301L);
		servico.setItemNome("Troca de oleo");
		servico.setTipoItem(TipoVendaItem.SERVICO);
		servico.setValor(new BigDecimal("199.90"));
		servico.setQuantidade(1);
		servico.setDataVenda(LocalDate.now().minusDays(4));

		Venda peca = new Venda();
		peca.setLojaId(1L);
		peca.setFuncId(102L);
		peca.setCliId(202L);
		peca.setItemId(302L);
		peca.setItemNome("Filtro de ar");
		peca.setTipoItem(TipoVendaItem.PECA);
		peca.setValor(new BigDecimal("89.90"));
		peca.setQuantidade(2);
		peca.setDataVenda(LocalDate.now().minusDays(2));

		Venda outraPeca = new Venda();
		outraPeca.setLojaId(1L);
		outraPeca.setFuncId(102L);
		outraPeca.setCliId(203L);
		outraPeca.setItemId(303L);
		outraPeca.setItemNome("Pastilha de freio");
		outraPeca.setTipoItem(TipoVendaItem.PECA);
		outraPeca.setValor(new BigDecimal("249.90"));
		outraPeca.setQuantidade(1);
		outraPeca.setDataVenda(LocalDate.now().minusDays(1));

		repository.saveAll(List.of(servico, peca, outraPeca));
	}
}
