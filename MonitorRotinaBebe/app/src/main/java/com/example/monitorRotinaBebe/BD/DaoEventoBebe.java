package com.example.monitorRotinaBebe.BD;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorRotinaBebe.entites.RelatorioRotina;
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
    private static List<RelatorioRotina> relatorioRotinas = new ArrayList<>();

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

    public void contarEventos() {
        AppDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int n = bd.daoDataBase().contarEventos(data, evento);
                Log.i("Eventos contados", "" + n);
            }
        });
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


    public void horasDormidas(String data) {

        int posicaoDataEncontrada = 0;
        boolean dataEncontrada = false;


        for (int i = 0; i < relatorioRotinas.size(); i++) {
            if (relatorioRotinas.get(i).getData().equalsIgnoreCase(data)) {
                posicaoDataEncontrada = i;
                dataEncontrada = true;
                break;
            }
        }

        String[] horasMinutos = null;
        List<String> horas = new ArrayList<>();
        List<String> minutos = new ArrayList<>();


        for (Rotina rotina : rotinasAll) {
            if (data.equalsIgnoreCase(rotina.getData())) {
                if (rotina.getEvento().equalsIgnoreCase("Dormiu")) {
                    if(dataEncontrada){


                    }
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

    public static List<RelatorioRotina> getRelatorioRotinas() {
        return relatorioRotinas;
    }

    public static List<Rotina> getRotinasdoDia() {
        return rotinasdoDia;
    }

    public static List<Rotina> getRotinasEvento() {
        return rotinasEvento;
    }
}
