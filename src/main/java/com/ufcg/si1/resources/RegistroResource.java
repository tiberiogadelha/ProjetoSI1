package com.ufcg.si1.resources;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.si1.model.Produto;
import com.ufcg.si1.model.Registro;
import com.ufcg.si1.repository.RegistroRepository;

@RestController
@RequestMapping("/registro")
@CrossOrigin
public class RegistroResource {

	@Autowired
	private RegistroRepository registroRepository;
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Registro>> listarRegistros() {
		List<Registro> registros = registroRepository.findAll();

		if (registros.isEmpty()) {
			return new ResponseEntity<List<Registro>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Registro>>(registros, HttpStatus.OK);
	}

	@PostMapping(produces="application/json")
	public ResponseEntity<Registro> registrar(@RequestBody Registro registro) {
		Registro registroNovo = new Registro(registro.getData(),registro.getProdutos());
		BigDecimal precoTotal = new BigDecimal(0);
		for (Produto produto : registro.getProdutos()) {
			precoTotal = precoTotal.add(produto.getPreco());
		}
		registroNovo.setPrecoTotal(precoTotal);
		registroRepository.save(registroNovo);
		return new ResponseEntity<Registro>(HttpStatus.ACCEPTED);
	}
	
}
