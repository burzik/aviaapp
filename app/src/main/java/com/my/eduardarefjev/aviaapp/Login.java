package com.my.eduardarefjev.aviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by EduardArefjev on 04/11/2017.
 */

public class Login extends AppCompatActivity{

    private EditText eEmail;
    private EditText ePass;
    private Button bSingUp;
    private Button bSingIn;
    private Button bSignInOffline;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_login);

        this.setTitle("Login");




        eEmail = (EditText) findViewById(R.id.LinearInpEmail);
        ePass = (EditText) findViewById(R.id.LinearPassword);
        bSingUp = (Button) findViewById(R.id.LinearSignUp);
        bSingIn = (Button) findViewById(R.id.LinearSignIn);
        bSignInOffline = (Button) findViewById(R.id.LinearSignInOffline);
        //TODO Offline button

        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        //String st = mUser.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null){
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
/*
        bSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singup(eEmail.getText().toString(), ePass.getText().toString());
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        */

        bSignInOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseManager.singinFB("admin@admin.com", "admin123", Login.this);
            }
        });
    }

    public void singin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()) {
                   Toast.makeText(Login.this, "Successful", Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(Login.this, MainActivity.class);
                   startActivity(intent);
               }
               else
                   Toast.makeText(Login.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void createUser(String email, String password){
       // CreateRequest request
    }

    public void singup(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                    Toast.makeText(Login.this, "Successful", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Login.this, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
        //updateUI(currentUser);
       // mAuth.addAuthStateListener();
    }

}
