package br.net.android.mutante.model.response;

import java.util.ArrayList;

import br.net.android.mutante.model.Habilidade;
import br.net.android.mutante.model.Mutante;

public class DetalhesMutanteResponse extends Response {
	private Mutante mutante;
	private ArrayList<Habilidade> habilidades;
	
	public DetalhesMutanteResponse() {
		
	}
	
	public DetalhesMutanteResponse(boolean sucesso, String mensagem) {
		this.setSucesso(sucesso);
		this.setMensagem(mensagem);
	}
	
	public DetalhesMutanteResponse(boolean sucesso, String mensagem, Mutante mutante, ArrayList<Habilidade> habilidades) {
		this.setSucesso(sucesso);
		this.setMensagem(mensagem);
		this.mutante = mutante;
		this.habilidades = habilidades;
	}
	
	public Mutante getMutante() {
		return this.mutante;
	}
	
	public void setMutante(Mutante mutante) {
		this.mutante = mutante;
	}
	
	public ArrayList<Habilidade> getHabilidades() {
		return this.habilidades;
	}
	
	public void setHabilidades(ArrayList<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}
}
