package com.example.class10.intimecashmanager.SubAtcivities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.AdapterSetting.DialogLoad;
import com.example.class10.intimecashmanager.IncomeExpenseList;
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ExpenseInsert extends AppCompatActivity {
    Button btnTodayOrSomeday; // 날짜 입력, 기본적으로 오늘 날짜 표시, 클릭시 캘린더 불러오기, 날짜 선택
    Button btnLoadFavoriteInExpense; // 자주쓰는 내역 불러오기
    CheckBox chkFavorite; // 자주쓰는 내역에 추가하기
    Button btnExpenseAtExpensePage, btnIncomeAtExpensePage; // 수입입력, 지출입력 페이지 불러오기
    EditText edtAmountOfMoney, edtUsage, edtUsedPlace; // 지출금액, 사용내역, 사용처 입력
    Button btnMonthlyInstallment; // 할부 적용하기, 다이얼로그 불러옴
    TextView tvInstallmentMonth; // 몇 개월 할부인지 setText
    Button btnAcount, btnCategoryCheck; // 계좌, 범주 선택하기(다이얼로그로 불러오기)
    EditText edtInputTagInExpenseInsert; // 태그 입력
    Button btnCancle, btnSave; // 취소, 저장 - 저장 시 데이터베이스 인서트 쿼리문


    public static DatabaseCreate myDB; // 데이터베이스 사용하기 위해서 my 데이터베이스 생성 클래스 불러오기
    ArrayList<String> arrayList;



    String weekdayResult =""; // 요일 문자열을 담을 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_insert);

        // 날짜 입력하기
        btnTodayOrSomeday = (Button) findViewById(R.id.btnTodayOrSomeday);
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH)+1;
        int cday = c.get(Calendar.DAY_OF_MONTH);
        int cweekday = c.get(Calendar.DAY_OF_WEEK);

        switch (cweekday){
            case 1:
                weekdayResult = "(일요일)";
            case 2:
                weekdayResult = "(월요일)";
            case 3:
                weekdayResult = "(화요일)";
            case 4:
                weekdayResult = "(수요일)";
            case 5:
                weekdayResult = "(목요일)";
            case 6:
                weekdayResult = "(금요일)";
            case 7:
                weekdayResult = "(토요일)";
        }
        btnTodayOrSomeday.setText(cyear+"년 "+cmonth+"월 "+cday+"일 ");
        btnTodayOrSomeday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogDatePicker(btnTodayOrSomeday, ExpenseInsert.this);
            }
        });

        // 자주쓰는 내역 불러오기
        btnLoadFavoriteInExpense = (Button)findViewById(R.id.btnLoadFavoriteInExpense);
        btnLoadFavoriteInExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다이얼로그 메소드 실행 DialogLoad
                DialogLoad.LoadFavoriteInExpense(ExpenseInsert.this);

            }
        });




        btnIncomeAtExpensePage = (Button)findViewById(R.id.btnIncomeAtExpensePage);
        btnIncomeAtExpensePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IncomeInsert.class);
                startActivity(intent);
                finish();
            }
        });

        btnMonthlyInstallment = (Button)findViewById(R.id.btnMonthlyInstallment);
        edtAmountOfMoney = (EditText)findViewById(R.id.edtAmountOfMoney);
        tvInstallmentMonth = (TextView)findViewById(R.id.tvInstallmentMonth);
        btnMonthlyInstallment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogMonthlyInstallment(ExpenseInsert.this, edtAmountOfMoney, tvInstallmentMonth);
            }
        });

        btnAcount = (Button)findViewById(R.id.btnAcount);
        final View[] dialogView = new View[1];
        btnAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
