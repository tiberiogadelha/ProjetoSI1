package com.ufcg.si1.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private BigDecimal desconto;

	public Categoria (String nome) {
		this.nome = nome;
		desconto = new BigDecimal(1);
	}
	
	public Categoria () {
		
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

	public long getId() {
		return id;
	}


	public void setDescontoSemDesconto() {
		this.desconto = new BigDecimal(1);
	}
	
	public void setDescontoBomDesconto() {
		this.desconto = new BigDecimal(0.10);
	}

	public void setDescontoOtimoDesconto() {
		this.desconto = new BigDecimal(0.25);
	}

	public void setDescontoSuperDesconto() {
		this.desconto = new BigDecimal(0.50);
	}

}
