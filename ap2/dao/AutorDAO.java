package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Autor;

public class AutorDAO {

    private Connection connection;

    public AutorDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Autor autor) {
        try {
            String sql = "INSERT INTO autor (nome) VALUES (?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, autor.getNome());


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

    public ArrayList<Autor> retriveAll(){

        ArrayList<Autor> autors = new ArrayList<Autor>();

		try {
			String sql = "SELECT id, nome FROM autor";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    Autor a = new Autor(id, nome);
                    autors.add(a);
                }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        return autors;
    }

    public ArrayList<Autor> retriveSpecific(String name){
        ArrayList<Autor> autors = new ArrayList<Autor>();
		try {
			String sql = "SELECT nome, id FROM autor WHERE nome = ?";


			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, name);
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    Autor a = new Autor(id, nome);
                    autors.add(a);
                }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        return autors;
    }

    public void update(String nome, String name){
        try {
			String sql = "UPDATE autor SET nome = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, name);
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

    public void delete(String autor){
        try {
			String sql = "DELETE FROM autor WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, autor);

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
