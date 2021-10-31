package DAO;

import Modelo.Clientes;
import Perspectiva.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Castelhano
 */
public class ClientesDAO {
    static Conexao cc = new Conexao();
    static Connection con = cc.conectar();
    
    /*
    *Metodo de cadastrar clientes
    */
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
    Metodo de ID automatico
    */
    public int Buscar_id(String sql)throws SQLException{
        int id = 0;
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    id = rs.getInt("id_clientes");
                }
                rs.close();
            }
        }
        return id;
    }
    /*
    *Metodo de BuscarCPF
    */
    public Clientes buscar_cpf(String sql)throws SQLException{
        Clientes c = new Clientes();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    c = new Clientes();
                    c.setId_clientes(rs.getInt("id_clientes"));
                    c.setNome(rs.getString("nome"));
                    c.setEndereco(rs.getString("endereco"));
                    c.setEmail(rs.getString("email"));
                    c.setCidade(rs.getString("cidade"));
                    c.setBairro(rs.getString("bairro"));
                    c.setCpf(rs.getString("cpf"));
                    c.setRg(rs.getString("rg"));
                    c.setUf(rs.getString("uf"));
                }
                rs.close();
            }
        }
        return c;
    }
    /*
    *Metodo de listar clientes
    */
    public List<Clientes>preencherTabela(String sql)throws SQLException{
        Clientes c;
        
        List<Clientes> lista = new ArrayList();
        
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    c = new Clientes();
                    c.setNome(rs.getString("nome"));
                    lista.add(c);
                }
                rs.close();
            }
        }
        return lista;
    }
}
