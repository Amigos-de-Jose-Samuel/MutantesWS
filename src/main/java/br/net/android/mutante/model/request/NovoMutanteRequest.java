package br.net.android.mutante.model.request;

import java.util.ArrayList;

import br.net.android.mutante.model.Mutante;

public class NovoMutanteRequest {
	
	private Mutante mutante;
	private ArrayList<String> hablidades;
	
	public Mutante getMutante() {
		return this.mutante;
	}
	
	public void setMutante(Mutante mutante) {
		this.mutante = mutante;
	}
	
	public ArrayList<String> getHabilidades(){
		return this.hablidades;
	}
	
	public void setHabilidades(ArrayList<String> habilidades) {
		this.hablidades = habilidades;
	}
}
