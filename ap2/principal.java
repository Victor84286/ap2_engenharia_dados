import java.sql.Connection;
import java.time.LocalDate;

import dao.ConnectionFactory;
import dao.MusicaDAO;
import modelo.Musica;

public class principal {
    public static void main(String[] args) {
        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.recuperaConexao();

        Musica music = new Musica("cu", "cucu", LocalDate.of(2000, 1, 01), 300, 16, 3);

        MusicaDAO musDAO = new MusicaDAO(connection);

        musDAO.create(music);
    }
}
