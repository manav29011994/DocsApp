package com.viralandroid.chartmessangingappui;

public class DataToUI {
    private String data;
    private boolean is_bot_chat;

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


    @Override
    public String toString() {
        return "DataToUI{" +
                "data='" + data + '\'' +
                ", is_bot_chat=" + is_bot_chat +
                '}';
    }
}
