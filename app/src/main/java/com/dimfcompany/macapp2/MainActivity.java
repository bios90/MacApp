package com.dimfcompany.macapp2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.solver.SolverVariable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tbText;
    //Fragment myFrag;
    FrameLayout myFrag;
    ImageButton cart;

    FirebaseAuth mAuth;
    DatabaseReference users;

    AccountHeader headerResult;

    Drawer result;

    private Typeface introHeadR;
    private Typeface Sherif;

    public static Typeface head;
    public static Typeface sans;

    //region LogBollean
    private static boolean  logged;
    public static boolean isLogged() {
        return logged;
    }

    public static void setLogged(boolean logg) {
        logged = logg;
    }
    //endregion




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        logged = false;

        mAuth=FirebaseAuth.getInstance();


        tbText=(TextView)findViewById(R.id.toolBarTitle);
        tbText.setText("Бургеры");
        tbText.setTextColor(getResources().getColor(R.color.macYellow));
        cart=(ImageButton)findViewById(R.id.cartImage);

        toolbar=(Toolbar)findViewById(R.id.toolBarTest);

        introHeadR=Typeface.createFromAsset(getAssets(),"fonts/IntroHeadR-Base.ttf");
        Sherif=Typeface.createFromAsset(getAssets(),"fonts/2211.ttf");

        sans=Sherif;
        head=introHeadR;

        tbText.setTypeface(introHeadR,Typeface.BOLD);
//        toolbar.setTitle("Бургеры");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.macYellow));

        //myFrag=(Fragment)getSupportFragmentManager().findFragmentById(R.id.testfrag);
        myFrag=(FrameLayout)findViewById(R.id.testfrag);



        NavigationDrawer();

        Fragment fragment=new allFood();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(myFrag.getId(),fragment);
        ft.addToBackStack(null);
        ft.commit();
        tbText.setText("Всё меню");

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               loadbasket();
            }
        });

        infoDialog();

    }

    void infoDialog()
    {
        AlertDialog.Builder mBuidler = new AlertDialog.Builder(MainActivity.this,R.style.Theme_Dialog);
        final View mView = getLayoutInflater().inflate(R.layout.showinfo,null);
        mBuidler.setView(mView);
        final AlertDialog dialog = mBuidler.create();


        Button moreinfo,allclear;

        ((TextView)mView.findViewById(R.id.infotitle)).setTypeface(MainActivity.head,Typeface.BOLD);
        ((TextView)mView.findViewById(R.id.infoMain)).setTypeface(MainActivity.sans);
        ((TextView)mView.findViewById(R.id.uslugi)).setTypeface(MainActivity.sans);

        moreinfo=(Button)mView.findViewById(R.id.moreInfo);
        allclear=(Button)mView.findViewById(R.id.closeinfo);

        moreinfo.setTypeface(MainActivity.head,Typeface.BOLD);
        allclear.setTypeface(MainActivity.head,Typeface.BOLD);

        final ExpandableRelativeLayout ex=(ExpandableRelativeLayout)mView.findViewById(R.id.moreInfoText);

        moreinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ex.toggle();
            }
        });
        allclear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.95);
        dialog.getWindow().setLayout(width,height);
    }
    void loadbasket()
    {
        Fragment basket=new basketFrag();
        FragmentManager fm3 = getSupportFragmentManager();
        FragmentTransaction ft3 = fm3.beginTransaction();
        ft3.replace(R.id.testfrag,basket,"Basket");
        ft3.addToBackStack(null);
        ft3.commit();
        tbText.setText("Корзина");
        result.closeDrawer();
    }

    public void DrawerItemClicked(int i)
    {
        Fragment fragment;

        switch (i)
        {
            case 2:

                fragment=new FragmentDrinks();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.testfrag,fragment);
                ft.commit();

                break;
            case 1:

                fragment=new fragtest1();
                FragmentManager fm1 = getSupportFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.replace(R.id.testfrag,fragment);
                ft1.commit();
                break;
        }

    }

    public void setToolBarText(String text)
    {

        toolbar.setTitle("dsfsdfsdf");
        //toolbar.setTitleTextColor(getResources().getColor(R.color.macYellow));
    }

    void DrawerLogged(String newMail)
    {
        final PrimaryDrawerItem itemMyAccaunt = new PrimaryDrawerItem().withIdentifier(6).withName("Мой Аккаунт").withTextColor(getResources().getColor(R.color.macYellow))
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_assignment_black_24dp))
                ;
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Корзина").withTextColorRes(R.color.macYellow)
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_shopping_basket_black_24dp));
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Информация").withTextColorRes(R.color.macYellow)
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_info_black_24dp));
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Меню").withTextColorRes(R.color.macYellow)
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_view_compact_black_24dp));
        result.removeItem(1);
        result.removeItem(2);
        result.removeItem(4);
        result.removeItem(5);
        result.addItemAtPosition(itemMyAccaunt,1);
        result.addItemAtPosition(item2,2);
        result.addItemAtPosition(item5,3);
        result.addItemAtPosition(item4,4);

        result.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem)
            {
                if(drawerItem==itemMyAccaunt)
                {
                    Fragment fragment=new myAccaunt();
                    FragmentManager fm3 = getSupportFragmentManager();
                    FragmentTransaction ft3 = fm3.beginTransaction();
                    ft3.replace(R.id.testfrag,fragment);
                    ft3.addToBackStack(null);
                    ft3.commit();

                    tbText.setText("Мой Аккаунт");

                }
                if(drawerItem==item5)
                {
                    CategoryShow();
                    result.closeDrawer();
                }
                if(drawerItem==item2)
                {
                    loadbasket();
                }
                if(drawerItem==item4)
                {
                    infoDialog();
                }


                return false;
            }
        });

        headerResult.removeProfileByIdentifier(1);


        headerResult.addProfiles(new ProfileDrawerItem().withName("Вход выполнен").withEmail(newMail).withIcon(getResources().getDrawable(R.drawable.m_logo)));
    }

    void NavigationDrawer()
    {
        // Create the AccountHeader
         headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header2).withCurrentProfileHiddenInList(true)
