package br.net.android.mutante.model.response;

public class RemoveMutanteResponse extends Response {

	public RemoveMutanteResponse(boolean sucesso, String mensagem) {
		this.setSucesso(sucesso);
		this.setMensagem(mensagem);
	}
}
