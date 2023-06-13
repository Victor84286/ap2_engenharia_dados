package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Categoria;
import modelo.Musica;
import modelo.Playlist;
import modelo.Usuario;

public class PlaylistDAO {

    private Connection connection;

    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Playlist playlist) {
        try {
            String sql = "INSERT INTO playlist (titulo, visibilidade, id, categoria_id, usuario_cpf) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, playlist.getTitulo());
                pstm.setInt(2, playlist.getVisibiliade());
                pstm.setInt(3, playlist.getId());
                pstm.setInt(4, playlist.getCategoria().valor);
                pstm.setInt(5, playlist.getUsuario().getCpf());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        playlist.setId(rst.getInt(3));
                        for (Musica musica : playlist.getMusicas()) {
                            MusicaDAO mdao = new MusicaDAO(connection);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Playlist> retrivePlaylist(){

        ArrayList<Playlist> playlist = new ArrayList<Playlist>();
        Playlist ultima = null;
        try {

            String sql = "SELECT p.id, p.nome, p.cpf, p.data_nascimento, p.idade, t.id, t.tipo, t.codigo_pais, t.codigo_area, t.numero "
                    + "FROM pessoa AS p "
                    + "INNER JOIN telefone AS t ON p.id = t.fk_pessoa";


            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultima == null || ultima.getId() != rst.getInt(3)) {
                            String p_titulo = rst.getString("categoria_id");
                            int visibilidade = rst.getInt("categoria_id");
                            int p_id = rst.getInt("categoria_id");
                            Categoria categoria_id = Categoria.values()[rst.getInt("categoria_id")]
                            Usuario usuario_cpf = rst.getObject(5, Usuario.class);
                            Playlist play = new Playlist(titulo, visibilidade, id, categoria_id, usuario_cpf);
                            playlist.add(play);
                            ultima = play;
                        }

                        String m_titulo = rst.getString("categoria_id");
                        String letra = rst.getString(7);
                        LocalDate data_lancamento = LocalDate.values()[rst.getObject(8)];
                        int duracao = rst.getInt(9);
                        int censura = rst.getInt(10);
                        int m_id = rst.getInt(11);
                        Categoria m_categoria = Categoria.values()[rst.getInt(12)];
                        Musica mus = new Musica(m_titulo, letra, data_lancamento, duracao, censura, m_id, m_categoria);
                        ultima.addMusica(mus);
                    }
                }
                return playlist;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateTitulo(String playlist, String titulo){
        try {
			String sql = "UPDATE playlist SET titulo = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, titulo);
                pstm.setString(2, playlist);

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateVisibilidade(String playlist, int visibilidade){
        try {
			String sql = "UPDATE playlist SET visibilidade = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, visibilidade);
                pstm.setString(2, playlist);

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        try {
			String sql = "DELETE FROM playlist WHERE id = ?";
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, id);

                pstm.execute();
                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}