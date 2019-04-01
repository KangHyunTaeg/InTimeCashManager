package com.example.class10.intimecashmanager.CategoryExpenseFragment;

import android.app.Activity;
import android.content.Intent;
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

import com.example.class10.intimecashmanager.AdapterSetting.DataInit;
import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.AdapterSetting.DialogLoad;
import com.example.class10.intimecashmanager.AdapterSetting.MenuSetting;
import com.example.class10.intimecashmanager.R;
import com.example.class10.intimecashmanager.SubAtcivities.ExpenseInsert;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {



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

    public static CategoryFragment newInstance(String sqlSelectSentence, String table, String[] columns) {
        // Required empty public constructor
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();

        args.putString("ARG_sqlSelectSentence", sqlSelectSentence);
        args.putString("ARG_table", table);
        args.putStringArray("ARG_colums", columns);

        fragment.setArguments(args); // 생성자의 파라미터로 받은 데이터를 번들에 담아 onCreate에서 받을 수 있도록 한다. (프레그먼트에서의 생성자 매개변수 처리)
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setArguments로 보낸 번들 데이터를 getArguments로 받아, 현재 프레그먼트에서 사용할 수 있는 static 변수에 담는다
        if(getArguments() != null){
            sqlSelectSentence = getArguments().getString("ARG_sqlSelectSentence");
            table = getArguments().getString("ARG_table");
            columns = getArguments().getStringArray("ARG_colums");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment, 인플레이트 시킬 정보는 생성자에서 매개변수로 받은 데이터로 불러와진 값
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        listViewCategory = (ListView)view.findViewById(R.id.listViewCategory);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayList); // 정보를 아답터에 담아서 리스트뷰에 장착시킨다
        listViewCategory.setAdapter(adapter);

        // 어뎁터에 담을 데이터 배열 (데이터베이스에서 불러오기)
        myDB = new DatabaseCreate(getContext());
        arrayList.removeAll(arrayList); // 먼저 배열에 뿌리기 전에 배열을 비워준다
        if(adapter.isEmpty()){
            DatabaseCreate.selectDB(sqlSelectSentence, myDB, arrayList); // myDB로 생성된 DB에서 sqlSelectSentence을 통해 테이블 데이터를 읽고, 그것을 arrayList<String> 배열에 담기
        }
        adapter.notifyDataSetChanged(); // 어뎁터 새로고침 (리스트뷰에 값변화를 실시간으로 반영)


        // 리스트뷰의 한 항목을 클릭하면 발생하는 이벤트 (선택하여, 보내기) - 문제점 : 환경설정에서 진입한 경우 클릭이벤트를 막는 방법은?
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> selectedArrayList = new ArrayList<>(); // 선택된 리스트뷰의 항목을 배열에 담기, 1개의 원소를 가진 배열임

                selectedItem = arrayList.get(position); //  선택한항목(탭이름별테이블의 해당아이템
                DatabaseCreate.selectDB("SELECT listItem FROM " + table + " WHERE listItem = '" + selectedItem + "';", myDB, selectedArrayList);
                String categoryID = selectedArrayList.get(0); // 0번째 값을 가져와 categoryID 변수에 담는다

                // 대메뉴 테이블 이름을 배열에 담는다
                DataInit dataInit = new DataInit();

                // 대메뉴 이름을 배열에 담는다
                ArrayList<String> menuNameList = new ArrayList<>();
                MenuSetting.expenseMenuItem(menuNameList);

                // 선택된 메뉴이름을 담을 변수
                String menuName = null;

                // 대메뉴 테이블만큼 반복해서 증가시킬 때, 현재 불러와진 테이블이름이 일치하는 테이블 이름 배열이 나타나면, 대메뉴이름 배열에서 해당인덱스의 메뉴이름을 가져와 menuName에 담는다
                for(int i=0; i<dataInit.tableInExpenseCategory().size(); i++){
                    if(table ==  dataInit.tableInExpenseCategory().get(i)){
                        menuName = menuNameList.get(i);
                    }
                }

                // 데이터를 번들 형태로 다시 돌려보내기
                Intent putIntent = new Intent(getContext(), ExpenseInsert.class);
                Bundle bundle = new Bundle();
                bundle.putString("categoryID", categoryID); // 리스트뷰에서 클릭한 항목
                bundle.putString("menuName", menuName); // categoryID 항목이 포함된 대메뉴이름을 담은 String 변수
                putIntent.putExtras(bundle);
                getActivity().setResult(Activity.RESULT_OK, putIntent);
                getActivity().finish();

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
