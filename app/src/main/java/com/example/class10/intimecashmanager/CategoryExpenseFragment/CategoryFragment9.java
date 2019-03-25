package com.example.class10.intimecashmanager.CategoryExpenseFragment;

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
import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;

public class CategoryFragment9 extends Fragment {
    public static DatabaseCreate myDB;
    public static ArrayAdapter<String> adapter;
    ListView listViewCategory9;
    Button btnAddItem;
    int num; // 롱 클릭했을 때 컨텍스트 메뉴에 넘겨줄 값을 받을 변수 선언
    String selectedItem;

    public static ArrayList<String> arrayList = new ArrayList<>();

    String sqlSelectSentence;
    String table = "taxListInExpnseCategoryTBL";
    String[] columns = {"taxList", "menuReference"};

    public static CategoryFragment9 newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        CategoryFragment9 fragment9 = new CategoryFragment9();
        fragment9.setArguments(args);
        return fragment9;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_fragment9, container, Boolean.parseBoolean(null));
        listViewCategory9 = (ListView)view.findViewById(R.id.listViewCategory9);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayList);
        listViewCategory9.setAdapter(adapter);

        myDB = new DatabaseCreate(getActivity());
        arrayList.clear();
        if(adapter.isEmpty()){

            sqlSelectSentence = "SELECT taxList FROM taxListInExpnseCategoryTBL;";
            DatabaseCreate.selectDB(sqlSelectSentence, myDB, arrayList);
        }

        // 리스트뷰의 아이템을 롱클릭하면 컨텍스트 메뉴가 나오고 삭제와 수정 가능
        registerForContextMenu(listViewCategory9);
        listViewCategory9.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                num = position;
                selectedItem = listViewCategory9.getItemAtPosition(position).toString();

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
                DialogLoad.DialogDeleteMenu(arrayList, myDB, num, selectedItem,listViewCategory9, adapter, table, columns);
                adapter.notifyDataSetChanged();
                return true;
            case 2:
                DialogLoad.DialogUpdateMenu(getContext(), num, table, columns, listViewCategory9, arrayList, selectedItem);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
