package com.dimfcompany.macapp2;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.zip.Inflater;



/**
 * Created by Bios on 16.03.2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<ListItem> listItems;
    private Context context;

    private Typeface introHeadR;
    private Typeface sansSheriff;


    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        introHeadR=Typeface.createFromAsset(context.getAssets(),"fonts/IntroHeadR-Base.ttf");
        sansSheriff=Typeface.createFromAsset(context.getAssets(),"fonts/2211.ttf");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);
        holder.tvName.setText(listItem.getName());
        holder.tvDesc.setText(listItem.getDesc());
        holder.tvPrice.setText(listItem.getPrice());
        holder.cardImage.setImageDrawable(listItem.getCardImage());

        holder.tvName.setTypeface(introHeadR,Typeface.BOLD);
        holder.tvDesc.setTypeface(sansSheriff);

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              LoadDetails(listItem);
            }
        });
        holder.cardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basket.BasketAdd(listItem.getName(),context);
            }
        });
        holder.tvDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDetails(listItem);
            }
        });
        holder.cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDetails(listItem);
            }
        });
        holder.tvPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basket.BasketAdd(listItem.getName(),context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName;
        public TextView tvDesc;
        public TextView tvPrice;
        public ImageView cardImage;
        public ImageButton cardAdd;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName=(TextView)itemView.findViewById(R.id.textName);
            tvDesc=(TextView)itemView.findViewById(R.id.textDescShort);
            tvPrice=(TextView)itemView.findViewById(R.id.textPrice);
            cardImage=(ImageView)itemView.findViewById(R.id.cardImage);
            cardAdd=(ImageButton)itemView.findViewById(R.id.cardAddButton);

        }
    }

    void LoadDetails(ListItem listItem)
    {
        Intent loadDetail = new Intent(context, itemDetails.class);
        loadDetail.putExtra("itemName", listItem.getName());
        context.startActivity(loadDetail);

    }
}

