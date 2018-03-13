package com.ufcg.si1.model.strategy;

import java.io.Serializable;
import java.math.BigDecimal;

public class SemDesconto extends Desconto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Override
	public BigDecimal calcularPrecoComDesconto(BigDecimal precoOriginal) {
		BigDecimal desconto = new BigDecimal(1);
		return precoOriginal.multiply(desconto);
	}

}
