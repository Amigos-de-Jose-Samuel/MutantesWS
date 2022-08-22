package br.net.android.mutante.controller;

import br.net.android.mutante.dao.UsuarioDAO;
import br.net.android.mutante.model.Usuario;
import br.net.android.mutante.model.response.UsuarioResponse;
import br.net.android.mutante.dao.ConnectionFactory;

public class UsuarioController {
	
	private static UsuarioDAO dao = null;

	private static UsuarioDAO getDAO() throws Exception {
		if(dao == null)
			dao = new UsuarioDAO(ConnectionFactory.getConnection());
		
		return dao;
	}

	public static UsuarioResponse realizarLogin(Usuario usuario) throws Exception {
		int id = getDAO().obterId(usuario);
	
		if(id == 0)
			return new UsuarioResponse(null, false, "Login ou Senha incorretos.");
		else if (id == -1)
			return new UsuarioResponse(null, false, "Houve um erro na consulta de usuários.");
		
		usuario.setId(id);
		return new UsuarioResponse(usuario, true);
	}
	
	public static UsuarioResponse novoUsuario(Usuario usuario) throws Exception {
		
		if(getDAO().obterId(usuario) > 0)
			return new UsuarioResponse(null, false, "Usuario já existente.")
		
		return getDAO().novoUsuario(Usuario usuario);
	}
}
