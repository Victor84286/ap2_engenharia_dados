package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Produtor;
import modelo.Musica;

public class Produtor_has_musicaDAO {

    private Connection connection;

    public Produtor_has_musicaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Produtor produtor, Musica musica) {
        try {
            String sql = "INSERT INTO produtor_has_musica (produtor_id, musica_id) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, produtor.getId());
                pstm.setInt(2, musica.getId());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        produtor.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
