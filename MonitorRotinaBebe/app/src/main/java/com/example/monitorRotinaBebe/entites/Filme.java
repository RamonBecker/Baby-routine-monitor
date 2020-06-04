package com.example.monitorRotinaBebe.entites;

public class Filme {
    private String titulo;
    private String anoLançamento;
    private String genero;
    private Ator ator;
    private Diretor diretor;
    private int idImagem;
    
    public Filme(String titulo, String anoLançamento, String genero, int idImagem) {
        this.titulo = titulo;
        this.anoLançamento = anoLançamento;
        this.genero = genero;
        this.idImagem = idImagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnoLançamento() {
        return anoLançamento;
    }

    public void setAnoLançamento(String anoLançamento) {
        this.anoLançamento = anoLançamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Ator getAtor() {
        return ator;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }
    
    
    
    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", anoLançamento='" + anoLançamento + '\'' +
                ", genero='" + genero + '\'' +
                ", ator=" + ator +
                ", diretor=" + diretor +
                ", idImagem=" + idImagem +
                '}';
    }
}
