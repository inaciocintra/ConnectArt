package com.connectart.models;

public class AvalCliente {
    private int avalClienteId;  // Atributo correspondente a aval_cliente_id na tabela
    private int avalClientePontuacao;  // Atributo correspondente a aval_cliente_pontuacao na tabela
    private String avalClienteComentario;  // Atributo correspondente a aval_cliente_comentario na tabela
    private int avalClienteArtista;  // Atributo correspondente a aval_cliente_artista na tabela
    private int avalClienteCliente;  // Atributo correspondente a aval_cliente_cliente na tabela

    // Construtor padrão (sem argumentos)
    public AvalCliente() {
        this.avalClienteId = -1;
        this.avalClientePontuacao = 0;
        this.avalClienteComentario = "";
        this.avalClienteArtista = -1;
        this.avalClienteCliente = -1;
    }

    // Construtor com argumentos
    public AvalCliente(int avalClienteId, int avalClientePontuacao, String avalClienteComentario, int avalClienteArtista, int avalClienteCliente) {
        this.avalClienteId = avalClienteId;
        this.avalClientePontuacao = avalClientePontuacao;
        this.avalClienteComentario = avalClienteComentario;
        this.avalClienteArtista = avalClienteArtista;
        this.avalClienteCliente = avalClienteCliente;
    }

    // Métodos getter e setter para os atributos

    public int getAvalClienteId() {
        return avalClienteId;
    }

    public void setAvalClienteId(int avalClienteId) {
        this.avalClienteId = avalClienteId;
    }

    public int getAvalClientePontuacao() {
        return avalClientePontuacao;
    }

    public void setAvalClientePontuacao(int avalClientePontuacao) {
        this.avalClientePontuacao = avalClientePontuacao;
    }

    public String getAvalClienteComentario() {
        return avalClienteComentario;
    }

    public void setAvalClienteComentario(String avalClienteComentario) {
        this.avalClienteComentario = avalClienteComentario;
    }

    public int getAvalClienteArtista() {
        return avalClienteArtista;
    }

    public void setAvalClienteArtista(int avalClienteArtista) {
        this.avalClienteArtista = avalClienteArtista;
    }

    public int getAvalClienteCliente() {
        return avalClienteCliente;
    }

    public void setAvalClienteCliente(int avalClienteCliente) {
        this.avalClienteCliente = avalClienteCliente;
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "AvalCliente [avalClienteId=" + avalClienteId + ", avalClientePontuacao=" + avalClientePontuacao + ", avalClienteComentario=" + avalClienteComentario + ", avalClienteArtista=" + avalClienteArtista + ", avalClienteCliente=" + avalClienteCliente + "]";
    }
}
