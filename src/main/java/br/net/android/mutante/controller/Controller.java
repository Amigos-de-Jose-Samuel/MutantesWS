package br.net.android.mutante.controller;

import br.net.android.mutante.dao.UsuarioDAO;
import br.net.android.mutante.model.HabilidadeQuantidadeModel;
import br.net.android.mutante.model.Mutante;
import br.net.android.mutante.model.Usuario;
import br.net.android.mutante.model.Habilidade;
import br.net.android.mutante.model.response.DashboardResponse;
import br.net.android.mutante.model.response.DetalhesMutanteResponse;
import br.net.android.mutante.model.response.EditaMutanteResponse;
import br.net.android.mutante.model.response.ListarMutantesResponse;
import br.net.android.mutante.model.response.LoginResponse;
import br.net.android.mutante.model.response.NovoMutanteResponse;
import br.net.android.mutante.model.response.PesquisarMutantesResponse;
import br.net.android.mutante.model.response.RemoveMutanteResponse;

import java.util.ArrayList;

import br.net.android.mutante.dao.ConnectionFactory;
import br.net.android.mutante.dao.HabilidadeDAO;
import br.net.android.mutante.dao.MutanteDAO;
import br.net.android.mutante.dao.MutanteHabilidadeDAO;

public class Controller {
	
	private static UsuarioDAO usuarioDAO = null;
	private static MutanteDAO mutanteDAO = null;
	private static MutanteHabilidadeDAO mutanteHabilidadeDAO = null;
	private static HabilidadeDAO habilidadeDAO = null;

	private static UsuarioDAO getUsuarioDAO() throws Exception {
		if(usuarioDAO == null)
			usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
		
		return usuarioDAO;
	}
	
	private static MutanteDAO getMutanteDAO() throws Exception {
		if(mutanteDAO == null)
			mutanteDAO = new MutanteDAO(ConnectionFactory.getConnection());
		
		return mutanteDAO;
	}
	
	private static MutanteHabilidadeDAO getMutanteHabilidadeDAO() throws Exception {
		if(mutanteHabilidadeDAO == null)
			mutanteHabilidadeDAO = new MutanteHabilidadeDAO(ConnectionFactory.getConnection());
		
		return mutanteHabilidadeDAO;
	}
	
	private static HabilidadeDAO getHabilidadeDAO() throws Exception {
		if(habilidadeDAO == null)
			habilidadeDAO = new HabilidadeDAO(ConnectionFactory.getConnection());
		
		return habilidadeDAO;
	}

	public static LoginResponse realizaLogin(Usuario usuario) throws Exception {
		int count = getUsuarioDAO().loginExistente(usuario);
	
		if(count == 0)
			return new LoginResponse(false, "Login ou Senha incorretos.");
		else if (count == -1)
			return new LoginResponse(false, "Houve um erro na consulta de usuários.");
		
		return new LoginResponse(true, "Usuário logado.");
	}
	
	public static DashboardResponse obtemDadosDashboard() throws Exception {
		int quantidadeMutantes = getMutanteDAO().obtemQuantidadeMutantes();
		
		if(quantidadeMutantes == -1)
			return new DashboardResponse(false, "Houve um erro na consulta de mutantes.");
		
		ArrayList<HabilidadeQuantidadeModel> habilidades = getMutanteHabilidadeDAO().obtemHabilidadesMaisUsadas();
		
		if(habilidades == null)
			return new DashboardResponse(false, "Houve um erro na consulta de habilidades.");
		
		return new DashboardResponse(true, "Dados obtidos com sucesso.", quantidadeMutantes, habilidades);
	}
	
