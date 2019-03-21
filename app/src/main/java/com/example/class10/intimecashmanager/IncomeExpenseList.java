package com.example.class10.intimecashmanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class10.intimecashmanager.AdapterSetting.ItemData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IncomeExpenseList extends AppCompatActivity {
    /*DatabaseCreate myDB;
    SQLiteDatabase sqlDB;*/

    ListView listIncomeAndExpense;
    ListViewAdapter adapter;

    Button btnCalendar, btnInOutList, btnStatistic, btnSetting, btnAddMoney;
    TextView tvSearchPeriod, tvSearchCategory, tvSearcgTag, tvFeedback;
    Button btnStartPeriod, btnEndPeriod, btnSearchCategory, btnSearchTag, btnSearch;
    EditText edtInputTag;


    // 임시 배열
    // String[] dateList = {};
    Integer[] imgBtnCategoryID = {R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house,
            R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house, R.drawable.house}; // 이미지버튼의 이름 배열, 10은 임의의 숫자
    String[] usageID = {"순대국", "짜장면", "짬뽕", "탕수육", "핸드폰요금", "쇼핑", "택시비", "버스비", "빕스", "한식부폐", "순대국", "짜장면", "짬뽕", "탕수육", "핸드폰요금", "쇼핑", "택시비", "버스비", "빕스", "한식부폐"};
    String[] categoryID = {"외식", "외식", "외식", "외식", "통신비", "쇼핑", "교통비", "교통비", "외식", "외식", "외식", "외식", "외식", "외식", "통신비", "쇼핑", "교통비", "교통비", "외식", "외식"};
    Integer[] moneyList = {10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_expense_list);



        listIncomeAndExpense = (ListView)findViewById(R.id.listIncomeAndExpense);

        List<ItemData> data = new ArrayList<>();
        for(int i=0; i<usageID.length; i++){
            data.add(new ItemData(imgBtnCategoryID[i], usageID[i], categoryID[i], moneyList[i]));
        }

        adapter = new ListViewAdapter(this, data);
        listIncomeAndExpense.setAdapter(adapter);

        final View[] dialogView = new View[1];

        btnCalendar = (Button)findViewById(R.id.btnCalendar);
        btnInOutList = (Button)findViewById(R.id.btnInOutList);
        btnStatistic = (Button)findViewById(R.id.btnStatistic);
        btnSetting = (Button)findViewById(R.id.btnSetting);
        btnAddMoney = (Button)findViewById(R.id.btnAddMoney);

        btnStartPeriod = (Button)findViewById(R.id.btnStartPeriod);
        btnEndPeriod = (Button)findViewById(R.id.btnEndPeriod);
        btnSearchCategory = (Button)findViewById(R.id.btnSearchCategory);
        btnSearchTag = (Button)findViewById(R.id.btnSearchTag);

        edtInputTag = (EditText)findViewById(R.id.edtInputTag);

        tvFeedback = (TextView)findViewById(R.id.tvFeedback);

        colorSetting();

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnInOutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.class10.intimecashmanager.IncomeExpenseList.class);
                startActivity(intent);
                finish();
            }
        });

        btnStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.class10.intimecashmanager.StatisticGraph.class);
                startActivity(intent);
                finish();
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.class10.intimecashmanager.EnvironmentSetting.class);
                startActivity(intent);
                finish();
            }
        });

        btnAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpenseInsert.class);
                startActivity(intent);
            }
        });


        btnStartPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDatePicker(btnStartPeriod);

            }
        });

        btnEndPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDatePicker(btnEndPeriod);
            }
        });

        btnSearchTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView[0] = (View)View.inflate(IncomeExpenseList.this, R.layout.dialog_input_tag, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(IncomeExpenseList.this);
                dlg.setTitle("# 태그입력");
                dlg.setView(dialogView[0]);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // btnSearchTag.setText(edtInputTag.getText().toString()); - Why NullPointerException?
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
    }

    public class ListViewAdapter extends BaseAdapter {
        Context context;
        List<ItemData> itemDataList;
        LayoutInflater mInflater;



        public ListViewAdapter(Context context, List<ItemData> itemDataList) {
            this.context = context;
            this.itemDataList = itemDataList;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return itemDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            /*btnSearchPeriod.setText(); // 기간검색 결과
            String categorySearch; // 분류검색 결과
            String tagSearch; // 태그검색 결과

            sqlDB = myDB.getReadableDatabase();
            Cursor cursor;
            cursor = sqlDB.rawQuery("SELECT date, FROM expenseTBL WHERE date=" + btnSearchPeriod.getText().toString() + ", " )*/

            View itemView = convertView;
            if(itemView == null){
                itemView = mInflater.inflate(R.layout.item_listview, null);
            }
            // TextView tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            RelativeLayout listOfDetail = (RelativeLayout) itemView.findViewById(R.id.listOfDetail);
            ImageButton ibtnCategory = (ImageButton)itemView.findViewById(R.id.ibtnCategory);
            TextView tvUsage = (TextView)itemView.findViewById(R.id.tvUsage);
            TextView tvCategory = (TextView)itemView.findViewById(R.id.tvCategory);
            TextView tvSumMoney = (TextView)itemView.findViewById(R.id.tvSumMoney);

            ItemData itemData = itemDataList.get(position);
            // tvDate.setText(itemData.getDateList().toString());
            ibtnCategory.setImageResource(itemData.getImgCategory());
            tvUsage.setText(itemData.getUsage());
            tvCategory.setText(itemData.getUseCategory());
            tvSumMoney.setText(new String(String.valueOf(itemData.getSumMoney())));
            return itemView;
        }
    }
    public void colorSetting(){
        btnCalendar.setBackgroundColor(Color.parseColor("#eeeeee"));
        btnInOutList.setTextColor(Color.RED);
        btnStatistic.setBackgroundColor(Color.parseColor("#eeeeee"));
        btnSetting.setBackgroundColor(Color.parseColor("#eeeeee"));
    }

    private void DialogDatePicker(final Button btn){
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
        DatePickerDialog alert = new DatePickerDialog(this,  mDateSetListener,
                cyear, cmonth, cday);
        alert.show();
    }
}
