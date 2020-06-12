package com.example.monitorRotinaBebe.BD;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorRotinaBebe.entites.RelatorioDormiu;
import com.example.monitorRotinaBebe.entites.RelatorioMamou;
import com.example.monitorRotinaBebe.entites.RelatorioRotina;
import com.example.monitorRotinaBebe.entites.RelatorioTrocou;
import com.example.monitorRotinaBebe.entites.Rotina;

import java.util.ArrayList;
import java.util.List;

public class DaoEventoBebe {
    private AppDataBase bd;
    private String data;
    private Rotina rotina;
    private String evento;
    private static List<Rotina> rotinasAll = new ArrayList<>();
    private static List<Rotina> rotinasdoDia = new ArrayList<>();
    private static List<Rotina> rotinasEvento = new ArrayList<>();
    private static List<String> datas = new ArrayList<>();
    private List<RelatorioRotina> relatorioRotinas = new ArrayList<>();
    public static long n_vezes_trocados;

    public DaoEventoBebe(AppCompatActivity activity) {

        bd = AppDataBase.getInstance(activity);
    }

    public void inserirRotina(final Rotina rotina) {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bd.daoDataBase().inserirRotina(rotina);
            }
        });
    }

    public void getRotinaDoDia() {

        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                rotinasdoDia = bd.daoDataBase().getAllDate(data);
                Log.i("Rotina dia", "" + rotinasdoDia);
            }
        });
    }

    public void removerRotina() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bd.daoDataBase().deletarRotina(rotina);
            }
        });
    }


    public void getRotinaEvento() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                rotinasEvento = bd.daoDataBase().getAllEvento(evento, data);
                //  Log.i("rotina evento", ""+rotinasdoDia);
            }
        });
    }

    public void getAll() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                rotinasAll = bd.daoDataBase().getAll();

                Log.i("Dentro do RUN1", "" + rotinasAll);
            }

        });


    }

    public long contarEventos(final String data, final String evento) {


        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                n_vezes_trocados = bd.daoDataBase().contarEventos(data, evento);
            }
        });
        return n_vezes_trocados;
    }


    public void getAllDatas() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                datas = bd.daoDataBase().getDatas();
                Log.i("Datas", "" + datas);
            }
        });
    }

    public void inicializarRelatorios() {
        relatorioRotinas = new ArrayList<>();
        for (String data : datas) {
            relatorioRotinas.add(new RelatorioRotina(data));

        }
    }

    public void n_vezes_Trocados_Mamou(String data) {
        int posicaoDataEncontrada = 0;
        boolean dataEncontrada = false;

        for (int i = 0; i < relatorioRotinas.size(); i++) {
            if (relatorioRotinas.get(i).getData().contentEquals(data)) {
                posicaoDataEncontrada = i;
                dataEncontrada = true;
                break;
            }
        }
        Log.i("data",""+data);
        for (Rotina rotina : rotinasAll) {
            if (data.equalsIgnoreCase(rotina.getData())) {
                if (rotina.getEvento().equalsIgnoreCase("Trocou")) {
                    RelatorioTrocou relatorioTrocou = null;
                    if(relatorioRotinas.get(posicaoDataEncontrada).getRelatorioTrocou() == null){
                        relatorioTrocou = new RelatorioTrocou();
                    }else{
                        relatorioTrocou = relatorioRotinas.get(posicaoDataEncontrada).getRelatorioTrocou();
                    }
                    long n_vezes_trocou = contarEventos(data,"Trocou");
                    relatorioTrocou.setEvento("Trocou");
                    relatorioTrocou.setN_vezes_bebe_trocado(n_vezes_trocou);
                    relatorioRotinas.get(posicaoDataEncontrada).setRelatorioTrocou(relatorioTrocou);
                }
                if(rotina.getEvento().equalsIgnoreCase("Mamou")){
                    Log.i("n mamou",""+contarEventos(data,"Mamou"));
                    RelatorioMamou relatorioMamou = null;
                    if(relatorioRotinas.get(posicaoDataEncontrada).getRelatorioMamou() == null){
                        relatorioMamou = new RelatorioMamou();
                    }else{
                        relatorioMamou = relatorioRotinas.get(posicaoDataEncontrada).getRelatorioMamou();
                    }
                    long n_vezes_mamou = contarEventos(data,"Mamou");
                    relatorioMamou.setEvento("Mamou");
                    relatorioMamou.setN_vezes_bebe_trocado(n_vezes_mamou);
                    relatorioRotinas.get(posicaoDataEncontrada).setRelatorioMamou(relatorioMamou);
                }
            }
        }
    }


    public void horasDormidas(String data) {
        Log.i("Data recebida", "" + data);
        int posicaoDataEncontrada = 0;
        boolean dataEncontrada = false;

        for (int i = 0; i < relatorioRotinas.size(); i++) {
            if (relatorioRotinas.get(i).getData().contentEquals(data)) {
                posicaoDataEncontrada = i;
                dataEncontrada = true;
                break;
            }
        }

        if (!dataEncontrada) {
            relatorioRotinas.add(new RelatorioRotina(data));
            posicaoDataEncontrada = relatorioRotinas.size() - 1;
        }

        String[] horasMinutos = null;
        List<String> horas = new ArrayList<>();
        List<String> minutos = new ArrayList<>();

        for (Rotina rotina : rotinasAll) {
            if (data.equalsIgnoreCase(rotina.getData())) {
                if (rotina.getEvento().equalsIgnoreCase("Dormiu")) {
                    RelatorioDormiu relatorioDormiu = null;
                    if (relatorioRotinas.get(posicaoDataEncontrada).getRelatorioDormiu() == null) {
                        relatorioDormiu = new RelatorioDormiu();
                        relatorioDormiu.setEvento("Dormiu");
                    } else {
                        relatorioDormiu = relatorioRotinas.get(posicaoDataEncontrada).getRelatorioDormiu();
                    }

                    horasMinutos = rotina.getHora().split(":");
                    int horasConvertida = Integer.parseInt(horasMinutos[0]);
                    int minutosConterido = Integer.parseInt(horasMinutos[1]);

                    //       Log.i("Data encontrada", "" + relatorioRotinas.get(posicaoDataEncontrada));
                    //       Log.i("Rotina", "" + rotina);
                    long horaRetorno = relatorioDormiu.getHoras();
                    long somaHora = horaRetorno + horasConvertida;

                    long minutosRetorno = relatorioDormiu.getMinutos();
                    long somaMinutos = minutosRetorno + minutosConterido;
                    //      Log.i("Soma das horas", "" + somaHora);
                    //       Log.i("Soma dos minutos", "" + minutos);
                    if (somaMinutos >= 60) {
                        long diferencaMinuto = somaMinutos - 60;
                        relatorioDormiu.setMinutos(diferencaMinuto);
                        somaHora = somaHora + 1;

                    } else {
                        relatorioDormiu.setMinutos(somaMinutos);
                    }
                    relatorioDormiu.setHoras(somaHora);
                    relatorioRotinas.get(posicaoDataEncontrada).setRelatorioDormiu(relatorioDormiu);
                }
            }
        }


    }

    public void atualizarRotina() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bd.daoDataBase().updateRotina(rotina);
            }
        });
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public static List<String> getDatas() {
        return datas;
    }

    public List<RelatorioRotina> getRelatorioRotinas() {
        return relatorioRotinas;
    }

    public static List<Rotina> getRotinasdoDia() {
        return rotinasdoDia;
    }

    public static List<Rotina> getRotinasEvento() {
        return rotinasEvento;
    }

    public static List<Rotina> getRotinasAll() {
        return rotinasAll;
    }
}
