package com.viralandroid.chartmessangingappui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;
    private MessagesDetailAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ImageView imageView;
    EditText editText;
    ProgressBar progressBar;
    String chatName;
    String botName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        imageView = (ImageView) v.findViewById(R.id.btn_send);
        editText = (EditText) v.findViewById(R.id.edit_text);
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        Bundle bundle = this.getArguments();
        chatName = bundle.getString("data");
        botName = bundle.getString("botName");

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final List<DataToUI> data = new ArrayList<>();
        if (App.get().getDB().chatDao().getAll(chatName, botName) != null) {
            data.addAll(App.get().getDB().chatDao().getAll(chatName, botName));
            mAdapter = new MessagesDetailAdapter(getActivity(), data);
            recyclerView.setAdapter(mAdapter);
            mAdapter = new MessagesDetailAdapter(getActivity(), data);
            recyclerView.setAdapter(mAdapter);
            if (mAdapter.getItemCount() - 1 > 0) {
                recyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
            }
        } else {
            mAdapter = new MessagesDetailAdapter(getActivity(), data);
            recyclerView.setAdapter(mAdapter);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.haveNetworkConnection(getActivity())) {
                    if (!TextUtils.isEmpty(editText.getText().toString())) {
                        DataToUI dataToUI = new DataToUI();
                        dataToUI.setData(editText.getText().toString());
                        dataToUI.setIs_bot_chat(false);
                        updateAdapter(dataToUI);
                        sendMessage(dataToUI);
                        DataToUI offlineChatRecord = new DataToUI();
                        offlineChatRecord.setData(editText.getText().toString());
                        offlineChatRecord.setIs_bot_chat(false);
                        offlineChatRecord.setExternalId(chatName);
                        App.get().getDB().chatDao().insertChat(offlineChatRecord);
                        editText.setText("");
                    } else {
                        Toast.makeText(getActivity(), "please enter some text", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    void sendMessage(DataToUI message) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<MainMessageData> call = service.getMessage("6nt5d1nJHkqbkphe", message.getData(), "63906", chatName);
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
                        DataToUI offlineChatRecord = new DataToUI();
                        offlineChatRecord.setData(mainMessageData.getMessage().getMessage());
                        offlineChatRecord.setIs_bot_chat(true);
                        offlineChatRecord.setExternalId(botName);
                        App.get().getDB().chatDao().insertChat(offlineChatRecord);
                        updateAdapter(dataToUI);
                    }
                }
            }

            @Override
            public void onFailure(Call<MainMessageData> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateAdapter(DataToUI message) {
        mAdapter.updateMessageList(message);
        recyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
        mAdapter.notifyDataSetChanged();
    }

}
