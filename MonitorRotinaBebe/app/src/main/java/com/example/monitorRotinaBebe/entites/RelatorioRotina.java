package com.example.monitorRotinaBebe.entites;

import java.util.List;

public class RelatorioRotina {
    private String data;
    private List<String> horas;
    private List<String> minutos;

    public RelatorioRotina(String data, List<String> horas, List<String> minutos) {
        this.data = data;
        this.horas = horas;
        this.minutos = minutos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> getHoras() {
        return horas;
    }

    public void setHoras(List<String> horas) {
        this.horas = horas;
    }

    public List<String> getMinutos() {
        return minutos;
    }

    public void setMinutos(List<String> minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        return "RelatorioRotina{" +
                "data='" + data + '\'' +
                ", horas=" + horas +
                ", minutos=" + minutos +
                '}';
    }
}