//                .addProfiles(
//                        new ProfileDrawerItem().withEmail("Что бы заказать выполните вход или зарегистрируйтесь").withIdentifier(1).withIcon(getResources().getDrawable(R.drawable.m_logo))
//                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        return false;
                    }
                }).withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {
                     @Override
                     public boolean onClick(View view, IProfile profile) {
                         headerResult.toggleSelectionList(MainActivity.this);
                         return false;
                     }
                 })
                .build();



        //if you want to update the items at a later time it is recommended to keep it in a variable
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Войти в Аккаунт").withTextColor(getResources().getColor(R.color.macYellow))
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_account_circle_black_24dp));
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Корзина").withTextColorRes(R.color.macYellow)
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_shopping_basket_black_24dp));
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Информация").withTextColorRes(R.color.macYellow)
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_info_black_24dp));
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Меню").withTextColorRes(R.color.macYellow)
                .withSelectedTextColorRes(R.color.macYellow)
                .withSelectedColorRes(R.color.macRed)
                .withIcon(getResources().getDrawable(R.drawable.ic_view_compact_black_24dp));
        SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName("Test2");

//create the drawer and remember the `Drawer` result object
         result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .withSliderBackgroundColor(getResources().getColor(R.color.macRed))
                .addDrawerItems(
                        item1,
                        item2,
                        item5,
                        item4

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if(drawerItem==item5)
                        {
                            CategoryShow();
                            result.closeDrawer();
                        }
                        if(drawerItem==item2)
                        {
                              loadbasket();
                        }
                        if(drawerItem==item1)
                        {
                            loginDialog();
                        }
                        if(drawerItem==item4)
                        {
                            infoDialog();
                        }



                        return true;
                    }

                })
                .build();



    }
    void loginDialog()
    {
        AlertDialog.Builder mBuidler = new AlertDialog.Builder(MainActivity.this,R.style.Theme_Dialog);
        View mView = getLayoutInflater().inflate(R.layout.login_dialog,null);
        mBuidler.setView(mView);
        final AlertDialog dialog = mBuidler.create();

        final EditText login,pass;
        Button loginButton;

        TextView notRegistred = (TextView)mView.findViewById(R.id.notReg);
        login=(EditText)mView.findViewById(R.id.loginMail);
        pass = (EditText)mView.findViewById(R.id.loginPass);
        loginButton=(Button)mView.findViewById(R.id.loginButton);

        notRegistred.setTypeface(sans);
        login.setTypeface(sans);
        pass.setTypeface(sans);
        loginButton.setTypeface(head,Typeface.BOLD);

        ((TextView)mView.findViewById(R.id.logTB)).setTypeface(head,Typeface.BOLD);
        ((TextView)mView.findViewById(R.id.passTB)).setTypeface(head,Typeface.BOLD);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                final String loginstr=login.getText().toString().trim();
                String passstr = pass.getText().toString().trim();
                if(!loginstr.isEmpty() && !passstr.isEmpty())
                {
                    mAuth.signInWithEmailAndPassword(loginstr,passstr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                makeRedToast("Вход Выполнен",MainActivity.this);
                                setLogged(true);

                                DrawerLogged(loginstr);

                                dialog.dismiss();
                                result.closeDrawer();
                            }
                            else
                                {
                                    makeRedToast("Проверьте введенные данные",MainActivity.this);
                                }
                        }
                    });
                }
                else makeRedToast("Пользователь не найден,повторите ввод или пройдите регистрацию",MainActivity.this);

            }
        });


        notRegistred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerDialog();
                dialog.dismiss();
            }
        });
        dialog.show();

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (getResources().getDisplayMetrics().heightPixels*1);

        dialog.getWindow().setLayout(width,height);
    }

    void registerDialog()
    {
        AlertDialog.Builder mBuidler = new AlertDialog.Builder(MainActivity.this,R.style.Theme_Dialog);
        View mView = getLayoutInflater().inflate(R.layout.register_dialog,null);
        mBuidler.setView(mView);
        final AlertDialog dialog = mBuidler.create();

        final EditText name,mail,phone,pass,passrepeat;
        Button register;



        name=(EditText)mView.findViewById(R.id.regName);
        mail=(EditText)mView.findViewById(R.id.regMail);
        phone=(EditText)mView.findViewById(R.id.regPhone);
        pass=(EditText)mView.findViewById(R.id.regPass);
        passrepeat=(EditText)mView.findViewById(R.id.regPassRepeat);
        register=(Button)mView.findViewById(R.id.regButton);

        ((TextView)mView.findViewById(R.id.regMailTB)).setTypeface(head,Typeface.BOLD);
        ((TextView)mView.findViewById(R.id.regNameTB)).setTypeface(head,Typeface.BOLD);
        ((TextView)mView.findViewById(R.id.regPhoneTB)).setTypeface(head,Typeface.BOLD);
        ((TextView)mView.findViewById(R.id.regPassTB)).setTypeface(head,Typeface.BOLD);
        register.setTypeface(head,Typeface.BOLD);

        name.setTypeface(sans);
        mail.setTypeface(sans);
        phone.setTypeface(sans);
        pass.setTypeface(sans);
        passrepeat.setTypeface(sans);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String namestr, mailstr, phonestr, passstr, passrepeatstr;
                namestr = name.getText().toString().trim();
                mailstr = mail.getText().toString().trim();
                phonestr = phone.getText().toString().trim();
                passstr = pass.getText().toString().trim();
                passrepeatstr = passrepeat.getText().toString().trim();

                if (pass.length() > 7)
                {
                    if (allclear(namestr, mailstr, phonestr, passstr, passrepeatstr)) {

                        mAuth.createUserWithEmailAndPassword(mailstr, passstr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful())
                                {
                                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                                    DatabaseReference dbRoot = db.getReference();
                                    users=dbRoot.child("Users");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(namestr).build();
                                    user.updateProfile(profile);
                                    DatabaseReference newmail = users.child(user.getUid());
                                    newmail.child("mail").setValue(mailstr);
                                    newmail.child("name").setValue(namestr);
                                    newmail.child("phone").setValue(phonestr);
                                    newmail.child("password").setValue(passstr);
                                    Date currentTime = Calendar.getInstance().getTime();
                                    newmail.child("orders").child("regdate").setValue(currentTime.toString());
                                    makeRedToast("Регистрация прошла успешно.",MainActivity.this);
                                    dialog.dismiss();
                                }
                                else
                                {
                                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                    {
                                        makeRedToast("Этот эмейл уже зарегистрирован,выполните вход",MainActivity.this);
                                    }
                                }

                            }
                        });
                    } else makeRedToast("Не все данные введены корректно,повторите ввод",MainActivity.this);
                }
                else
                    {
                        makeRedToast("Пароль слишком короткий,введите минимум 8 знаков",MainActivity.this);
                    }
            }

        });



        dialog.show();

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

        dialog.getWindow().setLayout(width,height);
        //dialog.getWindow().setLayout(800, 650);

    }

    boolean allclear(String name,String mail,String phone,String pass,String passrepeat)
    {
        if(!name.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches() && !phone.isEmpty() && !pass.isEmpty() && !passrepeat.isEmpty())
        {
            if(pass.equals(passrepeat)) return true;
            else return false;
        }
        else return false;
    }



    public void CategoryShow()
    {

        AlertDialog.Builder mBuidler = new AlertDialog.Builder(MainActivity.this,R.style.Theme_Dialog);
        View mView = getLayoutInflater().inflate(R.layout.categorychoose,null);
        mBuidler.setView(mView);

        TextView choose1=(TextView)mView.findViewById(R.id.choose1);
        TextView choose2=(TextView)mView.findViewById(R.id.choose2);
        TextView choose3=(TextView)mView.findViewById(R.id.choose3);
        TextView choose4=(TextView)mView.findViewById(R.id.choose4);

        choose1.setTypeface(introHeadR,Typeface.BOLD);
        choose2.setTypeface(introHeadR,Typeface.BOLD);
        choose3.setTypeface(introHeadR,Typeface.BOLD);
        choose4.setTypeface(introHeadR,Typeface.BOLD);

        ImageButton all =(ImageButton) mView.findViewById(R.id.categAll);
        ImageButton burgers =(ImageButton) mView.findViewById(R.id.categBurgers);
        ImageButton snacks =(ImageButton) mView.findViewById(R.id.categSnacks);
        ImageButton drinks =(ImageButton) mView.findViewById(R.id.categDrinks);


        final AlertDialog dialog = mBuidler.create();

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categClicked(v,dialog);
            }
        });

        burgers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categClicked(v,dialog);
            }
        });

        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categClicked(v,dialog);
            }
        });

        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categClicked(v,dialog);
            }
        });


        dialog.show();
    }

    private void categClicked(View v,AlertDialog dialog)
    {

        switch (v.getId())
        {
            case R.id.categAll:
                Fragment fragment3=new allFood();
                FragmentManager fm3 = getSupportFragmentManager();
                FragmentTransaction ft3 = fm3.beginTransaction();
                ft3.replace(R.id.testfrag,fragment3);
                ft3.addToBackStack(null);
                ft3.commit();
                dialog.dismiss();
                tbText.setText("Всё меню");
                break;
            case R.id.categBurgers:
                Fragment fragment=new fragtest1();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.testfrag,fragment);
                ft.addToBackStack(null);
                ft.commit();
                dialog.dismiss();
                tbText.setText("Бургеры");
                break;
            case R.id.categDrinks:
                Fragment fragment1=new FragmentDrinks();
                FragmentManager fm1 = getSupportFragmentManager();
                FragmentTransaction ft1 = fm1.beginTransaction();
                ft1.replace(R.id.testfrag,fragment1);
                ft1.addToBackStack(null);
                ft1.commit();
                dialog.dismiss();
                tbText.setText("Напитки");
                break;
            case R.id.categSnacks:
                Fragment fragment2=new snacks();
                FragmentManager fm2 = getSupportFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();

                ft2.replace(R.id.testfrag,fragment2);
                ft2.addToBackStack(null);
                ft2.commit();

                //Fragment helper = new yellowHelper();
//                FragmentManager fmHelper = getSupportFragmentManager();
//                FragmentTransaction ftHelper = fmHelper.beginTransaction();
//                ftHelper.replace(R.id.testfrag,fragment2);
//                ftHelper.commit();

                dialog.dismiss();
                tbText.setText("Закуски");
                break;
        }

    }
   public static void makeRedToast(String str,Context ctx)
    {
        Toast toast = Toast.makeText(ctx, str , Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(20);
        toastMessage.setTextColor(ctx.getResources().getColor(R.color.macYellow));
        float scale = ctx.getResources().getDisplayMetrics().density;
        int lefrright = (int) (16 * scale + 0.5f);
        int topbottom = (int) (4 * scale + 0.5f);
        toastMessage.setPadding(lefrright, 8, lefrright, 14);
        toastMessage.setCompoundDrawablePadding(16);

        toastView.setBackground(ctx.getResources().getDrawable(R.drawable.rounded_corners));


        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();
    }

    //region Lists and ListGetters
    public List<ListItem> allFood()
    {
        List<ListItem> food = new ArrayList<>();
        food.addAll(burgers());
        food.addAll(snacks());
        food.addAll(drinks());
        return food;
    }

    public List<ListItem> getBurgers()
    {
        return burgers();
    }

    public List<ListItem> getSnacks()
    {
        return snacks();
    }

    public List<ListItem> getDrinks()
    {
        return drinks();
    }

    private List<ListItem> burgers()
    {
        List<ListItem> brgs = new ArrayList<>();
        String BigMacDesc = "Большой сандвич с двумя рублеными бифштексами из натуральной говядины на специальной булочке.";
        Drawable BigMacImg = getResources().getDrawable(R.drawable.bigmac);
        ListItem BigMac = new ListItem("Биг Мак",BigMacDesc,"130 руб",BigMacImg);
        brgs.add(BigMac);

        String BigTastyDesc = "Это сандвич с большим бифштексом из 100% говядины. Особенный вкус сандвичу придает пикантный соус «Гриль».";
        Drawable BigTastyImg = getResources().getDrawable(R.drawable.bigtasty);
        ListItem BigTasty = new ListItem("Биг Тейсти",BigTastyDesc,"240 руб",BigTastyImg);
        brgs.add(BigTasty);

        String CheeseBurgerDesc = "Рубленый бифштекс из натуральной говядины с кусочками сыра «Чеддер», заправленной горчицей и кусочком маринованного огурчика.";
        Drawable CheeseBurgerImg = getResources().getDrawable(R.drawable.cheeseburger);
        ListItem CheeseBurger = new ListItem("Чизбургер",CheeseBurgerDesc,"50 руб",CheeseBurgerImg);
        brgs.add(CheeseBurger);

        String HamBurgerDesc ="Рубленый бифштекс из натуральной говядины, заправленной горчицей, кетчупом, луком и кусочком маринованного огурчика.";
        Drawable HamBurgerImg = getResources().getDrawable(R.drawable.hamburger);
        ListItem HamBurger = new ListItem("Гамбургер",HamBurgerDesc,"48 руб",HamBurgerImg);
        brgs.add(HamBurger);

        String ChickBurgerDesc ="Обжаренная куриная котлета, панированная в сухарях, заправленная свежим салатом и специальным соусом «Мак Чикен»®.";
        Drawable ChickBurgerImg = getResources().getDrawable(R.drawable.chickenburger);
        ListItem ChickBurger = new ListItem("Чикенбургер",ChickBurgerDesc,"50 руб",ChickBurgerImg);
        brgs.add(ChickBurger);

        String FilBurgerDesc ="Обжаренная куриная котлета, панированная в сухарях, заправленная свежим салатом и специальным соусом «Мак Чикен»®.";
        Drawable FilBurgerImg = getResources().getDrawable(R.drawable.fileofish);
        ListItem FilBurger = new ListItem("Филе-о-Фиш",FilBurgerDesc,"126 руб",FilBurgerImg);
        brgs.add(FilBurger);

        return brgs;
    }

    private List<ListItem> snacks()
    {
        List<ListItem> snacks = new ArrayList<>();

        String NaggetsDesc = "Обжаренные в растительном фритюре кусочки куриного мяса, панированные в сухарях, которые подаются с соусами на выбор.";
        Drawable NaggetsImg = getResources().getDrawable(R.drawable.naggets);
        ListItem Naggets = new ListItem("Наггетсы",NaggetsDesc,"150 руб",NaggetsImg);
        snacks.add(Naggets);

        String PotatoDesc = "Вкусные, обжаренные в растительном фритюре и слегка посоленные палочки картофеля.";
        Drawable PotatoImg = getResources().getDrawable(R.drawable.potato);
        ListItem Potato = new ListItem("Картофель Фри",PotatoDesc,"75 руб",PotatoImg);
        snacks.add(Potato);

        String VillPotatoDesc = "Вкусные, обжаренные в растительном фритюре ломтики картофеля со специями." ;
        Drawable VillPotatoImg = getResources().getDrawable(R.drawable.villagepotato);
        ListItem VillPotato = new ListItem("Картофель по-деревенски",VillPotatoDesc,"71 руб",VillPotatoImg);
        snacks.add(VillPotato);

        return snacks;

    }

    private List<ListItem> drinks()
    {
        List<ListItem> getDrinks = new ArrayList<>();

        String ColaDesc = "Самый популярный прохладительный газированный напиток.";
        Drawable ColaImg = getResources().getDrawable(R.drawable.cola);
        ListItem Cola = new ListItem("Кока-Кола",ColaDesc,"70 руб",ColaImg);
        getDrinks.add(Cola);

        String SpriteDesc = "Прохладительный газированный напиток со вкусом лимона.";
        Drawable SpriteImg = getResources().getDrawable(R.drawable.sprite);
        ListItem Sprite = new ListItem("Спрайт",SpriteDesc,"70 руб",SpriteImg);
        getDrinks.add(Sprite);

        String CoffeeDesc = "Черный кофе,на 100% приготовленный из лучших сортов арабики.";
        Drawable CoffeeImg = getResources().getDrawable(R.drawable.blackcofe);
        ListItem Coffee = new ListItem("Черный Кофе",CoffeeDesc,"80 руб",CoffeeImg);
        getDrinks.add(Coffee);

        String TeaDesc ="Черный чай из смеси лучших сортов индийского чая.";
        Drawable TeaImg = getResources().getDrawable(R.drawable.tea);
        ListItem Tea = new ListItem("Чай",TeaDesc,"70 руб",TeaImg);
        getDrinks.add(Tea);

        return getDrinks;
    }
    //endregion

}
