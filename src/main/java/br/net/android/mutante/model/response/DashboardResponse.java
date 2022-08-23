package br.net.android.mutante.model.response;

import java.util.ArrayList;

import br.net.android.mutante.model.HabilidadeQuantidadeModel;

public class DashboardResponse extends Response {

	private int quantidadeMutantes;
	private ArrayList<HabilidadeQuantidadeModel> habilidades;
	
	public DashboardResponse() {
		
	}
	
	public DashboardResponse(boolean suceso, String mensagem) {
		this.setSucesso(suceso);
		this.setMensagem(mensagem);
	}
	
	public DashboardResponse(boolean suceso, String mensagem, int quantidadeMutantes, ArrayList<HabilidadeQuantidadeModel> habilidades) {
		this.setSucesso(suceso);
		this.setMensagem(mensagem);
		this.quantidadeMutantes = quantidadeMutantes;
		this.habilidades = habilidades; 
	}
	
	public int getQuantidadeMutantes() {
		return this.quantidadeMutantes;
	}
	
	public void setQuantidadeMutantes(int qtdMutantes) {
		this.quantidadeMutantes = qtdMutantes;
	}
	
	public ArrayList<HabilidadeQuantidadeModel> getHabilidades() {
		return this.habilidades;
	}
	
	public void setHabilidades(ArrayList<HabilidadeQuantidadeModel> habilidades) {
		this.habilidades = habilidades;
	}
}
