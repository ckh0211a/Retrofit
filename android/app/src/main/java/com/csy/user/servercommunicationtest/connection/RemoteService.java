package com.csy.user.servercommunicationtest.connection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RemoteService {

    String BASE_URL = "http://13.209.68.137:3000";

    @POST("/member/create")
    Call<RequestResult> insertData(@Body Model model);

    @GET("/member/read")
    Call<RequestResult> getData(@Query("menu") String menu);

    @PUT("/member/update")
    Call<RequestResult> changeData(@Body Model model);

    @DELETE("/member/delete")
    Call<RequestResult> deleteData(@Query("menu") String menu);

}