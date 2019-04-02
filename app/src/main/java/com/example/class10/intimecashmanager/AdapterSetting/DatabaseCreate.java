package com.example.class10.intimecashmanager.AdapterSetting;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;

public class DatabaseCreate extends SQLiteOpenHelper {
    static SQLiteDatabase sqlDB;

    public DatabaseCreate(Context context) {
        super(context, "inTimeCashManagerDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 지출 테이블
        db.execSQL("CREATE TABLE `expenseTBL` (`dateExpenseIncome` TEXT NOT NULL, `sumMoney` INTEGER NOT NULL,  `usage` TEXT NOT NULL,  " +
                "`usePlace` TEXT,  `paymentCheck` INTEGER,  `card` INTEGER,  `acount` INTEGER,  `useCategory` INTEGER,  `tag` TEXT,  " +
                "`favoiteExpense` INTEGER,  `fixedExpense` INTEGER,  `timeValue` INTEGER );");

        // 메뉴 테이블 만들고, 컬럼에 메뉴 불러와서 인서트하기
        db.execSQL("CREATE TABLE `expenseCategoryTBL` (" +
                "`id`INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`categoryMenu` TEXT NOT NULL);");

        ArrayList<String> arrayExpenseMenuTab = new ArrayList<>();
        MenuSetting.expenseMenuItem(arrayExpenseMenuTab);
        for(int i=0; i<arrayExpenseMenuTab.size(); i++){
            db.execSQL("INSERT INTO expenseCategoryTBL (categoryMenu) VALUES ('" + arrayExpenseMenuTab.get(i) + "');");
        }

        // 서브메뉴별로 테이블 만들고, 기본값 인서트하기
        // 식비 테이블
        db.execSQL("CREATE TABLE `foodsListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem1 = new ArrayList<>();
        MenuSetting.foodsListInExpenseMenuItem(menuItem1);
        for(int i=0; i<menuItem1.size(); i++){
            db.execSQL("INSERT INTO foodsListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem1.get(i) + "', 1);");
        }

        // 주거/통신 테이블
        db.execSQL("CREATE TABLE `homeListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem2 = new ArrayList<>();
        MenuSetting.homeListInExpenseMenuItem(menuItem2);
        for(int i=0; i<menuItem2.size(); i++){
            db.execSQL("INSERT INTO homeListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem2.get(i) + "', 2);");
        }

        // 생활용품 테이블
        db.execSQL("CREATE TABLE `livingListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem3 = new ArrayList<>();
        MenuSetting.livingListInExpenseMenuItem(menuItem3);
        for(int i=0; i<menuItem3.size(); i++){
            db.execSQL("INSERT INTO livingListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem3.get(i) + "', 3);");
        }

        // 의복/미용 테이블
        db.execSQL("CREATE TABLE `beautyListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem4 = new ArrayList<>();
        MenuSetting.beautyListInExpenseMenuItem(menuItem4);
        for(int i=0; i<menuItem4.size(); i++){
            db.execSQL("INSERT INTO beautyListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem4.get(i) + "', 4);");
        }

        // 건강/문화 테이블
        db.execSQL("CREATE TABLE `healthListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem5 = new ArrayList<>();
        MenuSetting.healthListInExpenseMenuItem(menuItem5);
        for(int i=0; i<menuItem5.size(); i++){
            db.execSQL("INSERT INTO healthListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem5.get(i) + "', 5);");
        }

        // 교육/육아 테이블
        db.execSQL("CREATE TABLE `educationListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem6 = new ArrayList<>();
        MenuSetting.educationListInExpenseMenuItem(menuItem6);
        for(int i=0; i<menuItem6.size(); i++){
            db.execSQL("INSERT INTO educationListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem6.get(i) + "', 6);");
        }

        // 교통/차량 테이블
        db.execSQL("CREATE TABLE `trafficListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem7 = new ArrayList<>();
        MenuSetting.trafficListInExpenseMenuItem(menuItem7);
        for(int i=0; i<menuItem7.size(); i++){
            db.execSQL("INSERT INTO trafficListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem7.get(i) + "', 7);");
        }

        // 경조사/회비 테이블
        db.execSQL("CREATE TABLE `eventListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem8 = new ArrayList<>();
        MenuSetting.eventListInExpenseMenuItem(menuItem8);
        for(int i=0; i<menuItem8.size(); i++){
            db.execSQL("INSERT INTO eventListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem8.get(i) + "', 8);");
        }

        // 세금/이자 테이블
        db.execSQL("CREATE TABLE `taxListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem9 = new ArrayList<>();
        MenuSetting.taxListInExpenseMenuItem(menuItem9);
        for(int i=0; i<menuItem9.size(); i++){
            db.execSQL("INSERT INTO taxListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem9.get(i) + "', 9);");
        }

        // 용돈/기타 테이블
        db.execSQL("CREATE TABLE `etcListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem10 = new ArrayList<>();
        MenuSetting.etcListInExpenseMenuItem(menuItem10);
        for(int i=0; i<menuItem10.size(); i++){
            db.execSQL("INSERT INTO etcListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem10.get(i) + "', 10);");
        }

        // 용돈/기타 테이블
        db.execSQL("CREATE TABLE `depositListInExpnseCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItem11 = new ArrayList<>();
        MenuSetting.depositListInExpenseMenuItem(menuItem11);
        for(int i=0; i<menuItem11.size(); i++){
            db.execSQL("INSERT INTO depositListInExpnseCategoryTBL(listItem, menuReference) VALUES ('" + menuItem11.get(i) + "', 11);");
        }




        // 수입분류 테이블 만들고, 컬럼에 수입타입 불러와서 인서트하기
        db.execSQL("CREATE TABLE `incomeCategoryTBL` (" +
                "`id`INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`incomeType` TEXT NOT NULL);");

        ArrayList<String> arrayIncomeMenuTab = new ArrayList<>();
        MenuSetting.incomeMenuItem(arrayIncomeMenuTab);
        for(int i=0; i<arrayIncomeMenuTab.size(); i++){
            db.execSQL("INSERT INTO incomeCategoryTBL (incomeType) VALUES ('" + arrayIncomeMenuTab.get(i) + "');");
        }

        // 주수입 테이블
        db.execSQL("CREATE TABLE `revenewListInincomeCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItemIncome = new ArrayList<>();
        MenuSetting.revenueListInIncomeMenuItem(menuItemIncome);
        for(int i=0; i<menuItemIncome.size(); i++){
            db.execSQL("INSERT INTO revenewListInincomeCategoryTBL(listItem, menuReference) VALUES ('" + menuItemIncome.get(i) + "', 1);");
        }

        // 부수입 테이블
        db.execSQL("CREATE TABLE `extraIncomeListInincomeCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItemIncome2 = new ArrayList<>();
        MenuSetting.extraIncomeListInIncomeMenuItem(menuItemIncome2);
        for(int i=0; i<menuItemIncome2.size(); i++){
            db.execSQL("INSERT INTO extraIncomeListInincomeCategoryTBL(listItem, menuReference) VALUES ('" + menuItemIncome2.get(i) + "', 2);");
        }

        // 전월이월 테이블
        db.execSQL("CREATE TABLE `previousMonthListInincomeCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItemIncome3 = new ArrayList<>();
        MenuSetting.previousMonthListInIncomeMenuItem(menuItemIncome3);
        for(int i=0; i<menuItemIncome3.size(); i++){
            db.execSQL("INSERT INTO previousMonthListInincomeCategoryTBL(listItem, menuReference) VALUES ('" + menuItemIncome3.get(i) + "', 3);");
        }

        // 전월이월 테이블
        db.execSQL("CREATE TABLE `depositListInincomeCategoryTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "`listItem` TEXT NOT NULL, `menuReference` INTEGER NOT NULL);");
        ArrayList<String> menuItemIncome4 = new ArrayList<>();
        MenuSetting.depositListInIncomeMenuItem(menuItemIncome4);
        for(int i=0; i<menuItemIncome4.size(); i++){
            db.execSQL("INSERT INTO depositListInincomeCategoryTBL(listItem, menuReference) VALUES ('" + menuItemIncome4.get(i) + "', 4);");
        }

        // 카드 테이블
        db.execSQL("CREATE TABLE `cardListTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `cardCompany` INTEGER NOT NULL, `listItem` TEXT NOT NULL, `settlementDay` INTEGER, `usedPeriod` TEXT, `useOrNot` INTEGER, `useAccount` INTEGER);");

        // 현금계좌 테이블
        db.execSQL("CREATE TABLE `acountListTBL` (`id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `listItem` TEXT NOT NULL, `propertyType` INTEGER, `bankCategory` INTEGER, `startAmount` INTEGER, `startDay` TEXT, `todayBalance` INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 데이터 검색
    public static void selectDB(String sqlSelectSentence, DatabaseCreate myDB, ArrayList<String> arrayList){
        Cursor cursor;
        sqlDB = myDB.getReadableDatabase();
        cursor = sqlDB.rawQuery(sqlSelectSentence, null);
        while(cursor.moveToNext()){
            arrayList.add(cursor.getString(0));
        }
        sqlDB.close();
        cursor.close();
    }
}
