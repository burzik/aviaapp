package com.my.eduardarefjev.aviaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

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

        createNewRecord();
        showRecords();
        createUser();
        setPrivileges();
        settings();
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
