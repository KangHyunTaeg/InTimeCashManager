package com.example.class10.intimecashmanager.AdapterSetting;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.class10.intimecashmanager.IncomeExpenseList;
import com.example.class10.intimecashmanager.R;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    DatabaseCreate myDB;
    SQLiteDatabase sqlDB;

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


        // String sqlSelectSentence = "SELECT * FROM expenseTBL WHERE category='설정값' AND  tag='설정값' AND date BETWEEN "2019-01-11" AND "2019-03-11";";


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
                    "`date` TEXT NOT NULL, " +
                    "`amount` INTEGER NOT NULL, " +
                    "`usage` TEXT NOT NULL, " +
                    "`usedPlance` TEXT NOT NULL, " +
                    "`paymentCheck` INTEGER, " +
                    "`acount` INTEGER, " +
                    "`card` INTEGER, " +
                    "`category` REAL, " +
                    "`tag` INTEGER, " +
                    "`favorite` INTEGER, " +
                    "`fixed` INTEGER, " +
                    "`timeValue` INTEGER);");

        }*/




        View itemView = convertView;
        if(itemView == null){
            itemView = mInflater.inflate(R.layout.item_listview, null);
        }
        TextView tvDate = (TextView)itemView.findViewById(R.id.tvDate);
        RelativeLayout listOfDetail = (RelativeLayout) itemView.findViewById(R.id.listOfDetail);
        ImageButton ibtnCategory = (ImageButton)itemView.findViewById(R.id.ibtnCategory);
        TextView tvUsage = (TextView)itemView.findViewById(R.id.tvUsage);
        TextView tvCategory = (TextView)itemView.findViewById(R.id.tvCategory);
        TextView tvSubCategory = (TextView)itemView.findViewById(R.id.tvSubCategory);
        TextView tvSumMoney = (TextView)itemView.findViewById(R.id.tvSumMoney);


        ItemData itemData = itemDataList.get(position);
        tvDate.setText(itemData.getDateList().toString());
        ibtnCategory.setImageResource(itemData.getImgCategory());
        tvUsage.setText(itemData.getUsage());
        tvCategory.setText(itemData.getUseCategory());
        tvSumMoney.setText(new String(String.valueOf(itemData.getSumMoney())));
        return itemView;
    }
}
