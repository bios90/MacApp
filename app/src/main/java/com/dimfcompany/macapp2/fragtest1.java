package com.dimfcompany.macapp2;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class fragtest1 extends Fragment {

    private RecyclerView recView;
    private RecyclerView.Adapter testAdapter;

    public  List<ListItem> testItems;



    public fragtest1() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragtest1, container, false);
        return v;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        recView = (RecyclerView) view.findViewById(R.id.testRecycler);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        testItems=new ArrayList<>();
        testItems=((MainActivity)getActivity()).getBurgers();

        //FillTestList();


        testAdapter=new MyAdapter(testItems,view.getContext());
        recView.setAdapter(testAdapter);


    }


}
