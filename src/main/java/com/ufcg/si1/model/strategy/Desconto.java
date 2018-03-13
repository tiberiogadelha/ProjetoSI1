package com.ufcg.si1.model.strategy;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Desconto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public long id;
	
	public long getId() {
		return id;
	}

	public Desconto () {
		
	}
	
	public abstract BigDecimal calcularPrecoComDesconto(BigDecimal precoOriginal);
	
}
