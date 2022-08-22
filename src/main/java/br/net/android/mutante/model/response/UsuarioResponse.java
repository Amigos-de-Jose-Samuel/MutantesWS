package br.net.android.mutante.model.response;

import br.net.android.mutante.model.Usuario;

public class UsuarioResponse extends Response{
	private Usuario usuario;
	
	public UsuarioResponse() {
		
	}
	
	public UsuarioResponse(Usuario usuario, boolean sucesso) {
		this.usuario = usuario;
		this.setSucesso(sucesso);
	}
	
	public UsuarioResponse(Usuario usuario, boolean suceso, String mensagem) {
		this.usuario = usuario;
		this.setSucesso(suceso);
		this.setMensagem(mensagem);
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
