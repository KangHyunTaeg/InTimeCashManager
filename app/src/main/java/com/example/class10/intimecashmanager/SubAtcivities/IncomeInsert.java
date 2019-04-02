package com.example.class10.intimecashmanager.SubAtcivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.class10.intimecashmanager.R;
import com.example.class10.intimecashmanager.SubAtcivities.ExpenseInsert;

public class IncomeInsert extends AppCompatActivity {
    Button btnCancle, btnSave, btnExpenseAtIncomePage, btnIncomeAtIncomePage;
    Button btnTodayOrSomeday; // 날짜 입력, 기본적으로 오늘 날짜 표시, 클릭시 캘린더 불러오기, 날짜 선택
    Button btnLoadFavoriteInIncome; // 자주쓰는 내역 불러오기
    EditText edtAmountOfMoneyInIncome; // 수입금액
    EditText edtIncomeType; // 수입내역
    Button btnAcountTypeInIncome; // 입금계좌
    Button btnIncomeCategoryType; // 수입분류
    EditText tagInIncome; // 태그
    CheckBox chkFavorite; // 자주쓰는 내역으로 저장하기 체크박스


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_insert);

        btnExpenseAtIncomePage = (Button)findViewById(R.id.btnExpenseAtIncomePage);
        btnExpenseAtIncomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpenseInsert.class);
                startActivity(intent);
                finish();
            }
        });


        // 수입분류 입력
        btnIncomeCategoryType = (Button)findViewById(R.id.btnIncomeCategoryType);
        btnIncomeCategoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryManager.class);
                intent.putExtra("CHECK_INT", 2); // 인텐트된 액티비티에서 1을 받을 경우와 2를 받을 경우 다른 액션을 주기 위해
                startActivityForResult(intent, 101);
            }
        });


        btnCancle = (Button)findViewById(R.id.btnCancle);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
