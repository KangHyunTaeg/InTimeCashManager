package com.example.incomeexpenselist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listIncomeAndExpense;
    ListViewAdapter adapter;

    // 임시 배열
    Integer[] dateList = new Integer[10];
    Integer[] imgBtnCategoryID = new Integer[10]; // 이미지버튼의 이름 배열, 10은 임의의 숫자
    String[] usageID = new String[10];
    String[] categoryID = new String[10];
    Integer[] moneyList = new Integer[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listIncomeAndExpense = (ListView)findViewById(R.id.listIncomeAndExpense);

        List<ItemData> data = new ArrayList<>();
        for(int i=0; i<usageID.length; i++){
            data.add(new ItemData(dateList[i], imgBtnCategoryID[i], usageID[i], categoryID[i], moneyList[i]));
        }

        adapter = new ListViewAdapter(this, data);
    }

    public class ListViewAdapter extends BaseAdapter{
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
            TextView tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            ImageButton ibtnCategory = (ImageButton)itemView.findViewById(R.id.ibtnCategory);
            TextView tvUsage = (TextView)itemView.findViewById(R.id.tvUsage);
            TextView tvCategory = (TextView)itemView.findViewById(R.id.tvCategory);
            TextView tvSumMoney = (TextView)itemView.findViewById(R.id.tvSumMoney);
            return null;
        }
    }
}
