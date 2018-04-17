package com.dimfcompany.macapp2;

import android.graphics.drawable.Drawable;

/**
 * Created by Bios on 16.03.2018.
 */

public class ListItem
{
    private String Name;
    private String Desc;
    private String Price;
    private Drawable CardImage;

    public ListItem(String name, String desc, String price)
    {
        Name = name;
        Desc = desc;
        Price = price;


    }

    public ListItem(String name, String desc, String price, Drawable cardImage)
    {
        Name = name;
        Desc = desc;
        Price = price;
        CardImage = cardImage;

    }

    public String getName() {
        return Name;
    }

    public String getDesc() {
        return Desc;
    }

    public String getPrice() {
        return Price;
    }

    public Drawable getCardImage() {
        return CardImage;
    }
}
