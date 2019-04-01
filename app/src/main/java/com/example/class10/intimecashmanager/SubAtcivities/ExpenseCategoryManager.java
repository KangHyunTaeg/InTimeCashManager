package com.example.class10.intimecashmanager.SubAtcivities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.class10.intimecashmanager.AdapterSetting.CustomFragmentPagerAdapter;
import com.example.class10.intimecashmanager.AdapterSetting.DataInit;
import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryExpenseFragment;
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.List;

// 탭과 뷰페이저에 안착시키기 위해, 지출카테고리 데이터를 전달해주는 클래스
public class ExpenseCategoryManager extends AppCompatActivity {

    public static Activity _ExpenseCategoryManager; // 다른 액티비티에서 현재 액티비티를 제어(종료)하기 위해 액티비티 객체 변수를 선언

    DatabaseCreate myDB;
    SQLiteDatabase sqlDB;
    Cursor cursor;
    ArrayList<String> arrayExpenseMenuTab = new ArrayList<>();
    String sqlSelectSentence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_category_manager);

        _ExpenseCategoryManager = ExpenseCategoryManager.this; // _ExpenseCategoryManager 변수에 현재 액티비티의 정보를 저장

        // 프레그먼트어뎁터
        // 탭 추가 - 데이터베이스 메뉴 테이블에서 불러오기
        myDB = new DatabaseCreate(this);
        sqlDB = myDB.getReadableDatabase();
        sqlSelectSentence = "SELECT categoryMenu FROM expenseCategoryTBL;";
        cursor = sqlDB.rawQuery(sqlSelectSentence, null);
        while(cursor.moveToNext()){
            arrayExpenseMenuTab.add(cursor.getString(0));
        }

        List<Fragment> fragList = new ArrayList<>();
        // 뷰 페이저 추가 - 데이터베이스 소메뉴 테이블들에서 불러오기

        DataInit dataInit = new DataInit();

        for(int i=0; i<dataInit.tableInExpenseCategory().size(); i++){
            fragList.add(i, CategoryExpenseFragment.newInstance("SELECT listItem FROM " + dataInit.tableInExpenseCategory().get(i)
                    + " WHERE menuReference=" + (i+1) + ";", dataInit.tableInExpenseCategory().get(i), new String[]{"listItem", "menuReference"}));
        }


        // 커스텀프래그먼트 어댑터 객체 생성, 매개변수로 탭과 뷰페이져용 데이터 배열 받으면 반복문을 통해 탭과 뷰페이저에 매칭시킨다
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), arrayExpenseMenuTab, fragList);

        // 탭레이아웃, 뷰페이저에 장착하기
        TabLayout tabs = (TabLayout)findViewById(R.id.tabsInCategoryManager);
        final ViewPager pager = (ViewPager)findViewById(R.id.pagerInCategoryManager);
        pager.setAdapter(adapter); // 뷰페이저에 어댑터 장착
        tabs.setupWithViewPager(pager); // 탭레이아웃에 뷰페이저 연결


    }

}
