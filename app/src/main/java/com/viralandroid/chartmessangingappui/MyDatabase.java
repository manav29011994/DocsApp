package com.viralandroid.chartmessangingappui;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DataToUI.class}, version = 2,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract ChatDao chatDao();
}