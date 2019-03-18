package com.example.incomeexpenselist;

public class ItemData {
    private int dateList; // 내역의 날짜
    private int imgCategory; // 분류 이모티콘
    private String usage; // 사용내역
    private String useCategory; // 내역의 분류
    private int sumMoney; // 사용금액

    public ItemData(int dateList, int imgCategory, String usage, String useCategory, int sumMoney) {
        this.dateList = dateList;
        this.imgCategory = imgCategory;
        this.usage = usage;
        this.useCategory = useCategory;
        this.sumMoney = sumMoney;
    }

    public int getDateList(){
        return dateList;
    }

    public int getImgCategory(){
        return imgCategory;
    }

    public String getUsage(){
        return usage;
    }

    public String getUseCategory(){
        return useCategory;
    }

    public int getSumMoney(){
        return sumMoney;
    }
}
