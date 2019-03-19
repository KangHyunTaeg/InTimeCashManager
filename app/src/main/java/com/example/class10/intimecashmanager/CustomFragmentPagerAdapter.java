package com.example.class10.intimecashmanager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomFragmentPagerAdapter extends FragmentPagerAdapter {
    private  static int PAGE_NUMBER = 4;

    public CustomFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return  "분류별";
            case 1:
                return "카드별";
            case 2:
                return "예산비교";
            case 3:
                return "목표현황";
            default:
                return null;
        }
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
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
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }
}
