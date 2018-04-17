package com.dimfcompany.macapp2;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

public class activity_menu extends AppCompatActivity {

    Toolbar toolbar;

    TextView titleBar;

    private RecyclerView recView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> myItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        titleBar=(TextView)findViewById(R.id.toolBarTitle);
        //titleBar.setText("Бургеры");
        titleBar.setTextColor(getResources().getColor(R.color.macYellow));

        toolbar=(Toolbar)findViewById(R.id.toolbarMain);
//        toolbar.setTitle("Макдональдс Наше Все");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.macYellow));


        //titleBar=(TextView)findViewById(R.id.barTitle);
        Typeface introHeadR = Typeface.createFromAsset(getAssets(),"fonts/IntroHeadR-Base.ttf");
        //titleBar.setTypeface(introHeadR,Typeface.BOLD);

        recView=(RecyclerView)findViewById(R.id.recyclerView);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));

        myItems = new ArrayList<>();


        FillList();

        adapter= new MyAdapter(myItems,this);
        recView.setAdapter(adapter);

        NavigationDrawer();

    }

    void FillList()
    {
        String BigMacDesc = "Большой сандвич с двумя рублеными бифштексами из натуральной говядины на специальной булочке.";
        Drawable BigMacImg = getResources().getDrawable(R.drawable.bigmac);
        ListItem BigMac = new ListItem("Биг Мак",BigMacDesc,"130 руб",BigMacImg);
        myItems.add(BigMac);

        String BigTastyDesc = "Это сандвич с большим бифштексом из 100% говядины. Особенный вкус сандвичу придает пикантный соус «Гриль».";
        Drawable BigTastyImg = getResources().getDrawable(R.drawable.bigtasty);
        ListItem BigTasty = new ListItem("Биг Тейсти",BigTastyDesc,"240 руб",BigTastyImg);
        myItems.add(BigTasty);

        String CheeseBurgerDesc = "Рубленый бифштекс из натуральной говядины с кусочками сыра «Чеддер», заправленной горчицей и кусочком маринованного огурчика.";
        Drawable CheeseBurgerImg = getResources().getDrawable(R.drawable.cheeseburger);
        ListItem CheeseBurger = new ListItem("Чизбургер",CheeseBurgerDesc,"50 руб",CheeseBurgerImg);
        myItems.add(CheeseBurger);

        String HamBurgerDesc ="Рубленый бифштекс из натуральной говядины, заправленной горчицей, кетчупом, луком и кусочком маринованного огурчика.";
        Drawable HamBurgerImg = getResources().getDrawable(R.drawable.hamburger);
        ListItem HamBurger = new ListItem("Гамбургер",HamBurgerDesc,"48 руб",HamBurgerImg);
        myItems.add(HamBurger);

        String ChickBurgerDesc ="Обжаренная куриная котлета, панированная в сухарях, заправленная свежим салатом и специальным соусом «Мак Чикен»®.";
        Drawable ChickBurgerImg = getResources().getDrawable(R.drawable.chickenburger);
        ListItem ChickBurger = new ListItem("Чикенбургер",ChickBurgerDesc,"50 руб",ChickBurgerImg);
        myItems.add(ChickBurger);

        String FilBurgerDesc ="Обжаренная куриная котлета, панированная в сухарях, заправленная свежим салатом и специальным соусом «Мак Чикен»®.";
        Drawable FilBurgerImg = getResources().getDrawable(R.drawable.fileofish);
        ListItem FilBurger = new ListItem("Филе-о-Фиш",FilBurgerDesc,"126 руб",FilBurgerImg);
        myItems.add(FilBurger);


    }

    void NavigationDrawer()
    {
        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header2)
                .addProfiles(
                        new ProfileDrawerItem().withName("Parapapapa").withEmail("mikepenz@gmail.com").withIcon(getResources().getDrawable(R.drawable.m_logo))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();



        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Войти в Аккаунт").withTextColor(getResources().getColor(R.color.macYellow))
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_account_circle_black_24dp));
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Корзина").withTextColorRes(R.color.macYellow)
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_shopping_basket_black_24dp));
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Информация").withTextColorRes(R.color.macYellow)
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_info_black_24dp));
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Категории").withTextColorRes(R.color.macYellow)
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_view_compact_black_24dp));
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName("Test2");

//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .withSliderBackgroundColor(getResources().getColor(R.color.macRed))
                .addDrawerItems(
                        item1,
                        item2,
                        item4,
                        item5

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Toast ts1 = Toast.makeText(getApplicationContext(),Integer.toString(position),Toast.LENGTH_SHORT);
                        ts1.show();

                        return true;
                    }

                })
                .build();

        //String footar = "Закажите приложения для вашего магазина/ресторана/службы доставки и получите массу новы клиентов. Обращайтесь по телефону +79167062291,email bios90@mail.ru ";
        //result.addStickyFooterItem(new PrimaryDrawerItem().withName(footar)
                //.withTextColorRes(R.color.macYellow)

                        //);


    }
}