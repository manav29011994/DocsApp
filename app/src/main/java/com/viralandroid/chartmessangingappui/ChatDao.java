package com.viralandroid.chartmessangingappui;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM DataToUI where externalId=:chatName OR externalId=:botName")
    List<DataToUI> getAll(String chatName,String botName);

    @Insert
    void insertChat(DataToUI products);
}
