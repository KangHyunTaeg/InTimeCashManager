package com.example.class10.intimecashmanager.SubAtcivities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.AdapterSetting.DialogLoad;
import com.example.class10.intimecashmanager.AdapterSetting.ItemData;
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// import static com.example.class10.intimecashmanager.AdapterSetting.DialogLoad.DialogSearchCategory;

public class ExpenseInsert extends AppCompatActivity {
    Button btnTodayOrSomeday; // 날짜 입력, 기본적으로 오늘 날짜 표시, 클릭시 캘린더 불러오기, 날짜 선택
    Button btnLoadFavoriteInExpense; // 자주쓰는 내역 불러오기
    CheckBox chkFavorite; // 자주쓰는 내역에 추가하기
    Button btnExpenseAtExpensePage, btnIncomeAtExpensePage; // 수입입력, 지출입력 페이지 불러오기
    EditText edtAmountOfMoney, edtUsage, edtUsedPlace; // 지출금액, 사용내역, 사용처 입력
    Button btnMonthlyInstallment; // 할부 적용하기, 다이얼로그 불러옴
    TextView tvInstallmentMonth; // 몇 개월 할부인지 setText
    Button btnAcountOrCard, btnCategoryCheck; // 계좌, 범주 선택하기(다이얼로그로 불러오기)
    EditText edtInputTagInExpenseInsert; // 태그 입력
    Button btnCancle, btnSave; // 취소, 저장 - 저장 시 데이터베이스 인서트 쿼리문


    public static DatabaseCreate myDB; // 데이터베이스 사용하기 위해서 my 데이터베이스 생성 클래스 불러오기
    static SQLiteDatabase sqlDB;
    ArrayList<String> arrayList;

    // 입력 내용을 담을 변수들
    String dateExpenseIncome; // 날짜
    int sumMoney; // 금액
    String usage; // 사용내역
    String usedPlace; // 사용처
    int paymentCheck; // 지불방법
    int acount; // 현금지불시 현금계좌
    int card; // 카드지불시 사용카드
    String useCategory; // 분류
    String tag; // 태그
    int favoiteExpense; // 자주쓰는 내역 여부
    int fixedExpense; // 고정비용 여부
    int timeValue; // 시간환산 가치

    List<ItemData> data; // 입력된 데이터를 담을 배열 - List<InputData> 만들어서 담기
    Integer[] imgBtnCategoryID = {R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house,
            R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house}; // 임시 이미지버튼의 이름 배열, 10은 임의의 숫자





    String weekdayResult =""; // 요일 문자열을 담을 변수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_insert);

