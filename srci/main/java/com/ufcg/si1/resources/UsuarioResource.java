package com.ufcg.si1.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ufcg.si1.model.Usuario;
import com.ufcg.si1.repository.UsuarioRepository;

import java.util.ArrayList;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioResource{

    @Autowired
    private UsuarioRepository us;

    @PostMapping(produces="application/json")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
    	Usuario usuarioPesquisado = getUsuario(usuario.getNome());
    	if(usuarioPesquisado == null) {
    		us.save(usuario);
    		return new ResponseEntity<Usuario>(HttpStatus.ACCEPTED);
    	}
		return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping(produces="application/json")
    public ArrayList<Usuario> listaUsuarios(){
		Iterable<Usuario> listaUsuarios = us.findAll();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		for(Usuario usuario : listaUsuarios){
			usuarios.add(usuario);
		}
		return usuarios;
    	
    }
    
	@GetMapping(value="/{nome}", produces="application/json")
	public @ResponseBody Usuario getUsuario(@PathVariable(value="nome") String nome){		System.out.println(nome);
		Usuario usuario = null;
		ArrayList<Usuario> usuarios = listaUsuarios();
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getNome().equals(nome)) {
				return usuarios.get(i);
			}
		}
		return usuario;
	}
	
	public Usuario getUser(String nome){
		System.out.println(nome);
		Usuario usuario = null;
		ArrayList<Usuario> usuarios = listaUsuarios();
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).getNome().equals(nome)) {
				return usuarios.get(i);
			}
		}
		return usuario;
	}

	@DeleteMapping(value="/{nomeArtista}")
	public @ResponseBody void removeArtista(@PathVariable(value="nomeArtista") String nomeArtista) {
		Usuario usuario = us.findByNome(nomeArtista);
		us.delete(usuario);
		
	}
	
}