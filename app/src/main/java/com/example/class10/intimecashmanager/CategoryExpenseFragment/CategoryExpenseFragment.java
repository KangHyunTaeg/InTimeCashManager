package com.example.class10.intimecashmanager.CategoryExpenseFragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.AdapterSetting.DialogLoad;
import com.example.class10.intimecashmanager.AdapterSetting.MenuSetting;
import com.example.class10.intimecashmanager.R;
import com.example.class10.intimecashmanager.SubAtcivities.ExpenseCategoryManager;
import com.example.class10.intimecashmanager.SubAtcivities.ExpenseInsert;

import java.util.ArrayList;

public class CategoryExpenseFragment extends Fragment {



    public static DatabaseCreate myDB;
    public static ArrayAdapter<String> adapter;
    ListView listViewCategory;
    Button btnAddItem;
    int num; // 롱 클릭했을 때 컨텍스트 메뉴에 넘겨줄 값을 받을 변수 선언
    String selectedItem;

    public static ArrayList<String> arrayList = new ArrayList<>();

    static String sqlSelectSentence;
    static String table;
    static String[] columns;

    public static CategoryExpenseFragment newInstance(String sqlSelectSentence, String table, String[] columns) {
        // Required empty public constructor
        Bundle args = new Bundle();
        CategoryExpenseFragment fragment = new CategoryExpenseFragment();

        args.putString("ARG_sqlSelectSentence", sqlSelectSentence);
        args.putString("ARG_table", table);
        args.putStringArray("ARG_colums", columns);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            sqlSelectSentence = getArguments().getString("ARG_sqlSelectSentence");
            table = getArguments().getString("ARG_table");
            columns = getArguments().getStringArray("ARG_colums");


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_expense_fragment, container, false);
        listViewCategory = (ListView)view.findViewById(R.id.listViewCategory);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayList);
        listViewCategory.setAdapter(adapter);

        myDB = new DatabaseCreate(getContext());
        arrayList.removeAll(arrayList);
        if(adapter.isEmpty()){
            DatabaseCreate.selectDB(sqlSelectSentence, myDB, arrayList); // myDB로 생성된 DB에서 sqlSelectSentence을 통해 테이블 데이터를 읽고, 그것을 arrayList<String> 배열에 담기
        }
        adapter.notifyDataSetChanged();




        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> selectedArrayList = new ArrayList<>(); // 선택된 리스트뷰의 항목을 배열에 담기

                selectedItem = arrayList.get(position); //  선택한항목(탭이름별테이블의 해당아이템
                DatabaseCreate.selectDB("SELECT listItem FROM " + table + " WHERE listItem = '" + selectedItem + "';", myDB, selectedArrayList);

                String[] tableName = {"foodsListInExpnseCategoryTBL", "homeListInExpnseCategoryTBL", "livingListInExpnseCategoryTBL", "beautyListInExpnseCategoryTBL", "healthListInExpnseCategoryTBL",
                        "educationListInExpnseCategoryTBL", "trafficListInExpnseCategoryTBL", "eventListInExpnseCategoryTBL", "taxListInExpnseCategoryTBL", "etcListInExpnseCategoryTBL", "depositListInExpnseCategoryTBL"};
                ArrayList<String> menuNameList = new ArrayList<>();
                MenuSetting.expenseMenuItem(menuNameList);

                String menuName = null;
                /*switch (table){
                    case tableName[0]:
                        menuName = menuNameList.get(0);
                        break;
                }*/

                for(int i=0; i<tableName.length; i++){
                    if(table ==  tableName[i]){
                        menuName = menuNameList.get(i);
                    }
                }


                String categoryID = selectedArrayList.get(0);

                Intent putIntent = new Intent(getContext(), ExpenseInsert.class);
                putIntent.putExtra("categoryID", categoryID);
                putIntent.putExtra("menuName", menuName);
                startActivity(putIntent);

                ExpenseCategoryManager expenseCategoryManager = (ExpenseCategoryManager)ExpenseCategoryManager._ExpenseCategoryManager;  // ExpenseCategoryManager를 여기서 죵료시키기 위해
                // 여기에서 ExpenseCategoryManager를 선언하여 그 객체에 실제 ExpenseCategoryManager에 만들어 놓은 _ExpenseCategoryManager를 담는 것
                expenseCategoryManager.finish();

            }
        });


        // 리스트뷰의 아이템을 롱클릭하면 컨텍스트 메뉴가 나오고 삭제와 수정 가능
        registerForContextMenu(listViewCategory);
        listViewCategory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                num = position;
                selectedItem = listViewCategory.getItemAtPosition(position).toString();

                return false;
            }
        });

        // 항목 추가하기 - 다이얼로그 띄어서 입력후 반영하기
        btnAddItem = (Button)view.findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogLoad.DialogAddMenu(getContext(), table, columns, arrayList);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "삭제"); // 컨텍스트 메뉴
        menu.add(0, 2, 0, "수정");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()){
            case 1:
                DialogLoad.DialogDeleteMenu(arrayList, myDB, num, selectedItem,listViewCategory, adapter, table, columns);
                adapter.notifyDataSetChanged();
                return true;
            case 2:

                DialogLoad.DialogUpdateMenu(getContext(), num, table, columns, listViewCategory, arrayList, selectedItem);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }


}
