package br.net.android.mutante.model.response;


public class LoginResponse extends Response{
	
	public LoginResponse() {
		
	}
	
	public LoginResponse(boolean suceso, String mensagem) {
		this.setSucesso(suceso);
		this.setMensagem(mensagem);
	}
}
