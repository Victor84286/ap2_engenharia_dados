package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Autor;
import modelo.Categoria;
import modelo.Musica;
import modelo.Produtor;

public class MusicaDAO {

    private Connection connection;

    public MusicaDAO(Connection connection) {
        this.connection = connection;
    }

    // create musica

    public void create(Musica musica) {
        try {
            String sql = "INSERT INTO musica (titulo, letra, data_lancamento, duracao, censura, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, musica.getTitulo());
                pstm.setString(2, musica.getLetra());
                pstm.setObject(3, musica.getDataLancamento());
                pstm.setInt(4, musica.getDuracao());
                pstm.setInt(5, musica.getCensura());
                pstm.setInt(6, musica.getCategoria().valor);

                pstm.execute();
                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        musica.setId(rst.getInt(1));
                        for (Autor autor : musica.getAutor()) {
                            try {
                            String sql2 = "INSERT INTO autor_has_musica (autor_id, musica_id) VALUES (?, ?)";

                            try (PreparedStatement pstm2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {

                                pstm2.setInt(1, autor.getId());
                                pstm2.setInt(2, musica.getId());
                                pstm2.execute();

                                try (ResultSet rst2 = pstm2.getGeneratedKeys()) {
                                    while (rst2.next()) {
                                        autor.setId(rst2.getInt(1));
                                    }
                                }
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        }
                        for (Produtor produtor : musica.getProdutor()) {
                            try {
                                String sql2 = "INSERT INTO produtor_has_musica (produtor_id, musica_id) VALUES (?, ?)";

                                try (PreparedStatement pstm2 = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {

                                    pstm2.setInt(1, produtor.getId());
                                    pstm2.setInt(2, musica.getId());
                                    pstm2.execute();

                                    try (ResultSet rst2 = pstm2.getGeneratedKeys()) {
                                        while (rst2.next()) {
                                            produtor.setId(rst2.getInt(1));
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

    // ver todas as musicas cadastradas

    public ArrayList<Musica> retriveAll() {

        ArrayList<Musica> musicas = new ArrayList<Musica>();

        try {
            String sql = "SELECT titulo, letra, data_lancamento, duracao, censura, id, categoria_id FROM musica";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    String titulo = rst.getString("titulo");
                    String letra = rst.getString("letra");
                    Date data_lancamento = rst.getDate("data_lancamento");
                    int duracao = rst.getInt("duracao");
                    int censura = rst.getInt("censura");
                    Categoria categoria = Categoria.values()[rst.getInt("categoria_id")];
                    Musica p = new Musica(titulo, letra, id, data_lancamento, duracao, censura, categoria);

                    musicas.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return musicas;
    }

    // visualizar uma musica

    public String visualizarUma(String name){
        String musica = "";
		try {
			String sql = "SELECT titulo, data_lancamento, duracao, censura, id, categoria_id FROM musica WHERE titulo = ?";


			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, name);
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    int id = rst.getInt("id");
                    String titulo = rst.getString("titulo");
                    Date data_lancamento = rst.getDate("data_lancamento");
                    int duracao = rst.getInt("duracao");
                    int censura = rst.getInt("censura");
                    Categoria categoria = Categoria.values()[rst.getInt("categoria_id")];
                    Musica m = new Musica(titulo, id, data_lancamento, duracao, censura, categoria);
                    musica = m.visualizarUma();
                }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        return musica;
    }

    // escutar uma musica

    public String escutar(String name){
        String musica = "";
		try {
			String sql = "SELECT letra FROM musica WHERE titulo = ?";


			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, name);
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    String letra = rst.getString("letra");
                    Musica m = new Musica(letra);
                    musica = m.escutar();
                }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        return musica;
    }

    // retrive a partir de produtores

    public ArrayList<Musica> retriveAllProdutor(String nome_produtor) {

        ArrayList<Musica> musicas = new ArrayList<Musica>();

        try {
            String sql = "SELECT m.titulo, m.letra, m.data_lancamento, m.duracao, m.censura, m.id, m.categoria_id FROM musica as m INNER JOIN produtor_has_musica AS p_m ON m.id=p_m.musica_id INNER JOIN produtor AS p ON p_m.produtor_id=p.id WHERE p.nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, nome_produtor);
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    String titulo = rst.getString("titulo");
                    String letra = rst.getString("letra");
                    Date data_lancamento = rst.getDate("data_lancamento");
                    int duracao = rst.getInt("duracao");
                    int censura = rst.getInt("censura");
                    Categoria categoria = Categoria.values()[rst.getInt("categoria_id")];
                    Musica p = new Musica(titulo, letra, id, data_lancamento, duracao, censura, categoria);

                    musicas.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return musicas;
    }

    //retrive a partir de autores

    public ArrayList<Musica> retriveAllAutor(String nome_autor) {

        ArrayList<Musica> musicas = new ArrayList<Musica>();

        try {
            String sql = "SELECT m.titulo, m.letra, m.data_lancamento, m.duracao, m.censura, m.id, m.categoria_id FROM musica as m INNER JOIN autor_has_musica AS p_m ON m.id=p_m.musica_id INNER JOIN autor AS a ON p_m.autor_id=a.id WHERE a.nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, nome_autor);
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    String titulo = rst.getString("titulo");
                    String letra = rst.getString("letra");
                    Date data_lancamento = rst.getDate("data_lancamento");
                    int duracao = rst.getInt("duracao");
                    int censura = rst.getInt("censura");
                    Categoria categoria = Categoria.values()[rst.getInt("categoria_id")];
                    Musica p = new Musica(titulo, letra, id, data_lancamento, duracao, censura, categoria);

                    musicas.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return musicas;
    }

    // updates ===============
    // update titulo

    public void updateTitulo(String musica, String titulo){
        try {
			String sql = "UPDATE musica SET titulo = ? WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, titulo);
                pstm.setString(2, musica);

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

    // update data_lancamento

    public void updateDate(String musica, LocalDate data){
        try {
			String sql = "UPDATE musica SET data_lancamento = ? WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setObject(1, data);
                pstm.setString(2, musica);

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

    // update letra

    public void updateLetra(String musica, String letra){
        try {
			String sql = "UPDATE musica SET letra = ? WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, letra);
                pstm.setString(2, musica);

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

    // update duracao

    public void updateDuracao(String musica, int duracao){
        try {
			String sql = "UPDATE musica SET duracao = ? WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, duracao);
                pstm.setString(2, musica);

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

    // update censura

    public void updateCensura(String musica, int censura){
        try {
			String sql = "UPDATE musica SET censura = ? WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, censura);
                pstm.setString(2, musica);

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

    // update categoria

    public void updateCategoria(String nome, Categoria categoria){
        try {
			String sql = "UPDATE musica SET categoria = ? WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, categoria.valor);
                pstm.setString(2, nome);

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

    // deleta a partir do titulo

    public void delete(String nome){
        try {
			String sql = "DELETE FROM musica WHERE nome = ?";
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, nome);

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
