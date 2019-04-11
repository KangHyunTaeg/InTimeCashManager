package com.example.class10.intimecashmanager.AdapterSetting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class SubMenuArray{
    private String subMenuName;
    private int subMenuNum;

    SubMenuArray(String subMenuName, int subMenuNum){
        this.subMenuName = subMenuName;
        this.subMenuNum = subMenuNum;
    }

    public String getSubMenuName(){
        return subMenuName;
    }

    public int getSubMenuNum(){
        return subMenuNum;
    }
}

public class MenuSetting {
    public static void expenseMenuItem(ArrayList<String> tabArray){
        tabArray.add("식비");
        tabArray.add("주거, 통신");
        tabArray.add("생활용품");
        tabArray.add("의복, 미용");
        tabArray.add("건강, 문화");
        tabArray.add("교육, 육아");
        tabArray.add("교통, 차량");
        tabArray.add("경조사, 회비");
        tabArray.add("세금, 이자");
        tabArray.add("기타 비용");
        tabArray.add("저축, 보험");
    }

    public static void ExpenseSubMenuItem(List<SubMenuArray> menuArrays){
        menuArrays.add(new SubMenuArray("주식", 1));
        menuArrays.add(new SubMenuArray("부식", 1));
        menuArrays.add(new SubMenuArray("간식", 1));
        menuArrays.add(new SubMenuArray("외식", 1));
        menuArrays.add(new SubMenuArray("커피/음료", 1));
        menuArrays.add(new SubMenuArray("술/유흥", 1));
        menuArrays.add(new SubMenuArray("기타", 1));
        menuArrays.add(new SubMenuArray("관리비", 2));
        menuArrays.add(new SubMenuArray("공과금", 2));
        menuArrays.add(new SubMenuArray("이동통신", 2));
        menuArrays.add(new SubMenuArray("인터넷", 2));
        menuArrays.add(new SubMenuArray("월세", 2));
        menuArrays.add(new SubMenuArray("기타", 2));
        menuArrays.add(new SubMenuArray("가구/가전", 3));
        menuArrays.add(new SubMenuArray("주방/욕실", 3));
        menuArrays.add(new SubMenuArray("잡화소모", 3));
        menuArrays.add(new SubMenuArray("기타", 3));
        menuArrays.add(new SubMenuArray("의류비", 4));
        menuArrays.add(new SubMenuArray("패션잡화", 4));
        menuArrays.add(new SubMenuArray("헤어/뷰티", 4));
        menuArrays.add(new SubMenuArray("세탁수선비", 4));
        menuArrays.add(new SubMenuArray("기타", 4));
        menuArrays.add(new SubMenuArray("운동/레저", 5));
        menuArrays.add(new SubMenuArray("문화생활", 5));
        menuArrays.add(new SubMenuArray("여행", 5));
        menuArrays.add(new SubMenuArray("병원비", 5));
        menuArrays.add(new SubMenuArray("보장성보험", 5));
        menuArrays.add(new SubMenuArray("기타", 5));
        menuArrays.add(new SubMenuArray("등록금", 6));
        menuArrays.add(new SubMenuArray("학원/교재비", 6));
        menuArrays.add(new SubMenuArray("육아용품", 6));
        menuArrays.add(new SubMenuArray("기타", 6));
        menuArrays.add(new SubMenuArray("대중교통비", 7));
        menuArrays.add(new SubMenuArray("주유비", 7));
        menuArrays.add(new SubMenuArray("자동차보험", 7));
        menuArrays.add(new SubMenuArray("수리보수", 7));
        menuArrays.add(new SubMenuArray("기타", 7));
        menuArrays.add(new SubMenuArray("경조사비", 8));
        menuArrays.add(new SubMenuArray("모임회비", 8));
        menuArrays.add(new SubMenuArray("데이트", 8));
        menuArrays.add(new SubMenuArray("선물", 8));
        menuArrays.add(new SubMenuArray("기타", 8));
        menuArrays.add(new SubMenuArray("세금", 9));
        menuArrays.add(new SubMenuArray("대출이자", 9));
        menuArrays.add(new SubMenuArray("기타", 9));
        menuArrays.add(new SubMenuArray("용돈", 10));
        menuArrays.add(new SubMenuArray("기타", 10));
        menuArrays.add(new SubMenuArray("예금", 11));
        menuArrays.add(new SubMenuArray("적금", 11));
        menuArrays.add(new SubMenuArray("펀드", 11));
        menuArrays.add(new SubMenuArray("보험", 11));
        menuArrays.add(new SubMenuArray("투자", 11));
        menuArrays.add(new SubMenuArray("기타", 11));
    }

