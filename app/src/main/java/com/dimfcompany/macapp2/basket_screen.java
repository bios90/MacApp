package com.dimfcompany.macapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class basket_screen extends AppCompatActivity {

    public List<basket_item> currentBasketItems=new ArrayList<basket_item>();
    ListView basketlist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_screen);

        basketlist=(ListView)findViewById(R.id.basketListview);
        currentBasketItems=items();

        Toast.makeText(this,String.valueOf(currentBasketItems.size()),Toast.LENGTH_SHORT).show();
        BasketAdapter basketAdapter = new BasketAdapter();

        basketlist.setAdapter(basketAdapter);


    }

    private List<basket_item> items()
    {

        List<basket_item> list=new ArrayList<basket_item>();
        for(int a =0;a<basket.names.length;a++)
        {
            if(basket.count[a]>0)
            {
                list.add(new basket_item(basket.names[a],basket.prices[a],basket.count[a],basket.images[a]));
            }
        }
        return list;

    }


    class BasketAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return currentBasketItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            convertView=getLayoutInflater().inflate(R.layout.basket_item,null);


            ImageView image = (ImageView)convertView.findViewById(R.id.basketImage);
            TextView name = (TextView)convertView.findViewById(R.id.basketName);
            TextView price = (TextView)convertView.findViewById(R.id.basketPrice);
            TextView count = (TextView)convertView.findViewById(R.id.basketCount);

            image.setImageResource(currentBasketItems.get(position).getImage());
            name.setText(currentBasketItems.get(position).getName());
            price.setText((String.valueOf(currentBasketItems.get(position).getPrice())));
            count.setText(String.valueOf(currentBasketItems.get(position).getCount()));

            return convertView;
        }
    }
}
