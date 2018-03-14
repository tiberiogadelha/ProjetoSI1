package com.ufcg.si1.resources;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.Categoria;
import com.ufcg.si1.repository.CategoriaRepository;

@RestController
@RequestMapping("/desconto")
@CrossOrigin
public class DescontoResource {	

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping(value="/{id}",produces="application/json")
	public void alterarDescontos(@PathVariable("id") long id, @RequestBody Categoria categoria) {
		Categoria categoriaEditada = getCategoria(id);
		BigDecimal tipoDesconto = categoria.getDesconto();
		if(tipoDesconto.compareTo(new BigDecimal(1)) == 0) {
			categoriaEditada.setDescontoSemDesconto();
		} else if (tipoDesconto.compareTo(new BigDecimal(0.1)) == 0) {
			categoriaEditada.setDescontoBomDesconto();
		} else if (tipoDesconto.compareTo(new BigDecimal(0.25)) == 0) {
			categoriaEditada.setDescontoOtimoDesconto();
		} else {
			categoriaEditada.setDescontoSuperDesconto();
		}
		categoriaRepository.save(categoriaEditada);
	}
	
	public Categoria getCategoria(long id){
		Categoria categoria = null;
		ArrayList<Categoria> categorias = listarCategorias();
		for(int i = 0; i < categorias.size(); i++) {
			if(categorias.get(i).getId() == id) {
				return categorias.get(i);
			}
		}
		return categoria;
	}

	private ArrayList<Categoria> listarCategorias() {
		Iterable<Categoria> listaCategorias = categoriaRepository.findAll();
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		for(Categoria categoria : listaCategorias){
			categorias.add(categoria);
		}
		return categorias;
	}
	
}
