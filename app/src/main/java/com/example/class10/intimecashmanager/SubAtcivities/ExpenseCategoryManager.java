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
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.List;

// 탭과 뷰페이저에 안착시키기 위해, 지출카테고리 데이터를 전달해주는 클래스
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
        // 뷰 페이저 추가 - 데이터베이스 소메뉴 테이블들에서 불러오기
        fragList.add(0, CategoryExpenseFragment.newInstance("SELECT foodsList FROM foodsListInExpnseCategoryTBL WHERE menuReference=1;", "foodsListInExpnseCategoryTBL", new String[]{"foodsList", "menuReference"}));
        fragList.add(1, CategoryExpenseFragment.newInstance("SELECT homeList FROM homeListInExpnseCategoryTBL WHERE menuReference=2;", "homeListInExpnseCategoryTBL", new String[]{"homeList", "menuReference"}));
        fragList.add(2, CategoryExpenseFragment.newInstance("SELECT livingList FROM livingListInExpnseCategoryTBL WHERE menuReference=3;", "livingListInExpnseCategoryTBL", new String[]{"livingList", "menuReference"}));
        fragList.add(3, CategoryExpenseFragment.newInstance("SELECT beautyList FROM beautyListInExpnseCategoryTBL WHERE menuReference=4;", "beautyListInExpnseCategoryTBL", new String[]{"beautyList", "menuReference"}));
        fragList.add(4, CategoryExpenseFragment.newInstance("SELECT healthList FROM healthListInExpnseCategoryTBL WHERE menuReference=5;", "healthListInExpnseCategoryTBL", new String[]{"healthList", "menuReference"}));
        fragList.add(5, CategoryExpenseFragment.newInstance("SELECT educationList FROM educationListInExpnseCategoryTBL WHERE menuReference=6;", "educationListInExpnseCategoryTBL", new String[]{"educationList", "menuReference"}));
        fragList.add(6, CategoryExpenseFragment.newInstance("SELECT trafficList FROM trafficListInExpnseCategoryTBL WHERE menuReference=7;", "trafficListInExpnseCategoryTBL", new String[]{"trafficList", "menuReference"}));
        fragList.add(7, CategoryExpenseFragment.newInstance("SELECT eventList FROM eventListInExpnseCategoryTBL WHERE menuReference=8;", "eventListInExpnseCategoryTBL", new String[]{"eventList", "menuReference"}));
        fragList.add(8, CategoryExpenseFragment.newInstance("SELECT taxList FROM taxListInExpnseCategoryTBL WHERE menuReference=9;", "taxListInExpnseCategoryTBL", new String[]{"taxList", "menuReference"}));
        fragList.add(9, CategoryExpenseFragment.newInstance("SELECT etcList FROM etcListInExpnseCategoryTBL WHERE menuReference=10;", "etcListInExpnseCategoryTBL", new String[]{"etcList", "menuReference"}));
        fragList.add(10, CategoryExpenseFragment.newInstance("SELECT depositList FROM depositListInExpnseCategoryTBL WHERE menuReference=11;", "depositListInExpnseCategoryTBL", new String[]{"depositList", "menuReference"}));

        // 커스텀프래그먼트 어댑터 객체 생성, 매개변수로 탭과 뷰페이져용 데이터 배열 받으면 반복문을 통해 탭과 뷰페이저에 매칭시킨다
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), arrayExpenseMenuTab, fragList);

        // 탭레이아웃, 뷰페이저에 장착하기
        TabLayout tabs = (TabLayout)findViewById(R.id.tabsInCategoryManager);
        ViewPager pager = (ViewPager)findViewById(R.id.pagerInCategoryManager);
        pager.setAdapter(adapter); // 뷰페이저에 어댑터 장착
        tabs.setupWithViewPager(pager); // 탭레이아웃에 뷰페이저 연결
    }
}
