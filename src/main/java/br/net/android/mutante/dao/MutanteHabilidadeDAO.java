package br.net.android.mutante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.net.android.mutante.model.HabilidadeQuantidadeModel;

public class MutanteHabilidadeDAO {

	private Connection con = null;
    
    public MutanteHabilidadeDAO(Connection con) throws Exception {
        this.con = con;
        
        if(con == null) {
            throw new Exception("Conexão nula ao criar MutanteHabilidadeDAO.");
        }
    }
    
    public boolean insereMutanteHabilidade(int idMutante, int idHabilidade) {
    	String sql = "INSERT INTO MutanteHabilidade(idMutante, idHabilidade) " +
    				 "VALUES(?, ?)";
    	
    	try(PreparedStatement st = con.prepareStatement(sql)){
    		st.setInt(1, idMutante);
    		st.setInt(2, idHabilidade);
    		
			st.executeUpdate();
			return true;
		} catch (Exception ex) {
			return false;
		}
    }
    
    public ArrayList<HabilidadeQuantidadeModel> obtemHabilidadesMaisUsadas() {
    	ArrayList<HabilidadeQuantidadeModel> habilidades = new ArrayList<HabilidadeQuantidadeModel>();
    	
    	String sql = "select count(mh.idhabilidade), h.descricao "
    			   + "from mutantehabilidade mh "
    			   + "inner join habilidade h on (h.id = mh.idhabilidade) "
    			   + "group by mh.idhabilidade, h.descricao " 
    			   + "order by count(mh.idhabilidade) desc, h.descricao "
    			   + "limit 3";
    	
    	try(PreparedStatement st = con.prepareStatement(sql)){
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int quantidede = rs.getInt(1);
				String habilidade = rs.getString(2);
				habilidades.add(new HabilidadeQuantidadeModel(habilidade, quantidede));
			};

	    	return habilidades;
		} catch (Exception ex) {
			return null;
		}
    }
    
    public boolean deletaHabilidadesMutante(int idMutante) {
    	String sql = "DELETE FROM MutanteHabilidade WHERE idMutante = ?";
	
		try(PreparedStatement st = con.prepareStatement(sql)){
			st.setInt(1, idMutante);
			
			st.executeUpdate();
			return true;
		} catch (Exception ex) {
			return false;
		}
    }
}
