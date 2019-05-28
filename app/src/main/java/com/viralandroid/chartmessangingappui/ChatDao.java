package com.viralandroid.chartmessangingappui;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM DataToUI")
    List<DataToUI> getAll();

    @Insert
    void insertChat(DataToUI products);
}
