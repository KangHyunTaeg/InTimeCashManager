package com.example.class10.intimecashmanager.AdapterSetting;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;
import java.util.Calendar;


public class DialogLoad {
    private static EditText edtInputTag, edtInputMonth, edtAddMenu, edtUpdateMenu;
    final static View[] dialogView = new View[1];
    static DatabaseCreate myDB;
    static SQLiteDatabase sqlDB;
    static String menuData;


    public static void DialogAddMenu(final Context context, final String table, final String[] columns, final ArrayList<String> arrayList){
        dialogView[0] = (View)View.inflate(context, R.layout.dialog_add_menu, null);
        edtAddMenu = (EditText)dialogView[0].findViewById(R.id.edtAddMenu);
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle("# 항목 추가");
        dlg.setView(dialogView[0]);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                menuData = edtAddMenu.getText().toString();
                myDB = new DatabaseCreate(context);
                sqlDB = myDB.getWritableDatabase();
                try{
                    if(menuData != null){
                        sqlDB.execSQL("INSERT INTO " + table + "(" + columns[0] + ", " + columns[1] + ") VALUES ('" + menuData + "', 1);");
                    }
                    edtAddMenu.setText("");
                    DatabaseCreate.selectDB("SELECT " + columns[0] + " FROM " + table + " WHERE " + columns[0] + "='" + menuData + "';", myDB, arrayList);

                    sqlDB.close();

                } catch(SQLiteException e){
                    Toast.makeText(context, "중복되지 않은 항목으로 다시 입력하세요", Toast.LENGTH_SHORT).show();
                }

            }
        });
        dlg.setNegativeButton("취소", null);
        dlg.show();
    }


    public static void DialogUpdateMenu(Context context, final int num,  final String table, final String[] columns, final ListView list, final ArrayList<String> arrayList, final String selectedItem){
        dialogView[0] = (View)View.inflate(context, R.layout.dialog_update_menu, null);
        edtUpdateMenu = (EditText)dialogView[0].findViewById(R.id.edtUpdateMenu);


        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle("# 항목 수정");
        menuData = list.getItemAtPosition(num).toString();
        edtUpdateMenu.setText(menuData);
        dlg.setView(dialogView[0]);
        myDB = new DatabaseCreate(context);
        sqlDB = myDB.getWritableDatabase();
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String updateItem = edtUpdateMenu.getText().toString();
                // sqlDB.execSQL("UPDATE homeListInExpnseCategoryTBL SET homeList='"+ updateItem +"', WHERE homeList='"+ selectedItem +"';");
                sqlDB.execSQL("UPDATE "+ table + " SET " + columns[0] + "='" + updateItem + "' WHERE " + columns[0] + " = '" + selectedItem + "';");
                arrayList.remove(arrayList.get(num));
                arrayList.add(updateItem);
                sqlDB.close();
            }
        });
        dlg.setNegativeButton("취소", null);
        dlg.show();
    }

    public static void DialogDeleteMenu(ArrayList<String> arrayList, DatabaseCreate myDB, int num, String selectedItem, ListView list, ArrayAdapter<String> adapter, final String table, final String[] columns){

        arrayList.remove(arrayList.get(num));

        sqlDB = myDB.getWritableDatabase();
        // sqlDB.execSQL("DELETE FROM homeListInExpnseCategoryTBL WHERE homeList='"+ selectedItem +"'");
        sqlDB.execSQL("DELETE FROM " + table + " WHERE " + columns[0] + " ='" + selectedItem + "';");
        // list.clearChoices();
        adapter.notifyDataSetChanged();
    }

    public static void DialogInputTag(final Button btn, Context context){
        dialogView[0] = (View)View.inflate(context, R.layout.dialog_input_tag, null);
        edtInputTag = (EditText)dialogView[0].findViewById(R.id.edtInputTag);
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle("# 태그입력");
        dlg.setView(dialogView[0]);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn.setText(edtInputTag.getText().toString());
            }
        });
        dlg.setNegativeButton("취소", null);
        dlg.show();
    }

    public static void DialogMonthlyInstallment(final Context context, final EditText edt, final TextView tv){
        dialogView[0] = (View)View.inflate(context, R.layout.dialog_input_monthly_installment, null);
        edtInputMonth = (EditText)dialogView[0].findViewById(R.id.edtInputMonth);

        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle("# 할부입력");

        dlg.setView(dialogView[0]);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String month = edtInputMonth.getText().toString();
                int monthlyInstallment = Integer.parseInt(edt.getText().toString())/Integer.parseInt(month);
                tv.setText(" ("+ month + "개월)");
                tv.setTextColor(Color.RED);
                edt.setText(""+monthlyInstallment);
            }
        });
        dlg.setNegativeButton("취소", null);
        dlg.show();
    }

    public static void DialogDatePicker(final Button btn, Context context){
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    // onDateSet method
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date_selected = String.valueOf(year) + "년 " + String.valueOf(monthOfYear+1)+
                                "월 "+String.valueOf(dayOfMonth) + "일 ";

                        btn.setText(date_selected);
                        /*Toast.makeText(IncomeExpenseList.this,
                                "Selected Date is ="+date_selected, Toast.LENGTH_SHORT).show();*/
                    }
                };
        DatePickerDialog alert = new DatePickerDialog(context,  mDateSetListener,
                cyear, cmonth, cday);
        alert.show();
    }

    public static void DialogExpenseCategor(){

    }


}