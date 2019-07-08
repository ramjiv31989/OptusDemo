package com.optus.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.optus.demo.adapter.ItemsAdapter;
import com.optus.demo.adapter.ViewPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mappedItemText;
    private int RecyclerViewItemPosition;
    private View ChildView;
    ArrayList<String> itemValue;
    private Button redBtn, blueBtn, greenBtn, scenarioTwoBtn;
    private LinearLayout colorLayoutHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);
        itemValue = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mappedItemText = findViewById(R.id.mapped_item_text_view);
        redBtn = findViewById(R.id.btn_red_item);
        blueBtn = findViewById(R.id.btn_blue_item);
        greenBtn = findViewById(R.id.btn_green_item);
        colorLayoutHolder = findViewById(R.id.ll4);
        scenarioTwoBtn = findViewById(R.id.scenario_2);
        mappedItemText.setText(getString(R.string.default_display));
        listDatas();
        ItemsAdapter adapter = new ItemsAdapter(itemValue);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        //viewpager
        setUpView();
        redBtnColor();
        blueBtnColor();
        greenBtnColor();
        scenarioTwoBtns();

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView Recyclerview, @NonNull MotionEvent motionEvent) {
                ChildView = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (ChildView != null && gestureDetector.onTouchEvent(motionEvent)) {
                    //Getting clicked value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(ChildView);
                    // Showing clicked item value on screen using toast message.
                    mappedItemText.setText(getString(R.string.displayed_item) + getString(R.string.double_quotes) + itemValue.get(RecyclerViewItemPosition));
                }

                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView Recyclerview, @NonNull MotionEvent motionEvent) {
                //override methods
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                //override methods
            }
        });


    }

    private void scenarioTwoBtns() {
        scenarioTwoBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void greenBtnColor() {
        greenBtn.setOnClickListener(v -> colorLayoutHolder.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.green)));
    }

    private void blueBtnColor() {
        blueBtn.setOnClickListener(v -> colorLayoutHolder.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.blue)));
    }

    private void redBtnColor() {
        redBtn.setOnClickListener(v -> colorLayoutHolder.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.red)));
    }

    private void listDatas() {
        itemValue = new ArrayList<>();
        itemValue.add(getString(R.string.item_one));
        itemValue.add(getString(R.string.item_two));
        itemValue.add(getString(R.string.item_three));
        itemValue.add(getString(R.string.item_four));
        itemValue.add(getString(R.string.item_five));
    }

    private void setUpView() {
        ViewPager mViewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter _adapter = new ViewPagerAdapter( getSupportFragmentManager());
        mViewPager.setAdapter(_adapter);
        CirclePageIndicator mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setPageColor(R.color.gray);
        mIndicator.setFillColor(R.color.d_blue);
        mIndicator.setStrokeColor(R.color.colorAccent);


    }

}