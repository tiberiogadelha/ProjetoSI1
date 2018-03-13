package com.ufcg.si1.model.strategy;

import java.io.Serializable;
import java.math.BigDecimal;

public class OtimoDesconto extends Desconto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Override
	public BigDecimal calcularPrecoComDesconto(BigDecimal precoOriginal) {
		BigDecimal desconto = new BigDecimal(0.25);
		return precoOriginal.multiply(desconto);
	}

}
