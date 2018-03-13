package com.ufcg.si1.model.factory;

import org.springframework.beans.factory.annotation.Autowired;

import com.ufcg.si1.model.Categoria;
import com.ufcg.si1.repository.CategoriaRepository;

public class CategoriaFactory {
	

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public void criaCategorias() {
		
		Categoria categoriaAlimenticio = new Categoria("Alimenticio");
		Categoria categoriaHigiene = new Categoria("Higiene");
		Categoria categoriaBebida = new Categoria("Bebida");
		Categoria categoriaLimpeza = new Categoria("Limpeza");
		Categoria categoriaHortifruti = new Categoria("Hortifruti");
		
		categoriaRepository.save(categoriaAlimenticio);
		categoriaRepository.save(categoriaHigiene);
		categoriaRepository.save(categoriaBebida);
		categoriaRepository.save(categoriaLimpeza);
		categoriaRepository.save(categoriaHortifruti);


	}

}
