package com.example.network;

import com.example.model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String  BASE_URL = "https://test-api-88736-default-rtdb.firebaseio.com";
    String  RELATIVE_URL = "/values.json";


    @GET(RELATIVE_URL)
    Call<ResponseModel> getAll();



}
