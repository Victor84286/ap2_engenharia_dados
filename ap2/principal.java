import java.sql.Connection;
import java.util.Scanner;

import dao.AutorDAO;
import dao.ConnectionFactory;
import dao.MusicaDAO;
import dao.PlaylistDAO;
import dao.ProdutorDAO;
import dao.UsuarioDAO;

import modelo.Autor;

public class principal {
    public static void main(String[] args) {
        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.recuperaConexao();

        Scanner sc = new Scanner(System.in);

        AutorDAO autorDAO = new AutorDAO(connection);
        MusicaDAO musDAO = new MusicaDAO(connection);
        PlaylistDAO playlDAO = new PlaylistDAO(connection);
        ProdutorDAO produtorDAO = new ProdutorDAO(connection);
        UsuarioDAO usuDAO = new UsuarioDAO(connection);

        while(true) {
            System.out.println("Escreva o que deseja fazer\n(Criar, Buscar, fazer update ou deletar um autor, digite 1)\n(Criar, Buscar, fazer update ou deletar um produtor, digite 2)\n(Criar, Buscar, fazer update ou deletar uma musica, digite 3)\n(Criar, Buscar, fazer update ou deletar uma playlist, digite 4)\n(Criar, Buscar, fazer update ou deletar um usuario, digite 5)");
            int escolha = sc.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Escreva o que deseja fazer\n(Criar um autor, digite 1)\n" +
                    "(Buscar um autor, digite 2)\n(Fazer update em um autor, digite 3)\n(Deletar um autor, digite 4)");
                    escolha = sc.nextInt();
                    switch (escolha) {
                        case 1:
                            createAutor(autorDAO);
                            break;

                        case 2:
                            System.out.println("Escreva o nome");
                            String nome = sc.nextLine();
                            Autor aut = new Autor(nome);
                            autorDAO.create(aut);
                            break;
                    }
            }
        }
    }

    static void createAutor(AutorDAO autorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome");
        String nome = sc.nextLine();
        Autor aut = new Autor(nome);
        autorDAO.create(aut);
        sc.close();
    }

    static void buscaAutor(AutorDAO autorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome que quer buscar");
        String nome = sc.next();
        System.out.println(autorDAO.retriveSpecific(nome));
        sc.close();
    }

    static void updateAutor(AutorDAO autorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome do autor que quer alterar");
        String nome = sc.nextLine();
        System.out.println("Escreva o nome do autor para qual quer ser alterado");
        String name = sc.nextLine();
        autorDAO.update(nome, name);
        System.out.println("Alterado de ${nome} para ${name}");
        sc.close();
    }
}
