package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Musica {
    private String titulo;
    private String letra;
    private LocalDate data_lancamento;
    private int duracao;
    private int censura;
    private int id;
    private int categoria_id;
    private ArrayList<Produtor> produtores;
    private ArrayList<Autor> autores;



    public Musica(int id, String titulo, String letra, LocalDate data_lancamento, int duracao, int censura, int categoria_id){
        this.id = id;
        this.titulo = titulo;
        this.letra = letra;
        this.data_lancamento = data_lancamento;
        this.duracao = duracao;
        this.censura = censura;
        this.categoria_id = categoria_id;
    }
    public Musica(String titulo, String letra, LocalDate data_lancamento, int duracao, int censura, int categoria_id){
        this.titulo = titulo;
        this.letra = letra;
        this.data_lancamento = data_lancamento;
        this.duracao = duracao;
        this.censura = censura;
        this.categoria_id = categoria_id;
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

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public ArrayList<Produtor> getProdutores() {
        return produtores;
    }

    public void setProdutores(ArrayList<Produtor> produtores) {
        this.produtores = produtores;
    }

    public void addProdutor(Produtor produtor) {
        this.produtores.add(produtor);
    }

    public void removeProdutor(Produtor produtor) {
        this.produtores.remove(produtor);
    }

    public void setAutores(ArrayList<Autor> Autores) {
        this.autores = Autores;
    }

    public void addAutor(Autor Autor) {
        this.autores.add(Autor);
    }

    public void removeAutor(Autor Autor) {
        this.autores.remove(Autor);
    }

    @Override
    public String toString() {
        return "{'produtor':{'id': " + this.id + ", 'nome': '" + this.titulo + "}}";
    }
}
