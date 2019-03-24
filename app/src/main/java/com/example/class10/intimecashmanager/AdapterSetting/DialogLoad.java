package com.example.class10.intimecashmanager.AdapterSetting;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class10.intimecashmanager.R;

import java.util.Calendar;

public class DialogLoad {
    private static EditText edtInputTag, edtInputMonth;
    final static View[] dialogView = new View[1];


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
