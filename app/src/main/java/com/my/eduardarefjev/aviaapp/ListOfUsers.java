package com.my.eduardarefjev.aviaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by EduardArefjev on 03/11/2017.
 */

public class ListOfUsers extends AppCompatActivity {

    //private RecyclerView mUserList;

    private Button bNextStep;
    private DatabaseReference mDatabase;
    private ListView mUserList;
    private ArrayList<String> mUsernames = new ArrayList<>();
    //private ArrayList<User> mUsernames = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_list_of_users);

        this.setTitle("Users");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("User");
        mUserList = (ListView) findViewById(R.id.LinearListOfUsers);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsernames);
        mUserList.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //arrayAdapter.clear();
                User spacecraft=dataSnapshot.getValue(User.class);
                arrayAdapter.add(spacecraft.getFirstName());
                arrayAdapter.notifyDataSetChanged();

               /* for (DataSnapshot ds : dataSnapshot.getChildren())
                {

                }*/
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
/*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("User");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        User qq =   dataSnapshot1.getValue(User.class);
                        String name = qq.getFirstName();
                        mUsernames.add(name);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("dd", "Failed to read value.", error.toException());
            }
        });
*/
    }


    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextFinish);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(StepFinish.this, MainActivity.class);
                //startActivity(intent);
            }
        });
    }
}