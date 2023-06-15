package modelo;

import java.util.ArrayList;

public class Produtor {

    private String nome;
    private int id;
    private ArrayList<Musica> musicas = new ArrayList<Musica>();


    public Produtor(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    public Produtor(){
    }
    public Produtor(String nome){
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

     public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public void addMusicas(Musica musica) {
        this.musicas.add(musica);
    }

    public void removeMusicas(Musica musica) {
        this.musicas.remove(musica);
    }

    @Override
    public String toString() {
        return "{'produtor':{'id': " + this.id + ", 'nome': '" + this.nome + "}}";
    }

}
