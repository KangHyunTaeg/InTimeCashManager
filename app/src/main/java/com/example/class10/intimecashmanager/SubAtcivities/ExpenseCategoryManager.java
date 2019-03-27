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
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryExpenseFragment;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment10;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment11;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment2;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment3;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment4;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment5;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment6;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment7;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment8;
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment9;
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.List;

public class ExpenseCategoryManager extends AppCompatActivity {

    DatabaseCreate myDB;
    SQLiteDatabase sqlDB;
    Cursor cursor;
    ArrayList<String> arrayExpenseMenuTab = new ArrayList<>();
    String sqlSelectSentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_category_manager);

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
        // 뷰 페이저 추가
        String sqlSelectSentence = "SELECT foodsList FROM foodsListInExpnseCategoryTBL;";
        String table = "foodsListInExpnseCategoryTBL";
        String[] columns = {"foodsList", "menuReference"};
        fragList.add(0, CategoryExpenseFragment.newInstance(sqlSelectSentence, table, columns));
        fragList.add(1, CategoryExpenseFragment.newInstance("SELECT homeList FROM homeListInExpnseCategoryTBL;", "homeListInExpnseCategoryTBL", new String[]{"homeList", "menuReference"}));
        fragList.add(2, CategoryExpenseFragment.newInstance("SELECT livingList FROM livingListInExpnseCategoryTBL;", "livingListInExpnseCategoryTBL", new String[]{"livingList", "menuReference"}));
        fragList.add(3, CategoryExpenseFragment.newInstance(sqlSelectSentence, table, columns));
        fragList.add(4, CategoryExpenseFragment.newInstance(sqlSelectSentence, table, columns));
        fragList.add(5, CategoryExpenseFragment.newInstance(sqlSelectSentence, table, columns));
        fragList.add(6, CategoryExpenseFragment.newInstance(sqlSelectSentence, table, columns));
        fragList.add(7, CategoryExpenseFragment.newInstance(sqlSelectSentence, table, columns));
        fragList.add(8, CategoryExpenseFragment.newInstance(sqlSelectSentence, table, columns));
        fragList.add(9, CategoryExpenseFragment.newInstance(sqlSelectSentence, table, columns));
        fragList.add(10, CategoryExpenseFragment.newInstance(sqlSelectSentence, table, columns));

        /*fragList.add(CategoryFragment2.newInstance());
        fragList.add(CategoryFragment3.newInstance());
        fragList.add(CategoryFragment4.newInstance());
        fragList.add(CategoryFragment5.newInstance());
        fragList.add(CategoryFragment6.newInstance());
        fragList.add(CategoryFragment7.newInstance());
        fragList.add(CategoryFragment8.newInstance());
        fragList.add(CategoryFragment9.newInstance());
        fragList.add(CategoryFragment10.newInstance());
        fragList.add(CategoryFragment11.newInstance());*/

        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), arrayExpenseMenuTab, fragList);

        TabLayout tabs = (TabLayout)findViewById(R.id.tabsInCategoryManager);
        ViewPager pager = (ViewPager)findViewById(R.id.pagerInCategoryManager);
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        /*btnDeleteInCategoryManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnUpdateInCategoryManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }
}
