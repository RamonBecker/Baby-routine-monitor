package com.example.monitorRotinaBebe.entites;

public class RelatorioDormiu {

    private String evento;
    private long horas;
    private long minutos;


    public RelatorioDormiu(){

    }

    public RelatorioDormiu(String evento, long horas, long minutos) {
        this.evento = evento;
        this.horas = horas;
        this.minutos = minutos;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public long getHoras() {
        return horas;
    }

    public void setHoras(long horas) {
        this.horas = horas;
    }

    public long getMinutos() {
        return minutos;
    }

    public void setMinutos(long minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        return "Dormiu{" +
                "evento='" + evento + '\'' +
                ", horas=" + horas +
                ", minutos=" + minutos +
                '}';
    }
}
