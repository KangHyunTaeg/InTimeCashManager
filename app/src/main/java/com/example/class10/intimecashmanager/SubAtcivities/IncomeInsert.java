package com.example.class10.intimecashmanager.SubAtcivities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.AdapterSetting.DialogLoad;
import com.example.class10.intimecashmanager.R;
import com.example.class10.intimecashmanager.SubAtcivities.ExpenseInsert;

import java.util.Calendar;

public class IncomeInsert extends AppCompatActivity {
    Button btnCancle, btnSaveInIncome, btnExpenseAtIncomePage, btnIncomeAtIncomePage;
    Button btnTodayOrSomeday; // 날짜 입력, 기본적으로 오늘 날짜 표시, 클릭시 캘린더 불러오기, 날짜 선택
    Button btnLoadFavoriteInIncome; // 자주쓰는 내역 불러오기
    EditText edtAmountOfMoneyInIncome; // 수입금액
    EditText edtIncomeType; // 수입내역
    Button btnAcountTypeInIncome; // 입금계좌
    Button btnIncomeCategoryType; // 수입분류
    EditText edtTagInIncome; // 태그
    CheckBox chkFavorite; // 자주쓰는 내역으로 저장하기 체크박스

    public static DatabaseCreate myDB; // 데이터베이스 사용하기 위해서 my 데이터베이스 생성 클래스 불러오기
    static SQLiteDatabase sqlDB;


    // 입력 내용을 담을 변수들 - 초기값을 준 이유는 DB 저장시점에서 입력하지 않은 값들이 있을 것이기 때문임
    static String dateExpenseIncome = null; // 날짜
    static int sumMoney = 0; // 금액
    static String earnings = null; // 수입내역
    static int acount = 0; // 입금된 현금계좌
    static int incomeSupCategory = 0; // 대분류
    static int incomeSubCategory = 0; // 소분류
    static String tag = null; // 태그
    static int favoiteIncome = 0; // 자주쓰는 내역 여부
    static int timeValue = 0; // 시간환산 가치


    String weekdayString =""; // 요일 문자열을 담을 변수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_insert);

        // 데이터베이스 객체 생성
        myDB = new DatabaseCreate(getApplicationContext());

        // XML Casting
        btnTodayOrSomeday = (Button)findViewById(R.id.btnTodayOrSomeday);
        btnLoadFavoriteInIncome = (Button)findViewById(R.id.btnLoadFavoriteInIncome);
        btnExpenseAtIncomePage = (Button)findViewById(R.id.btnExpenseAtIncomePage);
        edtAmountOfMoneyInIncome = (EditText)findViewById(R.id.edtAmountOfMoneyInIncome);
        edtIncomeType = (EditText)findViewById(R.id.edtIncomeType);
        btnAcountTypeInIncome = (Button)findViewById(R.id.btnAcountTypeInIncome);
        btnIncomeCategoryType = (Button)findViewById(R.id.btnIncomeCategoryType);
        edtTagInIncome = (EditText)findViewById(R.id.edtTagInIncome);
        chkFavorite = (CheckBox)findViewById(R.id.chkFavorite);
        btnCancle = (Button)findViewById(R.id.btnCancle);
        btnSaveInIncome = (Button)findViewById(R.id.btnSaveInIncome);

