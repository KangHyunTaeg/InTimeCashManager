package com.example.class10.intimecashmanager.SubAtcivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class10.intimecashmanager.AdapterSetting.DialogLoad;
import com.example.class10.intimecashmanager.R;

public class ExpenseInsert extends AppCompatActivity {
    Button btnCancle, btnSave, btnAcount, btnExpenseAtExpensePage, btnIncomeAtExpensePage;
    EditText edtAmountOfMoney;
    Button btnMonthlyInstallment;
    TextView tvInstallmentMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_insert);

        btnIncomeAtExpensePage = (Button)findViewById(R.id.btnIncomeAtExpensePage);
        btnIncomeAtExpensePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IncomeInsert.class);
                startActivity(intent);
                finish();
            }
        });

        btnMonthlyInstallment = (Button)findViewById(R.id.btnMonthlyInstallment);
        edtAmountOfMoney = (EditText)findViewById(R.id.edtAmountOfMoney);
        tvInstallmentMonth = (TextView)findViewById(R.id.tvInstallmentMonth);
        btnMonthlyInstallment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogMonthlyInstallment(ExpenseInsert.this, edtAmountOfMoney, tvInstallmentMonth);
            }
        });

        btnAcount = (Button)findViewById(R.id.btnAcount);
        final View[] dialogView = new View[1];
        btnAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        btnCancle = (Button)findViewById(R.id.btnCancle);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
