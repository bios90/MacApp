package com.dimfcompany.macapp2;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import net.cachapa.expandablelayout.ExpandableLayout;

public class itemDetails extends Activity {

    Button sostavButton;
    ImageButton addButton;
    ExpandableRelativeLayout sostavText;
    TextView sostav,name,desc,price;
    ImageView image;

    private Typeface introHeadR;
    private Typeface sansSheriff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        addButton=(ImageButton)findViewById(R.id.detailsAddButton);
        sostavButton=(Button)findViewById(R.id.sostavButton);
        sostavText=(ExpandableRelativeLayout)findViewById(R.id.sostavText);
        sostav=(TextView)findViewById(R.id.sostav);
        name=(TextView)findViewById(R.id.detailName);
        desc=(TextView)findViewById(R.id.detailDesc);
        price=(TextView)findViewById(R.id.detailPrice);
        image=(ImageView)findViewById(R.id.detailImage);

        introHeadR=Typeface.createFromAsset(getAssets(),"fonts/IntroHeadR-Base.ttf");
        sansSheriff=Typeface.createFromAsset(getAssets(),"fonts/2211.ttf");

        name.setTypeface(introHeadR,Typeface.BOLD);
        sostavButton.setTypeface(introHeadR,Typeface.BOLD);
        desc.setTypeface(sansSheriff);
        sostav.setTypeface(sansSheriff);
        price.setTypeface(sansSheriff,Typeface.BOLD);

        Bundle b = getIntent().getExtras();
        String itemName = b.getString("itemName");
        WhatToLoad(itemName);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                basket.BasketAdd(name.getText().toString(),getApplicationContext());
//                Toast test = Toast.makeText(itemDetails.this,"Добавленно  в корзину :" + name.getText().toString()+Integer.toString(basket.bigmac),Toast.LENGTH_SHORT);
//                test.show();

            }
        });

        sostavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sostavText.toggle();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ScrollView myScroll=(ScrollView)findViewById(R.id.myScroll);
                        //myScroll.scroll(0,myScroll.getBottom());
                        myScroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                }, 450);
            }
        });
   }


