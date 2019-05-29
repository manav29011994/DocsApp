package com.viralandroid.chartmessangingappui;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityChangeReceiver extends BroadcastReceiver {

    private ConnectionLostCallback listener;

    public ConnectivityChangeReceiver(){

    }

    public ConnectivityChangeReceiver(ConnectionLostCallback listener ){

        this.listener = listener;

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(NetworkUtil.getConnectivityStatusString(context)){
          listener.reconnected();
        }
    }

    public  boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}