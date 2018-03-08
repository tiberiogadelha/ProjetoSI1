package com.ufcg.si1.model.strategy;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public interface Desconto{

	BigDecimal calcularPrecoComDesconto(BigDecimal precoOriginal);
	
}
