package com.dimfcompany.macapp2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class allFood extends Fragment {

    private RecyclerView recView;
    private RecyclerView.Adapter testAdapter;

    private List<ListItem> allFooodList;

    public allFood() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_all_food, container, false);

    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        allFooodList=new ArrayList<>();
        allFooodList=((MainActivity)getActivity()).allFood();




        recView = (RecyclerView) view.findViewById(R.id.allRecycler);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        testAdapter=new MyAdapter(allFooodList,view.getContext());
        recView.setAdapter(testAdapter);

    }

}
