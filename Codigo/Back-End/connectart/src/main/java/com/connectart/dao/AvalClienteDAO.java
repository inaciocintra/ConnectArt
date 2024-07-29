package com.connectart.dao;

import java.sql.*;

import com.connectart.models.AvalCliente;


public class AvalClienteDAO extends DAO{
    

    public AvalClienteDAO() {
        super();
        conectar();
    }

    public boolean inserirAvalCliente(AvalCliente avalCliente) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO connectart.avalcliente (aval_cliente_id, aval_cliente_pontuacao, aval_cliente_comentario, aval_cliente_artista, aval_cliente_cliente) "
                       + "VALUES (" + avalCliente.getAvalClienteId() + ", " + avalCliente.getAvalClientePontuacao() + ", '" + avalCliente.getAvalClienteComentario() + "', " + avalCliente.getAvalClienteArtista() + ", " + avalCliente.getAvalClienteCliente() + ");";
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean atualizarAvalCliente(AvalCliente avalCliente) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE connectart.avalcliente SET aval_cliente_pontuacao = " + avalCliente.getAvalClientePontuacao() + ", aval_cliente_comentario = '" + avalCliente.getAvalClienteComentario() + "', aval_cliente_artista = " + avalCliente.getAvalClienteArtista() + ", aval_cliente_cliente = " + avalCliente.getAvalClienteCliente() + " WHERE aval_cliente_id = " + avalCliente.getAvalClienteId();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public boolean excluirAvalCliente(int avalClienteId) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM connectart.avalcliente WHERE aval_cliente_id = " + avalClienteId;
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public AvalCliente[] getAvalClientes() {
        AvalCliente[] avalClientes = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM connectart.avalcliente");

            if (rs.next()) {
                rs.last();
                avalClientes = new AvalCliente[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    avalClientes[i] = new AvalCliente(rs.getInt("aval_cliente_id"), rs.getInt("aval_cliente_pontuacao"), rs.getString("aval_cliente_comentario"), rs.getInt("aval_cliente_artista"), rs.getInt("aval_cliente_cliente"));
                }
            }
            st.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return avalClientes;
    }
}
