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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	28.11.2017		Eduard Arefjev 		Created to manage firebase functions
 * 	25.12.2017      Eduard Arefjev      Created new function "updateData"
 */
public class FirebaseManager {

    static FirebaseAuth mAuth; //= FirebaseAuth.getInstance();
    private FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    static DatabaseReference mDatabase;
    static int counter=0;
    static String idd;

    static void singInFB(String email, String password, final Context context){
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

    public static void updateData(final String node, final int position, final User user){
        mDatabase = FirebaseDatabase.getInstance().getReference().child(node);
        counter=0;
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(position == counter) {
                    idd = dataSnapshot.getKey();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child(node).child(idd);
                    Map<String, Object> userNicknameUpdates = new HashMap<>();
                    //String name = user.getFirstName();
                    userNicknameUpdates.put("firstName", user.getFirstName());
                    userNicknameUpdates.put("lastName", user.getLastName());
                    //userNicknameUpdates.put("email", user.getEmail());
                    userNicknameUpdates.put("privileges", user.getPrivileges());
                    mDatabase.updateChildren(userNicknameUpdates);
                }
                counter++;
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



        //mDatabase.setValue();
    }

    //static String result;
    public static String currentUser(){
        final String[] result = new String[1];
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        assert mUser != null;
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
                        User user = issue.getValue(User.class);
                        assert user != null;
                        result[0] = user.getLastName();
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
