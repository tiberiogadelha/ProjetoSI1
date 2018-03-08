package com.ufcg.si1.model.strategy;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SemDesconto implements Desconto,Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	public long getId() {
		return id;
	}

	@Override
	public BigDecimal calcularPrecoComDesconto(BigDecimal precoOriginal) {
		BigDecimal desconto = new BigDecimal(1);
		return precoOriginal.multiply(desconto);
	}

}
