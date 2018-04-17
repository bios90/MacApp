package com.dimfcompany.macapp2;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimfcompany.macapp2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class snacks extends Fragment {

    private RecyclerView recView;
    private RecyclerView.Adapter testAdapter;

    private List<ListItem> snackItems;


    public snacks() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_snacks, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        recView = (RecyclerView) view.findViewById(R.id.snacksRecycler);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        snackItems=((MainActivity)getActivity()).getSnacks();

        testAdapter=new MyAdapter(snackItems,view.getContext());
        recView.setAdapter(testAdapter);


    }


}
