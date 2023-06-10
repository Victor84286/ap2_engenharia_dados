package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Musica {
    private String titulo;
    private String letra;
    private LocalDate data_lancamento;
    private Date data_lancamento2;
    private int duracao;
    private int censura;
    private int id;
    private Categoria categoria;
    private ArrayList<Produtor> produtores;
    private ArrayList<Autor> autores;
    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();



    public Musica(int id, String letra) {
        this.id = id;
        this.letra = letra;
    }

    public Musica(int id, String titulo, String letra, LocalDate data_lancamento, int duracao, int censura, Categoria categoria){
        this.id = id;
        this.titulo = titulo;
        this.letra = letra;
        this.data_lancamento = data_lancamento;
        this.duracao = duracao;
        this.censura = censura;
        this.categoria = categoria;
    }
    public Musica(String titulo, String letra, LocalDate data_lancamento, int duracao, int censura, Categoria categoria){
        this.titulo = titulo;
        this.letra = letra;
        this.data_lancamento = data_lancamento;
        this.duracao = duracao;
        this.censura = censura;
        this.categoria = categoria;
    }

    public Musica(String titulo, String letra, Categoria categoria) {
        this.titulo = titulo;
        this.letra = letra;
        this.categoria = categoria;
    }

    public Musica(String titulo, String letra, int id, Date data_lancamento2, int duracao, int censura, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.letra = letra;
        this.data_lancamento2 = data_lancamento2;
        this.duracao = duracao;
        this.censura = censura;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public LocalDate getDataLancamento() {
        return data_lancamento;
    }

    public void setDataLancamento(LocalDate data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getCensura() {
        return censura;
    }

    public void setCensura(int censura) {
        this.censura = censura;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Produtor> getProdutor() {
        return produtores;
    }

    public void setProdutor(ArrayList<Produtor> produtores) {
        this.produtores = produtores;
    }

    public void addProdutor(Produtor produtor) {
        this.produtores.add(produtor);
    }

    public void removeProdutor(Produtor produtor) {
        this.produtores.remove(produtor);
    }

    public ArrayList<Autor> getAutor() {
        return autores;
    }

    public void setAutor(ArrayList<Autor> Autores) {
        this.autores = Autores;
    }

    public void addAutor(Autor Autor) {
        this.autores.add(Autor);
    }

    public void removeAutor(Autor Autor) {
        this.autores.remove(Autor);
    }

    public ArrayList<Playlist> getPlaylist() {
        return playlists;
    }

    public void setPlaylist(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public void removePlaylist(Playlist playlist) {
        this.playlists.remove(playlist);
    }

    @Override
    public String toString() {
        return "{'musica':{'id': " + this.id + ", 'titulo': '" + this.titulo + ", 'letra': '" + this.letra + ", 'duracao': '" + this.duracao + ", 'censura': '" + this.censura + ", 'data de lançamento': '" + this.data_lancamento2 + ", 'nome': '" + this.titulo + ", 'nome': '" + this.titulo + "}}\n";
    }
}
