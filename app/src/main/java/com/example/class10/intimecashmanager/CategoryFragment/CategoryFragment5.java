package com.example.class10.intimecashmanager.CategoryFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.class10.intimecashmanager.R;

public class CategoryFragment5 extends Fragment {

    public static CategoryFragment5 newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        CategoryFragment5 fragment5 = new CategoryFragment5();
        fragment5.setArguments(args);
        return fragment5;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_fragment5, container, false);
        return view;
    }
}
