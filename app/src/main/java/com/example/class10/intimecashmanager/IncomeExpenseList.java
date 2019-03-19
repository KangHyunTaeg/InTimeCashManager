package com.example.class10.intimecashmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IncomeExpenseList extends AppCompatActivity {
    ListView listIncomeAndExpense;
    ListViewAdapter adapter;

    Button btnCalendar, btnInOutList, btnStatistic, btnSetting, btnSearch;
    TextView tvSearchPeriod, tvSearchCategory, tvSearcgTag;

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

        btnCalendar = (Button)findViewById(R.id.btnCalendar);
        btnInOutList = (Button)findViewById(R.id.btnInOutList);
        btnStatistic = (Button)findViewById(R.id.btnStatistic);
        btnSetting = (Button)findViewById(R.id.btnSetting);

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
}
