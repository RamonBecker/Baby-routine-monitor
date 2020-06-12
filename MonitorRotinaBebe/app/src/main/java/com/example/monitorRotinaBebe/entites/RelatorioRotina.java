package com.example.monitorRotinaBebe.entites;

import java.util.List;

public class RelatorioRotina {

    private String data;
    private RelatorioDormiu relatorioDormiu;
    private RelatorioMamou relatorioMamou;
    private RelatorioTrocou relatorioTrocou;

    public RelatorioRotina() {

    }

    public RelatorioRotina(String data) {
        this.data = data;
    }


    public RelatorioRotina(RelatorioDormiu relatorioDormiu) {
        this.relatorioDormiu = relatorioDormiu;
    }

    public RelatorioRotina(RelatorioMamou relatorioMamou) {
        this.relatorioMamou = relatorioMamou;
    }

    public RelatorioRotina(RelatorioTrocou relatorioTrocou) {
        this.relatorioTrocou = relatorioTrocou;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public RelatorioDormiu getRelatorioDormiu() {
        return relatorioDormiu;
    }

    public void setRelatorioDormiu(RelatorioDormiu relatorioDormiu) {
        this.relatorioDormiu = relatorioDormiu;
    }

    public RelatorioMamou getRelatorioMamou() {
        return relatorioMamou;
    }

    public void setRelatorioMamou(RelatorioMamou relatorioMamou) {
        this.relatorioMamou = relatorioMamou;
    }

    public RelatorioTrocou getRelatorioTrocou() {
        return relatorioTrocou;
    }

    public void setRelatorioTrocou(RelatorioTrocou relatorioTrocou) {
        this.relatorioTrocou = relatorioTrocou;
    }


    @Override
    public String toString() {
        return "RelatorioRotina{" +
                "data='" + data + '\'' +
                ", relatorioDormiu=" + relatorioDormiu +
                ", relatorioMamou=" + relatorioMamou +
                ", relatorioTrocou=" + relatorioTrocou +
                '}';
    }
}
