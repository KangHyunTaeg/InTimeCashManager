package com.example.class10.intimecashmanager.CategoryFragment;

import android.content.Context;
import android.net.Uri;
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

import com.example.class10.intimecashmanager.R;

import java.util.ArrayList;

public class CategoryFragment1 extends Fragment {
    ListView listViewCategory1;
    int num; // 롱 클릭했을 때 컨텍스트 메뉴에 넘겨줄 값을 받을 변수 선언

    // String[] foods = {"주식", "부식", "간식", "외식", "커피/음료", "술/유흥", "기타"};
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;



    public static CategoryFragment1 newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        CategoryFragment1 fragment1 = new CategoryFragment1();
        fragment1.setArguments(args);
        return fragment1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_fragment1, container, false);

        listViewCategory1 = (ListView)view.findViewById(R.id.listViewCategory1);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_single_choice, arrayList);
        listViewCategory1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewCategory1.setAdapter(adapter);

        arrayList.add("주식");
        arrayList.add("부식");
        arrayList.add("간식");
        arrayList.add("외식");
        arrayList.add("커피/음료");
        arrayList.add("술/유흥");
        arrayList.add("기타");

        // 리스트뷰의 아이템을 롱클릭하면 컨텍스트 메뉴가 나오고 삭제와 수정 가능
        registerForContextMenu(listViewCategory1);
        listViewCategory1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                num = position;
                return false;
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
        switch (item.getItemId()){
            case 1:
                arrayList.remove(num);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                arrayList.add("test");
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
