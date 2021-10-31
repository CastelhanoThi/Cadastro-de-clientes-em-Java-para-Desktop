package Perspectiva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Castelhano
 */
public class Conexao {
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/cadastroclientes";
    String usuario = "root";
    String senha = "";
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao realizada com sucesso");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("SQLException nao encontrado");
        }
        return con;
    }
    public void desconectar(){
        try {
            con.close();
        } catch (SQLException e) {
        }
    }
}
