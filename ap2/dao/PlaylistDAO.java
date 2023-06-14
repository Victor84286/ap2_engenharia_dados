package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import modelo.Categoria;
import modelo.Musica;
import modelo.Playlist;

public class PlaylistDAO {

    private Connection connection;

    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }

    // criar playlist

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
                        playlist.setId(rst.getInt(1));
                        for (Musica musica : playlist.getMusicas()) {
                            try {
                            String sql2 = "INSERT INTO musica_has_playlist (playlist_id, musica_id) VALUES (?, ?)";

                            try (PreparedStatement pstm2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                                pstm2.setInt(1, playlist.getId());
                                pstm2.setInt(2, musica.getId());
                                pstm2.execute();

                                try (ResultSet rst2 = pstm2.getGeneratedKeys()) {
                                    while (rst2.next()) {
                                        musica.setId(rst2.getInt(1));
                                    }
                                }
                            }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
      } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // ouvir playlist

    public ArrayList<Musica> ouvirPlaylist(String nome){

        ArrayList<Musica> playlist = new ArrayList<Musica>();

		try {
			String sql = "SELECT m.titulo, m.letra"
                        + "FROM musica as m"
                        + "INNER JOIN musica_has_playlist AS m_p ON m_p.m_id = m.id"
                        + "INNER JOIN playlist AS p ON m_p.playlist_id = p.id"
                        + " WHERE p.titulo = ?";


			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, nome);
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    String titulo = rst.getString("titulo");
                    String letra = rst.getString("letra");
                    Musica musica = new Musica(titulo, letra);
                    playlist.add(musica);
                }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        return playlist;
    }

    // buscar playlist pelo titulo

    public Playlist buscarPlaylistTitulo(String name){
		try {
			String sql = "SELECT id, titulo, data_criacao, visibilidade, categoria_id, usuario_cpf FROM playlist WHERE titulo = ?";


			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, name);
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    int id = rst.getInt("id");
                    String titulo = rst.getString("titulo");
                    Date data_criacao = rst.getDate("data_lancamento");
                    int visibilidade = rst.getInt("visibilidade");
                    Categoria categoria = Categoria.values()[rst.getInt("categoria_id")];
                    int usuario = rst.getInt("usuario_cpf");
                    Playlist play = new Playlist(id, titulo, data_criacao, visibilidade, categoria, usuario);
                    return play;
                }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        Playlist play = new Playlist();
        return play;
    }

    // buscar playlists de um usuario

    public ArrayList<Playlist> mostrarPlaylists(int identificador){

        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

		try {
			String sql = "SELECT playlist.titulo from playlist where usuario_cpf = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, identificador);
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    String titulo = rst.getString("titulo");
                    Playlist playlist = new Playlist(titulo);
                    playlists.add(playlist);
                }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        return playlists;
    }

    // buscar playlists criadas em um ano

    public ArrayList<Playlist> buscarPlaylistsAno(int ano){

        ArrayList<Playlist> playlists = new ArrayList<Playlist>();

		try {
			String sql = "SELECT playlist.titulo from playlist where year(data_criacao) = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, ano);
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    String titulo = rst.getString("titulo");
                    Playlist playlist = new Playlist(titulo);
                    playlists.add(playlist);
                }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        return playlists;
    }

    // updates

    public void updateTitulo(String playlist, String titulo){
        try {
			String sql = "UPDATE playlist SET titulo = ? WHERE titulo = ?";

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
			String sql = "UPDATE playlist SET visibilidade = ? WHERE titulo = ?";

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

    // delete

    public void delete(int id){
        try {
			String sql = "DELETE FROM playlist WHERE id = ?";
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setInt(1, id);

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