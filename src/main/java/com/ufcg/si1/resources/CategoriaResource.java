package com.ufcg.si1.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.factory.CategoriaFactory;
import com.ufcg.si1.model.Categoria;
import com.ufcg.si1.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

	private CategoriaFactory cf;

	@GetMapping(produces="application/json")
	public ResponseEntity<List<Categoria>> listCategorias() {
		List<Categoria> categorias = categoriaRepository.findAll();

		if (categorias.isEmpty()) {
			cf.criaCategorias();
			return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
		}
		return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
	}
	
	@PostMapping(produces="application/json")
	public void adicionarCategorias(Categoria categoria) {
		categoriaRepository.save(categoria);
	}
}
