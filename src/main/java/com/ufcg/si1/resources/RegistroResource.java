package com.ufcg.si1.resources;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
	@Autowired
	private ApiResource ApiResource;

	@GetMapping(produces="application/json")
	public ResponseEntity<List<Registro>> listarRegistros() {
		List<Registro> registros = registroRepository.findAll();

		if (registros.isEmpty()) {
			return new ResponseEntity<List<Registro>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Registro>>(registros, HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Registro> extornarCompra(@PathVariable("id") long id){
		Registro registro = buscaRegistro(id);
		if(registro == null) {
			return new ResponseEntity<Registro>(HttpStatus.NOT_FOUND);
		}
		
		ArrayList<Produto> produtos = (ArrayList<Produto>) registro.getProdutos();
		for (int i = 0; i < produtos.size(); i++) {
			ApiResource.extornarProduto(produtos.get(i));
		}
		
		registroRepository.delete(registro);
		return new ResponseEntity<Registro>(HttpStatus.ACCEPTED);
	}

	private Registro buscaRegistro(long id) {
		Registro registro = null;
		ArrayList<Registro> registros = listarRegistro();
		for (int i = 0; i < registros.size(); i++) {
			if(registros.get(i).getId() == id) {
				registro = registros.get(i);
			}
		}
		return registro;
	}
	
	private ArrayList<Registro> listarRegistro(){
		Iterable<Registro> listaRegistros = registroRepository.findAll();
		ArrayList<Registro> registros = new ArrayList<Registro>();
		for(Registro registro : listaRegistros){
			registros.add(registro);
		}
		return registros;
	}

	@GetMapping(value="ultimo",produces="application/json")
	public ResponseEntity<Registro> ultimoRegistro() {
		List<Registro> registros = registroRepository.findAll();
		if(registros.isEmpty()) {
			return new ResponseEntity<Registro>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Registro>(registros.get(registros.size() -1), HttpStatus.OK);
	}

	@PostMapping(produces="application/json")
	public void registrar(@RequestBody Registro registro) {
		List<Produto> produtos = registro.getProdutos();
		Registro registroNovo = new Registro(registro.getData(),registro.getNomeCliente(),registro.getProdutos());
		BigDecimal precoTotal = new BigDecimal(0);
		for (int i = 0; i < produtos.size(); i++) {
			Produto produto = produtos.get(i);
			ApiResource.removerDoLote(produto.getId());
			BigDecimal precoComDesconto = produto.getCategoria().getDesconto().multiply(produto.getPreco());
			precoTotal = precoTotal.add(precoComDesconto);
		}	
		registroNovo.setPrecoTotal(precoTotal);
		registroRepository.save(registroNovo);

	}
}
