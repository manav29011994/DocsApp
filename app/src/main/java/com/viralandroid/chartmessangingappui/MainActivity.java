package com.viralandroid.chartmessangingappui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MessagesDetailAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ImageView imageView;
    EditText editText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        imageView = (ImageView) findViewById(R.id.btn_send);
        editText = (EditText) findViewById(R.id.edit_text);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<DataToUI> data = new ArrayList<>();
        if(App.get().getDB().chatDao().getAll()!=null) {
            data.addAll(App.get().getDB().chatDao().getAll());
            mAdapter = new MessagesDetailAdapter(MainActivity.this, data);
            recyclerView.setAdapter(mAdapter);
            mAdapter = new MessagesDetailAdapter(MainActivity.this, data);
            recyclerView.setAdapter(mAdapter);
            recyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
        }else {
            mAdapter = new MessagesDetailAdapter(MainActivity.this, data);
            recyclerView.setAdapter(mAdapter);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    DataToUI dataToUI = new DataToUI();
                    dataToUI.setData(editText.getText().toString());
                    dataToUI.setIs_bot_chat(false);
                    updateAdapter(dataToUI);
                    sendMessage(dataToUI);
                    DataToUI offlineChatRecord=new DataToUI();
                    offlineChatRecord.setData(editText.getText().toString());
                    offlineChatRecord.setIs_bot_chat(false);
                    App.get().getDB().chatDao().insertChat(offlineChatRecord);
                    editText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "please enter some text", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void sendMessage(DataToUI message) {
        progressBar.setVisibility(View.GONE);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<MainMessageData> call = service.getMessage("6nt5d1nJHkqbkphe", message.getData(), "63906", "MANAV");
        call.enqueue(new Callback<MainMessageData>() {
            @Override
            public void onResponse(Call<MainMessageData> call, Response<MainMessageData> response) {
                if (response != null && response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    MainMessageData mainMessageData = response.body();
                    if (mainMessageData != null && mainMessageData.getMessage() != null && !TextUtils.isEmpty(mainMessageData.getMessage().getMessage())) {
                        DataToUI dataToUI = new DataToUI();
                        dataToUI.setData(mainMessageData.getMessage().getMessage());
                        dataToUI.setIs_bot_chat(true);
                        DataToUI offlineChatRecord=new DataToUI();
                        offlineChatRecord.setData(mainMessageData.getMessage().getMessage());
                        offlineChatRecord.setIs_bot_chat(true);
                        App.get().getDB().chatDao().insertChat(offlineChatRecord);
                        updateAdapter(dataToUI);
                    }
                }
            }

            @Override
            public void onFailure(Call<MainMessageData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateAdapter(DataToUI message) {
        mAdapter.updateMessageList(message);
        recyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
        mAdapter.notifyDataSetChanged();
    }
}