	public static NovoMutanteResponse cadastraMutante(Mutante mutante, ArrayList<String> habilidades) throws Exception {
		int count = getMutanteDAO().mutanteExiste(mutante.getNome());
		
		if(count > 0)
			return new NovoMutanteResponse(false, "Mutante já cadastrado.");
		else if(count == -1)
			return new NovoMutanteResponse(false, "Houve um erro na validação do mutante.");
		
		int idUsuario = getUsuarioDAO().obtemIdPorLogin(mutante.getLoginUsuarioCadastro());
		
		if(idUsuario == -1)
			return new NovoMutanteResponse(false, "Usuario indicado não existe.");
		
		mutante.setIdUsuario(idUsuario);
		
		int idMutante = getMutanteDAO().insereMutante(mutante);
		
		if(idMutante == -1)
			return new NovoMutanteResponse(false, "Houve um erro ao inserir o mutante.");
		
		mutante.setId(idMutante);
		
		for(String habilidade : habilidades) {
			int idHabilidade = getHabilidadeDAO().insereBuscaHabilidade(habilidade);
			
			if(idHabilidade == -1)
				return new NovoMutanteResponse(false, "Houve um erro no cadastro da habilidade.");
			
			boolean sucesso = getMutanteHabilidadeDAO().insereMutanteHabilidade(mutante.getId(), idHabilidade);
			if (!sucesso) {
				getMutanteHabilidadeDAO().deletaHabilidadesMutante(idMutante);
				getMutanteDAO().deletaMutante(idMutante);
				return new NovoMutanteResponse(false, "Houve um erro no cadastro das habilidades do mutante." + idHabilidade);
			}
		}
		
		return new NovoMutanteResponse(true, "Mutante cadastrado com sucesso.");
	}
	
	public static ListarMutantesResponse obtemListaMutantes() throws Exception {
		ArrayList<Mutante> mutantes = getMutanteDAO().obtemListaMutantes();
		
		if(mutantes == null)
			return new ListarMutantesResponse(false, "Houve um erro na consulta de Mutantes.");
		
		return new ListarMutantesResponse(true, "Lista obtida com sucesso.", mutantes);
	}
	
	public static DetalhesMutanteResponse obtemDetalhesMutante(int idMutante) throws Exception {
		int count = getMutanteDAO().mutanteExiste(idMutante);
		
		if(count == 0)
			return new DetalhesMutanteResponse(false, "Mutante não encontrado.");
		
		Mutante mutante = getMutanteDAO().obtemMutante(idMutante);
		
		if(mutante == null)
			return new DetalhesMutanteResponse(false, "Houve um erro na consulta do mutante.");
		
		ArrayList<Habilidade> habilidades = getHabilidadeDAO().obtemHabilidadesMutante(idMutante);
		
		if(habilidades == null)
			return new DetalhesMutanteResponse(false, "Houve um erro na consulta de Habilidades");
		
		return new DetalhesMutanteResponse(true, "Mutante obtido com sucesso.", mutante, habilidades);
	}
	
	public static RemoveMutanteResponse removeMutante(int idMutante) throws Exception {
		int count = getMutanteDAO().mutanteExiste(idMutante);
		
		if(count == 0)
			return new RemoveMutanteResponse(false, "Mutante não encontrado.");
		else if(count == -1)
			return new RemoveMutanteResponse(false, "Houve um erro na validação do mutante.");
		
		getMutanteHabilidadeDAO().deletaHabilidadesMutante(idMutante);
		
		getMutanteDAO().deletaMutante(idMutante);
		
		return new RemoveMutanteResponse(true, "Mutante removido com sucesso.");
	}
	
	public static EditaMutanteResponse editaMutante(Mutante mutante,  ArrayList<String> habilidades) throws Exception {
		int count = getMutanteDAO().mutanteExiste(mutante.getId());
		
		if(count == 0)
			return new EditaMutanteResponse(false, "Mutante não encontrado.");
		else if(count == -1)
			return new EditaMutanteResponse(false, "Houve um erro na validação do mutante.");
		
		getMutanteDAO().atualizaMutante(mutante);
		
		getMutanteHabilidadeDAO().deletaHabilidadesMutante(mutante.getId());
		
		for(String habilidade : habilidades) {
			int idHabilidade = getHabilidadeDAO().insereBuscaHabilidade(habilidade);
			
			if(idHabilidade == -1)
				return new EditaMutanteResponse(false, "Houve um erro no cadastro da habilidade.");
			
			getMutanteHabilidadeDAO().insereMutanteHabilidade(mutante.getId(), idHabilidade);
		}
		
		return new EditaMutanteResponse(true, "Mutante editado com sucesso.");
	}
	
	public static PesquisarMutantesResponse pesquisarMutantes(String habilidade) throws Exception {
		ArrayList<Mutante> mutantes = getMutanteDAO().obtemPorHabilidade(habilidade);
		
		if(mutantes == null)
			return new PesquisarMutantesResponse(false, "Houve um erro na pesquisa de mutantes.");
		
		return new PesquisarMutantesResponse(true, "Pesquisa realizada com sucesso.", mutantes);
	}
}
