package br.net.android.mutante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.net.android.mutante.model.Mutante;

public class MutanteDAO {
	private Connection con = null;
    
    public MutanteDAO(Connection con) throws Exception {
        this.con = con;
   
        if(con == null) {
            throw new Exception("Conexão nula ao criar DAO.");
        }
    }
    
    public int obtemQuantidadeMutantes() {
    	String sqlCount = "SELECT Count(*) " +
    				      "FROM Mutante ";
    
    	try(PreparedStatement st = con.prepareStatement(sqlCount)){
			
			ResultSet rs = st.executeQuery();
			rs.next();
				
			int count = rs.getInt(1);
			
			return count;
			
		} catch (Exception ex) {
			return -1;
		}
    }

    public int mutanteExiste(String nome) {
    	String sql = "SELECT count(*) "
				+ "FROM Mutante "
				+ "WHERE Nome = ?";
		
		try(PreparedStatement st = con.prepareStatement(sql)){
			
			st.setString(1, nome);
			
			ResultSet rs = st.executeQuery();
			rs.next();
				
			int count = rs.getInt(1);
			
			return count;
			
		} catch (Exception ex) {
			return -1;
		}
    }
    
    public int mutanteExiste(int idMutante) {
    	String sql = "SELECT count(*) "
				+ "FROM Mutante "
				+ "WHERE id = ?";
		
		try(PreparedStatement st = con.prepareStatement(sql)){
			
			st.setInt(1, idMutante);
			
			ResultSet rs = st.executeQuery();
			rs.next();
				
			int count = rs.getInt(1);
			
			return count;
			
		} catch (Exception ex) {
			return -1;
		}
    }
    
    public int insereMutante(Mutante mutante) {
    	String sql = "";
    	
    	try(PreparedStatement st = con.prepareStatement(sql)){
			
			//st.setString(1, nome);
			
			ResultSet rs = st.executeQuery();
			rs.next();
				
			//int count = rs.getInt(1);
			
			//return count;
			return 1;
			
		} catch (Exception ex) {
			return -1;
		}
    }
    
    public boolean deletaMutante(int idMutante) {
		String sql = "DELETE Mutante WHERE id = ?";
    	
    	try(PreparedStatement st = con.prepareStatement(sql)){
			
			st.setInt(1, idMutante);
			
			ResultSet rs = st.executeQuery();
			rs.next();
			
			return true;
			
		} catch (Exception ex) {
			return false;
		}
    }
    
    public ArrayList<Mutante> obtemListaMutantes(){
    	 ArrayList<Mutante> mutantes = new  ArrayList<Mutante>();
    	 
    	 String sql = "SELECT id, nome, foto FROM Mutante ORDER By nome;";
    	 
    	 try(PreparedStatement st = con.prepareStatement(sql)) {
    		 ResultSet rs = st.executeQuery();
    		 
    		 while(rs.next()) {
    			 Mutante mutante = new Mutante();
    			 mutante.setId(rs.getInt(1));
    			 mutante.setNome(rs.getString(2));
    			 mutante.setFoto(rs.getBytes(3));
    			 
    			 mutantes.add(mutante);
    		 }
    		 
    		 return mutantes;
    	 } catch(Exception ex) {
    		 return null;
    	 }
    }
    
    public Mutante obtemMutante(int idMutante) {
		Mutante mutante = null;
    	
    	String sql = "SELECT m.id, m.nome, m.foto, u.login"
    			   + "FROM Mutante m"
    			   + "INNER JOIN Usuario u ON (u.id = m.idUsuario) "
    			   + "WHERE m.id = ? "
    			   + "ORDER BY nome;";
    	
	   	 try(PreparedStatement st = con.prepareStatement(sql)) {
	   		 st.setInt(1, idMutante);
	   		 ResultSet rs = st.executeQuery();
	   		 
	   		 rs.next();
	   		 
   			 mutante = new Mutante();
   			 mutante.setId(rs.getInt(1));
   			 mutante.setNome(rs.getString(2));
   			 mutante.setFoto(rs.getBytes(3));
   			 mutante.setLoginUsuarioCadastro(rs.getString(4));
   			 
   			 return mutante;
	   		
	   	 } catch(Exception ex) {
	   		 return null;
	   	 }
    }
    
    public ArrayList<Mutante> obtemPorHabilidade(String habilidade) {
    	ArrayList<Mutante> mutantes = new  ArrayList<Mutante>();
    	
    	String sql = "SELECT m.id, m.nome, m.foto "
    			+ "FROM Mutante m "
    			+ "INNER JOIN Usuario u ON (u.id = m.idUsuario) "
    			+ "INNER JOIN MutanteHabilidade mh ON (mh.idmutante = m.id) "
    			+ "INNER JOIN Habilidade h ON (h.id = mh.idhabilidade) "
    			+ "WHERE h.descricao LIKE CONCAT( '%',?,'%')";
    	
    	try(PreparedStatement st = con.prepareStatement(sql)) {
    		st.setString(1, habilidade);
    		ResultSet rs = st.executeQuery();
	   		 
	   		while(rs.next()) {
		   		Mutante mutante = new Mutante();
			  	mutante.setId(rs.getInt(1));
			  	mutante.setNome(rs.getString(2));
			  	mutante.setFoto(rs.getBytes(3));
			  			 
			  	mutantes.add(mutante);
	   		}
	   	} catch(Exception ex) {
	   		return null;
	   	}
    
    	return mutantes;
    }
    
    public boolean atualizaMutante(Mutante mutante) {
    	String sql = "UPDATE Mutante SET nome = ?, foto = ? WHERE id = ?";
    	
    	try(PreparedStatement st = con.prepareStatement(sql)) {
    		st.setString(1, mutante.getNome());
    		st.setBytes(2, mutante.getFoto());
    		st.setInt(3, mutante.getId());
    		
    		st.executeUpdate();
	   		return true;
	   	} catch(Exception ex) {
	   		return false;
	   	}
    }
}
