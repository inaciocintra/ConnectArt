package com.connectart.service;
import com.connectart.dao.ArtistaDAO;
import com.connectart.models.Artista;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;



public class ArtistaService {
    private ArtistaDAO artistaDAO = new ArtistaDAO();

    public Object cadastrarArtista(Request request, Response response) {
        Gson gson = new Gson();
        Artista artista = gson.fromJson(request.body(), Artista.class);

        artistaDAO.inserirArtista(artista);
        response.status(201); // Created
        return gson.toJson(artista);
    }

    public boolean loginArtista(Request request, Response response) {
        try {
            Gson gson = new Gson();
            Artista artista = gson.fromJson(request.body(), Artista.class);
            System.out.println("Email recebido: " + artista.getArtistaEmail());
            System.out.println("Senha recebida: " + artista.getArtistaSenha());

            Artista aux = artistaDAO.autenticarArtista(artista.getArtistaEmail());
            if (aux != null) {
                System.out.println("Artista encontrado: " + aux.getArtistaEmail());
                if (artista.getArtistaSenha().equals(aux.getArtistaSenha())) {
                    // Se o artista for autenticado com sucesso
                    response.status(200); // OK
                    response.type("application/json");
                    return true;
                } else {
                    System.out.println("Senha incorreta.");
                }
            } else {
                System.out.println("Artista não encontrado.");
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

    public Object getArtistaByEmail(Request request, Response response) {
        String email = request.params(":email");
        Artista artista = artistaDAO.autenticarArtista(email);
        if (artista != null) {
            response.status(200);
            response.type("application/json");
            return new Gson().toJson(artista);
        } else {
            response.status(404); // Not Found
            return "Artista não encontrado";
        }
    }

    public boolean excluirArtista(Request request, Response response) {
        try {
            Gson gson = new Gson();
            Artista artista = gson.fromJson(request.body(), Artista.class);
            
            // Verificar se o artista existe antes de excluir
            if (artistaDAO.autenticarArtista(artista.getArtistaEmail()) != null) {
                // Excluir o artista
                boolean excluido = artistaDAO.excluirArtista(artista.getArtistaEmail());
                
                if (excluido) {
                    response.status(200); // OK
                    response.type("application/json");
                    return true;
                } else {
                    response.status(500); // Internal Server Error
                    return false;
                }
            } else {
                response.status(404); // Not Found (Artista não encontrado)
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.status(500); // Internal Server Error
            return false;
        }
    }
    
}
