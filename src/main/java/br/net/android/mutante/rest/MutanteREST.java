package br.net.android.mutante.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import br.net.android.mutante.dao.UsuarioDAO;
import br.net.android.mutante.controller.UsuarioController;
import br.net.android.mutante.dao.ConnectionFactory;
import br.net.android.mutante.model.Usuario;
import br.net.android.mutante.model.response.UsuarioResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MutanteREST {

	@PostMapping("/login")
	public UsuarioResponse login(@RequestBody Usuario usuario) {
		try {
			return UsuarioController.realizarLogin(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/novo-usuario")
	public Usuario novoUsuario(Usuario usuario) {
		try {
			return UsuarioController.novoUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
