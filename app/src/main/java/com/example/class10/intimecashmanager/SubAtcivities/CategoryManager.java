package com.example.class10.intimecashmanager.SubAtcivities;

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
import com.example.class10.intimecashmanager.CategoryExpenseFragment.CategoryFragment;
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.List;

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
        TabLayout tabs = (TabLayout)findViewById(R.id.tabsInCategoryManager);
        if(fragmentPagerAdapter.PAGE_NUMBER > 4){
            tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        } else{
            tabs.setTabGravity(TabLayout.GRAVITY_FILL);
            tabs.setTabMode(TabLayout.MODE_FIXED);
        }

        ViewPager pager = (ViewPager)findViewById(R.id.pagerInCategoryManager);
        pager.setAdapter(fragmentPagerAdapter); // 뷰페이저에 어댑터 장착
        tabs.setupWithViewPager(pager); // 탭레이아웃에 뷰페이저 연결
    }

}
