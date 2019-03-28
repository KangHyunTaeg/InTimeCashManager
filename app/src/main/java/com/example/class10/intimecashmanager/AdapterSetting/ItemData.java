package com.example.class10.intimecashmanager.AdapterSetting;

public class ItemData {

    /*
            Cursor cursor;
            sqlDB = myDB.getReadableDatabase();
            cursor = sqlDB.rawQuery(sqlSelectSentence, null);
            while(cursor.moveToNext()){
                arrayList.add(cursor.getString(0));

            sqlDB.close();
            cursor.close();

               // 컬럼 참조하기
            db.execSQL("CREATE TABLE `expenseTBL` (" +
                    "`ID` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "`dateExpenseIncome` TEXT NOT NULL, " +
                    "`sumMoney` INTEGER NOT NULL, " +
                    "`usage` TEXT NOT NULL, " +
                    "`usedPlance` TEXT NOT NULL, " +
                    "`paymentCheck` INTEGER, " +
                    "`acount` INTEGER, " +
                    "`card` INTEGER, " +
                    "`useCategory` REAL, " +
                    "`tag` INTEGER, " +
                    "`favorite` INTEGER, " +
                    "`fixed` INTEGER, " +
                    "`timeValue` INTEGER);");

        }*/

    private String dateExpenseIncome; // 내역의 날짜
    private int imgCategory; // 분류 이모티콘
    private String usage; // 사용내역
    private String useCategory; // 내역의 분류
    private int sumMoney; // 사용금액

    public ItemData(String dateExpenseIncome, int imgCategory, String usage, String useCategory, int sumMoney) {
        this.dateExpenseIncome = dateExpenseIncome;
        this.imgCategory = imgCategory;
        this.usage = usage;
        this.useCategory = useCategory;
        this.sumMoney = sumMoney;
    }

    public String getDateList(){
        return dateExpenseIncome;
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
