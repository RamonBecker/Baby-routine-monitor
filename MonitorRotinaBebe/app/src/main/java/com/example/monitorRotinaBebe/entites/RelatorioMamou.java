package com.example.monitorRotinaBebe.entites;

public class RelatorioMamou {
    private String evento;
    private long n_vezes_bebe_mamou;

    public RelatorioMamou() {
    }

    public RelatorioMamou(String evento, long n_vezes_bebe_mamou) {
        this.evento = evento;
        this.n_vezes_bebe_mamou = n_vezes_bebe_mamou;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public long getN_vezes_bebe_mamou() {
        return n_vezes_bebe_mamou;
    }

    public void setN_vezes_bebe_mamou(long n_vezes_bebe_mamou) {
        this.n_vezes_bebe_mamou = n_vezes_bebe_mamou;
    }

    @Override
    public String toString() {
        return "RelatorioMamou{" +
                "evento='" + evento + '\'' +
                ", n_vezes_bebe_trocado=" + n_vezes_bebe_mamou +
                '}';
    }
}
