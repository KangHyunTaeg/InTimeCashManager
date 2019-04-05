package com.example.class10.intimecashmanager.AdapterSetting;

import java.util.ArrayList;

public class DataInit {

    public ArrayList<String> tableInExpenseCategory(){
        ArrayList<String> tableInExpenseCategory = new ArrayList<>();
        tableInExpenseCategory.add("foodsListInExpnseCategoryTBL");
        tableInExpenseCategory.add("homeListInExpnseCategoryTBL");
        tableInExpenseCategory.add("livingListInExpnseCategoryTBL");
        tableInExpenseCategory.add("beautyListInExpnseCategoryTBL");
        tableInExpenseCategory.add("healthListInExpnseCategoryTBL");
        tableInExpenseCategory.add("educationListInExpnseCategoryTBL");
        tableInExpenseCategory.add("trafficListInExpnseCategoryTBL");
        tableInExpenseCategory.add("eventListInExpnseCategoryTBL");
        tableInExpenseCategory.add("taxListInExpnseCategoryTBL");
        tableInExpenseCategory.add("etcListInExpnseCategoryTBL");
        tableInExpenseCategory.add("depositListInExpnseCategoryTBL");

        return tableInExpenseCategory;
    }

    public ArrayList<String> tableInIncomeCategory(){
        ArrayList<String> tableInIncomeCategory = new ArrayList<>();
        tableInIncomeCategory.add("revenewListInincomeCategoryTBL");
        tableInIncomeCategory.add("extraIncomeListInincomeCategoryTBL");
        tableInIncomeCategory.add("previousMonthListInincomeCategoryTBL");
        tableInIncomeCategory.add("depositListInincomeCategoryTBL");

        return tableInIncomeCategory;
    }

    public String tableInAccount(){
        String tableInAccount = "acountListTBL";
        return tableInAccount;
    }

    public String tableInCard(){
        String tableInCard = "cardListTBL";
        return tableInCard;
    }


}
