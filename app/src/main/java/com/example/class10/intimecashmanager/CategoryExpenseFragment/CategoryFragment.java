package com.example.class10.intimecashmanager.CategoryExpenseFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class10.intimecashmanager.AdapterSetting.DataInit;
import com.example.class10.intimecashmanager.AdapterSetting.DatabaseCreate;
import com.example.class10.intimecashmanager.AdapterSetting.DialogLoad;
import com.example.class10.intimecashmanager.AdapterSetting.MenuSetting;
import com.example.class10.intimecashmanager.R;
import com.example.class10.intimecashmanager.SubAtcivities.ExpenseInsert;

import java.util.ArrayList;

// CategoryManager의 ViewPager에 inflate시킬 fragment
public class CategoryFragment extends Fragment {



    public static DatabaseCreate myDB;

    public static ArrayAdapter<String> arrayAdapter;
    ListView listViewCategory;
    Button btnAddItem;
    TextView tvButtonMessage;
    int num; // 롱 클릭했을 때 컨텍스트 메뉴에 넘겨줄 값을 받을 변수 선언
    String selectedItem;

    public static ArrayList<String> arrayList = new ArrayList<>();

    static String sqlSelectSentence;
    static String table;
    static int checkNum; // CategoryManager에서 생성자로 받은 숫자 (1 = 지출항목, 2 = 수입항목, 3 = 카드항목, 4 = 현금항목)
    // 인플레이트 시킬 페이지로 보낼 정보 분별에도 사용됨

    int menuReferenceNum = 0; // 해당 아이템의 매뉴탭의 아이디
    String supMenuName = null; // 해당 아이템의 매뉴탭의 이름

