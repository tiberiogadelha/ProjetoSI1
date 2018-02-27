package com.ufcg.si1.resources;

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

import com.ufcg.si1.model.Registro;
import com.ufcg.si1.repository.RegistroRepository;

@RestController
@RequestMapping("/registro")
@CrossOrigin
public class RegistroResource {

	@Autowired
	private RegistroRepository registroRepository;
	
	@GetMapping(value="/produto",produces="application/json")
	public ResponseEntity<List<Registro>> listarRegistros() {
		List<Registro> registros = registroRepository.findAll();

		if (registros.isEmpty()) {
			return new ResponseEntity<List<Registro>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Registro>>(registros, HttpStatus.OK);
	}

	@PostMapping(value="/produto")
	public ResponseEntity<Registro> registrar(@RequestBody Registro registro) {
		registroRepository.save(registro);
		return new ResponseEntity<Registro>(HttpStatus.ACCEPTED);
	}
	
}
