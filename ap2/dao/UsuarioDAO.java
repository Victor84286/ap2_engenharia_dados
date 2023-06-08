package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import modelo.Usuario;

public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuario (nome, cpf, data_nascimento, numero_cartao) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, usuario.getNome());
                pstm.setInt(2, usuario.getCpf());
                pstm.setObject(3, usuario.getDataNascimento());
                pstm.setInt(4, usuario.getNumeroCartao());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario retrive(int cpf){

		Usuario usu = new Usuario(null, cpf, null, cpf);

        try {
			String sql = "SELECT nome, cpf, data_nascimento, numero_cartao FROM usuario where cpf = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, cpf);
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    String nome = rst.getString("nome");
                    int cpf2 = rst.getInt("cpf");
                    LocalDate dataNascimento = rst.getObject("data_nascimento", LocalDate.class);
                    int numeroCartao = rst.getInt("numero_cartao");
                    usu = new Usuario(nome, cpf2, dataNascimento, numeroCartao);
                    return usu;
                }
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
        return usu;
    }


}