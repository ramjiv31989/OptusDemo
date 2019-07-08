package com.optus.demo.interfaces;

import com.optus.demo.model.MyTransport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpinnerInterfaces {

    String JSONURL = "https://express-it.optusnet.com.au/";

    @GET("sample.json")
    Call<List<MyTransport>> getJSONString();
}
