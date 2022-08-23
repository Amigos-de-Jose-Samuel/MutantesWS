package br.net.android.mutante.model;

public class Mutante {
	private int id;
	private String nome;
	private byte[] foto;
	private int idUsuario;
	private String loginUsuarioCadastro;
	
	public Mutante() {
		
	}
	
	public Mutante(int id, String nome, byte[] foto, int idUsuario) {
		this.id = id;
		this.nome = nome;
		this.foto = foto;
		this.idUsuario = idUsuario;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public byte[] getFoto() {
		return this.foto;
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public int getIdUsuario() {
		return this.idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getLoginUsuarioCadastro() {
		return this.loginUsuarioCadastro;
	}
	
	public void setLoginUsuarioCadastro(String loginUsuarioCadastro) {
		this.loginUsuarioCadastro = loginUsuarioCadastro;
	}
}
