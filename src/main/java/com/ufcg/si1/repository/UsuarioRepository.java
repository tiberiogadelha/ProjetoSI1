package com.ufcg.si1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.si1.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Usuario findByNome(String nomeArtista);

}
