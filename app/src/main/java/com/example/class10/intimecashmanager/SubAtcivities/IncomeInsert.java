package com.example.class10.intimecashmanager.SubAtcivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.class10.intimecashmanager.R;
import com.example.class10.intimecashmanager.SubAtcivities.ExpenseInsert;

public class IncomeInsert extends AppCompatActivity {
    Button btnCancle, btnSave, btnExpenseAtIncomePage, btnIncomeAtIncomePage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_insert);

        btnExpenseAtIncomePage = (Button)findViewById(R.id.btnExpenseAtIncomePage);
        btnExpenseAtIncomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpenseInsert.class);
                startActivity(intent);
                finish();
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
