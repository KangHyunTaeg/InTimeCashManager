package com.example.class10.intimecashmanager.CategoryFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.class10.intimecashmanager.R;


public class CategoryFragment4 extends Fragment {

    public static CategoryFragment4 newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        CategoryFragment4 fragment4 = new CategoryFragment4();
        fragment4.setArguments(args);
        return fragment4;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_fragment4, container, false);
        return view;
    }
}
