package com.example.monitorRotinaBebe.BD;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.monitorRotinaBebe.entites.Rotina;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Rotina.class, version = 1, exportSchema = true)
public abstract class AppDataBase extends RoomDatabase {
    public abstract DaoDataBase daoDataBase();

    private static volatile AppDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


}