package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Autor;
import modelo.Musica;

public class Autor_has_musicaDAO {

    private Connection connection;

    public Autor_has_musicaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Autor autor, Musica musica) {
        try {
            String sql = "INSERT INTO autor_has_musica (autor_id, musica_id) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, autor.getId());
                pstm.setInt(2, musica.getId());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        autor.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
