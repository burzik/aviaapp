package com.my.eduardarefjev.aviaapp.UserInformation;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;


/**
 * HISTORY
 * 	Date			Author				Comments
 * 	03.11.2017		Eduard Arefjev 		Created "ListOfUser" screen to display all users
 * 	28.12.2017      Eduard Arefjev      Some minor refactoring
 */

public class ListOfUsers extends ListActivity {

    private DatabaseReference mDatabase;
    private ArrayList<User> userArrayList = new ArrayList<>();
    private ListOfUsersViewHolder m_adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_two_elements);
        this.setTitle("Users");

        m_adapter = new ListOfUsersViewHolder(this, R.layout.listview_two_elements, userArrayList);

        setListAdapter(m_adapter);
        Runnable viewParts = new Runnable() {
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread =  new Thread(null, viewParts, "ListOfUsersBackground");
        thread.start();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    private void updateAdapter(){
        m_adapter.clear();
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user;
                user = dataSnapshot.getValue(User.class);
                m_adapter.add(user);
                m_adapter.notifyDataSetChanged();

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
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {


        public void handleMessage(Message msg)
        {
            m_adapter = new ListOfUsersViewHolder(ListOfUsers.this, R.layout.listview_records, userArrayList);
            // display the list.
            setListAdapter(m_adapter);
        }
    };

    @Override
    protected void onResume() {
        super.onStart();
        updateAdapter();
        }
}