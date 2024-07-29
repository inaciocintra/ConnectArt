package com.connectart.service;
import java.util.Collections;

import com.connectart.dao.ClienteDAO;
import com.connectart.models.Cliente;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ClienteService {

    private ClienteDAO clienteDAO = new ClienteDAO();

    public Object cadastrarCliente(Request request, Response response) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(request.body(), Cliente.class);

        clienteDAO.inserirCliente(cliente);
        response.status(201); // Created
        return gson.toJson(cliente);
    }

    public boolean loginCliente(Request request, Response response) {
        try {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(request.body(), Cliente.class);
            System.out.println("Email recebido: " + cliente.getClienteEmail());
            System.out.println("Senha recebida: " + cliente.getClienteSenha());

            Cliente aux = clienteDAO.autenticarCliente(cliente.getClienteEmail());
            if (aux != null) {
                System.out.println("Usuário encontrado: " + aux.getClienteEmail());
                if (cliente.getClienteSenha().equals(aux.getClienteSenha())) {
                    // Se o usuário for autenticado com sucesso
                    response.status(200); // OK
                    response.type("application/json");
                    return true;
                } else {
                    System.out.println("Senha incorreta.");
                }
            } else {
                System.out.println("Usuário não encontrado.");
            }
            // Se as credenciais estiverem incorretas
            response.status(401); // Unauthorized
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro durante o login: " + e.getMessage());
            response.status(500); // Internal Server Error
            return false;
        }
    }

    public Object getClienteByEmail(Request request, Response response) {
        String email = request.params(":email");
        Cliente cliente = clienteDAO.autenticarCliente(email);
        if (cliente != null) {
            response.status(200);
            response.type("application/json");
            return new Gson().toJson(cliente);
        } else {
            response.status(404); // Not Found
            return "Cliente não encontrado";
        }
    }

    public boolean excluirCliente(Request request, Response response) {
        try {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(request.body(), Cliente.class);
            
            // Verificar se o cliente existe antes de excluir
            if (clienteDAO.autenticarCliente(cliente.getClienteEmail()) != null) {
                // Excluir o cliente
                boolean excluido = clienteDAO.excluirCliente(cliente.getClienteEmail());
                
                if (excluido) {
                    response.status(200); // OK
                    response.type("application/json");
                    return true;
                } else {
                    response.status(500); // Internal Server Error
                    return false;
                }
            } else {
                response.status(404); // Not Found (Cliente não encontrado)
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.status(500); // Internal Server Error
            return false;
        }
    }
    
    
}