    public static CategoryFragment newInstance(String sqlSelectSentence, String table, int checkNum) {
        // Required empty public constructor
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();

        args.putString("ARGs_sqlSelectSentence", sqlSelectSentence);
        args.putString("ARGs_table", table);
        args.putInt("ARGs_checkNum", checkNum);

        fragment.setArguments(args); // 생성자의 파라미터로 받은 데이터를 번들에 담아 onCreate에서 받을 수 있도록 한다. (프레그먼트에서의 생성자 매개변수 처리)
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setArguments로 보낸 번들 데이터를 getArguments로 받아, 현재 프레그먼트에서 사용할 수 있는 static 변수에 담는다
        if(getArguments() != null){
            sqlSelectSentence = getArguments().getString("ARGs_sqlSelectSentence");
            table = getArguments().getString("ARGs_table");
            checkNum = getArguments().getInt("ARGs_checkNum");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment, 인플레이트 시킬 정보는 생성자에서 매개변수로 받은 데이터로 불러와진 값
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        listViewCategory = (ListView)view.findViewById(R.id.listViewCategory);

        // 어뎁터에 담을 데이터 배열 (데이터베이스에서 불러오기)
        myDB = new DatabaseCreate(getContext());
        arrayList.removeAll(arrayList); // 먼저 배열에 뿌리기 전에 배열을 비워준다
        DatabaseCreate.selectSingleDB(sqlSelectSentence, myDB, arrayList); // myDB로 생성된 DB에서 sqlSelectSentence을 통해 테이블 데이터를 읽고, 그것을 arrayList<String> 배열에 담기
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayList); // arrayList<String> 배열의 정보를 아답터에 담아서 리스트뷰 adapter에 장착시킨다
        listViewCategory.setAdapter(arrayAdapter); // 리스트뷰에 adapter를 뿌려준다
        arrayAdapter.notifyDataSetChanged(); // 어뎁터 새로고침 (리스트뷰에 값변화를 실시간으로 반영)


        // 리스트뷰의 한 항목을 클릭하면 발생하는 이벤트 (선택하여, 보내기) - 문제점 : 환경설정에서 진입한 경우 클릭이벤트를 막는 방법은?
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  선택한항목의 아이템 구하기
                selectedItem = listViewCategory.getItemAtPosition(position).toString();

                // 선택한 항목의 탭(메뉴) 이름 구하기
                ArrayList<String> supMenuNameList = new ArrayList<>();
                ArrayList<Integer> supMenuNameListNum = new ArrayList<>();

                switch (checkNum){
                    case 1: // 지출일 때,
                        // DatabaseCreate.selectSingleDB("SELECT categoryMenu FROM expenseCategoryTBL LEFT JOIN expenseSubCategory ON expenseCategoryTBL.id = expenseSubCategory.menuReference WHERE expenseSubCategory.listItem = '" + selectedItem +"';", myDB, supMenuNameList);
                        SQLiteDatabase sqlDB;
                        Cursor cursor;

                        sqlDB = myDB.getReadableDatabase();
                        cursor = sqlDB.rawQuery("SELECT expenseCategoryTBL.id, expenseCategoryTBL.categoryMenu FROM expenseCategoryTBL LEFT JOIN expenseSubCategory ON expenseCategoryTBL.id = expenseSubCategory.menuReference WHERE expenseSubCategory.listItem = '" + selectedItem +"';", null);
                        while(cursor.moveToNext()){
                            supMenuNameListNum.add(cursor.getInt(0));
                            supMenuNameList.add(cursor.getString(1));
                        }
                        sqlDB.close();
                        cursor.close();

                        menuReferenceNum = supMenuNameListNum.get(0);
                        supMenuName = supMenuNameList.get(0);
                        break;
                    case 2: // 수입일 때,
                        DatabaseCreate.selectSingleDB("SELECT incomeType FROM incomeCategoryTBL LEFT JOIN incomeSubCategory ON incomeCategoryTBL.id = incomeSubCategory.menuReference WHERE incomeSubCategory.listItem = '" + selectedItem +"';", myDB, supMenuNameList);
                        supMenuName = supMenuNameList.get(0);
                        break;
                    case 3: // 카드일 때,
                        // DatabaseCreate.selectSingleDB("SELECT listItem FROM cardListTBL WHERE listItem = '" + selectedItem +"';", myDB, supMenuNameList);
                        supMenuName = "카드";
                        break;
                    case 4: // 현금일 때,
                        // DatabaseCreate.selectSingleDB("SELECT listItem FROM acountListTBL WHERE listItem = '" + selectedItem +"';", myDB, supMenuNameList);
                        supMenuName = "현금";
                        break;
                }

                // 데이터를 번들 형태로 다시 돌려보내기 (현재는 지출입력 페이지에만 넘기는 중임)
                Intent putIntent = new Intent(getContext(), ExpenseInsert.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectedItem", selectedItem); // 리스트뷰에서 클릭한 항목
                bundle.putString("menuName", supMenuName); // categoryID 항목이 포함된 대메뉴이름을 담은 String 변수
                bundle.putInt("checkNum", checkNum);
                putIntent.putExtras(bundle);
                getActivity().setResult(Activity.RESULT_OK, putIntent);
                getActivity().finish();

            }
        });


        // 리스트뷰의 아이템을 롱클릭하면 컨텍스트 메뉴가 나오고 삭제와 수정 가능
        // 롱클릭시 클릭한 아이템의 포지션 받아오기
        registerForContextMenu(listViewCategory); // 리스트뷰에 컨텍스트메뉴 장착
        listViewCategory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                num = position; // 포지션의 인덱스 번호
                selectedItem = listViewCategory.getItemAtPosition(position).toString(); // 포지션에 해당되는 아이템이름
                return false;
            }
        });

        // 항목 추가하기 - 다이얼로그 띄어서 입력후 반영하기
        btnAddItem = (Button)view.findViewById(R.id.btnAddItem);
        tvButtonMessage = (TextView)view.findViewById(R.id.tvButtonMessage);
        if(checkNum == 1 || checkNum == 2){
            btnAddItem.setVisibility(View.VISIBLE);
            tvButtonMessage.setVisibility(View.VISIBLE);
        }
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoad.DialogAddMenu(getContext(), table, menuReferenceNum, arrayList);
                DatabaseCreate.selectSingleDB(sqlSelectSentence, myDB, arrayList);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    /*@Override
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
                DialogLoad.DialogDeleteMenu(arrayList, myDB, num, selectedItem,listViewCategory, arrayAdapter, table, columns);
                arrayAdapter.notifyDataSetChanged();
                return true;
            case 2:

                DialogLoad.DialogUpdateMenu(getContext(), num, table, columns, listViewCategory, arrayList, selectedItem);
                arrayAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }*/


}
