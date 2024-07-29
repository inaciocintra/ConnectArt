package com.connectart.dao;

import com.connectart.models.Cliente;
import java.sql.*;
import java.util.Collections;


public class ClienteDAO extends DAO {
    
    public ClienteDAO() {
        super();
        conectar();    
    }
    
public boolean inserirCliente(Cliente cliente) {
    boolean status = false;
    try {  
        String sql = "INSERT INTO connectart.cliente (cliente_nome, cliente_email, cliente_senha) "
                   + "VALUES (?, ?, ?)";
        PreparedStatement pst = conexao.prepareStatement(sql);
        pst.setString(1, cliente.getClienteNome());
        pst.setString(2, cliente.getClienteEmail());
        pst.setString(3, cliente.getClienteSenha());
        pst.executeUpdate();
        pst.close();
        status = true;
    } catch (SQLException u) {  
        throw new RuntimeException(u);
    }
    return status;
}

public boolean atualizarCliente(Cliente cliente) {
    boolean status = false;
    try {  
        Statement st = conexao.createStatement();
        //String sql = "UPDATE connectart.cliente SET cliente_nome = '" + cliente.getClienteNome() + "', cliente_email = '"  
                   //+ cliente.getClienteEmail() + "', cliente_senha = '" + cliente.getClienteSenha() + "', cliente_compras = '" + cliente.getClienteCompras() + "', cliente_telefone = '" + cliente.getClienteTelefone()+ "'"
                   //+ " WHERE cliente_id = " + cliente.getClienteId();

                   String sql = "UPDATE connectart.cliente SET cliente_nome = '" + cliente.getClienteNome() + "', cliente_email = '"  
                   + cliente.getClienteEmail() + "', cliente_senha = '" + cliente.getClienteSenha() + "'"
                   + " WHERE cliente_email = " + cliente.getClienteEmail();
        st.executeUpdate(sql);
        st.close();
        status = true;
    } catch (SQLException u) {  
        throw new RuntimeException(u);
    }
    return status;
}

public boolean excluirCliente(String email) {
    try {
        String sql = "DELETE FROM connectart.cliente WHERE cliente_email = ?";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, email);
        int rowsAffected = st.executeUpdate();
        st.close();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


public Cliente autenticarCliente(String email) {
    Cliente cliente = null;
    try {
        String sql = "SELECT * FROM connectart.cliente WHERE cliente_email = ?";
        PreparedStatement st = conexao.prepareStatement(sql);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            String nome = rs.getString("cliente_nome");
            String senha = rs.getString("cliente_senha");
            cliente = new Cliente(nome, email, senha);
            System.out.println("Cliente autenticado: " + cliente.getClienteEmail());
        } else {
            System.out.println("Nenhum cliente encontrado com o email: " + email);
        }
        rs.close();
        st.close();
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Erro ao autenticar cliente: " + e.getMessage(), e);
    }
    return cliente;
}


}


/*public Cliente[] getClientes() {
    Cliente[] clientes = null;
    
    try {
        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT * FROM connectart.cliente");		
         if(rs.next()){
             rs.last();
             clientes = new Cliente[rs.getRow()];
             rs.beforeFirst();

             for(int i = 0; rs.next(); i++) {
                clientes[i] = new Cliente(rs.getInt("cliente_id"), rs.getString("cliente_nome"), 
                                          rs.getString("cliente_email"), rs.getString("cliente_senha"), rs.getInt("cliente_compras"),rs.getString("cliente_telefone"));
             }
          }
          st.close();
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
    return clientes;
}*/
