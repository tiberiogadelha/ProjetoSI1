package com.ufcg.si1.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.Categoria;
import com.ufcg.si1.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Categoria>> listCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();
		if (categorias.isEmpty()) {
			criarCategorias();
			categorias = categoriaRepository.findAll();
			return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
		}
		return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
	}

	private void criarCategorias() {
		
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
