package com.example.class10.intimecashmanager.AdapterSetting;

public class ItemData {

    //private String dateList; // 내역의 날짜
    private int imgCategory; // 분류 이모티콘
    private String usage; // 사용내역
    private String useCategory; // 내역의 분류
    private int sumMoney; // 사용금액

    public ItemData(int imgCategory, String usage, String useCategory, int sumMoney) {
        // this.dateList = dateList;
        this.imgCategory = imgCategory;
        this.usage = usage;
        this.useCategory = useCategory;
        this.sumMoney = sumMoney;
    }

    /*public String getDateList(){
        return dateList;
    }*/

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