        if (savedInstanceState != null) {
            btnTodayOrSomeday.setText(savedInstanceState.getString("TodayOrSomeDay"));
            edtAmountOfMoney.setText(savedInstanceState.getString("AmountOfMoney"));
            tvInstallmentMonth.setText(savedInstanceState.getString("InstallmentMonth"));
            edtUsage.setText(savedInstanceState.getString("Usage"));
            edtUsedPlace.setText(savedInstanceState.getString("UsedPlace"));
            // btnAcount.setText(savedInstanceState.getString("Account"));
            // btnCategoryCheck.setText(savedInstanceState.getString("CategoryCheck"));
            edtInputTagInExpenseInsert.setText(savedInstanceState.getString("Tag"));
            chkFavorite.setChecked(savedInstanceState.getBoolean("FavoriteCheck"));
        }

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
                break;
            case 2:
                weekdayResult = "(월요일)";
                break;
            case 3:
                weekdayResult = "(화요일)";
                break;
            case 4:
                weekdayResult = "(수요일)";
                break;
            case 5:
                weekdayResult = "(목요일)";
                break;
            case 6:
                weekdayResult = "(금요일)";
                break;
            case 7:
                weekdayResult = "(토요일)";
                break;
        }
        btnTodayOrSomeday.setText(cyear+"년 "+cmonth+"월 "+cday+"일 " + weekdayResult);
        btnTodayOrSomeday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogDatePicker(btnTodayOrSomeday, ExpenseInsert.this);
            }
        });
        if(btnTodayOrSomeday.getText().toString() != null){
            dateExpenseIncome = btnTodayOrSomeday.getText().toString();
        } else{
            Toast.makeText(this, "날짜를 클릭해서 입력하세요", Toast.LENGTH_SHORT).show();
        }


        // 자주쓰는 내역 불러오기
        btnLoadFavoriteInExpense = (Button)findViewById(R.id.btnLoadFavoriteInExpense);
        btnLoadFavoriteInExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다이얼로그 메소드 실행 DialogLoad
                DialogLoad.LoadFavoriteInExpense(ExpenseInsert.this);

            }
        });


        // 수입 입력 페이지로 전환
        btnIncomeAtExpensePage = (Button)findViewById(R.id.btnIncomeAtExpensePage);
        btnIncomeAtExpensePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IncomeInsert.class);
                startActivity(intent);
                finish();
            }
        });

        // 금액 입력 - 입력되지 않으면 저장되지 않는다
        try{
            if(edtAmountOfMoney.getText().toString() != null){
                sumMoney = Integer.parseInt(edtAmountOfMoney.getText().toString());
            }
        } catch(NullPointerException e){

        }

        // 사용내역 입력 - 입력되지 않으면 저장되지 않는다
        try{
            if(edtUsage.getText().toString() != null){
                usage = edtUsage.getText().toString();
            }
        } catch(NullPointerException e){

        }


        // 사용처 입력
        try{
            if(edtUsedPlace.getText().toString() != null){
                usedPlace = edtUsedPlace.getText().toString();
            }
        } catch (NullPointerException e){

        }


        // 출금계좌 - 출력값 = 카드현금체크(paymentCheck), acount(paymentCheck=1) or card(paymentCheck=2)의 내역
        btnAcountOrCard = (Button)findViewById(R.id.btnAcountOrCard);
        btnAcountOrCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 왜 버튼이 안먹지?
                paymentCheck = 1;
                Intent intent = new Intent(getApplicationContext(), CategoryManager.class);
                intent.putExtra("CHECK_INT", 3);
                startActivityForResult(intent, 101);
            }
        });


        // 카테고리 입력
        btnCategoryCheck = (Button)findViewById(R.id.btnCategoryCheck);
        btnCategoryCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DialogLoad.DialogSearchCategory(ExpenseInsert.this);
                Intent intent = new Intent(getApplicationContext(), CategoryManager.class);
                intent.putExtra("CHECK_INT", 1); // 인텐트된 액티비티에서 1을 받을 경우와 2를 받을 경우 다른 액션을 주기 위해
                startActivityForResult(intent, 101);
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



        btnCancle = (Button)findViewById(R.id.btnCancle);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*data = new ArrayList<>();
                data.add(new ItemData(imgBtnCategoryID[0], dateExpenseIncome, sumMoney, usage, usedPlace, paymentCheck, acount, card, useCategory, tag, favoiteExpense, fixedExpense, timeValue));*/

                /*if(edtAmountOfMoney.getText().toString() == null || edtUsage.getText().toString() == null){
                    Toast.makeText(ExpenseInsert.this, "최소한 지출금액과 사용내역은 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                } else{
                    sqlDB = myDB.getWritableDatabase();
                    sqlDB.execSQL("INSERT INTO expenseTBL(dateExpenseIncome, sumMoney, usage, usePlace, paymentCheck, card, acount, useCategory, tag, favoiteExpense, fixedExpense, timeValue)" +
                            " VALUES ('" + dateExpenseIncome + "', '" + sumMoney + "', '" + usage + "', '" + usedPlance + "', '" + paymentCheck + "', '" + card + "', '" + acount + "', '" +
                            useCategory + "', '" + tag + "', '" + favoiteExpense + "', '" + fixedExpense + "', '" + timeValue + "', '" + "');");
                }*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK){
            // 지출 카테고리 프레그먼트로 보냈다가 다시 받은 데이터를 처리
            Bundle bundle = data.getExtras();
            String menuName = bundle.getString("menuName");
            String categoryID = bundle.getString("categoryID");
            btnCategoryCheck.setText(menuName + " > " + categoryID);

            try{
                if(btnCategoryCheck.getText().toString() != null){
                    useCategory = btnCategoryCheck.getText().toString();
                }
            } catch (NullPointerException e){

            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        String todayOrSomeday = btnTodayOrSomeday.getText().toString();
        String amountOfmoney = edtAmountOfMoney.getText().toString();
        String installmentMonth = tvInstallmentMonth.getText().toString();

        outState.putString("TodayOrSomeDay", todayOrSomeday);
        outState.putString("AmountOfMoney", amountOfmoney);
        outState.putString("InstallmentMonth", installmentMonth);
        // outState.putString("Usage", edtUsage.getText().toString());
        // outState.putString("UsedPlace", edtUsedPlace.getText().toString());
        // outState.putString("Account", btnAcount.getText().toString());
        // outState.putString("CategoryCheck", btnCategoryCheck.getText().toString());
        // outState.putString("Tag", edtInputTagInExpenseInsert.getText().toString());
        // outState.putBoolean("FavoriteCheck", chkFavorite.isChecked());

        // 데이터도 별도로 저장상태에 둬야 하는가?
        /*String dateExpenseIncome; // 날짜
        int sumMoney; // 금액
        String usage; // 사용내역
        String usedPlance; // 사용처
        int paymentCheck; // 지불방법
        int acount; // 현금지불시 현금계좌
        int card; // 카드지불시 사용카드
        String useCategory; // 분류
        String tag; // 태그
        int favoiteExpense; // 자주쓰는 내역 여부
        int fixedExpense; // 고정비용 여부
        int timeValue; // 시간환산 가치*/

        super.onSaveInstanceState(outState);
    }

    /*@Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        btnTodayOrSomeday.setText(savedInstanceState.getString("TodayOrSomeDay"));
        edtAmountOfMoney.setText(savedInstanceState.getString("AmountOfMoney"));
        tvInstallmentMonth.setText(savedInstanceState.getString("InstallmentMonth"));
        edtUsage.setText(savedInstanceState.getString("Usage"));
        edtUsedPlace.setText(savedInstanceState.getString("UsedPlace"));
        // btnAcount.setText(savedInstanceState.getString("Account"));
        // btnCategoryCheck.setText(savedInstanceState.getString("CategoryCheck"));
        edtInputTagInExpenseInsert.setText(savedInstanceState.getString("Tag"));
        chkFavorite.setChecked(savedInstanceState.getBoolean("FavoriteCheck"));

        super.onRestoreInstanceState(savedInstanceState);
    }*/
}
