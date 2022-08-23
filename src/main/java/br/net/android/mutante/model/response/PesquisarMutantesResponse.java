package br.net.android.mutante.model.response;

import java.util.ArrayList;

import br.net.android.mutante.model.Mutante;

public class PesquisarMutantesResponse extends Response {
	private ArrayList<Mutante> mutantes;
	
	public PesquisarMutantesResponse(boolean sucesso, String mensagem) {
		this.setSucesso(sucesso);
		this.setMensagem(mensagem);
	}
	
	public PesquisarMutantesResponse(boolean sucesso, String mensagem, ArrayList<Mutante> mutantes) {
		this.setSucesso(sucesso);
		this.setMensagem(mensagem);
		this.mutantes = mutantes;
	}
	
	public ArrayList<Mutante> getMutantes() {
		return this.mutantes;
	}
	
	public void setMutantes(ArrayList<Mutante> mutantes) {
		this.mutantes = mutantes;
	}
}
