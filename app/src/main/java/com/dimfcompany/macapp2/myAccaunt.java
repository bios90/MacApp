package com.dimfcompany.macapp2;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dimfcompany.macapp2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class myAccaunt extends Fragment {

    TextView mail,name,phone;

    FirebaseAuth mAuth;
    DatabaseReference users;

    Button exit;

    public myAccaunt()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
               return inflater.inflate(R.layout.fragment_my_accaunt, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        name = (TextView)view.findViewById(R.id.accName);
        phone = (TextView)view.findViewById(R.id.accPhone);
        mail = (TextView)view.findViewById(R.id.accMail);
        exit=(Button)view.findViewById(R.id.exitButton);

        name.setTypeface(MainActivity.head);
        phone.setTypeface(MainActivity.head);
        mail.setTypeface(MainActivity.head);
        exit.setTypeface(MainActivity.head,Typeface.BOLD);

        ((TextView)view.findViewById(R.id.TB1)).setTypeface(MainActivity.head,Typeface.BOLD);
        ((TextView)view.findViewById(R.id.TB2)).setTypeface(MainActivity.head,Typeface.BOLD);
        ((TextView)view.findViewById(R.id.TB3)).setTypeface(MainActivity.head,Typeface.BOLD);



        mAuth=FirebaseAuth.getInstance();
        users= FirebaseDatabase.getInstance().getReference("Users");

        final String id = mAuth.getCurrentUser().getUid().toString();

        users.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String namestr = dataSnapshot.child(id).child("name").getValue(String.class);
                String mailstr = dataSnapshot.child(id).child("mail").getValue(String.class);
                String phonestr =dataSnapshot.child(id).child("phone").getValue(String.class);

                name.setText(namestr);
                phone.setText(phonestr);
                mail.setText(mailstr);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        exit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mAuth.signOut();
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);

            }
        });

    }
}
