package com.example.monitorRotinaBebe.BD;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.monitorRotinaBebe.entites.Rotina;

@Dao
public interface DaoDataBase {

    @Insert
    public void inserirRotina(Rotina rotina);
}
