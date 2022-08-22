package br.net.android.mutante.model.response;

public abstract class Response {
	private boolean sucesso;
	private String mensagem;

	public boolean getSucesso() {
		return this.sucesso;
	}
	
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	
	public String getMensagem() {
		return this.mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
