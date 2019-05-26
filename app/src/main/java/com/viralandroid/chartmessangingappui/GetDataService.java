package com.viralandroid.chartmessangingappui;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("/api/chat/")
    Call<MainMessageData> getMessage(@Query("apiKey") String apiKey,@Query("message") String message,
                                     @Query("chatBotID")String chatBotID,@Query("externalID")String externalID);
}