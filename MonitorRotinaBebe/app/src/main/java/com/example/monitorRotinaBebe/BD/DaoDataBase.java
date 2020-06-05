package com.example.monitorRotinaBebe.BD;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.monitorRotinaBebe.entites.Rotina;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoDataBase {

    @Insert
    public void inserirRotina(Rotina rotina);

    @Query("SELECT * FROM rotina")
     public List<Rotina> getAll();

    @Query("SELECT * FROM rotina WHERE data LIKE :data ")
    public List<Rotina> getAllDate(String data);
}
