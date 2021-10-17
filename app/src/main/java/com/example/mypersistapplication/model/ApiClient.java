package com.example.mypersistapplication.model;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    String BASE_URL = " https://randomuser.me/api/";

    @GET("?results=10")
    Call<UserList> getUserList();
}
