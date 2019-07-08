package com.optus.demo.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.optus.demo.CommonService.TransportRepository;
import com.optus.demo.model.MyTransport;

import java.util.List;

public class TransportViewModel extends AndroidViewModel {
    private MutableLiveData<List<MyTransport>> mutableLiveData;
    private TransportRepository moviesRepository;

    public TransportViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = new TransportRepository(application);
    }

    //initialization method
    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        //singleton to handle
        moviesRepository = TransportRepository.getInstance();
    }
//
    public MutableLiveData <List<MyTransport>> getNewsRepository() {
        return moviesRepository.getTransportUpdates();
    }
}

