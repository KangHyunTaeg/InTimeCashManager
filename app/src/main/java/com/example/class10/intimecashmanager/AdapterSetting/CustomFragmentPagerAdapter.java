package com.example.class10.intimecashmanager.AdapterSetting;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.class10.intimecashmanager.StatisticsFragment.StatisticBudgetFragment;
import com.example.class10.intimecashmanager.StatisticsFragment.StatisticCardFragment;
import com.example.class10.intimecashmanager.StatisticsFragment.StatisticCategoryFragment;
import com.example.class10.intimecashmanager.StatisticsFragment.StatisticGoalFragment;

import java.util.ArrayList;
import java.util.List;


public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
    private  static int PAGE_NUMBER;
    ArrayList<String> tabArray;
    List<Fragment> fragList;


    public CustomFragmentPagerAdapter(FragmentManager fm, ArrayList<String> tabArray, List<Fragment> fragList) {
        super(fm);
        this.tabArray = new ArrayList<>();
        for(int i=0; i<tabArray.size(); i++){
            this.tabArray.add(tabArray.get(i));
        }
        PAGE_NUMBER = tabArray.size();

        this.fragList = new ArrayList<>();
        for(int j=0; j<fragList.size(); j++){
            this.fragList.add(fragList.get(j));
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        for(int i=0; i<tabArray.size(); i++){
            if(position == i){
                return tabArray.get(i);
            }
        }
        return null;

        /*switch (position){

            case 0:
                return tabArray.get(0);
            case 1:
                return tabArray.get(1);
            case 2:
                return tabArray.get(2);
            case 3:
                return tabArray.get(3);
            default:
                return null;
        }*/

    }




    @Override
    public Fragment getItem(int position) {

        for(int i=0; i<fragList.size(); i++){
            if(position == i){
                return fragList.get(i);
            }
        }

        /*switch (position){
            case 0:
                return StatisticCategoryFragment.newInstance();
            case 1:
                return StatisticCardFragment.newInstance();
            case 2:
                return StatisticBudgetFragment.newInstance();
            case 3:
                return StatisticGoalFragment.newInstance();
            default:
                return null;
        }*/
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
