package com.ufcg.si1.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import com.ufcg.si1.model.Produto;

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
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {

		boolean produtoExiste = false;

		for (Produto p : produtoRepository.findAll()) {
			if (p.getCodigoBarra().equals(produto.getCodigoBarra())) {
				produtoExiste = true;
			}
		}

		if (produtoExiste) {
			return new ResponseEntity<Produto>(HttpStatus.CONFLICT);
		}

		try {
			produto.mudaSituacao(Produto.INDISPONIVEL);
		} catch (ObjetoInvalidoException e) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_ACCEPTABLE);
		}

		produtoRepository.save(produto);

		return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
	}

	@GetMapping(value="/{nome}", produces="application/json")
	public ResponseEntity<Produto> consultarProduto(@PathVariable("nome") String nome) {
		Produto produto = getProduto(nome);
		if (produto == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@PutMapping(produces="application/json")
	public ResponseEntity<Produto> updateProduto(@RequestBody @Valid Produto produto) {

		Produto currentProduto = getProduto(produto.getNome());
		System.out.println(produto.getNome());
		if (currentProduto == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}

		currentProduto.mudaNome(produto.getNome());
		currentProduto.setPreco(produto.getPreco());
		currentProduto.setCodigoBarra(produto.getCodigoBarra());
		currentProduto.mudaFabricante(produto.getFabricante());
		currentProduto.mudaCategoria(produto.getCategoria());

		produtoRepository.save(currentProduto);
		return new ResponseEntity<Produto>(currentProduto, HttpStatus.OK);
	}

	@DeleteMapping(value="/{nome}",produces="application/json")
	public ResponseEntity<Produto> deleteUser(@PathVariable("nome") String nome) {

		Produto user = getProduto(nome);
		if (user == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		produtoRepository.delete(user);
		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}

	private Produto getProduto(String nome){
		Produto produto = null;
		ArrayList<Produto> produtos = listaProdutos();
		for(int i = 0; i < produtos.size(); i++) {
			if(produtos.get(i).getNome().equals(nome)) {
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
