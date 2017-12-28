package com.my.eduardarefjev.aviaapp.UserInformation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.eduardarefjev.aviaapp.R;

import java.util.HashMap;
import java.util.List;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.10.2017		Eduard Arefjev 		Created "UserCreation" screen
 */

public class UserCreation extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseUser mUser2;
    private FirebaseAuth mAuth2;

    private EditText eFirstName;
    private EditText eLastName;
    private EditText eEmail;
    private EditText ePassword;
    private Spinner spinnerPrivileges;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.linear_create_user);

        //EA Bind variables
        Button bCreate = (Button) findViewById(R.id.LinearButtonCreateUser);
        Button bClear = (Button) findViewById(R.id.LinearButtonClear);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        eFirstName = (EditText) findViewById(R.id.LinearLabelInpFirstName);
        eLastName = (EditText) findViewById(R.id.LinearLabelInpLastName);
        eEmail = (EditText) findViewById(R.id.LinearLabelInpEmail);
        ePassword = (EditText) findViewById(R.id.LinearLabelInpPassword);
        spinnerPrivileges = (Spinner) findViewById(R.id.LinearLabelInpSpinnerPrivileges);

        //EA Create second copy of db
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setDatabaseUrl("https://aviaapp-c9281.firebaseio.com/")
                .setApiKey("AIzaSyCJPedkRhA5bdzTnTJLEq4UQnr3KY3iQEU")
                .setApplicationId("aviaapp-c9281").build();


        boolean hasBeenInitialized = false;
        List<FirebaseApp> firebaseApps = FirebaseApp.getApps(this);
        FirebaseApp finestayApp;

        for(FirebaseApp app : firebaseApps){
            if(app.getName().equals("AnyAppName")){
                hasBeenInitialized = true;
                finestayApp = app;
                mAuth2 = FirebaseAuth.getInstance(finestayApp);
            }
        }

        if(!hasBeenInitialized) {
            FirebaseApp myApp = FirebaseApp.initializeApp(getApplicationContext(),firebaseOptions, "AnyAppName");
            mAuth2 = FirebaseAuth.getInstance(myApp);
        }

        //EA Create DropDown List
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(UserCreation.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.privileges));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrivileges.setAdapter(myAdapter);


        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //EA Get values from inputs
                String sFirstName = eFirstName.getText().toString().trim();
                String sLastName = eLastName.getText().toString().trim();
                String sEmail = eEmail.getText().toString().trim();
                String sPassword = ePassword.getText().toString().trim();
                String sPrivileges = spinnerPrivileges.getSelectedItem().toString();

                //EA fill datamap
                HashMap<String, String> dataMap = new HashMap<>();
                dataMap.put("firstName", sFirstName);
                dataMap.put("lastName", sLastName);
                dataMap.put("email", sEmail);
                dataMap.put("privileges", sPrivileges);

                //EA Sign up user
                createUser(sEmail, sPassword, dataMap);
            }
        });

        bClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eFirstName.setText("");
                eLastName.setText("");
                eEmail.setText("");
                ePassword.setText("");
            }
        });
    }

    private void createUser(String email, String password, final HashMap<String, String> dataMap) {
        mAuth2.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {

                    String ex = task.getException().toString();
                    Toast.makeText(UserCreation.this, "Registration Failed"+ex, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UserCreation.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    mUser2 = mAuth2.getCurrentUser();
                    assert mUser2 != null;
                    String sUID = mUser2.getUid();
                    mAuth2.signOut();

                    dataMap.put("uid", sUID);
                    mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(UserCreation.this, "NYA", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(UserCreation.this, "sad", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
