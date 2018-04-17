package com.dimfcompany.macapp2;


import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dimfcompany.macapp2.R;
import com.dimfcompany.macapp2.basket;
import com.dimfcompany.macapp2.basket_item;
import com.dimfcompany.macapp2.basket_screen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class basketFrag extends Fragment {

    public List<basket_item> currentBasketItems=new ArrayList<basket_item>();
    ListView basketlist;
    TextView all;
    Button clear,makeOrder;

    int fullprice = 0;

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference users;



    int number;

    void setNumber(int num)
    {
        number=num;
    }

    public basketFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_basket_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        basketlist=(ListView)view.findViewById(R.id.basketListview);
        all=(TextView)view.findViewById(R.id.basketFullPrice);
        makeOrder=(Button)view.findViewById(R.id.buttonMakeOrder);
        clear=(Button)view.findViewById(R.id.buttonClear);
        makeOrder=(Button)view.findViewById(R.id.buttonMakeOrder);

        mAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        users=db.getReference("Users");

        currentBasketItems=items();
        BaseAdapter testAdapter = new BasketAdapter(currentBasketItems);
        fullprice = 0;
        for(basket_item baskit : currentBasketItems)
        {
            fullprice+=(baskit.getPrice()*baskit.getCount());

        }


    basket.basketLoaded=true;

        if(currentBasketItems.size()>0)
        {
            basketlist.setAdapter(testAdapter);
            all.setText(String.valueOf(fullprice)+" рублей");
        }
        else
            {
                LinearLayout basketLayout = view.findViewById(R.id.basketLayout);
                TextView empty = new TextView(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Resources r = getActivity().getResources();
                int leftright = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,16,r.getDisplayMetrics());
                int top = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,12,r.getDisplayMetrics());
                int sidepadding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,16,r.getDisplayMetrics());
                int toppadding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,r.getDisplayMetrics());
                params.setMargins(leftright,top,leftright,0);

                empty.setLayoutParams(params);
                empty.setText("Вы ещё ничего не выбрали? Скорее окрывайте меню и выбирайте вкусняшки!=)))");
                empty.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
                empty.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
                empty.setGravity(Gravity.START);
                empty.setTypeface(MainActivity.sans);
                empty.setTextColor(getResources().getColor(R.color.macYellow));
                empty.setPadding(sidepadding,toppadding,sidepadding,toppadding);
                basketLayout.addView(empty,0);
            }


            clear.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    for(int a =0;a<basket.count.length;a++)
                    {
                        basket.count[a]=0;
                    }
                    ReloadBasket();
                }
            });

        makeOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(MainActivity.isLogged())
                {

                    adreesdialog();

                }
                else MainActivity.makeRedToast("Для завершения заказа войдите в аккаунт или зарегистрируйтесь.",getActivity());
            }
        });

    }

    void adreesdialog()
    {
        AlertDialog.Builder mBuidler = new AlertDialog.Builder(getActivity(),R.style.Theme_Dialog);
        View mView = getActivity().getLayoutInflater().inflate(R.layout.adress_dialog,null);
        mBuidler.setView(mView);
        final AlertDialog dialog = mBuidler.create();

        ((TextView)mView.findViewById(R.id.tbAdress)).setTypeface(MainActivity.head, Typeface.BOLD);
        Button make=(Button)mView.findViewById(R.id.buttonAdress);
        make.setTypeface(MainActivity.head, Typeface.BOLD);
        final EditText et = (EditText)mView.findViewById(R.id.etAdress);
        et.setTypeface(MainActivity.sans);



        make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!et.getText().toString().isEmpty())
                {
                    final String id = mAuth.getCurrentUser().getUid();

                    final DatabaseReference order=users.child(id).child("orders");

                    final ValueEventListener onEvent = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)
                        {
                             setNumber((int)dataSnapshot.getChildrenCount());

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    final String adress = et.getText().toString();
                    order.addValueEventListener(onEvent);
                    users.child(id).child("orders").child(String.valueOf(999)).setValue("test", new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            String neworder = String.valueOf(number);
                            Date currentTime = Calendar.getInstance().getTime();
                            order.removeEventListener(onEvent);
                            users.child(id).child("orders").child(neworder).setValue(currentTime.toString());
                            for(int a = 0;a<currentBasketItems.size();a++)
                            {
                                users.child(id).child("orders").child(String.valueOf(number)).child(currentBasketItems.get(a).getName()).setValue(currentBasketItems.get(a).getCount());
                            }
                            users.child(id).child("orders").child(String.valueOf(number)).child(adress).setValue(currentTime.toString());
                            users.child(id).child("orders").child(String.valueOf(number)).child("Price").setValue(String.valueOf(fullprice));
                            for(int a =0;a<basket.count.length;a++)
                            {
                                basket.count[a]=0;
                            }
                            redToast(adress);
                            dialog.dismiss();


                            ReloadBasket();

                        }
                    });

                }
                else MainActivity.makeRedToast("Введите Адресс",getActivity());
            }
        });


        dialog.show();
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (getResources().getDisplayMetrics().heightPixels*1);

        dialog.getWindow().setLayout(width,height);
    }


    void ReloadBasket()
    {
        Fragment frg = null;
        frg = getFragmentManager().findFragmentByTag("Basket");
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        basket.basketLoaded=false;
    }

    private List<basket_item> items()
    {

        List<basket_item> list=new ArrayList<basket_item>();
        for(int a = 0; a< basket.names.length; a++)
        {
            if(basket.count[a]>0)
            {
                list.add(new basket_item(basket.names[a],basket.prices[a],basket.count[a],basket.images[a]));
            }

        }
        return list;

    }

    void redToast(String str)
    {
        Toast toast = Toast.makeText(getActivity(),"Ваш заказ будет доставлен по адрессу :" +str+"\nПриятного аппетита=)", Toast.LENGTH_LONG);
        View toastView = toast.getView();
        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(20);
        toastMessage.setTextColor(getActivity().getResources().getColor(R.color.macYellow));
        float scale = getActivity().getResources().getDisplayMetrics().density;
        int lefrright = (int) (16 * scale + 0.5f);
        int topbottom = (int) (4 * scale + 0.5f);
        toastMessage.setPadding(lefrright, 8, lefrright, 14);
        toastMessage.setCompoundDrawablePadding(16);
        //toastMessage.setBackground(currentContext.getResources().getDrawable(R.drawable.rounded_corners));
        toastView.setBackground(getActivity().getResources().getDrawable(R.drawable.rounded_corners));


        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();
    }

    public class BasketAdapter extends BaseAdapter
    {
        private List<basket_item> baskitems;
        public BasketAdapter(List<basket_item> baskitems)
        {
            this.baskitems=baskitems;
        }

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
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            convertView=getActivity().getLayoutInflater().inflate(R.layout.basket_item,null);


            ImageView image = (ImageView)convertView.findViewById(R.id.basketImage);
            TextView name = (TextView)convertView.findViewById(R.id.basketName);
            TextView price = (TextView)convertView.findViewById(R.id.basketPrice);
            TextView count = (TextView)convertView.findViewById(R.id.basketCount);
            ImageButton plus = (ImageButton)convertView.findViewById(R.id.imagePlus);
            ImageButton minus = (ImageButton)convertView.findViewById(R.id.imageMinus);

            image.setImageResource(currentBasketItems.get(position).getImage());
            name.setText(currentBasketItems.get(position).getName());
            price.setText((String.valueOf(currentBasketItems.get(position).getPrice())));
            count.setText(String.valueOf(currentBasketItems.get(position).getCount()));

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    basket.BasketAdd(currentBasketItems.get(position).getName(),getActivity());
                    ReloadBasket();
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    basket.BasketRemove(currentBasketItems.get(position).getName(),getActivity());
                    ReloadBasket();
                }
            });

            return convertView;
        }
    }
}
