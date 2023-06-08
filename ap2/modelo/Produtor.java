package modelo;

public class Produtor {

    private String nome;
    private int id;

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

    public static void add(Produtor p) {
    }

    @Override
    public String toString() {
        return "{'produtor':{'id': " + this.id + ", 'nome': '" + this.nome + "}}";
    }

}
