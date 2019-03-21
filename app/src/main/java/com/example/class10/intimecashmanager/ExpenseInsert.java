package com.example.class10.intimecashmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class ExpenseInsert extends AppCompatActivity {
    Button btnCancle, btnSave, btnAcount, btnExpenseAtExpensePage, btnIncomeAtExpensePage;

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
