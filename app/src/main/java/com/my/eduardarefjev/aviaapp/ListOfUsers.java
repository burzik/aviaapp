package com.my.eduardarefjev.aviaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by EduardArefjev on 03/11/2017.
 */

public class ListOfUsers extends ListActivity {

    //private RecyclerView mUserList;

    private Button bNextStep;
    private DatabaseReference mDatabase;
    private ListView mUserList;
    private ArrayList<String> mUsernames = new ArrayList<>();
    //private ArrayList<User> mUsernames = new ArrayList<>();
    private ArrayList<User> m_parts = new ArrayList<User>();
    private Runnable viewParts;
    private ListOfUsersViewHolder m_adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Users");

        m_adapter = new ListOfUsersViewHolder(this, R.layout.activity_main, m_parts);

        setListAdapter(m_adapter);
        viewParts = new Runnable() {
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread =  new Thread(null, viewParts, "MagentoBackground");
        thread.start();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mUserList = (ListView) findViewById(R.id.LinearListOfUsers);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsernames);
        //mUserList.setAdapter(arrayAdapter);

        //updateAdapter();

/*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("User");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        User qq =   dataSnapshot1.getValue(User.class);
                        String name = qq.getFirstName();
                        mUsernames.add(name);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("dd", "Failed to read value.", error.toException());
            }
        });
*/
    }

    private void updateAdapter(){
        m_adapter.clear();
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                User user=dataSnapshot.getValue(User.class);
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

    private Handler handler = new Handler()
    {


        public void handleMessage(Message msg)
        {
            // create some objects
            // here is where you could also request data from a server
            // and then create objects from that data.
            //m_parts.add(new User("MyItemName", "This is item #1", 0));
            //m_parts.add(new User("MyItemName #2", "123"));

            m_adapter = new ListOfUsersViewHolder(ListOfUsers.this, R.layout.activity_main, m_parts);

            // display the list.

            setListAdapter(m_adapter);
        }
    };

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextFinish);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(StepFinish.this, MainActivity.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onStart();

        updateAdapter();
        int a = 1;
        int b =2;
        }
}