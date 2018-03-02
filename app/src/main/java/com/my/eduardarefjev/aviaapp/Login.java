package com.my.eduardarefjev.aviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	04.11.2017		Eduard Arefjev 		Created "Login" screen
 * 	01.01.2017      Eduard Arefjev      Created new method "isAuthorized"
 */

public class Login extends AppCompatActivity{

    private EditText eEmail;
    private EditText ePass;
    private FirebaseAuth mAuth;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_login);
        this.setTitle(R.string.login);

        mAuth = FirebaseAuth.getInstance();

        singIn();
        signInOffline();
    }

    public void singIn(){
        Button bSingIn = findViewById(R.id.RelativeButtonSignIn);
        eEmail = findViewById(R.id.RelativeInpEmail);
        ePass = findViewById(R.id.RelativeInpPassword);

        bSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!eEmail.getText().toString().isEmpty() && !ePass.getText().toString().isEmpty())
                    FirebaseManager.singInFB(eEmail.getText().toString(), ePass.getText().toString(), Login.this);
                else Toast.makeText(Login.this, "Enter email or password", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signInOffline(){
        Button bSignInOffline = findViewById(R.id.RelativeButtonSignInOffline);

        bSignInOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void isAuthorized(){
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        isAuthorized();
    }
}
