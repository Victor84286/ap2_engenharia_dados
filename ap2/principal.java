import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.AutorDAO;
import dao.ConnectionFactory;
import dao.MusicaDAO;
import dao.ProdutorDAO;

import modelo.Autor;
import modelo.Categoria;
import modelo.Musica;
import modelo.Produtor;

public class principal {
    public static void main(String[] args) {
        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.recuperaConexao();

        AutorDAO autorDAO = new AutorDAO(connection);
        ProdutorDAO produtorDAO = new ProdutorDAO(connection);
        MusicaDAO musDAO = new MusicaDAO(connection);

        System.out.println(musDAO.visualizarUma("cu"));
    }
}
