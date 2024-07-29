package com.connectart.models;

public class Artista {
    
    private String artistaNome;  
    private String artistaEmail;  
    private String artistaSenha;  
    private String artistaEndereco;  
    private String artistaTelefone;  

    // Construtor padrão (sem argumentos)
    public Artista() {
        
       
    }

    public Artista(String artistaNome, String artistaEmail, String artistaSenha, String artistaEndereco,String artistaTelefone) {
        
        this.artistaNome = artistaNome;
        this.artistaEmail = artistaEmail;
        this.artistaSenha = artistaSenha;
        this.artistaEndereco = artistaEndereco;
        this.artistaTelefone = artistaTelefone;
    }
   
    /*
    // Métodos getter e setter para os atributos

    public int getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(int artistaId) {
        this.artistaId = artistaId;
    }*/

    public String getArtistaNome() {
        return artistaNome;
    }

    public void setArtistaNome(String artistaNome) {
        this.artistaNome = artistaNome;
    }

    public String getArtistaEmail() {
        return artistaEmail;
    }

    public void setArtistaEmail(String artistaEmail) {
        this.artistaEmail = artistaEmail;
    }

    public String getArtistaSenha() {
        return artistaSenha;
    }

    public void setArtistaSenha(String artistaSenha) {
        this.artistaSenha = artistaSenha;
    }

    public String getArtistaEndereco() {
        return artistaEndereco;
    }

    public void setArtistaEndereco(String artistaEndereco) {
        this.artistaEndereco = artistaEndereco;
    }

    /*public String getArtistaDescricao() {
        return artistaDescricao;
    }

    public void setArtistaDescricao(String artistaDescricao) {
        this.artistaDescricao = artistaDescricao;
    }*/

    public String getArtistaTelefone() {
        return artistaTelefone;
    }

    public void setArtistaTelefone(String artistaTelefone) {
        this.artistaTelefone = artistaTelefone;
    }

    // Método toString para representar o objeto como uma string
    @Override
    /*public String toString() {
        return "Artista [artistaId=" + artistaId + ", artistaNome=" + artistaNome + ", artistaEmail=" + artistaEmail + ", artistaSenha=" + artistaSenha + ", artistaEndereco=" + artistaEndereco + ", artistaDescricao=" + artistaDescricao + ", artistaTelefone=" + artistaTelefone + "]";
    }*/
    public String toString() {
        return "Artista [artistaNome=" + artistaNome + ", artistaEmail=" + artistaEmail + ", artistaSenha=" + artistaSenha + ", artistaEndereco=" + artistaEndereco +  ", artistaTelefone=" + artistaTelefone + "]";
    }
}
