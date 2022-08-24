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
	   	 			+ "ORDER BY h.descricao;";
   	 
	   	try(PreparedStatement st = con.prepareStatement(sql)) {
	   		st.setInt(1, idMutante);
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
    
    public int insereBuscaHabilidade(String descricao) {
    	
    	String count = "SELECT count(id) FROM Habilidade WHERE lower(descricao) = lower(?)";
    	
    	try(PreparedStatement st = con.prepareStatement(count)) {
    		st.setString(1, descricao);
	   		ResultSet rs = st.executeQuery();
	   		
			rs.next();
			
			if(rs.getInt(1) > 0) {
				String select = "SELECT id FROM Habilidade WHERE lower(descricao) = lower(?)";
				
				try(PreparedStatement st2 = con.prepareStatement(select)){
					st2.setString(1, descricao);
					ResultSet rs2 = st2.executeQuery();
					rs2.next();
					
					return rs2.getInt(1);
				}
			} else {
				String insert = "INSERT INTO Habilidade(descricao) VALUES(?) RETURNING Id";
				try(PreparedStatement st2 = con.prepareStatement(insert)){
					st2.setString(1, descricao);
					ResultSet rs2 = st2.executeQuery();
					rs2.next();
					
					return rs2.getInt(1);
				}
			}
	   	 } catch(Exception ex) {
	   		 return -1;
	   	 }
    }
}
