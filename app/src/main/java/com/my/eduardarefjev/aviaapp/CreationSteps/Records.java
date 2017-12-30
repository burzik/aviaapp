package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

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
 * 	29.10.2017		Eduard Arefjev 		Created "Records" screen, to display all records
 */

public class Records extends ListActivity {

    private Button bNextStep;

    private DatabaseReference mDatabase;
    private ArrayList<StepEngineData> m_parts = new ArrayList<>();
    private RecordsViewHolder m_adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Записи");

        m_adapter = new RecordsViewHolder(this, R.layout.activity_main, m_parts);

        setListAdapter(m_adapter);
        Runnable viewParts = new Runnable() {
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread =  new Thread(null, viewParts, "ListOfUsersBackground");
        thread.start();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineLaunches");
        //nextSecondStep();
    }


    private void updateAdapter(){
        m_adapter.clear();
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                StepEngineData user = dataSnapshot.getValue(StepEngineData.class);
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
            m_adapter = new RecordsViewHolder(Records.this, R.layout.activity_main, m_parts);
            // display the list.
            setListAdapter(m_adapter);
        }
    };

    @Override
    protected void onResume() {
        super.onStart();

        updateAdapter();
    }

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
}