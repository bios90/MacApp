package com.dimfcompany.macapp2;


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

import java.util.ArrayList;
import java.util.List;


public class FragmentDrinks extends Fragment {

    Toolbar toolbar;

    TextView titleBar;

    private RecyclerView recView;
    private RecyclerView.Adapter testAdapter;

    private List<ListItem> drinkItems;


    public FragmentDrinks() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_drinks, container, false);
        return v;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        recView = (RecyclerView) view.findViewById(R.id.drinksRecycler);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        drinkItems=((MainActivity)getActivity()).getDrinks();

        testAdapter=new MyAdapter(drinkItems,view.getContext());
        recView.setAdapter(testAdapter);

    }

}
