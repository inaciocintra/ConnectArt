package com.connectart.service;

import com.connectart.dao.ProdutoDAO;
import com.connectart.models.Produto;
import com.google.gson.Gson;

import java.io.File;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import spark.Request;
import spark.Response;


public class ProdutoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    private final String UPLOAD_DIRECTORY = "C:/Users/glauc/Downloads/Connect Art/Codigo/Front-End/imagens/";
    ;

    public Object cadastrarProduto(Request request, Response response) {
        Gson gson = new Gson();
        Produto produto = new Produto();
        String imageName = ""; // Nome do arquivo, em vez de imagePath
    
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request.raw());
            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest(request.raw());
    
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        switch (item.getFieldName()) {
                            case "produtoNome":
                                produto.setProdutoNome(item.getString());
                                break;
                            case "produtoPreco":
                                produto.setProdutoPreco(Double.parseDouble(item.getString()));
                                break;
                            case "produtoDescricao":
                                produto.setProdutoDescricao(item.getString());
                                break;
                            case "artistaEmail":
                                produto.setArtistaEmail(item.getString());
                                break;
                        }
                    } else {
                        String fileName = new File(item.getName()).getName();
                        imageName = fileName; // Salvar apenas o nome do arquivo
                        String filePath = UPLOAD_DIRECTORY + fileName;
                        File uploadedFile = new File(filePath);
                        item.write(uploadedFile);
                    }
                }
            }
    
            produto.setImagePath(imageName); // Salvar apenas o nome do arquivo no produto
            int produtoId = produtoDAO.inserirProduto(produto);
    
            if (produtoId != -1) {
                produto.setProdutoId(produtoId);
                response.status(201); // Created
                return gson.toJson(produto);
            } else {
                response.status(500); // Internal Server Error
                return "Erro ao cadastrar produto";
            }
        } catch (Exception e) {
            response.status(500); // Internal Server Error
            return "Erro ao processar o upload de imagem";
        }
    }

    public Object listarProdutos(Request request, Response response) {
        Gson gson = new Gson();
        List<Produto> produtos = produtoDAO.listarProdutos();
        return gson.toJson(produtos);
    }
    public Object listarProdutosPorEmailArtista(Request request, Response response) {
        String artistaEmail = request.params(":email");
        List<Produto> produtos = produtoDAO.listarProdutosPorEmailArtista(artistaEmail);
        if (produtos != null && !produtos.isEmpty()) {
            response.status(200); // OK
            response.type("application/json");
            return new Gson().toJson(produtos);
        } else {
            response.status(404); // Not Found
            return new Gson().toJson(Collections.singletonMap("message", "Nenhum produto encontrado para este artista"));
        }
    }
    
    // Método para excluir um produto pelo ID
    
    public Object excluirProduto(Request request, Response response) {
        try {
            int produto_Id = Integer.parseInt(request.params(":id"));

            boolean excluido = produtoDAO.excluirProduto(produto_Id);
            if (excluido) {
                response.status(200); // OK
                response.type("application/json");
                return "Produto excluído com sucesso";
            } else {
                response.status(404); // Not Found (Produto não encontrado)
                return "Produto não encontrado";
            }
        } catch (NumberFormatException e) {
            response.status(400); // Bad Request
            return "ID inválido";
        } catch (Exception e) {
            e.printStackTrace();
            response.status(500); // Internal Server Error
            return "Erro ao excluir produto";
        }
    }
    public Object deletarProduto(Request request, Response response) {
        Gson gson = new Gson();
        int produtoId = Integer.parseInt(request.params(":id"));
        
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> payload = gson.fromJson(request.body(), type);
        String artistaEmail = (String) payload.get("artistaEmail");
        
        boolean deletado = produtoDAO.deletarProduto(produtoId, artistaEmail);
        if (deletado) {
            response.status(200); // OK
            return gson.toJson(Collections.singletonMap("success", true));
        } else {
            response.status(404); // Not Found
            return gson.toJson(Collections.singletonMap("success", false));
        }
    }

    // Método para autenticar um produto pelo ID
    public Object autenticarProduto(Request request, Response response) {
        try {
            int produtoId = Integer.parseInt(request.params(":id"));

            Produto produto = produtoDAO.autenticarProduto(produtoId);
            if (produto != null) {
                response.status(200); // OK
                response.type("application/json");
                return new Gson().toJson(produto);
            } else {
                response.status(404); // Not Found
                return "Produto não encontrado";
            }
        } catch (NumberFormatException e) {
            response.status(400); // Bad Request
            return "ID inválido";
        } catch (Exception e) {
            e.printStackTrace();
            response.status(500); // Internal Server Error
            return "Erro ao autenticar produto";
        }
    }
    public Object buscarProdutoPorId(Request request, Response response) {
        Gson gson = new Gson();
        int produtoId = Integer.parseInt(request.params(":id"));
        Produto produto = produtoDAO.buscarProdutoPorId(produtoId);
        if (produto != null) {
            return gson.toJson(produto);
        } else {
            response.status(404); // Not Found
            return "Produto não encontrado";
        }
    }
}
