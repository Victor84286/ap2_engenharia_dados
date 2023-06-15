import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import dao.AutorDAO;
import dao.ConnectionFactory;
import dao.MusicaDAO;
import dao.PlaylistDAO;
import dao.ProdutorDAO;
import dao.UsuarioDAO;

import modelo.Autor;
import modelo.Categoria;
import modelo.Musica;
import modelo.Playlist;
import modelo.Produtor;
import modelo.Usuario;

public class Principal {
    public static void main(String[] args) {
        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.recuperaConexao();

        Scanner sc2 = new Scanner(System.in);

        AutorDAO autorDAO = new AutorDAO(connection);
        MusicaDAO musDAO = new MusicaDAO(connection);
        PlaylistDAO playlDAO = new PlaylistDAO(connection);
        ProdutorDAO produtorDAO = new ProdutorDAO(connection);
        UsuarioDAO usuDAO = new UsuarioDAO(connection);

        while(true) {
            System.out.println("Escreva o que deseja fazer\n(Criar, Buscar, fazer update ou deletar um autor, digite 1)\n(Criar, Buscar, fazer update ou deletar um produtor, digite 2)\n(Criar, Buscar, fazer update ou deletar uma musica, digite 3)\n(Criar, Buscar, fazer update ou deletar um usuario, digite 4)\n(Criar, Buscar, Fazer uma alteracao ou deletar uma playlist, fazendo login, digite 5)");
            int escolha = sc2.nextInt();
            switch (escolha) {
                case 1:
                    System.out.println("Escreva o que deseja fazer\n(Criar um autor, digite 1)\n(Buscar os autores cadastrados, digite 2)\n(Buscar um autor, digite 3)\n(Fazer update em um autor, digite 4)\n(Deletar um autor, digite 5)");
                    escolha = sc2.nextInt();
                    switch (escolha) {
                        case 1:
                            createAutor(autorDAO);
                            break;

                        case 2:
                            buscaAllAutor(autorDAO);
                            break;

                        case 3:
                            buscaAutor(autorDAO);
                            break;

                        case 4:
                            updateAutor(autorDAO);
                            break;

                        case 5:
                            deleteAutor(autorDAO);
                            break;

                        default:
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Escreva o que deseja fazer\n(Criar um produtor, digite 1)\n(Buscar os produtores cadastrados, digite 2)\n(Buscar um produtor, digite 3)\n(Fazer update em um produtor, digite 4)\n(Deletar um produtor, digite 5)");
                    escolha = sc2.nextInt();
                    switch (escolha) {
                        case 1:
                            createProdutor(produtorDAO);
                            break;

                        case 2:
                            buscaAllProdutor(produtorDAO);
                            break;

                        case 3:
                            buscaProdutor(produtorDAO);
                            break;

                        case 4:
                            updateProdutor(produtorDAO);
                            break;

                        case 5:
                            deleteProdutor(produtorDAO);
                            break;

                        default:
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Escreva o que deseja fazer\n(Criar uma musica, digite 1)\n(Buscar as musiccas cadastradas, digite 2)\n(Buscar uma musica, digite 3)\n(Escutar uma musica, digite 4)\n(Fazer update em uma musica, digite 5)\n(Deletar uma musica, digite 6)");
                    escolha = sc2.nextInt();
                    switch (escolha) {
                        case 1:
                            createMusica(musDAO, produtorDAO, autorDAO);
                            break;

                        case 2:
                            buscaAllMusica(musDAO);
                            break;

                        case 3:
                            System.out.println("Escreva o que deseja fazer\n(Buscar uma musica pelo titulo, digite 1)\n(Buscar uma musica pelo autor, digite 2)\n(Buscar uma musica pelo produtor, digite 3)");
                            escolha = sc2.nextInt();
                            switch (escolha) {
                                case 1:
                                    buscaMusica(musDAO);
                                    break;
                                case 2:
                                    buscaAutorMusica(musDAO);
                                    break;
                                case 3:
                                    buscaProdutorMusica(musDAO);
                                    break;
                            }
                            break;

                        case 4:
                            escutarMusica(musDAO);
                            break;

                        case 5:
                            updateMusica(musDAO);
                            break;

                        case 6:
                            deleteMusica(musDAO);
                            break;

                        default:
                            break;
                    }
                    break;

                case 4:
                    System.out.println("Escreva o que deseja fazer\n(Criar um usuario, digite 1)\n(Buscar um usuario, digite 2)\n(Fazer update em um usuario, digite 3)\n(Deletar um usuario, digite 4)");
                    escolha = sc2.nextInt();
                    switch (escolha) {
                        case 1:
                            createUsuario(usuDAO);
                            break;

                        case 2:
                            buscaUsuario(usuDAO);
                            break;

                        case 3:
                            updateProdutor(produtorDAO);
                            break;

                        case 4:
                            deleteProdutor(produtorDAO);
                            break;

                        default:
                            break;
                    }
                    break;

                case 5:
                    System.out.println("Fazer login como qual usuario?\n(Escreva o cpf)");
                    int cpf = sc2.nextInt();
                    Usuario usuario = usuDAO.retriveSpecific(cpf);
                    System.out.println("Escreva o que deseja fazer\n(Criar um playlist, digite 1)\n(Buscar um playlist, digite 3)\n(Fazer update em um playlist, digite 4)\n(Deletar um playlist, digite 5)");
                    escolha = sc2.nextInt();
                    switch (escolha) {
                        case 1:
                            createPlaylist(playlDAO, musDAO, produtorDAO, autorDAO, usuario);
                            break;

                        case 2:
                            ouvirPlaylist(playlDAO, usuario);
                            break;

                        case 3:
                            buscaPlaylist(playlDAO, usuario);
                            break;

                        case 4:
                            updatePlaylist(playlDAO, usuario);
                            break;

                        case 5:
                            deletePlaylist(playlDAO, usuario);
                            break;

                        default:
                            break;
                    }
                    break;
            }
        }
    }

//autores

    static void createAutor(AutorDAO autorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome");
        String nome = sc.nextLine();
        Autor aut = new Autor(nome);
        autorDAO.create(aut);
    }

    static void buscaAllAutor(AutorDAO autorDAO) {
        System.out.println(autorDAO.retriveAll());
    }

    static void buscaAutor(AutorDAO autorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome que quer buscar");
        String nome = sc.nextLine();
        System.out.println(autorDAO.retriveSpecific(nome));
    }

    static void updateAutor(AutorDAO autorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome do autor que quer alterar");
        String nome = sc.nextLine();
        System.out.println("Escreva o nome do autor para qual quer ser alterado");
        String name = sc.nextLine();
        autorDAO.update(nome, name);
    }

    static void deleteAutor(AutorDAO autorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome que quer deletar do anco de dados");
        String nome = sc.nextLine();
        autorDAO.delete(nome);
    }

// produtores

    static void createProdutor(ProdutorDAO produtorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome");
        String nome = sc.nextLine();
        Produtor aut = new Produtor(nome);
        produtorDAO.create(aut);
    }

    static void buscaAllProdutor(ProdutorDAO produtorDAO) {
        System.out.println(produtorDAO.retriveAll());
    }

    static void buscaProdutor(ProdutorDAO produtorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome que quer buscar");
        String nome = sc.nextLine();
        System.out.println(produtorDAO.retriveSpecific(nome));
    }

    static void updateProdutor(ProdutorDAO produtorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome do autor que quer alterar");
        String nome = sc.nextLine();
        System.out.println("Escreva o nome do autor para qual quer ser alterado");
        String name = sc.nextLine();
        produtorDAO.update(nome, name);
    }

    static void deleteProdutor(ProdutorDAO produtorDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome que quer deletar do anco de dados");
        String nome = sc.nextLine();
        produtorDAO.delete(nome);
    }

// Musicas

    static void createMusica(MusicaDAO musicaDAO, ProdutorDAO produtorDAO, AutorDAO autorDAO) {
        ArrayList<Produtor> listaProd = new ArrayList<Produtor>();
        ArrayList<Autor> listaAut = new ArrayList<Autor>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o titulo da musica");
        String titulo = sc.nextLine();
        System.out.println("Escreva a letra da musica");
        String letra = sc.nextLine();
        System.out.println("Escreva a data de lançamento da musica no modelo yyyy-mm-dd");
        LocalDate data = LocalDate.parse(sc.nextLine());
        System.out.println("Escreva a duração da musica");
        int duracao = sc.nextInt();
        System.out.println("Escreva a censura da musica");
        int censura = sc.nextInt();
        System.out.println("Escreva a categoria da musica\nAxe(1), Blues(2), Country(3), Eletr\u00F4nica(4), Forr\u00F3(5), Funk(6), Gospel(7), Hip_Hop(8), Jazz(9), MPB(10), M\u00FAsica_cl\u00E1ssica(11), Pagode(12), Pop(13), Rap(14), Reggae(15), Rock(16), Samba(17)");
        Categoria categoria = Categoria.values()[sc.nextInt()];
        Musica musica = new Musica(titulo, letra, data, duracao, censura, categoria);

        while(true){
            System.out.println("Escreva o nome do produtor\nNão escreva nada para atribuir Autores\n");
            String nome = sc.nextLine();
            if(nome.equals("")){
                break;
            }
            Produtor prod = new Produtor(nome);
            produtorDAO.create(prod);
            listaProd.add(prod);
        }
        musica.setProdutor(listaProd);

        while(true){
            System.out.println("Escreva o nome do autor\nNão escreva nada para parar");
            String nome = sc.nextLine();
            if(nome.equals("")){
                break;
            }
            Autor prod = new Autor(nome);
            autorDAO.create(prod);
            listaAut.add(prod);
        }
        musica.setAutor(listaAut);

        musicaDAO.create(musica);
    }

    static void buscaAllMusica(MusicaDAO musicaDAO) {
        System.out.println(musicaDAO.retriveAll());
    }

    static void buscaMusica(MusicaDAO musicaDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome que quer buscar\n");
        String nome = sc.nextLine();
        System.out.println(musicaDAO.visualizarUma(nome));
    }

    static void buscaAutorMusica(MusicaDAO musicaDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome do autor que quer buscar as musicas\n");
        String nome = sc.nextLine();
        System.out.println(musicaDAO.retriveAllAutor(nome));
    }

    static void buscaProdutorMusica(MusicaDAO musicaDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome do produtor que quer buscar as musicas\n");
        String nome = sc.nextLine();
        System.out.println(musicaDAO.retriveAllProdutor(nome));
    }

    static void escutarMusica(MusicaDAO musicaDAO){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome que quer escutar\n");
        String nome = sc.nextLine();
        System.out.println(musicaDAO.escutar(nome));
    }

    static void updateMusica(MusicaDAO musicaDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o que deseja fazer\n(update titulo, digite 1)\n(update data de lançamento, digite 2)\n(update letra, digite 3)\n(update duracao, digite 4)\n(update censura, digite 5)\n(update categoria, digite 6)");
        int escolha = sc.nextInt();
        System.out.println("Escreva o nome da musica que quer atualizar");
        String nome = sc.nextLine();
        switch (escolha) {
            case 1:
                System.out.println("Escreva o novo titulo");
                String titulo = sc.nextLine();
                musicaDAO.updateTitulo(nome, titulo);
                break;
            case 2:
                System.out.println("Escreva a nova data de lancamentoda musica no modelo yyyy-mm-dd");
                LocalDate data = LocalDate.parse(sc.nextLine());
                musicaDAO.updateDate(nome, data);
                break;
            case 3:
                System.out.println("Escreva a nova letra da musica");
                String letra = sc.nextLine();
                musicaDAO.updateLetra(nome, letra);
                break;
            case 4:
                System.out.println("Escreva a nova duração da musica");
                int duracao = sc.nextInt();
                musicaDAO.updateDuracao(nome, duracao);
                break;
            case 5:
                System.out.println("Escreva a nova censura");
                int censura = sc.nextInt();
                musicaDAO.updateCensura(nome, censura);
                break;
            case 6:
                System.out.println("Escreva a nova categoria");
                Categoria categoria = Categoria.values()[sc.nextInt()];
                musicaDAO.updateCategoria(nome, categoria);
                break;
            default:
                break;
        }
    }

    static void deleteMusica(MusicaDAO musicaDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome que quer deletar do banco de dados\n");
        String nome = sc.nextLine();
        musicaDAO.delete(nome);
    }

// Usuarios

    static void createUsuario(UsuarioDAO usuarioDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o nome do usuario");
        String nome = sc.nextLine();
        System.out.println("Escreva o cpf do usuario");
        int cpf = sc.nextInt();
        System.out.println("Escreva a data de nascimento do usuario");
        LocalDate nascimento = LocalDate.parse(sc.nextLine());
        System.out.println("Escreva o numero do cartao do usuario");
        int cartao = sc.nextInt();
        Usuario usuario = new Usuario(nome, cpf, nascimento, cartao);
        usuarioDAO.create(usuario);
    }

    static void buscaUsuario(UsuarioDAO usuarioDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o cpf que quer buscar\n");
        int cpf = sc.nextInt();
        System.out.println(usuarioDAO.retriveSpecific(cpf));
    }

    static void updateUsuario(UsuarioDAO usuarioDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o cpf do usuario que quer atualizar");
        int cpf = sc.nextInt();
        System.out.println("Escreva o novo cartão da musica");
        int cartao = sc.nextInt();
        usuarioDAO.updateCartao(cartao, cpf);
    }

    static void deleteUsuario(UsuarioDAO usuarioDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o cpf que quer deletar do banco de dados\n");
        int cpf = sc.nextInt();
        usuarioDAO.delete(cpf);
    }

// Playlists

    static Musica createMusicaPlaylist(MusicaDAO musicaDAO, ProdutorDAO produtorDAO, AutorDAO autorDAO) {
        ArrayList<Produtor> listaProd = new ArrayList<Produtor>();
        ArrayList<Autor> listaAut = new ArrayList<Autor>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o titulo da musica");
        String titulo = sc.nextLine();
        System.out.println("Escreva a letra da musica");
        String letra = sc.nextLine();
        System.out.println("Escreva a data de lançamento da musica no modelo yyyy-mm-dd");
        LocalDate data = LocalDate.parse(sc.nextLine());
        System.out.println("Escreva a duração da musica");
        int duracao = sc.nextInt();
        System.out.println("Escreva a censura da musica");
        int censura = sc.nextInt();
        System.out.println("Escreva a categoria da musica\nAxe(1), Blues(2), Country(3), Eletr\u00F4nica(4), Forr\u00F3(5), Funk(6), Gospel(7), Hip_Hop(8), Jazz(9), MPB(10), M\u00FAsica_cl\u00E1ssica(11), Pagode(12), Pop(13), Rap(14), Reggae(15), Rock(16), Samba(17)");
        Categoria categoria = Categoria.values()[sc.nextInt()];
        Musica musica = new Musica(titulo, letra, data, duracao, censura, categoria);

        while(true){
            System.out.println("Escreva o nome do produtor\nNão escreva nada para atribuir Autores\n");
            String nome = sc.nextLine();
            if(nome.equals("")){
                break;
            }
            Produtor prod = new Produtor(nome);
            produtorDAO.create(prod);
            listaProd.add(prod);
        }
        musica.setProdutor(listaProd);

        while(true){
            System.out.println("Escreva o nome do autor\nNão escreva nada para parar");
            String nome = sc.nextLine();
            if(nome.equals("")){
                break;
            }
            Autor prod = new Autor(nome);
            autorDAO.create(prod);
            listaAut.add(prod);
        }
        musica.setAutor(listaAut);

        musicaDAO.create(musica);
        return musica;
    }

    static void createPlaylist(PlaylistDAO playlistDAO, MusicaDAO musicaDAO, ProdutorDAO produtorDAO, AutorDAO autorDAO, Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o titulo da playlist");
        String titulo = sc.nextLine();
        System.out.println("Escreva a visibilidade da playlist");
        int visibilidade = sc.nextInt();
        System.out.println("Escreva a data de criacao da playlist no modelo yyyy-mm-dd");
        LocalDate data = LocalDate.parse(sc.nextLine());
        System.out.println("Escreva a categoria da playlist\nAxe(1), Blues(2), Country(3), Eletronica(4), Forro(5), Funk(6), Gospel(7), Hip_Hop(8), Jazz(9), MPB(10), Musica_classica(11), Pagode(12), Pop(13), Rap(14), Reggae(15), Rock(16), Samba(17)");
        Categoria categoria = Categoria.values()[sc.nextInt()];
        Playlist playlist = new Playlist(titulo, visibilidade, data, categoria, usuario);

        while(true){
            System.out.println("deseja adicionar uma musica?\n(1 para sim, 2 para não)");
            int escolha = sc.nextInt();
            if(escolha == 1) {
                Musica musica = createMusicaPlaylist(musicaDAO, produtorDAO, autorDAO);
                playlist.addMusica(musica);
            }
            else{
                break;
            }
        }

        playlistDAO.create(playlist);
    }

    static void buscaPlaylist(PlaylistDAO musicaDAO, Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o que deseja fazer\n(Buscar pelo titulo, digite 1)\n(Buscar pelo usuario, digite 2)\n(Buscar pelo ano, digite 3)");
        int escolha = sc.nextInt();
        switch(escolha){
            case 1:
                System.out.println("Escreva o titulo da playlist que quer buscar\n");
                String nome = sc.nextLine();
                System.out.println(musicaDAO.buscarPlaylistTitulo(nome, usuario));
                break;

            case 2:
                System.out.println("Escreva o cpf do usuario que quer buscar");
                int cpf = sc.nextInt();
                System.out.println(musicaDAO.buscarPlaylistsUsuario(cpf, usuario));
                break;

            case 3:
                System.out.println("Escreva o ano que quer pesquisar");
                int ano = sc.nextInt();
                System.out.println(musicaDAO.buscarPlaylistsAno(ano, usuario));
                break;
            default:
                break;

        }
    }

    static void ouvirPlaylist(PlaylistDAO musicaDAO, Usuario usuario){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o titulo da playlist que quer escutar\n");
        String nome = sc.nextLine();
        System.out.println(musicaDAO.ouvirPlaylist(nome, usuario));
    }

    static void updatePlaylist(PlaylistDAO musicaDAO, Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o que deseja fazer\n(update titulo, digite 1)\n(update data de lançamento, digite 2)\n(update letra, digite 3)\n(update duracao, digite 4)\n(update censura, digite 5)\n(update categoria, digite 5)");
        int escolha = sc.nextInt();
        System.out.println("Escreva o nome da playlist que quer atualizar");
        String nome = sc.nextLine();
        switch (escolha) {
            case 1:
                System.out.println("Escreva o novo titulo");
                String titulo = sc.nextLine();
                musicaDAO.updateTitulo(nome, titulo, usuario);
                break;
            case 2:
                System.out.println("Escreva a nova data de lancamentoda musica no modelo yyyy-mm-dd");
                int visibilidade = sc.nextInt();
                musicaDAO.updateVisibilidade(nome, visibilidade, usuario);
                break;
            default:
                break;
        }
    }

    static void deletePlaylist(PlaylistDAO musicaDAO, Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escreva o id da playlist que quer deletar do banco de dados\n");
        int id = sc.nextInt();
        musicaDAO.delete(id, usuario);
    }

}
