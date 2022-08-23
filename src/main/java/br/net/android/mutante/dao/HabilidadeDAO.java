package br.net.android.mutante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.net.android.mutante.model.Habilidade;

public class HabilidadeDAO {
	private Connection con = null;
    
    public HabilidadeDAO(Connection con) throws Exception {
        this.con = con;
        
        if(con == null) {
            throw new Exception("Conexão nula ao criar HabilidadeDAO.");
        }
    }
    
    public ArrayList<Habilidade> obtemHabilidadesMutante(int idMutante) {
    	ArrayList<Habilidade> habilidades = new  ArrayList<Habilidade>();
   	 
   	 	String sql = "SELECT h.id, h.descricao "
	   	 			+ "FROM Habilidade h "
	   	 			+ "INNER JOIN MutanteHabilidade mh ON (mh.idhabilidade = h.id) "
	   	 			+ "WHERE mh.idmutante = ? "
	   	 			+ "ORDER BY h.descrico;";
   	 
	   	try(PreparedStatement st = con.prepareStatement(sql)) {
	   		ResultSet rs = st.executeQuery();
	   		 
			while(rs.next()) {
				Habilidade habilidade = new Habilidade();
				habilidade.setId(rs.getInt(1));
				habilidade.setDescricao(rs.getString(2));
				habilidades.add(habilidade);
			}
	   		 
   		 	return habilidades;
	   	 } catch(Exception ex) {
	   		 return null;
	   	 }
    }
}
