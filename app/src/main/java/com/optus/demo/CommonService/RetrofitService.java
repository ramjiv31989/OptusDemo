package com.optus.demo.CommonService;

import com.optus.demo.interfaces.SpinnerInterfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//for lint error clear, public statement been removed
class RetrofitService {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SpinnerInterfaces.JSONURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //no usage of public for lint error clear
    static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }


}
