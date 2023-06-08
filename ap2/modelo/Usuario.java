package modelo;
import java.time.LocalDate;

public class Usuario {
    private String nome;
    private int cpf;
    private LocalDate dataNascimento;
    private int numeroCartao;

    public Usuario(String nome, int cpf, LocalDate dataNascimento, int numeroCartao) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.numeroCartao = numeroCartao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public String toString() {
        return "{'usuario':{'nome': '" + this.nome + "', 'cpf': '" + this.cpf + "'}}";
    }
}