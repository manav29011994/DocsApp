package com.viralandroid.chartmessangingappui;

public class OfflineChatRecord {
    private String data;
    private boolean is_bot_chat;
    private String externalID;

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

    public String getExternalID() {
        return externalID;
    }

    public void setExternalID(String externalID) {
        this.externalID = externalID;
    }

    @Override
    public String toString() {
        return "OfflineChatRecord{" +
                "data='" + data + '\'' +
                ", is_bot_chat=" + is_bot_chat +
                ", externalID='" + externalID + '\'' +
                '}';
    }
}
