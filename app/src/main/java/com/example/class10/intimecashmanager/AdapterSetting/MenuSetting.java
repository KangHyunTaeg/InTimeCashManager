package com.example.class10.intimecashmanager.AdapterSetting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
