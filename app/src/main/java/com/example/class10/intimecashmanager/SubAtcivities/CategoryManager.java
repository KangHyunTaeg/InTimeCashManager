package com.example.class10.intimecashmanager.SubAtcivities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.class10.intimecashmanager.AdapterSetting.CustomFragmentPagerAdapter;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment1;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment10;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment11;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment12;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment2;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment3;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment4;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment5;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment6;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment7;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment8;
import com.example.class10.intimecashmanager.CategoryFragment.CategoryFragment9;
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_manager);


        // 프레그먼트어뎁터
        ArrayList<String> tabArray = new ArrayList<>();
        // 탭 추가
        tabArray.add("식비");
        tabArray.add("주거, 통신");
        tabArray.add("생활용품");
        tabArray.add("의복, 미용");
        tabArray.add("지식, 문화");
        tabArray.add("의료, 건강");
        tabArray.add("교육, 육아");
        tabArray.add("교통, 차량");
        tabArray.add("경조사, 회비");
        tabArray.add("세금, 이자");
        tabArray.add("기타 비용");
        tabArray.add("저축, 보험");

        List<Fragment> fragList = new ArrayList<>();
        // 뷰 페이저 추가
        fragList.add(CategoryFragment1.newInstance());
        fragList.add(CategoryFragment2.newInstance());
        fragList.add(CategoryFragment3.newInstance());
        fragList.add(CategoryFragment4.newInstance());
        fragList.add(CategoryFragment5.newInstance());
        fragList.add(CategoryFragment6.newInstance());
        fragList.add(CategoryFragment7.newInstance());
        fragList.add(CategoryFragment8.newInstance());
        fragList.add(CategoryFragment9.newInstance());
        fragList.add(CategoryFragment10.newInstance());
        fragList.add(CategoryFragment11.newInstance());
        fragList.add(CategoryFragment12.newInstance());

        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), tabArray, fragList);

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
