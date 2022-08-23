package br.net.android.mutante.model;

public class Habilidade {
	private int id;
	private String descricao;
	
	public Habilidade() {
		
	}
	
	public Habilidade(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
