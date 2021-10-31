package DAO;

import Modelo.Usuario;
import Perspectiva.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Castelhano
 */
public class UsuarioDAO {

    static Conexao cc = new Conexao();
    static Connection con = cc.conectar();

    public boolean comando(String sql)throws SQLException{
        boolean fechado = true;
        PreparedStatement stmt = con.prepareStatement(sql);
        if(stmt.execute()){
            fechado = false;
        }
        stmt.close();
        return fechado;
    }
    /*
    Metedo para cadastrar usuario no banco de dados
     */
    public Usuario validarUsuario(String sql, String usuario, String password, String permissao) {
        Usuario u = null;
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setUsuario(rs.getString("usuario"));
                u.setPassword(rs.getString("password"));
                u.setPermissao(rs.getString("permissao"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuario nao encontrado");
        }
        return u;
    }
    public List<Usuario> preencherUsuario(String sql) throws SQLException{
        Usuario u;
        
        List<Usuario> lista = new ArrayList<>();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    u = new Usuario();
                    u.setNome(rs.getString("nome"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setPermissao(rs.getString("permissao"));
                    u.setPassword(rs.getString("password"));
                    u.setNivel(rs.getString("nivel"));
                    lista.add(u);
                }
                rs.close();
            }
            return lista;
        }
    }

    public boolean comando(Usuario us) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
