package com.example.class10.intimecashmanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.AdapterSetting.DialogLoad;
import com.example.class10.intimecashmanager.AdapterSetting.ItemData;
import com.example.class10.intimecashmanager.AdapterSetting.ListViewAdapter;
import com.example.class10.intimecashmanager.SubAtcivities.ExpenseInsert;

import java.util.ArrayList;
import java.util.List;

public class IncomeExpenseList extends AppCompatActivity {
    DatabaseCreate myDB;
    SQLiteDatabase sqlDB;

    ListView listIncomeAndExpense;
    ListViewAdapter adapter;

    Button btnCalendar, btnInOutList, btnStatistic, btnSetting, btnAddMoney;
    TextView tvSearchPeriod, tvSearchCategory, tvSearcgTag, tvFeedback;
    Button btnStartPeriod, btnEndPeriod, btnSearchCategory, btnSearchTag, btnSearch;
    EditText edtInputTag;

    // 임시 배열
    ArrayList<String> dateList;
    ArrayList<Integer> imgBtnCategoryID;
    ArrayList<String> usageID;
    ArrayList<Integer> supCategoryID;
    ArrayList<Integer> subCategoryID;
    ArrayList<Integer> moneyList;

    /*Integer[] imgBtnCategoryID = {R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house,
            R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house}; // 이미지버튼의 이름 배열, 10은 임의의 숫자
    String[] usageID = {"순대국", "짜장면", "짬뽕", "탕수육", "핸드폰요금", "쇼핑", "택시비", "버스비", "빕스", "한식부폐", "순대국", "짜장면", "짬뽕", "탕수육", "핸드폰요금", "쇼핑", "택시비", "버스비", "빕스", "한식부폐"};
    String[] categoryID = {"외식", "외식", "외식", "외식", "통신비", "쇼핑", "교통비", "교통비", "외식", "외식", "외식", "외식", "외식", "외식", "통신비", "쇼핑", "교통비", "교통비", "외식", "외식"};
    Integer[] moneyList = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000};*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_expense_list);

        btnCalendar = (Button)findViewById(R.id.btnCalendar);
        btnInOutList = (Button)findViewById(R.id.btnInOutList);
        btnStatistic = (Button)findViewById(R.id.btnStatistic);
        btnSetting = (Button)findViewById(R.id.btnSetting);
        btnAddMoney = (Button)findViewById(R.id.btnAddMoney);

        btnStartPeriod = (Button)findViewById(R.id.btnStartPeriod);
        btnEndPeriod = (Button)findViewById(R.id.btnEndPeriod);
        btnSearchCategory = (Button)findViewById(R.id.btnSearchCategory);
        btnSearchTag = (Button)findViewById(R.id.btnSearchTag);

        edtInputTag = (EditText)findViewById(R.id.edtInputTag);

        tvFeedback = (TextView)findViewById(R.id.tvFeedback);

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


        btnStartPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogDatePicker(btnStartPeriod, IncomeExpenseList.this);

            }
        });

        btnEndPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogDatePicker(btnEndPeriod, IncomeExpenseList.this);
            }
        });



        /*tvSearchCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogSearchCategory(IncomeExpenseList.this);
            }
        });*/

        btnSearchTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogInputTag(btnSearchTag, IncomeExpenseList.this);
            }
        });

        // 검색



        // DB 내용을 배열에 담기
        dateList = new ArrayList<>();
        imgBtnCategoryID = new ArrayList<>();
        usageID = new ArrayList<>();
        supCategoryID = new ArrayList<>();
        subCategoryID = new ArrayList<>();
        moneyList = new ArrayList<>();

        myDB = new DatabaseCreate(this);
        sqlDB = myDB.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT dateExpenseIncome, usage, useSupCategory, useSubCategory, sumMoney FROM expenseTBL", null); // WHERE문 추가
        while(cursor.moveToNext()){
            imgBtnCategoryID.add(R.drawable.house);
            dateList.add(cursor.getString(0));
            usageID.add(cursor.getString(1));
            supCategoryID.add(cursor.getInt(2));
            subCategoryID.add(cursor.getInt(3));
            moneyList.add(cursor.getInt(4));
        }
        sqlDB.close();
        cursor.close();


        // my 리스트뷰 세팅
        listIncomeAndExpense = (ListView)findViewById(R.id.listIncomeAndExpense);
        List<ItemData> data = new ArrayList<>();
        for(int i=0; i<usageID.size(); i++){
            data.add(new ItemData(dateList.get(i), imgBtnCategoryID.get(i), usageID.get(i), supCategoryID.get(i), subCategoryID.get(i), moneyList.get(i)));
        }

        /*data = new ArrayList<>();
        data.add(new ItemData(imgBtnCategoryID[0], dateExpenseIncome, sumMoney, usage, usedPlace, paymentCheck, acount, card, useCategory, tag, favoiteExpense, fixedExpense, timeValue));*/

        adapter = new ListViewAdapter(this, data);
        listIncomeAndExpense.setAdapter(adapter);
    }


    public void colorSetting(){
        btnCalendar.setBackgroundColor(Color.parseColor("#eeeeee"));
        btnInOutList.setTextColor(Color.RED);
        btnStatistic.setBackgroundColor(Color.parseColor("#eeeeee"));
        btnSetting.setBackgroundColor(Color.parseColor("#eeeeee"));
    }


    public void getSearchContion(){
        String StartPeriod = btnStartPeriod.getText().toString();
        String EndPeriod = btnEndPeriod.getText().toString();

    }
}
