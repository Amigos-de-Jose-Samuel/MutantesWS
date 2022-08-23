package br.net.android.mutante.rest;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import br.net.android.mutante.controller.Controller;
import br.net.android.mutante.model.Habilidade;
import br.net.android.mutante.model.Mutante;
import br.net.android.mutante.model.Usuario;
import br.net.android.mutante.model.response.DashboardResponse;
import br.net.android.mutante.model.response.DetalhesMutanteResponse;
import br.net.android.mutante.model.response.EditaMutanteResponse;
import br.net.android.mutante.model.response.ListarMutantesResponse;
import br.net.android.mutante.model.response.LoginResponse;
import br.net.android.mutante.model.response.NovoMutanteResponse;
import br.net.android.mutante.model.response.PesquisarMutantesResponse;
import br.net.android.mutante.model.response.RemoveMutanteResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class MutanteREST {

	@PostMapping("/login")
	public LoginResponse login(@RequestBody Usuario usuario) {
		try {
			return Controller.realizaLogin(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return new LoginResponse(false, "Houve um erro na consulta de usuários.");
		}
	}
	
	@GetMapping("/dashboard")
	public DashboardResponse dashboard() {
		try {
			return Controller.obtemDadosDashboard();
		} catch (Exception e) {
			e.printStackTrace();
			return new DashboardResponse(false, "Houve um erro na consulta da dashboard.");
		}
	}
	
	@PostMapping("/novo-mutante")
	public NovoMutanteResponse novoMutante(@RequestBody Mutante mutante, @RequestBody ArrayList<Habilidade> habilidades) {
		try {
			return Controller.cadastraMutante(mutante, habilidades);
		} catch (Exception e) {
			e.printStackTrace();
			return new NovoMutanteResponse(false, "Houve um erro no cadastro do Mutante.");
		}
	}
	
	@GetMapping("/listar")
	public ListarMutantesResponse listarMutantes() {
		try {
			return Controller.obtemListaMutantes();
		} catch (Exception e) {
			e.printStackTrace();
			return new ListarMutantesResponse(false, "Houve um erro na busca dos mutantes.");
		}
	}
	
	@GetMapping("/detalhe/{id}")
	public DetalhesMutanteResponse detalhesMutante(@PathVariable("id") int idMutante) {
		try {
			return Controller.obtemDetalhesMutante(idMutante);
		} catch (Exception e) {
			e.printStackTrace();
			return new DetalhesMutanteResponse(false, "Houve um erro na busca do mutante.");
		}
	}
	
	@DeleteMapping("/deletar/{id}")
	public RemoveMutanteResponse removeMutante(@PathVariable("id") int idMutante) {
		try {
			return Controller.removeMutante(idMutante);
		} catch (Exception e) {
			e.printStackTrace();
			return new RemoveMutanteResponse(false, "Houve um erro ao remover o mutante.");
		}
	}
	
	@PutMapping("/editar")
	public EditaMutanteResponse editaMutante(@RequestBody Mutante mutante, @RequestBody ArrayList<Habilidade> habilidades) {
		try {
			return Controller.editaMutante(mutante, habilidades);
		} catch (Exception e) {
			e.printStackTrace();
			return new EditaMutanteResponse(false, "Houve um erro ao atualizar o mutante.");
		}
	}
	
	@GetMapping("/pesquisar/{habilidade}")
	public PesquisarMutantesResponse pesquisarMutantes(@PathVariable("habilidade") String habilidade) {
		try {
			return Controller.pesquisarMutantes(habilidade);
		} catch (Exception e) {
			e.printStackTrace();
			return new PesquisarMutantesResponse(false, "Houve um erro nas pesquisa dos mutantes.");
		}
	}
}
