package com.example.class10.intimecashmanager.SubAtcivities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.class10.intimecashmanager.AdapterSetting.CustomFragmentPagerAdapter;
import com.example.class10.intimecashmanager.AdapterSetting.DataInit;
import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment;
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.List;

// 탭과 뷰페이저에 안착시키기 위해, 지출카테고리 데이터를 전달해주는 클래스
public class CategoryManager extends AppCompatActivity {

    DatabaseCreate myDB;
    SQLiteDatabase sqlDB;
    ArrayList<String> arrayTabName;
    ArrayList<Integer> arrayTabNameID;
    int checkNum;

    TabLayout tabs;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_manager);

        arrayTabName = new ArrayList<>();
        arrayTabNameID = new ArrayList<>();
        myDB = new DatabaseCreate(this);
        sqlDB = myDB.getReadableDatabase();


        tabs = (TabLayout)findViewById(R.id.tabsInCategoryManager);

        Intent inIntent = getIntent(); // ExpenseInsert에서 넘긴값을 받는다 (카테고리 버튼, 계좌 버튼 클릭시)
        // 예를 들어, "CHECK_INT"에 담아서 받은 값이 1이면, 지출 카테고리 테이블을 불러와 arrayTabName 배열에 담는다 (2면, 수입 테이블 / 3,4번은 출금계좌/입금계좌 부분에서 처리할 프로세스)
        if(inIntent.getIntExtra("CHECK_INT", 1) == 1){
            Cursor cursor;
            cursor = sqlDB.rawQuery("SELECT id, categoryMenu FROM expenseCategoryTBL;", null);
            while(cursor.moveToNext()){
                arrayTabNameID.add(cursor.getInt(0));
                arrayTabName.add(cursor.getString(1));
            }
            cursor.close();
            sqlDB.close();
            checkNum = 1;
        } else if(inIntent.getIntExtra("CHECK_INT", 1) == 2){
            Cursor cursor;
            cursor = sqlDB.rawQuery("SELECT id, incomeType FROM incomeCategoryTBL;", null); // 수입 카테고리 테이블에서 incomeType 불러와 담기
            while(cursor.moveToNext()){
                arrayTabNameID.add(cursor.getInt(0));
                arrayTabName.add(cursor.getString(1));
            }
            cursor.close();
            sqlDB.close();
            checkNum = 2;
        } else if(inIntent.getIntExtra("CHECK_INT", 1) == 3){
            arrayTabName.add("카드");
            arrayTabName.add("현금");
            checkNum = 3;
        } else if(inIntent.getIntExtra("CHECK_INT", 1) == 4){
            arrayTabName.add("현금");
            checkNum = 4;
        }

        // arrayTabName 배열의 원소를 차례대로 탭에 뿌려준다
        for(int i=0; i<arrayTabName.size(); i++){
            tabs.addTab(tabs.newTab().setText(arrayTabName.get(i)));
        }

        // 탭에 개수가 4개 이상이면 좌우 스크롤을 세팅해준다
        if(arrayTabName.size() > 4){
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else{
            tabs.setTabGravity(TabLayout.GRAVITY_FILL);
            tabs.setTabMode(TabLayout.MODE_FIXED);
        }

        pager = (ViewPager)findViewById(R.id.pagerInCategoryManager);
        CustomFragmentPagerAdapter customFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), arrayTabName.size(), checkNum); // 프레그먼트 아답터 객체 생성 (탭개수와 checkNum을 인자로 넘김)
        pager.setAdapter(customFragmentPagerAdapter);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}





