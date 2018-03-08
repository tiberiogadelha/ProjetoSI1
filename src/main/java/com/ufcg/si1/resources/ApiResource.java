package com.ufcg.si1.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.ufcg.si1.repository.LoteRepository;
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

import com.ufcg.si1.model.Lote;
import com.ufcg.si1.model.Produto;
import com.ufcg.si1.model.DTO.LoteDTO;

import exceptions.ObjetoInvalidoException;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiResource {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private LoteRepository loteRepository;

	//	Metodos de Produto

	@GetMapping(value="/produto",produces="application/json")
	public ResponseEntity<List<Produto>> listAllUsers() {
		List<Produto> produtos = produtoRepository.findAll();

		if (produtos.isEmpty()) {
			return new ResponseEntity<List<Produto>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	@PostMapping(value="/produto")
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {

		if(getProduto(produto.getId()) == null) {
			try {
				produto.mudaSituacao(Produto.INDISPONIVEL);
				
			} catch (ObjetoInvalidoException e) {
				return new ResponseEntity<Produto>(HttpStatus.NOT_ACCEPTABLE);
			}

			produtoRepository.save(produto);

			return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);			
		}else {

			return new ResponseEntity<Produto>(HttpStatus.CONFLICT);
		}

	}

	@GetMapping(value="/produto/{id}", produces="application/json")
	public ResponseEntity<Produto> consultarProduto(@PathVariable("id") long id) {
		Produto produto = getProduto(id);
		if (produto == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	@PutMapping(value="/produto",produces="application/json")
	public ResponseEntity<Produto> updateProduto(@RequestBody @Valid Produto produto) {

		Produto currentProduto = getProduto(produto.getId());
		if (currentProduto == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}

		currentProduto.mudaNome(produto.getNome());
		currentProduto.setPreco(produto.getPreco());
		currentProduto.setCodigoBarra(produto.getCodigoBarra());
		currentProduto.mudaFabricante(produto.getFabricante());
		currentProduto.setCategoria(produto.getCategoria());
		
		produtoRepository.save(currentProduto);
		return new ResponseEntity<Produto>(currentProduto, HttpStatus.OK);
	}

	@DeleteMapping(value="/produto/{id}",produces="application/json")
	public ResponseEntity<Produto> deleteUser(@PathVariable("nome") long id) {

		Produto user = getProduto(id);
		if (user == null) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		produtoRepository.delete(user);
		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}

	public Produto getProduto(long id){
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


	//	Metodos de Lote


	@PostMapping(value = "/lote/{id}")
	public ResponseEntity<Lote> criarLote(@PathVariable("id") long produtoId, @RequestBody LoteDTO loteDTO) {
		Produto produto = produtoRepository.findById(produtoId);
		if (produto == null) {
			return new ResponseEntity<Lote>(HttpStatus.NOT_FOUND);
		}

		Lote lote = loteRepository.save(new Lote(produto, loteDTO.getNumeroDeItens(), loteDTO.getDataDeValidade()));

		try {
			if (produto.getSituacao() == Produto.INDISPONIVEL) {
				if (loteDTO.getNumeroDeItens() > 0 && produto.getPreco().doubleValue() > 0) {
					Produto produtoDisponivel = produto;
					produtoDisponivel.situacao = Produto.DISPONIVEL;
					produtoRepository.save(produtoDisponivel);
				}
			}
		} catch (ObjetoInvalidoException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Lote>(lote, HttpStatus.CREATED);
	}

	@GetMapping(value="/lote", produces="application/json")
	public ResponseEntity<List<Lote>> listarAllLotes() {
		List<Lote> lotes = loteRepository.findAll();

		if (lotes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Lote>>(lotes, HttpStatus.OK);
	}

	@DeleteMapping(value="/lote/{id}")
	public ResponseEntity<Lote> removerDoLote(@PathVariable("id") long produtoId){
		List<Lote> lotes = buscarLotesDoProduto(produtoId);
		
		if(removerDoLote(lotes,0) == 0) {
			return new ResponseEntity<Lote>(HttpStatus.ACCEPTED);
		}
		
		Produto produto = getProduto(produtoId);
		produto.situacao = Produto.INDISPONIVEL;
		return new ResponseEntity<Lote>(HttpStatus.NOT_FOUND);
	}
	
	private int removerDoLote(List<Lote> lotes,int indice) {
		Lote lote = lotes.get(indice);
		int quantidadeItens = lote.getNumeroDeItens();
		if(quantidadeItens > 0) {
			lote.setNumeroDeItens(quantidadeItens -=1);
			loteRepository.save(lote);
			return 0;
		}else{
			if (lotes.get(indice ++) != null) {
				removerDoLote(lotes,indice++);
			}
			return -1;
		}
	}
	
	private List<Lote> buscarLotesDoProduto(long produtoId) {
		List<Lote> lotes = new ArrayList<Lote>();
		for(Lote l : loteRepository.findAll()) {
			if(l.getProduto().getId() == produtoId) {
				lotes.add(l);
			}
		}
		return lotes;
	}
}
