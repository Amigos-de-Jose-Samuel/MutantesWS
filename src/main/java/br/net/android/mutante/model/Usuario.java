package br.net.android.mutante.model;

public class Usuario {
	private int id;
	private String login;
	private String senha;
	
	public Usuario() {	}
	
	public Usuario(int id, String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return this.senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
