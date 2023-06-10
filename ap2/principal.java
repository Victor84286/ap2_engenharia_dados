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

        Musica musica = new Musica("cu", "cucu", LocalDate.of(2000, 1, 01), 300, 16, Categoria.Country);

        Autor autor1 = new Autor("Victor");
        Autor autor2 = new Autor("Jean");
        Autor autor3 = new Autor("Eduardo");
        Autor autor4 = new Autor("Luciane");
        Autor autor5 = new Autor("Juliana");
        ArrayList<Autor> autores = new ArrayList<Autor>();
        autores.add(autor1);
        autores.add(autor2);
        autores.add(autor3);
        autores.add(autor4);
        autores.add(autor5);

        musica.setAutor(autores);

        AutorDAO autorDAO = new AutorDAO(connection);
        for(Autor autor : autores){

            autorDAO.create(autor);
        }

        Produtor produtor1 = new Produtor("Victor");
        Produtor produtor2 = new Produtor("Jean");
        Produtor produtor3 = new Produtor("Eduardo");
        Produtor produtor4 = new Produtor("Luciane");
        Produtor produtor5 = new Produtor("Juliana");
        ArrayList<Produtor> produtores = new ArrayList<Produtor>();
        produtores.add(produtor1);
        produtores.add(produtor2);
        produtores.add(produtor3);
        produtores.add(produtor4);
        produtores.add(produtor5);

        musica.setProdutor(produtores);

        ProdutorDAO produtorDAO = new ProdutorDAO(connection);
        for(Produtor produtor : produtores){

            produtorDAO.create(produtor);
        }

        MusicaDAO musDAO = new MusicaDAO(connection);

        musDAO.create(musica);
    }
}
