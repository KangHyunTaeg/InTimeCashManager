package com.example.class10.intimecashmanager.AdapterSetting;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

// 탭과 탭에 대응되는 뷰페이저에 프래그먼트를 인플레이트 시키는 클래스 (동일 목적 공용 기능)
public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
    public  static int PAGE_NUMBER;
    ArrayList<String> tabArray; // TabLayout에 담을 데이터 배열 (String)
    List<Fragment> fragList; // ViewPager에 inflate시킬 데이터 배열

    public CustomFragmentPagerAdapter(FragmentManager fm, ArrayList<String> tabArray, List<Fragment> fragList) {
        super(fm);

        // 탭에 들어갈 데이터를 매개변수로 동적String배열로 받아서 CustomFragmentPagerAdapter의 동적String배열에 매칭시키기
        this.tabArray = new ArrayList<>();
        for(int i=0; i<tabArray.size(); i++){
            this.tabArray.add(tabArray.get(i));
        }

        // 생성할 탭의 개수 정의 : 데이터의 개수만큼(배열의 크기)
        PAGE_NUMBER = tabArray.size();

        // ViewPager에 inflate시킬 Fragment 배열을 CustomFragmentPagerAdapter의 배열에 매칭시키기
        this.fragList = new ArrayList<>();
        for(int j=0; j<fragList.size(); j++){
            this.fragList.add(fragList.get(j));
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // 데이터의 개수(배열의 크기) 만큼 탭 생성
        for(int i=0; i<tabArray.size(); i++){
            if(position == i){
                return tabArray.get(i);
            }
        }
        return null;
        /*switch (position){
            case 0: return tabArray.get(0);
            case 1: return tabArray.get(1);
            case 2: return tabArray.get(2);
            case 3: return tabArray.get(3);
            case 4: return tabArray.get(4);
            case 5: return tabArray.get(5);
            case 6: return tabArray.get(6);
            case 7: return tabArray.get(7);
            case 8: return tabArray.get(8);
            case 9: return tabArray.get(9);
            case 10: return tabArray.get(10);
            default: return null;
        }*/

    }

    @Override
    public Fragment getItem(int position) {

        for(int i=0; i<fragList.size(); i++){
            if(position == i){
                return fragList.get(i);
            }
        }
        return null;
        /*switch (position){
            case 0: return CategoryFragment.newInstance("SELECT foodsList FROM foodsListInExpnseCategoryTBL;", "foodsListInExpnseCategoryTBL", new String[]{"foodsList", "menuReference"});
            case 1: return CategoryFragment.newInstance("SELECT homeList FROM homeListInExpnseCategoryTBL;", "homeListInExpnseCategoryTBL", new String[]{"homeList", "menuReference"});
            case 2: return CategoryFragment.newInstance("SELECT livingList FROM livingListInExpnseCategoryTBL;", "livingListInExpnseCategoryTBL", new String[]{"livingList", "menuReference"});
            case 3: return CategoryFragment.newInstance("SELECT beautyList FROM beautyListInExpnseCategoryTBL;", "beautyListInExpnseCategoryTBL", new String[]{"beautyList", "menuReference"});
            case 4: return CategoryFragment.newInstance("SELECT healthList FROM healthListInExpnseCategoryTBL;", "healthListInExpnseCategoryTBL", new String[]{"healthList", "menuReference"});
            case 5: return CategoryFragment.newInstance("SELECT educationList FROM educationListInExpnseCategoryTBL;", "educationListInExpnseCategoryTBL", new String[]{"educationList", "menuReference"});
            case 6: return CategoryFragment.newInstance("SELECT trafficList FROM trafficListInExpnseCategoryTBL;", "trafficListInExpnseCategoryTBL", new String[]{"trafficList", "menuReference"});
            case 7: return CategoryFragment.newInstance("SELECT eventList FROM eventListInExpnseCategoryTBL;", "eventListInExpnseCategoryTBL", new String[]{"eventList", "menuReference"});
            case 8: return CategoryFragment.newInstance("SELECT taxList FROM taxListInExpnseCategoryTBL;", "taxListInExpnseCategoryTBL", new String[]{"taxList", "menuReference"});
            case 9: return CategoryFragment.newInstance("SELECT etcList FROM etcListInExpnseCategoryTBL;", "etcListInExpnseCategoryTBL", new String[]{"etcList", "menuReference"});
            case 10: return CategoryFragment.newInstance("SELECT depositList FROM depositListInExpnseCategoryTBL;", "depositListInExpnseCategoryTBL", new String[]{"depositList", "menuReference"});
            default: return null;}*/
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
