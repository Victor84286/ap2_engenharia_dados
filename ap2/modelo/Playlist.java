package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Playlist {

    private String titulo;
    private int visibilidade;
    private int id;
    private LocalDate data_criacao;
    private Date data_criacao2;
    private Categoria categoria;
    private Usuario usuario;
    private ArrayList<Musica> musicas;
    private int usuario_cpf;

    public Playlist(String titulo, int visibilidade, int id, LocalDate data_criacao, Categoria categoria, Usuario usuario, ArrayList<Musica> musicas) {
        this.titulo = titulo;
        this.visibilidade = visibilidade;
        this.id = id;
        this.data_criacao = data_criacao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.musicas = musicas;
    }

    public Playlist(String titulo, int visibilidade, int id, LocalDate data_criacao, Categoria categoria, Usuario usuario) {
        this.titulo = titulo;
        this.visibilidade = visibilidade;
        this.id = id;
        this.data_criacao = data_criacao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.musicas = new ArrayList<Musica>();
    }

    public Playlist(int id, String titulo, Date data_criacao, int visibilidade, Categoria categoria, int usuario) {
        this.id = id;
        this.titulo = titulo;
        this.data_criacao2 = data_criacao;
        this.visibilidade = visibilidade;
        this.categoria = categoria;
        this.usuario_cpf = usuario;
    }

    public Playlist(String titulo) {
        this.titulo = titulo;
    }

    public Playlist() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getVisibiliade() {
        return visibilidade;
    }

    public void setVisibilidade(int visibilidade) {
        this.visibilidade = visibilidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public LocalDate getDataCriacao() {
        return data_criacao;
    }

    public void setDataCriacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public void removeMusica(Musica musica) {
        this.musicas.remove(musica);
    }

    public void setData_criacao2(Date data_criacao2) {
        this.data_criacao2 = data_criacao2;
    }

    public Date getData_criacao2() {
        return data_criacao2;
    }

    public void setUsuario_cpf(int usuario_cpf) {
        this.usuario_cpf = usuario_cpf;
    }

    public int getUsuario_cpf() {
        return usuario_cpf;
    }


    @Override
    public String toString() {
        return "{'playlist':{'id': " + this.id + ", 'titulo': '" + this.titulo + "'}}";
    }
}