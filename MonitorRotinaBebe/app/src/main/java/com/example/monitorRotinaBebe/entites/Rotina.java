package com.example.monitorRotinaBebe.entites;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rotina")
public class Rotina implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "evento")
    private String evento;
    @ColumnInfo(name = "data")
    private String data;
    @ColumnInfo(name = "hora")
    private String hora;
    @ColumnInfo(name = "img_bebe")
    private int idImagem;

    public Rotina(){
    }

    public Rotina(String evento, String data, String hora) {
        if(evento == null || evento.isEmpty()){
            throw  new IllegalArgumentException("O campo evento não pode ser vazio");
        }

        else if(data == null || data.isEmpty()){
            throw  new IllegalArgumentException("A data não pode ser vazia");
        }

        else if(hora == null || hora.isEmpty()){
            throw  new IllegalArgumentException("A hora não pode ser vazia");
        }

        this.evento = evento;
        this.data = data;
        this.hora = hora;
    }

    public Rotina(String evento, String data, String hora, int idImagem) {

        if(evento == null || evento.isEmpty()){
            throw  new IllegalArgumentException("O campo evento não pode ser vazio");
        }

        else if(data == null || data.isEmpty()){
            throw  new IllegalArgumentException("A data não pode ser vazia");
        }

        else if(hora == null || hora.isEmpty()){
            throw  new IllegalArgumentException("A hora não pode ser vazia");
        }

        this.evento = evento;
        this.data = data;
        this.hora = hora;
        this.idImagem = idImagem;
    }

    public Rotina(long id, String evento, String data, String hora) {
        if(id < 0){
            throw  new IllegalArgumentException("O ID não pode ser negativo");
        }

        else if(evento == null || evento.isEmpty()){
            throw  new IllegalArgumentException("O campo evento não pode ser vazio");
        }

        else if(data == null || data.isEmpty()){
            throw  new IllegalArgumentException("A data não pode ser vazia");
        }

        else if(hora == null || hora.isEmpty()){
            throw  new IllegalArgumentException("A hora não pode ser vazia");
        }
        this.id = id;
        this.evento = evento;
        this.data = data;
        this.hora = hora;
    }

    protected Rotina(Parcel in){
        id = in.readLong();
        evento = in.readString();
        data = in.readString();
        hora = in.readString();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(evento);
        dest.writeString(data);
        dest.writeString(hora);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Rotina> CREATOR = new Creator<Rotina>() {
        @Override
        public Rotina createFromParcel(Parcel in) {
            Rotina rotina = new Rotina();
            rotina.setId(in.readLong());
            rotina.setEvento(in.readString());
            rotina.setData(in.readString());
            rotina.setHora(in.readString());
            return new Rotina(in);
        }

        @Override
        public Rotina[] newArray(int size) {
            return new Rotina[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

    @Override
    public String toString() {
        return "Rotina{" +
                "id=" + id +
                ", evento='" + evento + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
               '}';
    }
}
