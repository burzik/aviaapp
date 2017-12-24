package com.my.eduardarefjev.aviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by EduardArefjev on 23/12/2017.
 */

public class DetailedUserInfo extends AppCompatActivity {

    private static ArrayList<User> values;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_detailed_user_info);

        this.setTitle("Users");

        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", -1);
        String as = intent.getStringExtra("firstname");
        values = new ArrayList<>();
        //values= intent.getParcelableArrayListExtra("firstname");
        int a = 1;
        //final User us = intent.getParcelableArrayListExtra("firstname");



        //User user = values.get(position);
        //String name = user.getFirstName();
        //String xx;
        //User todoList = ListOfUsersViewHolder.get(position);

    }
}
