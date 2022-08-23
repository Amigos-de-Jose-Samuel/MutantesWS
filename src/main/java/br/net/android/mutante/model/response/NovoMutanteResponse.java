package br.net.android.mutante.model.response;

public class NovoMutanteResponse extends Response {
	
	public NovoMutanteResponse() {
		
	}
	
	public NovoMutanteResponse(boolean sucesso, String mensagem) {
		this.setSucesso(sucesso);
		this.setMensagem(mensagem);
	}
}
