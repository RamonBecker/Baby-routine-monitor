package com.example.monitorRotinaBebe.controller;

import com.example.monitorRotinaBebe.R;
import com.example.monitorRotinaBebe.entites.Filme;

import java.util.ArrayList;
import java.util.List;

public class ControllerFilme {
    private static ControllerFilme controllerFilme;
    private List<Filme> listaFilme;

    private ControllerFilme() {
        this.listaFilme = new ArrayList<>();
        criarFilmes();
    }

    public static ControllerFilme getInstance() {
        if (controllerFilme == null) {
            controllerFilme = new ControllerFilme();
        }
        return controllerFilme;
    }

    public List<Filme> getListaFilme() {
        return listaFilme;
    }

    public void criarFilmes(){

        ControllerAtor controllerAtor = ControllerAtor.getInstance();
        ControllerDiretor controllerDiretor = ControllerDiretor.getInstance();

        Filme filme1 = new Filme("Coringa", "2019","Drama",R.drawable.coringa);
        filme1.setAtor(controllerAtor.getListAtor().get(0));
        filme1.setDiretor(controllerDiretor.getListDiretor().get(0));
        this.listaFilme.add(filme1) ;

        Filme filme2 = new Filme("Coração Valente", "1995","Guerra",R.drawable.coracaovalente);
        filme2.setAtor(controllerAtor.getListAtor().get(1));
        filme2.setDiretor(controllerDiretor.getListDiretor().get(1));
        this.listaFilme.add(filme2) ;

        Filme filme3 = new Filme("O resgate do soldado Ryan", "1998","Guerra",R.drawable.oresgatedosoldadoryan);
        filme3.setAtor(controllerAtor.getListAtor().get(2));
        filme3.setDiretor(controllerDiretor.getListDiretor().get(2));
        this.listaFilme.add(filme3) ;


        Filme filme4 = new Filme("Band of Brothers", "2001","Minissérie",R.drawable.bandofbrothers);
        filme4.setAtor(controllerAtor.getListAtor().get(3));
        filme4.setDiretor(controllerDiretor.getListDiretor().get(3));
        this.listaFilme.add(filme4) ;
    }
}
