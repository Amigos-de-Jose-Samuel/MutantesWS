package br.net.android.mutante.dao;

import br.net.android.mutante.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class UsuarioDAO {
	
	private Connection con = null;
    
    public UsuarioDAO(Connection con) throws Exception {
        
        if(con == null) {
            throw new Exception("Conexão nula ao criar UsuarioDAO.");
        }
        
        this.con = con;
    }
	
	public int obterId(Usuario usuario) {
		String sql = "SELECT id "
				+ "FROM Usuario "
				+ "WHERE Login = ? "
				+ "AND senha = ?";
		
		try(PreparedStatement st = con.prepareStatement(sql)){
			
			ResultSet rs = st.executeQuery();
			rs.next();
				
			String id = rs.getString(1);
			
			if(id == null || id == "")
				return 0;
			
			return Integer.parseInt(id);
			
		} catch (Exception ex) {
			return -1;
		}
	}
	
	public int novoUsuario(Usuario usuario) {
		String sql = "INSERT INTO Usuario (login, senha) "
				+ "VALUES(?, ?) RETURNING id";
		
		try(PreparedStatement st = con.prepareStatement(sql)) {
			ResultSet rs = st.executeQuery();
			rs.next();
			
			String id = rs.getString(1);
			
			if(id == null || id == "")
				return 0;
			//validar se pau no insert retorna 0 null
			return Integer.parseInt(id);
			
		} catch(Exception ex) {
			return -1;
		}
	}
	
	public int usuarioExiste(Usuario usuario) {
		String sql = "SELECT Count(*) FROM Usuario WHERE login = ?";
	}
}
