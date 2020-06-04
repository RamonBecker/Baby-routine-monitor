package com.example.monitorRotinaBebe.entites;

public class Diretor {
    private String nome;
    private String dataNascimento;
    private int idImagem;

    public Diretor(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Diretor(String nome, String dataNascimento, int idImagem) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idImagem = idImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    @Override
    public String toString() {
        return nome+" "+dataNascimento;
    }
}
