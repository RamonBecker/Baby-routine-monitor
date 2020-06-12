package com.example.monitorRotinaBebe.entites;

public class RelatorioTrocou {
    private String evento;
    private long n_vezes_bebe_trocado;

    public RelatorioTrocou(){
    }

    public RelatorioTrocou(String evento, long n_vezes_bebe_trocado) {
        this.evento = evento;
        this.n_vezes_bebe_trocado = n_vezes_bebe_trocado;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public long getN_vezes_bebe_trocado() {
        return n_vezes_bebe_trocado;
    }

    public void setN_vezes_bebe_trocado(long n_vezes_bebe_trocado) {
        this.n_vezes_bebe_trocado = n_vezes_bebe_trocado;
    }

    @Override
    public String toString() {
        return "RelatorioTrocou{" +
                "evento='" + evento + '\'' +
                ", n_vezes_bebe_trocado=" + n_vezes_bebe_trocado +
                '}';
    }
}
