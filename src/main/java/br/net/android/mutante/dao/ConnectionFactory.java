package br.net.android.mutante.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory implements AutoCloseable {

	private static String DRIVER = "org.postgresql.Driver";
    private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String LOGIN = "postgres";
    private static String SENHA = "1234";
    
    private static Connection con = null;
    
    public static Connection getConnection() throws Exception {
        if(con == null){
            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, LOGIN, SENHA);
            } 
            catch(ClassNotFoundException e){
                throw new Exception("Driver do banco não encontrado: " + DRIVER, e);
            }
            catch(Exception e){
                throw new Exception("Erro conectando ao BD: " + URL + "/" + LOGIN + "/" + SENHA);
            }
        }
        return con;
    }

    @Override
    public void close() {
        if(con != null){
            try {
                con.close();
                con = null;
            }
            catch(Exception e) {
                System.out.println("Erro fechando a conexao. IGNORADO");
                e.printStackTrace();
            }
        }
    }
}
