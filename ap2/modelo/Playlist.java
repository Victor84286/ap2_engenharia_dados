package modelo;

import java.util.ArrayList;

public class Playlist {

    private String titulo;
    private int visibilidade;
    private int id;
    private Categoria categoria;
    private Usuario usuario;
    private ArrayList<Musica> musicas;

    public Playlist(String titulo, int visibilidade, int id, Categoria categoria, Usuario usuario, ArrayList<Musica> musicas) {
        this.titulo = titulo;
        this.visibilidade = visibilidade;
        this.id = id;
        this.categoria = categoria;
        this.usuario = usuario;
        this.musicas = musicas;
    }

    public Playlist(String titulo, int visibilidade, int id, Categoria categoria, Usuario usuario) {
        this.titulo = titulo;
        this.visibilidade = visibilidade;
        this.id = id;
        this.categoria = categoria;
        this.usuario = usuario;
        this.musicas = new ArrayList<Musica>();
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

    @Override
    public String toString() {
        return "{'playlist':{'id': " + this.id + ", 'titulo': '" + this.titulo + "'}}";
    }
}