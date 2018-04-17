package com.dimfcompany.macapp2;

import android.graphics.drawable.Drawable;

/**
 * Created by Bios on 21.03.2018.
 */

public class basket_item
{
    private String name;
    private int price;
    private int count;
    private int image;

    public basket_item(String name, int price, int count, int image)
    {
        this.name = name;
        this.price = price;
        this.count = count;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public int getImage() {
        return image;
    }
}
