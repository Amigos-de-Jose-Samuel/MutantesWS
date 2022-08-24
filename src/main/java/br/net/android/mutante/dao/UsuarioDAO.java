package br.net.android.mutante.dao;

import br.net.android.mutante.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class UsuarioDAO {
	
	private Connection con = null;
    
    public UsuarioDAO(Connection con) throws Exception {
        this.con = con;
        
        if(con == null) {
            throw new Exception("Conexão nula ao criar UsuarioDAO.");
        }
    }
	
	public int loginExistente(Usuario usuario) {
		String sql = "SELECT count(*) "
				+ "FROM Usuario "
				+ "WHERE Login = ? "
				+ "AND senha = ?";
		
		try(PreparedStatement st = con.prepareStatement(sql)){
			
			st.setString(1, usuario.getLogin());
			st.setString(2, usuario.getSenha());
			
			ResultSet rs = st.executeQuery();
			rs.next();
				
			int count = rs.getInt(1);
			
			return count;
			
		} catch (Exception ex) {
			return -1;
		}
	}
	
	public int obtemIdPorLogin(String login) {
		String sql = "SELECT id "
				+ "FROM Usuario "
				+ "WHERE Login = ? ";
		
		try(PreparedStatement st = con.prepareStatement(sql)){
			
			st.setString(1, login);
			
			ResultSet rs = st.executeQuery();
			rs.next();
				
			int id = rs.getInt(1);
			
			return id;
			
		} catch (Exception ex) {
			return -1;
		}
	}
}
