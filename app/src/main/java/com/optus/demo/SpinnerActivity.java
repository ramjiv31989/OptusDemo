package com.optus.demo;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.optus.demo.Utils.Utils;
import com.optus.demo.ViewModels.TransportViewModel;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;

public class SpinnerActivity extends AppCompatActivity {
    private ArrayList<String> transportNames = new ArrayList<>();
    private Spinner spinnerTxt;
    private TextView carTxt, trainTxt;
    private String getLatitude, getLongitude;
    private Button scenarioOneBtn;
    TransportViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        spinnerTxt = findViewById(R.id.spinner_container);
        carTxt = findViewById(R.id.car_txt);
        trainTxt = findViewById(R.id.train_txt);
        scenarioOneBtn = findViewById(R.id.scenario_1);
        Button navigateDirectionsBtn = findViewById(R.id.btn_location);
        scenarioOneBtns();
        if (Utils.isNetworkConnected(this)) {
            retrofitCallBack();
            navigateDirectionsBtn.setOnClickListener(v -> {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + getLatitude + "," + getLongitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });
        } else {
            Toast.makeText(SpinnerActivity.this, getString(R.string.internet_connection_not_available), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    //mvvm pattern based on live data
    private void retrofitCallBack() {
        ProgressDialog progressDialog = Utils.showProgressDialog(this, getString(R.string.loading_message));
        progressDialog.show();
        newsViewModel = ViewModelProviders.of(this).get(TransportViewModel.class);
        newsViewModel.init();
        newsViewModel.getNewsRepository().observe(this, newsResponse -> {
            progressDialog.dismiss();
            if (newsResponse != null) {
                for (int i = 0; i < newsResponse.size(); i++) {
                    transportNames.add(newsResponse.get(i).getName());
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(SpinnerActivity.this, simple_spinner_item, transportNames);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spinnerTxt.setAdapter(spinnerArrayAdapter);

                spinnerTxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        carTxt.setText(getString(R.string.car_name) + newsResponse.get(position).getFromcentral().getCar());
                        if (newsResponse.get(position).getFromcentral().getTrain() != null) {
                            trainTxt.setText(getString(R.string.train_name) + newsResponse.get(position).getFromcentral().getTrain());
                        } else {
                            trainTxt.setText(getString(R.string.train_name) + getString(R.string.train_not_available));
                        }
                        getLatitude = newsResponse.get(position).getLocation().getLatitude();
                        getLongitude = newsResponse.get(position).getLocation().getLongitude();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //nothing selected

                    }
                });
            }
        });
    }


    private void scenarioOneBtns() {
        scenarioOneBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SpinnerActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }


}

