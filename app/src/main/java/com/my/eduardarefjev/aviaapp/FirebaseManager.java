package com.my.eduardarefjev.aviaapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

/**
 * Created by EduardArefjev on 28/11/2017.
 */

public class FirebaseManager {

    static FirebaseAuth mAuth; //= FirebaseAuth.getInstance();
    private FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

    static void singInFB(String email, String password, final Context context) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
                else
                    Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
    //static String result;
    public static String currentUser(){
        final String[] result = new String[1];
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String userUid = mUser.getUid();
        //Query
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        Query query = mDatabase.orderByChild("uid").equalTo(userUid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //User spacecraft=dataSnapshot.getValue(User.class);
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        User spacecraft1 = issue.getValue(User.class);
                        result[0] = spacecraft1.getLastName();
                        String b = "bb";
                        // do something with the individual "issues"
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return result[0];
    }

}
