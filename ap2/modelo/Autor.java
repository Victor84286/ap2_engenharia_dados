package modelo;

import java.util.ArrayList;

public class Autor {

    private int id;
    private String nome;
    private ArrayList<Musica> musicas = new ArrayList<Musica>();

    public Autor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Autor(String nome) {
        this.nome = nome;
    }

    public Autor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public void addMusicas(Musica musicas) {
        this.musicas.add(musicas);
    }

    public void removeMusicas(Musica musicas) {
        this.musicas.remove(musicas);
    }

    @Override
    public String toString() {
        return "{'autor':{'id': " + this.id + ", 'nome': '" + this.nome + "}}\n";
    }
}
