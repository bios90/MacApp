package com.dimfcompany.macapp2;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bios on 21.03.2018.
 */

public class basket
{
   static public Context currentContext;

   static int bigmac ;
   static int bigtasty ;
   static int blackcoffe ;
   static int cheeseburger;
   static int chikenburger;
   static int cola;
   static int fileofish;
   static int hamburger;
   static int naggets;
   static int potato;
   static int sprite;
   static int tea;
   static int village;
    static boolean basketLoaded;


    public static String[] names = {"Биг Мак", "Биг Тейсти", "Черный Кофе", "Чизбургер", "Кока-Кола", "Чикенбургер", "Филе-о-Фиш", "Гамбургер", "Наггетсы", "Картофель Фри", "Спрайт", "Чай", "Картофель по-деревенски"};
    public static int[] count = new int[names.length];
    public static int[] images = {R.drawable.bigmac, R.drawable.bigtasty,R.drawable.blackcofe,R.drawable.cheeseburger,R.drawable.cola,R.drawable.chickenburger,R.drawable.fileofish,R.drawable.hamburger,R.drawable.naggets,R.drawable.potato,R.drawable.sprite,R.drawable.tea,R.drawable.villagepotato};
    public static int[] prices = {130,240,80,50,50,70,126,48,150,75,70,70,71};

    public static void BasketAdd(String item, Context now)
    {
        currentContext=now;
        switch (item)
        {

            case "Биг Мак" :
                count[0]++;
                ShowToast(item);
                break;
            case "Биг Тейсти" :
                count[1]++;
                ShowToast(item);
                break;
            case "Черный Кофе" :
                count[2]++;
                ShowToast(item);
                break;
            case "Чизбургер" :
                count[3]++;
                ShowToast(item);
                break;
            case "Кока-Кола" :
                count[4]++;
                ShowToast(item);
                break;
            case "Чикенбургер" :
               count[5]++;
                ShowToast(item);
                break;
            case "Филе-о-Фиш" :
                count[6]++;
                ShowToast(item);
                break;
            case "Гамбургер" :
                count[7]++;
                ShowToast(item);
                break;
            case "Наггетсы" :
                count[8]++;
                ShowToast(item);
                break;
            case "Картофель Фри" :
                count[9]++;
                ShowToast(item);
                break;
            case "Спрайт" :
                count[10]++;
                ShowToast(item);
                break;
            case "Чай" :
                count[11]++;
                ShowToast(item);
                break;
            case "Картофель по-деревенски" :
                count[12]++;
                ShowToast(item);
                break;
        }

    }

    public static void BasketRemove(String item,Context now)
    {
        currentContext=now;
        switch (item)
        {

            case "Биг Мак" :
                count[0]--;

                break;
            case "Биг Тейсти" :
                count[1]--;

                break;
            case "Черный Кофе" :
                count[2]--;

                break;
            case "Чизбургер" :
                count[3]--;

                break;
            case "Кока-Кола" :
                count[4]--;

                break;
            case "Чикенбургер" :
                count[5]--;

                break;
            case "Филе-о-Фиш" :
                count[6]--;

                break;
            case "Гамбургер" :
                count[7]--;

                break;
            case "Наггетсы" :
                count[8]--;

                break;
            case "Картофель Фри" :
                count[9]--;

                break;
            case "Спрайт" :
                count[10]--;

                break;
            case "Чай" :
                count[11]--;

                break;
            case "Картофель по-деревенски" :
                count[12]--;
                break;
        }
    }

    public static void ShowToast(String item)
    {
        if(!basketLoaded)
        {
            Toast toast = Toast.makeText(currentContext, "Добавлено в корзину: " + item, Toast.LENGTH_SHORT);
            View toastView = toast.getView();
            TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
            toastMessage.setTextSize(20);
            toastMessage.setTextColor(currentContext.getResources().getColor(R.color.macYellow));
            float scale = currentContext.getResources().getDisplayMetrics().density;
            int lefrright = (int) (16 * scale + 0.5f);
            int topbottom = (int) (4 * scale + 0.5f);
            toastMessage.setPadding(lefrright, 8, lefrright, 14);
            toastMessage.setCompoundDrawablePadding(16);
            //toastMessage.setBackground(currentContext.getResources().getDrawable(R.drawable.rounded_corners));
            toastView.setBackground(currentContext.getResources().getDrawable(R.drawable.rounded_corners));


            toast.setGravity(Gravity.CENTER, 0, 0);

            toast.show();
        }
    }
}
