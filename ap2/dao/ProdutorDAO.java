package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Produtor;

public class ProdutorDAO {

    private static Connection connection;

    public ProdutorDAO(Connection connection) {
        ProdutorDAO.connection = connection;
    }

    public void create(Produtor produtor) {
        try {
            String sql = "INSERT INTO produtor (nome) VALUES (?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, produtor.getNome());
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

    public ArrayList<Produtor> retriveAll() {

        ArrayList<Produtor> produtores = new ArrayList<Produtor>();

        try {
            String sql = "SELECT id, nome FROM produtor";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()) {
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    Produtor p = new Produtor(id, nome);
                    produtores.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produtores;
    }

    public ArrayList<Produtor> retriveSpecific(String name){
        ArrayList<Produtor> produtores = new ArrayList<Produtor>();
		try {
			String sql = "SELECT DISTINCT nome, id FROM produtor WHERE nome = ?";


			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, name);
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    Produtor p = new Produtor(id, nome);
                    produtores.add(p);
                }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        return produtores;
    }

    public void update(Produtor produtor, String name){
        try {
			String sql = "UPDATE produtor SET nome = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, name);
                pstm.setInt(2, produtor.getId());

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

    public void delete(Produtor produtor){
        try {
			String sql = "DELETE FROM produtor WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, produtor.getId());

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
