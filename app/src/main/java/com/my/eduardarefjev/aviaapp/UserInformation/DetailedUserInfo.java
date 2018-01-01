package com.my.eduardarefjev.aviaapp.UserInformation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.my.eduardarefjev.aviaapp.FirebaseManager;
import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	23.12.2017		Eduard Arefjev 		Created "DetailedUserInfo" screen to change user information
 * 	01.01.2017      Eduard Arefjev      Updated layout id, due of duplicate error
 */

public class DetailedUserInfo extends AppCompatActivity {

    //EA init
    private static ArrayList<User> values;
    private EditText eFirstName;
    private EditText eLastName;
    private TextView eEmail;
    private EditText ePassword;
    private Spinner spinnerPrivileges;
    private Button bUpdate;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_detailed_user_info);
        this.setTitle("Users");

        //EA get user
        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", -1);
        Bundle extra = getIntent().getBundleExtra("extra");
        ArrayList<User> objects = (ArrayList<User>) extra.getSerializable("objects");
        assert objects != null;
        final User user = objects.get(position);

        //EA find views
        eFirstName = (EditText) findViewById(R.id.LinearLabelInpFirstName);
        eLastName = (EditText) findViewById(R.id.LinearLabelInpLastName);
        eEmail = (TextView) findViewById(R.id.LinearLabelInpEmailDetailed);
        //ePassword = (EditText) findViewById(R.id.LinearLabelInpPassword);
        spinnerPrivileges = (Spinner) findViewById(R.id.LinearLabelInpSpinnerPrivileges);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(DetailedUserInfo.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.privileges));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrivileges.setAdapter(myAdapter);
        int spinnerPosition = myAdapter.getPosition(user.getPrivileges());
        spinnerPrivileges.setSelection(spinnerPosition);
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        //EA fill data
        eFirstName.setText(user.getFirstName());
        eLastName.setText(user.getLastName());
        eEmail.setText(user.getEmail());
        //ePassword.setText(user.getPa());


        bUpdate = (Button) findViewById(R.id.LinearButtonUpdateUser);
        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setFirstName(eFirstName.getText().toString());
                user.setLastName(eLastName.getText().toString());
                user.setEmail(eEmail.getText().toString());
                user.setPrivileges(spinnerPrivileges.getSelectedItem().toString());
                FirebaseManager.updateData("Users", position, user);
            }
        });
    }
}