// 이전 코드
/*
// 탭과 뷰페이저에 안착시키기 위해, 지출카테고리 데이터를 전달해주는 클래스
public class CategoryManager extends AppCompatActivity {

    DatabaseCreate myDB;
    SQLiteDatabase sqlDB;

    ArrayList<String> arrayMenuTab = new ArrayList<>();
    ArrayList<Integer> arrayMenuTabNum = new ArrayList<>();
    int menuReferenceNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_manager);

        TabLayout tabs = (TabLayout)findViewById(R.id.tabsInCategoryManager);
        ViewPager pager = (ViewPager)findViewById(R.id.pagerInCategoryManager);

        // 프레그먼트어뎁터
        // 탭 추가 - 데이터베이스 메뉴 테이블에서 불러오기
        myDB = new DatabaseCreate(this);
        sqlDB = myDB.getReadableDatabase();
        DataInit dataInit = new DataInit();
        List<Fragment> fragList = new ArrayList<>();

        Intent inIntent = getIntent();
        // "CHECK_INT"에 담아서 받은 값이 1이면, 지출 테이블 불러오는 sql문을 담고, 2면, 수입 테이블을 불러오는 sql문을 담는다, 3,4번은 출금계좌/입금계좌 부분에서 처리할 프로세스
        if(inIntent.getIntExtra("CHECK_INT", 1) == 1){
            Cursor cursor;
            cursor = sqlDB.rawQuery("SELECT id, categoryMenu FROM expenseCategoryTBL;", null); // 메뉴탭에 지출 분류 이름 담기위한 sql문
            while(cursor.moveToNext()){
                arrayMenuTabNum.add(cursor.getInt(0)); // 아이디 1... // 식비에 해당되는 테이블을 불러오려면, expenseCategoryTBL의 아이디와 expenseSubCategory의 menuReference를 일치시키면 되겠지?
                arrayMenuTab.add(cursor.getString(1)); // 메뉴 "식비"... // 이 String 배열이 tabLayout에 담길 이름
            }
            cursor.close();

            // 뷰 페이저 추가 - 데이터베이스 지출 소메뉴 테이블들에서 불러오기
            // arrayMenuTab이 "식비"이면, expenseSubCategory 테이블에서 menuReference=1인 값들을 가져와서, 해당 tab의 리스트에 뿌려준다
            for(int i=0; i<arrayMenuTab.size(); i++){
                fragList.add(i, CategoryFragment.newInstance("SELECT listItem FROM expenseSubCategory WHERE menuReference=" + (i+1) + ";", "expenseSubCategory", 1));
            }
            cursor.close();
            sqlDB.close();
        } else if(inIntent.getIntExtra("CHECK_INT", 1) == 2){
            Cursor cursor;
            cursor = sqlDB.rawQuery("SELECT id, incomeType FROM incomeCategoryTBL;", null); // 메뉴탭에 수입 분류 이름 담기위한 sql문
            while(cursor.moveToNext()){
                arrayMenuTabNum.add(cursor.getInt(0));
                arrayMenuTab.add(cursor.getString(1));
            }


            // 뷰 페이저 추가 - 데이터베이스 수입 소메뉴 테이블들에서 불러오기
            for(int i=0; i<dataInit.tableInIncomeCategory().size(); i++){
                fragList.add(i, CategoryFragment.newInstance("SELECT listItem FROM incomeSubCategory WHERE menuReference=" + (i+1) + ";", "incomeSubCategory", 2));
            }

            cursor.close();
            sqlDB.close();
        } else if(inIntent.getIntExtra("CHECK_INT", 1) == 3){
            // 카드/현금 관리 테이블에서 리스트 불러오기
            arrayMenuTab.add("카드");
            arrayMenuTab.add("현금");

            fragList.add(0, CategoryFragment.newInstance("SELECT listItem FROM cardListTBL;", "cardListTBL", 3));
            fragList.add(1, CategoryFragment.newInstance("SELECT listItem FROM acountListTBL;", "acountListTBL", 4));
        } else if(inIntent.getIntExtra("CHECK_INT", 1) == 4){
            // 현금 관리 테이블에서 리스트 불러오기
            arrayMenuTab.add("현금");
            fragList.add(0, CategoryFragment.newInstance("SELECT listItem FROM acountListTBL;", "acountListTBL", 4));
        }

        // 커스텀프래그먼트 어댑터 객체 생성, 매개변수로 탭과 뷰페이져용 데이터 배열 받으면 반복문을 통해 탭과 뷰페이저에 매칭시킨다
        CustomFragmentPagerAdapter fragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), arrayMenuTab, fragList);

        // 탭레이아웃, 뷰페이저에 장착하기

        if(fragmentPagerAdapter.PAGE_NUMBER > 4){
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else{
            tabs.setTabGravity(TabLayout.GRAVITY_FILL);
            tabs.setTabMode(TabLayout.MODE_FIXED);
        }


        pager.setAdapter(fragmentPagerAdapter); // 뷰페이저에 어댑터 장착
        tabs.setupWithViewPager(pager); // 탭레이아웃에 뷰페이저 연결
    }

}
*/
