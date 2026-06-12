package com.comunicacao.catalogo.web;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.comunicacao.catalogo.domain.ItemCatalogo;
import com.comunicacao.catalogo.domain.TipoItemCatalogo;

public class ItemCatalogoRequest {
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@NotNull
	private TipoItemCatalogo tipo;
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal valor;

	public ItemCatalogo toEntity(Long lojaId) {
		ItemCatalogo item = new ItemCatalogo();
		item.setLojaId(lojaId);
		item.setNome(nome);
		item.setDescricao(descricao);
		item.setTipo(tipo);
		item.setValor(valor);
		item.setDataCadastro(java.time.LocalDate.now());
		return item;
	}

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public TipoItemCatalogo getTipo() { return tipo; }
	public void setTipo(TipoItemCatalogo tipo) { this.tipo = tipo; }
	public BigDecimal getValor() { return valor; }
	public void setValor(BigDecimal valor) { this.valor = valor; }
}
