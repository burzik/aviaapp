package com.my.eduardarefjev.aviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.my.eduardarefjev.aviaapp.CreationSteps.StepEngineInfo;

import static com.my.eduardarefjev.aviaapp.FirebaseManager.mAuth;

public class MainActivity extends AppCompatActivity {

    private Button bCreateRecord;
    private Button bShowRecords;
    private Button bCreateUser;
    private Button bSetPrivileges;
    private Button bSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_main_screen);

        final TextView hello = (TextView) findViewById(R.id.LinearHello);
        //String name = FirebaseManager.currentUser();
        //hello.append(" name" + name);

        createNewRecord();
        showRecords();
        createUser();
        setPrivileges();
        settings();


        //////TODO Add to FirebaseManager
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String userUid = "";
        if (mUser != null) {
            userUid = mUser.getUid();
        }
            //Query
            DatabaseReference mDatabase;
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
            Query query = mDatabase.orderByChild("uid").equalTo(userUid);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot issue : dataSnapshot.getChildren()) {
                            User spacecraft1 = issue.getValue(User.class);
                            hello.append(" " + spacecraft1.getLastName());
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        //}
    }

    public void createNewRecord(){
        bCreateRecord = (Button)findViewById(R.id.LinearCreateRecord);
        bCreateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StepEngineInfo.class);
                startActivity(intent);
            }
        });
    }

    public void showRecords(){
        bShowRecords = (Button)findViewById(R.id.LinearShowRecords);
        bShowRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Records.class);
                startActivity(intent);
            }
        });
    }

    public void createUser(){
        bCreateUser = (Button)findViewById(R.id.LinearCreateUser);
        bCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateUser.class);
                startActivity(intent);
            }
        });
    }

    public void setPrivileges(){
        bSetPrivileges = (Button)findViewById(R.id.LinearSetPrivileges);
        bSetPrivileges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListOfUsers.class);
                startActivity(intent);
            }
        });
    }

    public void settings(){
        bSettings = (Button)findViewById(R.id.LinearSettings);
        bSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