//region FoofLoading
    void WhatToLoad(String item)
    {
        switch (item)
        {
            case "Чизбургер" :
                String CheeseDesc = "Рубленый бифштекс из натуральной цельной говядины с кусочками сыра «Чеддер» на карамелизованной булочке, заправленной горчицей, кетчупом, луком и кусочком маринованного огурчика.";
                String CheeseSostav = "Плавленый сыр Чеддер\n" +
                        "\n" +
                        "Огурцы маринованные резаные\n" +
                        "\n" +
                        "Лук репчатый резаный восстановленный\n" +
                        "\n" +
                        "Горчичный соус\n" +
                        "\n" +
                        "Булочка для гамбургеров, поджаренная в тостере\n" +
                        "\n" +
                        "Бифштекс из говядины рубленый 34 г, приготовленный на гриле\n" +
                        "\n" +
                        "Кетчуп томатный\n" +
                        "\n" +
                        "Приправа для гриля";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.cheeseburger));
                desc.setText(CheeseDesc);
                price.setText("50 рублей");
                sostav.setText(CheeseSostav);
                break;
            case "Биг Мак" :
                String BigMacDesc = "Большой сандвич с двумя рублеными бифштексами из натуральной цельной говядины на специальной булочке «Биг Мак» ®, заправленной луком, двумя кусочками маринованных огурчиков, ломтиком сыра «Чеддер», свежим салатом, и специальным соусом «Биг Мак»®.";
                String BigMacSostav = "Приправа для гриля\n" +
                        "\n" +
                        "Огурцы маринованные резаные\n" +
                        "\n" +
                        "Лук репчатый резаный восстановленный\n" +
                        "\n" +
                        "Плавленый сыр Чеддер\n" +
                        "\n" +
                        "Соус на основе растительных масел Биг-Мак\n" +
                        "\n" +
                        "Салат \"Айсберг мелкой нарезки\"\n" +
                        "\n" +
                        "Бифштексы из говядины рубленые 34 г, приготовленные на гриле\n" +
                        "\n" +
                        "Булочка с кунжутом Биг Мак, поджаренная в тостере";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.bigmac));
                desc.setText(BigMacDesc);
                price.setText("130 рублей");
                sostav.setText(BigMacSostav);
                break;
            case "Биг Тейсти" :
                String BigTastyDesc ="Это сандвич с большим, рубленым бифштексом из 100% говядины на большой булочке с кунжутом. Особенный вкус сандвичу придают три кусочка сыра «эмменталь», два ломтика помидора, свежий салат, лук и пикантный соус «Гриль».";
                String BigTastySostav = "Плавленый сыр со вкусом Эмменталь\n" +
                        "\n" +
                        "Соус на основе растительных масел Биг Тэйсти\n" +
                        "\n" +
                        "Помидор свежий резаный\n" +
                        "\n" +
                        "Булочка для гамбургеров Биг Тести, поджаренная в тостере\n" +
                        "\n" +
                        "Бифштекс из говядины рубленый 110 г, приготовленный на гриле\n" +
                        "\n" +
                        "\"Айсберг крупной нарезки\"\n" +
                        "\n" +
                        "Лук резаный\n" +
                        "\n" +
                        "Приправа для гриля";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.bigtasty));
                desc.setText(BigTastyDesc);
                price.setText("240 рублей");
                sostav.setText(BigTastySostav);
                break;
            case "Черный Кофе" :
                String CoffeeDesc = "Черный кофе,на 100% приготовленный из лучших сортов арабики.";
                String CoffeeSostav = "Горячая вода\n" +
                        "\n" +
                        "Кофейные зерна Paulig Espresso Originale";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.blackcofe));
                desc.setText(CoffeeDesc);
                price.setText("80 рублей");
                sostav.setText(CoffeeSostav);
                break;
            case "Кока-Кола" :
                String colaDesc = "Самый популярный прохладительный газированный напиток.";
                String colaSostav = "Сироп купажный пост-микс «Кока-Кола»®\n" +
                        "\n" +
                        "Лед пищевой\n" +
                        "\n" +
                        "Охлажденная газированная вода";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.cola));
                desc.setText(colaDesc);
                price.setText("70 рублей");
                sostav.setText(colaSostav);
                break;
            case "Чикенбургер" :
                String ChickenDesc = "Обжаренная куриная котлета, панированная в сухарях, которая подается на карамелизованной булочке, заправленной свежим салатом и специальным соусом «Мак Чикен»®.";
                String ChickenSostav = "Соус на основе растительных масел МакЧикен\n" +
                        "\n" +
                        "Салат \"Айсберг мелкой нарезки\"\n" +
                        "\n" +
                        "Котлета куриная Велью, приготовленная во фритюре\n" +
                        "\n" +
                        "Масло растительное фритюрное\n" +
                        "\n" +
                        "Булочка для гамбургеров, поджаренная в тостере";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.chickenburger));
                desc.setText(ChickenDesc);
                price.setText("50 рублей");
                sostav.setText(ChickenSostav);
                break;
            case "Филе-о-Фиш" :
                String fishDesc = "Филе хорошо прожаренной рыбы (семейства тресковых), которое подается на пышной пропаренной булочке с половинкой кусочка сыра «Чеддер», заправленной специальным соусом «Тар-Тар».";
                String fishSostav = "Плавленый сыр Чеддер\n" +
                        "\n" +
                        "Масло растительное фритюрное\n" +
                        "\n" +
                        "Булочка для гамбургеров, пропаренная\n" +
                        "\n" +
                        "Соус на основе растительных масел Тар-Тар\n" +
                        "\n" +
                        "Филе минтая порционное панированное";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.fileofish));
                desc.setText(fishDesc);
                price.setText("126 рублей");
                sostav.setText(fishSostav);
                break;
            case "Гамбургер" :
                String hamburgDesc = "Рубленый бифштекс из натуральной цельной говядины на карамелизованной булочке, заправленной горчицей, кетчупом, луком и кусочком маринованного огурчика.";
                String hamburgSostav = "Булочка для гамбургеров, поджаренная в тостере\n" +
                        "\n" +
                        "Бифштекс из говядины рубленый 34 г, приготовленный на гриле\n" +
                        "\n" +
                        "Кетчуп томатный\n" +
                        "\n" +
                        "Горчичный соус\n" +
                        "\n" +
                        "Лук репчатый резаный восстановленный\n" +
                        "\n" +
                        "Огурцы маринованные резаные\n" +
                        "\n" +
                        "Приправа для гриля";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.hamburger));
                desc.setText(hamburgDesc);
                price.setText("48 рублей");
                sostav.setText(hamburgSostav);
                break;
            case "Наггетсы" :
                String naggetsDesc = "Обжаренные в растительном фритюре кусочки куриного мяса, панированные в сухарях, которые подаются с горчичным, кисло-сладким, барбекю соусом или соусом Карри. С каждой порцией два соуса по специальной сниженной цене.";
                String naggetsSostav = "Котлеты куриные Макнаггетс, приготовленные во фритюре\n" +
                        "\n" +
                        "Масло растительное фритюрное";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.naggets));
                desc.setText(naggetsDesc);
                price.setText("150 рублей");
                sostav.setText(naggetsSostav);
                break;
            case "Картофель Фри" :
                String freeDesc = "Вкусные, обжаренные в растительном фритюре и слегка посоленные палочки картофеля.";
                String freeSostav = "Масло растительное – смесь ВЕГАФРАЙ М5\n" +
                        "\n" +
                        "Картофель-фри замороженный\n" +
                        "\n" +
                        "Соль йодированная экстра";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.potato));
                desc.setText(freeDesc);
                price.setText("75 рублей");
                sostav.setText(freeSostav);
                break;
            case "Спрайт" :
                String spriteDesc = "Прохладительный газированный напиток со вкусом лимона.";
                String spriteSostav = "Концентрат купажный пост-микс «Спрайт»®\n" +
                        "\n" +
                        "Лед пищевой\n" +
                        "\n" +
                        "Газированная вода";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.sprite));
                desc.setText(spriteDesc);
                price.setText("70 рублей");
                sostav.setText(spriteSostav);
                break;
            case "Чай" :
                String teaDesc = "Черный чай из смеси лучших сортов индийского чая.";
                String teaSostav = "Richard® Royal Ceylon. Чай черный листовой цейлонский в пирамидках\n" +
                        "\n" +
                        "Горячая вода";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.tea));
                desc.setText(teaDesc);
                price.setText("70 рублей");
                sostav.setText(teaSostav);
                break;
            case "Картофель по-деревенски" :
                String villageDesc = "Вкусные, обжаренные в растительном фритюре ломтики картофеля со специями.";
                String villageSostav = "Полуфабрикат картофель замороженный в ломтиках с кожурой, обжаренный в специях\n" +
                        "\n" +
                        "Масло растительное – смесь ВЕГАФРАЙ М5";
                name.setText(item);
                image.setImageDrawable(getResources().getDrawable(R.drawable.villagepotato));
                desc.setText(villageDesc);
                price.setText("71 рублей");
                sostav.setText(villageSostav);
                break;
        }

    }
    //endregion
}
