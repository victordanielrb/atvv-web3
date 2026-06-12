package com.comunicacao.catalogo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.comunicacao.catalogo.domain.ItemCatalogo;
import com.comunicacao.catalogo.domain.TipoItemCatalogo;
import com.comunicacao.catalogo.domain.VeiculoCatalogo;
import com.comunicacao.catalogo.repository.ItemCatalogoRepository;
import com.comunicacao.catalogo.repository.VeiculoCatalogoRepository;

@Service
public class CatalogoSeedService {

	private final ItemCatalogoRepository itemRepository;
	private final VeiculoCatalogoRepository veiculoRepository;

	public CatalogoSeedService(ItemCatalogoRepository itemRepository, VeiculoCatalogoRepository veiculoRepository) {
		this.itemRepository = itemRepository;
		this.veiculoRepository = veiculoRepository;
	}

	@PostConstruct
	void seed() {
		if (itemRepository.count() == 0) {
			ItemCatalogo servico = new ItemCatalogo();
			servico.setLojaId(1L);
			servico.setNome("Troca de oleo");
			servico.setDescricao("Servico completo de troca de oleo");
			servico.setTipo(TipoItemCatalogo.SERVICO);
			servico.setValor(new BigDecimal("199.90"));
			servico.setDataCadastro(LocalDate.now().minusDays(15));

			ItemCatalogo mercadoria = new ItemCatalogo();
			mercadoria.setLojaId(1L);
			mercadoria.setNome("Filtro de ar");
			mercadoria.setDescricao("Filtro de ar para revisao");
			mercadoria.setTipo(TipoItemCatalogo.MERCADORIA);
			mercadoria.setValor(new BigDecimal("89.90"));
			mercadoria.setDataCadastro(LocalDate.now().minusDays(10));

			itemRepository.saveAll(List.of(servico, mercadoria));
		}

		if (veiculoRepository.count() == 0) {
			VeiculoCatalogo veiculo = new VeiculoCatalogo();
			veiculo.setLojaId(1L);
			veiculo.setPlaca("ABC1D23");
			veiculo.setModelo("Corolla");
			veiculo.setMarca("Toyota");
			veiculo.setClienteNome("Maria");
			veiculo.setDataAtendimento(LocalDate.now().minusDays(3));
			veiculoRepository.save(veiculo);
		}
	}
}
