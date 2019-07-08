package com.optus.demo.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.optus.demo.R;

public class FourthFragment extends Fragment {


    public static Fragment newInstance() {

        return new FourthFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams")
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.layout_one, null);
        RelativeLayout relativeLayout = root.findViewById(R.id.relative_layout_view);
        TextView textDisplay = root.findViewById(R.id.text_display);
        textDisplay.setText(getString(R.string.fourth_fragment));
        relativeLayout.setOnClickListener(v -> Toast.makeText(getActivity(), "Fragment 4", Toast.LENGTH_SHORT)
                .show());
        return root;
    }

}
