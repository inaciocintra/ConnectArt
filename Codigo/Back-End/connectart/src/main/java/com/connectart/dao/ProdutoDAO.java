package com.connectart.dao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.connectart.models.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends DAO {

    public ProdutoDAO() {
        super();
        conectar();    
    }

    public int inserirProduto(Produto produto) {
        int produtoId = -1;
        try {
            String sql = "INSERT INTO connectart.produto (produto_nome, produto_preco, produto_descricao, artista_email, image_path) "
                       + "VALUES (?, ?, ?, ?, ?) RETURNING produto_id";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, produto.getProdutoNome());
            pst.setDouble(2, produto.getProdutoPreco());
            pst.setString(3, produto.getProdutoDescricao());
            pst.setString(4, produto.getArtistaEmail());
            pst.setString(5, produto.getImagePath());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                produtoId = rs.getInt(1);
            }
            rs.close();
            pst.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return produtoId;
    }

     public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM connectart.produto";
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setProdutoId(rs.getInt("produto_id"));
                produto.setProdutoNome(rs.getString("produto_nome"));
                produto.setProdutoPreco(rs.getDouble("produto_preco"));
                produto.setProdutoDescricao(rs.getString("produto_descricao"));
                produto.setArtistaEmail(rs.getString("artista_email"));
                produto.setImagePath(rs.getString("image_path"));
                produtos.add(produto);
            }
            rs.close();
            pst.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return produtos;
    }

    public boolean excluirProduto(int id) {
        try {
            String sql = "DELETE FROM connectart.produto WHERE produto_id = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            st.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deletarProduto(int produtoId, String artistaEmail) {
        boolean deletado = false;
        try {
            String sql = "DELETE FROM connectart.produto WHERE produto_id = ? AND artista_email = ?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, produtoId);
            pst.setString(2, artistaEmail);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                deletado = true;
            }
            pst.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return deletado;
    }

    public Produto autenticarProduto(int id) {
        Produto produto = null;
        try {
            String sql = "SELECT * FROM connectart.produto WHERE produto_id = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("produto_nome");
                double preco = rs.getDouble("produto_preco");
                String descricao = rs.getString("produto_descricao");
                String artistaEmail = rs.getString("artista_email");
                String imagePath = rs.getString("image_path");
                produto = new Produto(nome, preco, descricao, artistaEmail, imagePath);
                System.out.println("Produto autenticado: " + produto.getProdutoNome());
            } else {
                System.out.println("Nenhum produto encontrado com o id: " + id);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao autenticar produto: " + e.getMessage(), e);
        }
        return produto;
    }
    public Produto buscarProdutoPorId(int produtoId) {
        Produto produto = null;
        try {
            String sql = "SELECT * FROM connectart.produto WHERE produto_id = ?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, produtoId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setProdutoId(rs.getInt("produto_id"));
                produto.setProdutoNome(rs.getString("produto_nome"));
                produto.setProdutoPreco(rs.getDouble("produto_preco"));
                produto.setProdutoDescricao(rs.getString("produto_descricao"));
                produto.setArtistaEmail(rs.getString("artista_email"));
                produto.setImagePath(rs.getString("image_path"));
            }
            rs.close();
            pst.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return produto;
    }
    public List<Produto> listarProdutosPorEmailArtista(String email) {
        List<Produto> produtos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM connectart.produto WHERE artista_email = ?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setProdutoId(rs.getInt("produto_id"));
                produto.setProdutoNome(rs.getString("produto_nome"));
                produto.setProdutoPreco(rs.getDouble("produto_preco"));
                produto.setProdutoDescricao(rs.getString("produto_descricao"));
                produto.setArtistaEmail(rs.getString("artista_email"));
                produto.setImagePath(rs.getString("image_path"));
                produtos.add(produto);
            }
            rs.close();
            pst.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return produtos;
    }
}
