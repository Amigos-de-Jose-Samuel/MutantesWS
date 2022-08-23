package br.net.android.mutante.model.response;

public class EditaMutanteResponse extends Response{

	public EditaMutanteResponse(boolean sucesso, String mensagem) {
		this.setSucesso(sucesso);
		this.setMensagem(mensagem);
	}
}
