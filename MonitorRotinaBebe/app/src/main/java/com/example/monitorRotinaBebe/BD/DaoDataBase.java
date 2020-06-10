package com.example.monitorRotinaBebe.BD;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.monitorRotinaBebe.entites.Rotina;
import java.util.List;

@Dao
public interface DaoDataBase {

    @Insert
    public void inserirRotina(Rotina rotina);

    @Query("SELECT * FROM rotina")
     public List<Rotina> getAll();

    @Query("SELECT * FROM rotina WHERE data LIKE :data ")
    public List<Rotina> getAllDate(String data);

    @Update
    public void updateRotina(Rotina rotina);

    @Query("DELETE FROM rotina")
    public void deleteAll();

    @Delete
    public void deletarRotina(Rotina rotina);

    @Query("SELECT * FROM rotina where evento LIKE :evento AND data LIKE :data")
    public List<Rotina> getAllEvento(String evento, String data);

    @Query("SELECT COUNT(*) FROM rotina WHERE data LiKE :data and evento LIKE :evento")
    public int contarEventos(String data, String evento);

    @Query("SELECT DISTINCT data FROM rotina")
    public List<String> getDatas();
}
