package com.example.class10.intimecashmanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.class10.intimecashmanager.AdapterSetting.CustomFragmentPagerAdapter;

import java.util.ArrayList;

public class StatisticGraph extends AppCompatActivity {
    Button btnCalendar, btnInOutList, btnStatistic, btnSetting, btnAddMoney;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_graph);



        btnCalendar = (Button)findViewById(R.id.btnCalendar);
        btnInOutList = (Button)findViewById(R.id.btnInOutList);
        btnStatistic = (Button)findViewById(R.id.btnStatistic);
        btnSetting = (Button)findViewById(R.id.btnSetting);
        btnAddMoney = (Button)findViewById(R.id.btnAddMoney);

        colorSetting();

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnInOutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.class10.intimecashmanager.IncomeExpenseList.class);
                startActivity(intent);
                finish();
            }
        });

        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.class10.intimecashmanager.StatisticGraph.class);
                startActivity(intent);
                finish();
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.class10.intimecashmanager.EnvironmentSetting.class);
                startActivity(intent);
                finish();
            }
        });

        btnAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpenseInsert.class);
                startActivity(intent);
            }
        });

        // 프레그먼트어뎁터
        ArrayList<String> tabArray = new ArrayList<>();
        tabArray.add("분류별");
        tabArray.add("카드별");
        tabArray.add("예산비교");
        tabArray.add("목표현황");


        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), tabArray);
        TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);



//        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
//        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


    }

    public void colorSetting(){
        btnCalendar.setBackgroundColor(Color.parseColor("#eeeeee"));
        btnInOutList.setBackgroundColor(Color.parseColor("#eeeeee"));
        btnStatistic.setTextColor(Color.RED);
        btnSetting.setBackgroundColor(Color.parseColor("#eeeeee"));
    }
}
