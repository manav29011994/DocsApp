package com.viralandroid.chartmessangingappui;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM DataToUI where externalId=:chatName OR externalId=:botName")
    List<DataToUI> getAll(String chatName,String botName);

    @Query("SELECT * FROM DataToUI where externalId=:chatName AND chatResponseFetched=:isChatsent")
    List<DataToUI> getAllOffline(String chatName,boolean isChatsent);

    @Query("UPDATE DataToUI SET chatResponseFetched=:setOfflineChatStatus WHERE id = :id")
    void update(boolean setOfflineChatStatus, int id);

    @Insert
    void insertChat(DataToUI products);
}
