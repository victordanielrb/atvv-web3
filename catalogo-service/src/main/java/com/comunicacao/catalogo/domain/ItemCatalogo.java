package com.comunicacao.catalogo.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catalogo_itens")
public class ItemCatalogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loja_id", nullable = false)
	private Long lojaId;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, length = 1000)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoItemCatalogo tipo;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;

	@Column(name = "data_cadastro", nullable = false)
	private LocalDate dataCadastro;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public Long getLojaId() { return lojaId; }
	public void setLojaId(Long lojaId) { this.lojaId = lojaId; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
	public TipoItemCatalogo getTipo() { return tipo; }
	public void setTipo(TipoItemCatalogo tipo) { this.tipo = tipo; }
	public BigDecimal getValor() { return valor; }
	public void setValor(BigDecimal valor) { this.valor = valor; }
	public LocalDate getDataCadastro() { return dataCadastro; }
	public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
}
