package com.ufcg.si1.model;

import java.math.BigDecimal;

public class Categoria {
	
	private String nome;
	private BigDecimal desconto;

	public Categoria (String nome, BigDecimal desconto) {
		this.nome = nome;
		this.desconto = desconto;
	}
	
	public Categoria (String nome) {
		this.nome = nome;
		this.desconto = new BigDecimal(0);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

}