        // 수입 지출 입력 전환
        btnExpenseAtIncomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpenseInsert.class);
                startActivity(intent);
                finish();
            }
        });

        // 날짜 입력하기
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH)+1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentWeekday = calendar.get(Calendar.DAY_OF_WEEK);

        switch (currentWeekday){
            case 1: weekdayString = "일요일"; break;
            case 2: weekdayString = "월요일"; break;
            case 3: weekdayString = "화요일"; break;
            case 4: weekdayString = "수요일"; break;
            case 5: weekdayString = "목요일"; break;
            case 6: weekdayString = "금요일"; break;
            case 7: weekdayString = "토요일"; break;
        }
        btnTodayOrSomeday.setText(currentYear+"년 "+currentMonth+"월 "+currentDay+"일 " + weekdayString);
        btnTodayOrSomeday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogDatePicker(btnTodayOrSomeday, IncomeInsert.this);
            }
        });

        // 자주쓰는 내역 불러오기
        btnLoadFavoriteInIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다이얼로그 메소드 실행 DialogLoad
                DialogLoad.LoadFavoriteInExpense(IncomeInsert.this);

            }
        });

        // 입금계좌 입력
        btnAcountTypeInIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryManager.class);
                intent.putExtra("CHECK_INT", 4);
                startActivityForResult(intent, 101);
            }
        });

        // 수입분류 입력
        btnIncomeCategoryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryManager.class);
                intent.putExtra("CHECK_INT", 2); // 인텐트된 액티비티에서 1을 받을 경우와 2를 받을 경우 다른 액션을 주기 위해
                startActivityForResult(intent, 101);
            }
        });


        // 취소 버튼
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 저장
        btnSaveInIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    sqlDB = myDB.getWritableDatabase();

                    dateExpenseIncome = btnTodayOrSomeday.getText().toString();
                    sumMoney = Integer.parseInt(edtAmountOfMoneyInIncome.getText().toString()); // 입력한 금액 변수에 담기 - 입력되지 않으면 저장되지 않는다 (NOT NULL)
                    earnings = edtIncomeType.getText().toString(); // 입력한 수입내역 변수에 담기 - 입력되지 않으면 저장되지 않는다
                    tag = edtTagInIncome.getText().toString(); // 입력한 태그 변수에 담기

                    // 자주쓰는 내역에 체크
                    if(chkFavorite.isChecked()){
                        favoiteIncome = 1;
                    }

                    sqlDB.execSQL("INSERT INTO incomeTBL(dateExpenseIncome, sumMoney, earnings, acount, incomeSupCategory, incomeSubCategory, tag, favoiteIncome, timeValue) " +
                            "VALUES ('" + dateExpenseIncome + "', " + sumMoney + ", '" + earnings + "', " + acount + ", " + incomeSupCategory + ", " + incomeSubCategory + ", '" + tag + "', " + favoiteIncome + ", " + timeValue + ");");
                    sqlDB.close();

                    Toast.makeText(IncomeInsert.this, "입력 내용이 저장되었습니다", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e){
                    if(sumMoney == 0){
                        Toast.makeText(IncomeInsert.this, "금액을 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    }
                    if(earnings == null){
                        Toast.makeText(IncomeInsert.this, "사용내역을 입력하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    }
                }

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
            int checkNum = bundle.getInt("checkNum");

            sqlDB = myDB.getReadableDatabase();
            Cursor cursor;

            String tableNameInSuper = "incomeCategoryTBL";
            String columnNameInSuper = "incomeType";

            if(checkNum == 2){
                btnIncomeCategoryType.setText(menuName + " > " + categoryID);

                cursor = sqlDB.rawQuery("SELECT id FROM " + tableNameInSuper + " WHERE " + columnNameInSuper + " = '" + menuName + "';", null);
                cursor.moveToFirst();
                incomeSupCategory = cursor.getInt(0); // useSupCategory 대메뉴의 ID를 담는다

                String tableName = null;
                switch (menuName) {
                    case "주수입": tableName = "revenewListInincomeCategoryTBL"; break;
                    case "부수입": tableName = "extraIncomeListInincomeCategoryTBL"; break;
                    case "전월이월": tableName = "previousMonthListInincomeCategoryTBL"; break;
                    case "저축, 보험(수입)": tableName = "depositListInincomeCategoryTBL"; break; // 수입관련 테이블은 수입입력 페이지에서 적용
                }
                cursor = sqlDB.rawQuery("SELECT id FROM " + tableName + " WHERE listItem = '" + categoryID + "';", null);
                cursor.moveToFirst();
                incomeSubCategory = cursor.getInt(0); // useSubCategory 소범주의 id를 담는다, 출력할 때 menuReference에 해당하는 대메뉴 이름과 (인덱스가 같은) 함께 불러온다
                cursor.close();
                sqlDB.close();
            }
            if(checkNum == 4){
                btnAcountTypeInIncome.setText(menuName + " > " + categoryID);

                cursor = sqlDB.rawQuery("SELECT id FROM acountListTBL WHERE listItem = '" + categoryID + "';", null);
                cursor.moveToFirst();
                acount = cursor.getInt(0); // 선택한 현금계좌에 해당하는 ID를 담기, acountListTBL에서 찾아
                cursor.close();
                sqlDB.close();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {


        String todayOrSomeday = btnTodayOrSomeday.getText().toString();
        String amountOfmoney = edtAmountOfMoneyInIncome.getText().toString();


        outState.putString("TodayOrSomeDay", todayOrSomeday);
        outState.putString("AmountOfMoney", amountOfmoney);

        outState.putString("earnings", edtIncomeType.getText().toString());
        outState.putString("Account", btnAcountTypeInIncome.getText().toString());
        outState.putString("CategoryCheck", btnIncomeCategoryType.getText().toString());
        outState.putString("Tag", edtTagInIncome.getText().toString());
        outState.putBoolean("FavoriteCheck", chkFavorite.isChecked());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        btnTodayOrSomeday.setText(savedInstanceState.getString("TodayOrSomeDay"));
        edtAmountOfMoneyInIncome.setText(savedInstanceState.getString("AmountOfMoney"));

        edtIncomeType.setText(savedInstanceState.getString("earnings"));
        btnAcountTypeInIncome.setText(savedInstanceState.getString("Account"));
        btnIncomeCategoryType.setText(savedInstanceState.getString("CategoryCheck"));
        edtTagInIncome.setText(savedInstanceState.getString("Tag"));
        chkFavorite.setChecked(savedInstanceState.getBoolean("FavoriteCheck"));

        super.onRestoreInstanceState(savedInstanceState);
    }
}
