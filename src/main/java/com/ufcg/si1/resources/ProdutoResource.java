package com.ufcg.si1.resources;

import java.util.ArrayList;
import java.util.List;
import com.ufcg.si1.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ufcg.si1.model.Produto;
import com.ufcg.si1.util.CustomErrorType;

import exceptions.ObjetoInvalidoException;

@RestController
@RequestMapping("/produto")
@CrossOrigin
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping(produces="application/json")
	public ResponseEntity<List<Produto>> listAllUsers() {
		List<Produto> produtos = produtoRepository.findAll();

		if (produtos.isEmpty()) {
			return new ResponseEntity<List<Produto>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {

		boolean produtoExiste = false;

		for (Produto p : produtoRepository.findAll()) {
			if (p.getCodigoBarra().equals(produto.getCodigoBarra())) {
				produtoExiste = true;
			}
		}

		if (produtoExiste) {
			return new ResponseEntity<>(new CustomErrorType("O produto " + produto.getNome() + " do fabricante "
					+ produto.getFabricante() + " ja esta cadastrado!"), HttpStatus.CONFLICT);
		}

		try {
			produto.mudaSituacao(Produto.INDISPONIVEL);
		} catch (ObjetoInvalidoException e) {
			return new ResponseEntity<>(new CustomErrorType("Error: Produto" + produto.getNome() + " do fabricante "
					+ produto.getFabricante() + " alguma coisa errada aconteceu!"), HttpStatus.NOT_ACCEPTABLE);
		}

		produtoRepository.save(produto);

		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}

	@GetMapping(value="/{id}", produces="application/json")
	public ResponseEntity<?> consultarProduto(@PathVariable("id") long id) {

		Produto produto = getProduto(id);
		if (produto == null) {
			return new ResponseEntity<>(new CustomErrorType("Produto inexistente. Id do produto: " + id + " nao existe"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@PutMapping(value="/{id}", produces="application/json")
	public ResponseEntity<?> updateProduto(@PathVariable("id") long id, @RequestBody Produto produto) {

		Produto currentProduto = getProduto(id);
		if (currentProduto == null) {
			return new ResponseEntity<>(new CustomErrorType("Produto inexistente. Id do produto: " + id + " nao existe."),
					HttpStatus.NOT_FOUND);
		}

		currentProduto.mudaNome(produto.getNome());
		currentProduto.setPreco(produto.getPreco());
		currentProduto.setCodigoBarra(produto.getCodigoBarra());
		currentProduto.mudaFabricante(produto.getFabricante());
		currentProduto.mudaCategoria(produto.getCategoria());

		produtoRepository.save(currentProduto);
		return new ResponseEntity<Produto>(currentProduto, HttpStatus.OK);
	}

	@DeleteMapping(value="/{id}",produces="application/json")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

		Produto user = getProduto(id);
		if (user == null) {
			return new ResponseEntity<>(new CustomErrorType("Produto inexistente. Id do produto: " + id + " nao existe."),
					HttpStatus.NOT_FOUND);
		}
		produtoRepository.delete("id");
		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value="/{id}", produces="application/json")
	public @ResponseBody Produto getProduto(@PathVariable(value="id") long id){
		Produto produto = null;
		ArrayList<Produto> produtos = listaProdutos();
		for(int i = 0; i < produtos.size(); i++) {
			if(produtos.get(i).getId() == id) {
				return produtos.get(i);
			}
		}
		return produto;
	}

	private ArrayList<Produto> listaProdutos() {
		Iterable<Produto> listaProdutos = produtoRepository.findAll();
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		for(Produto produto : listaProdutos){
			produtos.add(produto);
		}
		return produtos;
	}


}
