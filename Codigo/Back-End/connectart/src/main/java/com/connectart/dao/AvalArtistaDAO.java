

    package com.connectart.dao;
    import java.sql.SQLException;
    import java.sql.Statement;
    
    import com.connectart.models.AvalArtista;
    
    public class AvalArtistaDAO extends DAO {
    
        public AvalArtistaDAO() {
            super();
            conectar();
        }
    
        public boolean inserirAvalArtista(AvalArtista avalArtista) {
            boolean status = false;
            try {
                Statement st = conexao.createStatement();
                String sql = "INSERT INTO connectart.avalartista (aval_artista_pontuacao, aval_artista_comentario, aval_artista_cliente) "
                           + "VALUES (" + avalArtista.getAvalArtistaPontuacao() + ", '" + avalArtista.getAvalArtistaComentario() + "', " + avalArtista.getAvalArtistaCliente() +  ");";
                st.executeUpdate(sql);
                st.close();
                status = true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return status;
        }
    
        /*public AvalArtista[] getAvalArtistas() {
             AvalArtista[] avalArtistas = null;
     
             try {
                 Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 ResultSet rs = st.executeQuery("SELECT * FROM connectart.avalartista");
     
                 if (rs.next()) {
                     rs.last();
                     avalArtistas = new AvalArtista[rs.getRow()];
                     rs.beforeFirst();
     
                     for (int i = 0; rs.next(); i++) {
                         avalArtistas[i] = new AvalArtista(rs.getInt("aval_artista_id"), rs.getInt("aval_artista_pontuacao"), rs.getString("aval_artista_comentario"), rs.getInt("aval_artista_cliente"), rs.getInt("aval_artista_artista"));
                     }
                 }
                 st.close();
             } catch (SQLException e) {
                 System.err.println(e.getMessage());
             }
             return avalArtistas;
         }*/

        public boolean atualizarAvalArtista(AvalArtista avalArtista) {
            boolean status = false;
            try {
                Statement st = conexao.createStatement();
                String sql = "UPDATE connectart.avalartista SET aval_artista_pontuacao = " + avalArtista.getAvalArtistaPontuacao() + ", aval_artista_comentario = '" + avalArtista.getAvalArtistaComentario() + " WHERE aval_artista_cliente = " + avalArtista.getAvalArtistaCliente();
                st.executeUpdate(sql);
                st.close();
                status = true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return status;
        }
    
        public boolean excluirAvalArtista(int avalArtistaId) {
            boolean status = false;
            try {
                Statement st = conexao.createStatement();
                String sql = "DELETE FROM connectart.avalartista WHERE aval_artista_id = " + avalArtistaId;
                st.executeUpdate(sql);
                st.close();
                status = true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return status;
        }
    
    }
    