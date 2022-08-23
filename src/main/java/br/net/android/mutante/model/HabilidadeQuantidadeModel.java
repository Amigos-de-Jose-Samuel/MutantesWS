package br.net.android.mutante.model;

public class HabilidadeQuantidadeModel {
	private String habilidade;
	private int quantidade;
	
	public HabilidadeQuantidadeModel(String habilidade, int quantidade) {
		this.habilidade = habilidade;
		this.quantidade = quantidade;
	}
	
	public String getHabilidade() {
		return this.habilidade;
	}
	
	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
