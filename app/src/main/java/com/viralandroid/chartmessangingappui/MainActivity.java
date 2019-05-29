package com.viralandroid.chartmessangingappui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        String externalId = "Chat 1";
        bundle.putString("data", externalId );
        bundle.putString("botName", "bot1" );
        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(bundle);
        fragmentTransaction.add(R.id.fragment_container, chatFragment, "HELLO");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
     }

    public void ChatScreen1(MenuItem menuItem){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        String externalId = "Chat 1";
        bundle.putString("data", externalId );
        bundle.putString("botName", "bot1");
        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, chatFragment, "HELLO");
        fragmentTransaction.commit();

    }

    public void ChatScreen2(MenuItem menuItem){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        String externalId = "Chat 2";
        bundle.putString("data", externalId );
        bundle.putString("botName", "bot2" );
        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, chatFragment, "HELLO");
        fragmentTransaction.commit();
    }

    public void ChatScreen3(MenuItem menuItem){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        String externalId = "Chat 3";
        bundle.putString("data", externalId );
        bundle.putString("botName", "bot3" );
        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, chatFragment, "HELLO");
        fragmentTransaction.commit();
    }

}
