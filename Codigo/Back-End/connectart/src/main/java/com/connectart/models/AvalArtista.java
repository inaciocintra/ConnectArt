package com.connectart.models;

public class AvalArtista {
     
    private int avalArtistaPontuacao;  // Atributo correspondente a aval_artista_pontuacao na tabela
    private String avalArtistaComentario;  // Atributo correspondente a aval_artista_comentario na tabela
    private int avalArtistaCliente;  // Atributo correspondente a aval_artista_cliente na tabela
    private int avalArtistaArtista;  // Atributo correspondente a aval_artista_artista na tabela

    // Construtor padrão (sem argumentos)
    public AvalArtista() {
        
        this.avalArtistaPontuacao = 0;
        this.avalArtistaComentario = "";
        this.avalArtistaCliente = -1;
        this.avalArtistaArtista = -1;
    }

    // Construtor com argumentos
    public AvalArtista( int avalArtistaPontuacao, String avalArtistaComentario, int avalArtistaCliente, int avalArtistaArtista) {
        
        this.avalArtistaPontuacao = avalArtistaPontuacao;
        this.avalArtistaComentario = avalArtistaComentario;
        this.avalArtistaCliente = avalArtistaCliente;
        this.avalArtistaArtista = avalArtistaArtista;
    }

    // Métodos getter e setter para os atributos

   

    public int getAvalArtistaPontuacao() {
        return avalArtistaPontuacao;
    }

    public void setAvalArtistaPontuacao(int avalArtistaPontuacao) {
        this.avalArtistaPontuacao = avalArtistaPontuacao;
    }

    public String getAvalArtistaComentario() {
        return avalArtistaComentario;
    }

    public void setAvalArtistaComentario(String avalArtistaComentario) {
        this.avalArtistaComentario = avalArtistaComentario;
    }

    public int getAvalArtistaCliente() {
        return avalArtistaCliente;
    }

    public void setAvalArtistaCliente(int avalArtistaCliente) {
        this.avalArtistaCliente = avalArtistaCliente;
    }

    public int getAvalArtistaArtista() {
        return avalArtistaArtista;
    }

    public void setAvalArtistaArtista(int avalArtistaArtista) {
        this.avalArtistaArtista = avalArtistaArtista;
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "AvalArtista [avalArtistaPontuacao=" + avalArtistaPontuacao + ", avalArtistaComentario=" + avalArtistaComentario + ", avalArtistaCliente=" + avalArtistaCliente + ", avalArtistaArtista=" + avalArtistaArtista + "]";
    }
}
