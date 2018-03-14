package com.ufcg.si1.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufcg.si1.model.strategy.BomDesconto;
import com.ufcg.si1.model.strategy.Desconto;
import com.ufcg.si1.model.strategy.OtimoDesconto;
import com.ufcg.si1.model.strategy.SemDesconto;
import com.ufcg.si1.model.strategy.SuperDesconto;

@Entity
public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private Desconto desconto;

	public Categoria (String nome) {
		this.nome = nome;
	}
	
	public Categoria () {
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Desconto getDesconto() {
		return desconto;
	}

	public long getId() {
		return id;
	}


	public void setDescontoSemDesconto() {
		this.desconto = new SemDesconto();
	}
	
	public void setDescontoBomDesconto() {
		this.desconto = new BomDesconto();
	}

	public void setDescontoOtimoDesconto() {
		this.desconto = new OtimoDesconto();
	}

	public void setDescontoSuperDesconto() {
		this.desconto = new SuperDesconto();
	}


}
