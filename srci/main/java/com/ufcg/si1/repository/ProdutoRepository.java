package com.ufcg.si1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.si1.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{

	Produto findById(long id);
	
    List<Produto> findByNomeStartingWithOrderByNome(String nome);
	
}
