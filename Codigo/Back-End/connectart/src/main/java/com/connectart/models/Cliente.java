package com.connectart.models;
public class Cliente {
    
    private String clienteNome;  
    private String clienteEmail;  
    private String clienteSenha;  
    

    // Construtor padrão (sem argumentos)
    public Cliente() {

    }

    public Cliente(String clienteNome, String clienteEmail, String clienteSenha) {
    
        this.clienteNome = clienteNome;
        this.clienteEmail = clienteEmail;
        this.clienteSenha = clienteSenha;
    }

    // Métodos getter e setter para os atributos

    /*public int getClienteId() {
        return clienteId;
    }*/

   /* public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }*/

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteSenha() {
        return clienteSenha;
    }

    public void setClienteSenha(String clienteSenha) {
        this.clienteSenha = clienteSenha;
    }

    

    // Método toString para representar o objeto como uma string
    @Override
    
    /*public String toString() {
        return "Cliente [clienteId=" + clienteId + ", clienteNome=" + clienteNome + ", clienteEmail=" + clienteEmail
                + ", clienteSenha=" + clienteSenha + ", clienteCompras=" + clienteCompras + ", clienteTelefone=" + clienteTelefone + "]";
    }*/

public String toString() {
    return "Cliente [clienteNome=" + clienteNome + ", clienteEmail=" + clienteEmail
            + ", clienteSenha=" + clienteSenha + "]";
}

}