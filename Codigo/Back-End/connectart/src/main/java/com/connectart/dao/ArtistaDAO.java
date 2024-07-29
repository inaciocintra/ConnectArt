package com.connectart.dao;

import java.sql.*;

import com.connectart.models.Artista;

public class ArtistaDAO extends DAO{
    

    public ArtistaDAO() {
        super();
        conectar();
    }

    public boolean inserirArtista(Artista artista) {
        boolean status = false;
        try {
            String sql = "INSERT INTO connectart.artista (artista_nome, artista_email, artista_senha, artista_endereco, artista_telefone) "
                       + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, artista.getArtistaNome());
            pst.setString(2, artista.getArtistaEmail());
            pst.setString(3, artista.getArtistaSenha());
            pst.setString(4, artista.getArtistaEndereco());
            pst.setString(5, artista.getArtistaTelefone());
            pst.executeUpdate();
            pst.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirArtista(String email) {
        try {
            String sql = "DELETE FROM connectart.artista WHERE artista_email = ?";
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

    
    public boolean atualizarArtista(Artista artista) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE connectart.artista SET artista_nome = '" + artista.getArtistaNome() + "', artista_email = '" + artista.getArtistaEmail() + "', artista_senha = '" + artista.getArtistaSenha() + "', artista_endereco = '" + artista.getArtistaEndereco() + "', artista_telefone = '" + artista.getArtistaTelefone() + "' WHERE artista_email = " + artista.getArtistaEmail();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }


    public Artista autenticarArtista(String email) {
        Artista artista = null;
        try {
            String sql = "SELECT * FROM connectart.artista WHERE artista_email = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("artista_nome");
                String senha = rs.getString("artista_senha");
                String endereco = rs.getString("artista_endereco");
                String telefone = rs.getString("artista_telefone");
                artista = new Artista(nome, email, senha, endereco, telefone);
                System.out.println("Artista autenticado: " + artista.getArtistaEmail());
            } else {
                System.out.println("Nenhum artista encontrado com o email: " + email);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao autenticar artista: " + e.getMessage(), e);
        }
        return artista;
    }
    

    /*public Artista[] getArtistas() {
        Artista[] artistas = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM connectart.artista");

            if (rs.next()) {
                rs.last();
                artistas = new Artista[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {new Artista(rs.getString("artista_nome"), rs.getString("artista_email"), rs.getString("artista_senha"), rs.getString("artista_endereco"), rs.getString("artista_telefone"));
                    artistas[i] = ;
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return artistas;
    }*/
}