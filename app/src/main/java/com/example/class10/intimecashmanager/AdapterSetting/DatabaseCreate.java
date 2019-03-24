package com.example.class10.intimecashmanager.AdapterSetting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseCreate extends SQLiteOpenHelper {
    public DatabaseCreate(Context context) {
        super(context, "inTimeCashManagerDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `expenseTBL` (`date` TEXT NOT NULL, `amount` INTEGER NOT NULL,  `use` TEXT NOT NULL,  " +
                "`place` TEXT,  `cardCheck` INTEGER,  `card` INTEGER,  `acount` INTEGER,  `category` INTEGER,  `tag` TEXT,  " +
                "`favoiteExpense` INTEGER,  `fixedExpense` INTEGER,  `timeValue` INTEGER );");

        db.execSQL("CREATE TABLE `expenseCategoryTBL` (`id` INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
/*
CREATE TABLE `expenseTBL` (`date`	TEXT NOT NULL, 	`amount`	INTEGER NOT NULL, 	`use`	TEXT NOT NULL, 	`place`	TEXT, 	`cardCheck`	INTEGER, 	`card`	INTEGER, 	`acount`	INTEGER, 	`category`	INTEGER, 	`tag`	TEXT, 	`favoiteExpense`	INTEGER, 	`fixedExpense`	INTEGER, 	`timeValue`	INTEGER );  */