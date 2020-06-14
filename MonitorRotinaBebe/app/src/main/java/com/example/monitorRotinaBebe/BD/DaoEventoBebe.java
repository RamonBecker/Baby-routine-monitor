package com.example.monitorRotinaBebe.BD;

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
        Log.i("data", "" + data);
        for (Rotina rotina : rotinasAll) {
            if (data.equalsIgnoreCase(rotina.getData())) {
                if (rotina.getEvento().equalsIgnoreCase("Trocou")) {
                    RelatorioTrocou relatorioTrocou = null;
                    if (relatorioRotinas.get(posicaoDataEncontrada).getRelatorioTrocou() == null) {
                        relatorioTrocou = new RelatorioTrocou();
                    } else {
                        relatorioTrocou = relatorioRotinas.get(posicaoDataEncontrada).getRelatorioTrocou();
                    }
                    long n_vezes_trocou = contarEventos(data, "Trocou");
                    relatorioTrocou.setEvento("O bêbe foi trocado: ");
                    relatorioTrocou.setN_vezes_bebe_trocado(n_vezes_trocou);
                    relatorioRotinas.get(posicaoDataEncontrada).setRelatorioTrocou(relatorioTrocou);
                }
                if (rotina.getEvento().equalsIgnoreCase("Mamou")) {
                    RelatorioMamou relatorioMamou = null;
                    if (relatorioRotinas.get(posicaoDataEncontrada).getRelatorioMamou() == null) {
                        relatorioMamou = new RelatorioMamou();
                    } else {
                        relatorioMamou = relatorioRotinas.get(posicaoDataEncontrada).getRelatorioMamou();
                    }
                    long n_vezes_mamou = contarEventos(data, "Mamou");
                    relatorioMamou.setEvento("O bebê mamou: ");
                    relatorioMamou.setN_vezes_bebe_mamou(n_vezes_mamou);
                    relatorioRotinas.get(posicaoDataEncontrada).setRelatorioMamou(relatorioMamou);
                }
            }
        }
    }


    public void calcDorme(String data) {
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


        for (Rotina rotina : rotinasAll) {
            if (data.equalsIgnoreCase(rotina.getData())) {
                if (rotina.getEvento().equalsIgnoreCase("Dormiu")) {
                    relatorioRotinas.get(posicaoDataEncontrada).getListDormiu().add(rotina);

                }
                if (rotina.getEvento().equalsIgnoreCase("Acordou")) {
                    relatorioRotinas.get(posicaoDataEncontrada).getListAcordou().add(rotina);
                }
            }
        }

        int tamanhoListAcordou = relatorioRotinas.get(posicaoDataEncontrada).getListAcordou().size();

        int auxMinutosDormiu = 0;
        Rotina auxRotinaDormiu = null;
        Rotina auxRotinaAcordou = null;
        int auxCountElementosDormiu = 0;
        int j = 0;
        String[] horasMinutosAcordou = null;
        String[] horasMinutosDormiu = null;
        boolean auxCheckHora = true;

        int auxHoraFinal = 0;
        int auxHoraInicial = 0;
        int auxMinuto = 0;
        int auxCalcMinuto = 0;
        int auxCalcHora = 0;
        int auxHora = 0;

        // calcular as horas
        for (int i = 0; i < relatorioRotinas.get(posicaoDataEncontrada).getListDormiu().size(); i++) {
            if (auxCountElementosDormiu > tamanhoListAcordou) {
                break;
            }

            if (j < relatorioRotinas.get(posicaoDataEncontrada).getListAcordou().size()) {
                if (relatorioRotinas.get(posicaoDataEncontrada).getListAcordou() != null && relatorioRotinas.get(posicaoDataEncontrada).getListAcordou().get(j) != null) {
                    auxRotinaAcordou = relatorioRotinas.get(posicaoDataEncontrada).getListAcordou().get(j);
                    horasMinutosAcordou = auxRotinaAcordou.getHora().split(":");
                    auxHoraFinal = Integer.parseInt(horasMinutosAcordou[0]);
                    auxCheckHora = true;
                } else {
                    auxCheckHora = false;
                }
                j++;
            }

            auxRotinaDormiu = relatorioRotinas.get(posicaoDataEncontrada).getListDormiu().get(i);
            horasMinutosDormiu = auxRotinaDormiu.getHora().split(":");
            auxHoraInicial = Integer.parseInt(horasMinutosDormiu[0]);

            if (auxCheckHora) {
                auxCalcHora += auxHoraFinal - auxHoraInicial;
            }
            auxCountElementosDormiu++;
        }


        //Calcular minuto
        String[] minutosAcordou = null;
        for (int i = 0; i < relatorioRotinas.get(posicaoDataEncontrada).getListAcordou().size(); i++) {
            Rotina rotina = relatorioRotinas.get(posicaoDataEncontrada).getListAcordou().get(i);
            minutosAcordou = rotina.getHora().split(":");
            auxMinuto = Integer.parseInt(minutosAcordou[1]);
            auxCalcMinuto += auxMinuto;

            if (auxCalcMinuto > 60) {
                auxCalcMinuto = auxCalcMinuto - 60;
                auxHora += 1;
            }
        }

        if (auxHora > 0) {
            auxCalcHora = auxCalcHora + auxHora;
        }
        Log.i("Hora final", "calcDorme: " + auxCalcHora);
        Log.i("Minuto Final", "Minuto Final" + auxCalcMinuto);

        RelatorioDormiu relatorioDormiu = new RelatorioDormiu();
        relatorioDormiu.setEvento("O bebê dormiu por: ");
        relatorioDormiu.setHoras(auxCalcHora);
        relatorioDormiu.setMinutos(auxCalcMinuto);
        relatorioRotinas.get(posicaoDataEncontrada).setRelatorioDormiu(relatorioDormiu);
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
