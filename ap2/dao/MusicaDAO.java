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
                            Autor_has_musicaDAO tdao = new Autor_has_musicaDAO(connection);
                            tdao.create(autor, musica);
                        }
                        for (Produtor produtor : musica.getProdutor()) {
                            Produtor_has_musicaDAO tdao = new Produtor_has_musicaDAO(connection);
                            tdao.create(produtor, musica);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
}
