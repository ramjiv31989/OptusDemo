package com.optus.demo.CommonService;


import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.optus.demo.R;
import com.optus.demo.interfaces.SpinnerInterfaces;
import com.optus.demo.model.MyTransport;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransportRepository {
    private static TransportRepository moviesRepository;
    private SpinnerInterfaces newsApi;
    private static Application application;

    public static TransportRepository getInstance() {
        if (moviesRepository == null) {
            moviesRepository = new TransportRepository(application);
        }
        return moviesRepository;
    }


    public TransportRepository(Application application) {
        TransportRepository.application = application;
        newsApi = RetrofitService.createService(SpinnerInterfaces.class);
    }

    public MutableLiveData<List<MyTransport>> getTransportUpdates() {
        final MutableLiveData<List<MyTransport>> moviesData = new MutableLiveData<>();
        newsApi.getJSONString().enqueue(new Callback<List<MyTransport>>() {
            @Override
            public void onResponse(@NonNull Call<List<MyTransport>> call, @NonNull Response<List<MyTransport>> response) {
                Log.i("RETROFIT RESPONSE", "Success" + response.body());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        moviesData.setValue(response.body());
                    } else {
                        Toast.makeText(application, application.getString(R.string.response_unsuccessful), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<MyTransport>> call, @NonNull Throwable t) {
                Log.i("RETROFIT RESPONSE", "Failure" + call.toString());
                Log.i("RETROFIT RESPONSE", "Failure" + t);
                moviesData.setValue(null);
            }
        });
        return moviesData;
    }
}