    public static void foodsListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("주식");
        arrayList.add("부식");
        arrayList.add("간식");
        arrayList.add("외식");
        arrayList.add("커피/음료");
        arrayList.add("술/유흥");
        arrayList.add("기타");
    }

    public static void homeListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("관리비");
        arrayList.add("공과금");
        arrayList.add("이동통신");
        arrayList.add("인터넷");
        arrayList.add("월세");
        arrayList.add("기타");
    }

    public static void livingListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("가구/가전");
        arrayList.add("주방/욕실");
        arrayList.add("잡화소모");
        arrayList.add("기타");
    }

    public static void beautyListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("의류비");
        arrayList.add("패션잡화");
        arrayList.add("헤어/뷰티");
        arrayList.add("세탁수선비");
        arrayList.add("기타");
    }

    public static void healthListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("운동/레저");
        arrayList.add("문화생활");
        arrayList.add("여행");
        arrayList.add("병원비");
        arrayList.add("보장성보험");
        arrayList.add("기타");
    }

    public static void educationListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("등록금");
        arrayList.add("학원/교재비");
        arrayList.add("육아용품");
        arrayList.add("기타");
    }

    public static void trafficListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("대중교통비");
        arrayList.add("주유비");
        arrayList.add("자동차보험");
        arrayList.add("수리보수");
        arrayList.add("기타");
    }

    public static void eventListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("경조사비");
        arrayList.add("모임회비");
        arrayList.add("데이트");
        arrayList.add("선물");
        arrayList.add("기타");
    }

    public static void taxListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("세금");
        arrayList.add("대출이자");
        arrayList.add("기타");
    }

    public static void etcListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("용돈");
        arrayList.add("기타");
    }

    public static void depositListInExpenseMenuItem(ArrayList<String>  arrayList){
        arrayList.add("예금");
        arrayList.add("적금");
        arrayList.add("펀드");
        arrayList.add("보험");
        arrayList.add("투자");
        arrayList.add("기타");
    }


    public static void incomeMenuItem(ArrayList<String> tabArray){
        tabArray.add("주수입");
        tabArray.add("부수입");
        tabArray.add("전월이월");
        tabArray.add("저축, 보험(수입)");
    }


    public static void incomeSubMenuItem(List<SubMenuArray> menuArraysIncome){
        menuArraysIncome.add(new SubMenuArray("급여", 1));
        menuArraysIncome.add(new SubMenuArray("상여", 1));
        menuArraysIncome.add(new SubMenuArray("사업소득", 1));
        menuArraysIncome.add(new SubMenuArray("기타", 1));
        menuArraysIncome.add(new SubMenuArray("이자/배당금", 2));
        menuArraysIncome.add(new SubMenuArray("카드캐쉬백", 2));
        menuArraysIncome.add(new SubMenuArray("중고판매", 2));
        menuArraysIncome.add(new SubMenuArray("기타", 2));
        menuArraysIncome.add(new SubMenuArray("전월이월", 3));
        menuArraysIncome.add(new SubMenuArray("잔액조정", 3));
        menuArraysIncome.add(new SubMenuArray("기타", 3));
        menuArraysIncome.add(new SubMenuArray("예금", 4));
        menuArraysIncome.add(new SubMenuArray("적금", 4));
        menuArraysIncome.add(new SubMenuArray("펀드", 4));
        menuArraysIncome.add(new SubMenuArray("보험", 4));
        menuArraysIncome.add(new SubMenuArray("투자", 4));
        menuArraysIncome.add(new SubMenuArray("기타", 4));
    }

    public static void revenueListInIncomeMenuItem(ArrayList<String> tabArray){
        tabArray.add("급여");
        tabArray.add("상여");
        tabArray.add("사업소득");
        tabArray.add("기타");
    }

    public static void extraIncomeListInIncomeMenuItem(ArrayList<String> tabArray){
        tabArray.add("이자/배당금");
        tabArray.add("카드캐쉬백");
        tabArray.add("중고판매");
        tabArray.add("기타");
    }

    public static void previousMonthListInIncomeMenuItem(ArrayList<String> tabArray){
        tabArray.add("전월이월");
        tabArray.add("잔액조정");
        tabArray.add("기타");
    }

    public static void depositListInIncomeMenuItem(ArrayList<String> tabArray){
        tabArray.add("예금");
        tabArray.add("적금");
        tabArray.add("펀드");
        tabArray.add("보험");
        tabArray.add("투자");
        tabArray.add("기타");
    }


}
