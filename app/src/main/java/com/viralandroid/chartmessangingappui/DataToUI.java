package com.viralandroid.chartmessangingappui;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DataToUI {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "data")
    private String data;
    @ColumnInfo(name = "is_bot_chat")
    private boolean is_bot_chat;
    @ColumnInfo(name = "externalId")
    private String externalId;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isIs_bot_chat() {
        return is_bot_chat;
    }

    public void setIs_bot_chat(boolean is_bot_chat) {
        this.is_bot_chat = is_bot_chat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public String toString() {
        return "DataToUI{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", is_bot_chat=" + is_bot_chat +
                ", externalId='" + externalId + '\'' +
                '}';
    }
}
