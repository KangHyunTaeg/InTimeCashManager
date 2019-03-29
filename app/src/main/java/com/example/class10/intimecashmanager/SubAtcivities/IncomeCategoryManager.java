package com.example.class10.intimecashmanager.SubAtcivities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.class10.intimecashmanager.AdapterSetting.CustomFragmentPagerAdapter;
import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.CategoryIncomeFragment.CategoryIncomeFragment;
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.List;

public class IncomeCategoryManager extends AppCompatActivity {
    DatabaseCreate myDB;
    SQLiteDatabase sqlDB;
    Cursor cursor;
    ArrayList<String> arrayIncomeMenuTab = new ArrayList<>();
    String sqlSelectSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_category_manager);

        // 프레그먼트어뎁터
        // 탭 추가 - 데이터베이스 메뉴 테이블에서 불러오기
        myDB = new DatabaseCreate(this);
        sqlDB = myDB.getReadableDatabase();
        sqlSelectSentence = "SELECT incomeType FROM incomeCategoryTBL;";
        cursor = sqlDB.rawQuery(sqlSelectSentence, null);
        while(cursor.moveToNext()){
            arrayIncomeMenuTab.add(cursor.getString(0));
        }

        List<Fragment> fragList = new ArrayList<>();
        // 뷰 페이저 추가

        ArrayList<String> tableName = new ArrayList<>();
        tableName.add("revenewListInincomeCategoryTBL");
        tableName.add("extraIncomeListInincomeCategoryTBL");
        tableName.add("previousMonthListInincomeCategoryTBL");
        tableName.add("depositListInincomeCategoryTBL");

        for(int i=0; i<tableName.size(); i++){
            fragList.add(i, CategoryIncomeFragment.newInstance("SELECT listItem FROM " + tableName.get(i) + " WHERE menuReference=" + (i+1) + ";", tableName.get(i), new String[]{"listItem", "menuReference"}));
        }

        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), arrayIncomeMenuTab, fragList);

        final TabLayout tabs = (TabLayout)findViewById(R.id.tabsInIncomeCategoryManager);
        final ViewPager pager = (ViewPager)findViewById(R.id.pagerInIncomeCategoryManager);

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
    }
}


