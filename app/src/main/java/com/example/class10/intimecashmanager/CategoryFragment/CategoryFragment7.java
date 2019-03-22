package com.example.class10.intimecashmanager.CategoryFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.class10.intimecashmanager.R;

public class CategoryFragment7 extends Fragment {

    public static CategoryFragment7 newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        CategoryFragment7 fragment7 = new CategoryFragment7();
        fragment7.setArguments(args);
        return fragment7;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_fragment7, container, false);
        return view;
    }
}
