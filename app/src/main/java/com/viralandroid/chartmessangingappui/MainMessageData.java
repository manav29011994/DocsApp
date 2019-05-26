package com.viralandroid.chartmessangingappui;

public class MainMessageData {
   private String errorMessage;
   private Message message;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MainMessageData{" +
                "errorMessage='" + errorMessage + '\'' +
                ", message=" + message +
                '}';
    }
}
