package com.connectart.models;

public class Produto {
    private int produtoId;
    private String produtoNome;  
    private double produtoPreco;  
    private String produtoDescricao;  
    private String artistaEmail;
    private String imagePath;
    // Construtor padrão (sem argumentos)
    public Produto() {
       
    }

    // Construtor com argumentos
    public Produto(String produtoNome, double produtoPreco, String produtoDescricao, String artistaEmail, String imagePath) {
        
        this.produtoNome = produtoNome;
        this.produtoPreco = produtoPreco;
        this.produtoDescricao = produtoDescricao;
        this.artistaEmail = artistaEmail;
        this.imagePath = imagePath;
    }

    // Métodos getter e setter para os atributos

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public double getProdutoPreco() {
        return produtoPreco;
    }

    public void setProdutoPreco(double produtoPreco) {
        this.produtoPreco = produtoPreco;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }

    public String getArtistaEmail() {
        return artistaEmail;
    }

    public void setArtistaEmail(String artistaEmail) {
        this.artistaEmail = artistaEmail;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "Produto [produtoNome=" + produtoNome + ", produtoPreco=" + produtoPreco + ", produtoDescricao=" + produtoDescricao + "]";
    }
}
