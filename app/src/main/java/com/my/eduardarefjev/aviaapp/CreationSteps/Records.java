package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.10.2017		Eduard Arefjev 		Created "Records" screen, to display all records
 * 	01.01.2017      Eduard Arefjev      Minor Refactoring
 * 	15.02.2018      Eduard Arefjev      Created sort
 */

public class Records extends ListActivity {

    private DatabaseReference mDatabase;
    private ArrayList<StepEngineData> stepEngineDataArrayList = new ArrayList<>();
    private ArrayList<StepEngineData> enginesNumbers = new ArrayList<>();
    private RecordsViewHolder m_adapter;
    private boolean isSelected = false;
    boolean isAcc = true;
    private List<String> list = new ArrayList<>();
    private Spinner engineNumber;
    private ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_records);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineLaunches");
        engineNumber = findViewById(R.id.RelativeSpinnerSearchEngineNumber);
        m_adapter = new RecordsViewHolder(this, R.layout.listview_records, stepEngineDataArrayList);
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        setListAdapter(m_adapter);

        Runnable viewParts = new Runnable() {
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread =  new Thread(null, viewParts, "ListOfRecordsBackground");
        thread.start();

        createSpinnerForEngine();
        fillEngineList();
        sortByDate();
    }

    public void sortByDate(){
        TextView textDate = findViewById(R.id.RelativeSearchDate);
        final TextView textView = findViewById(R.id.RelativeSearchDateSymbol);

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAcc ^= true;
                m_adapter.sort(new Comparator<StepEngineData>() {
                    @Override
                    public int compare(StepEngineData o1, StepEngineData o2) {
                        if(isAcc) {
                            textView.setText("↑");
                            return o2.getLaunchDate().compareTo(o1.getLaunchDate());
                        } else {
                            textView.setText("↓");
                            return o1.getLaunchDate().compareTo(o2.getLaunchDate());
                        }
                    }
                });
            }
        });
    }

    public void fillEngineList(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineNumber");
        list.add(getString(R.string.label_all));
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getKey());
                engineNumber.setAdapter(myAdapter);
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

    public void createSpinnerForEngine(){
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        engineNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isSelected){
                    enginesNumbers = new ArrayList<>();
                    String value = list.get(position);
                    if(value.equals(getString(R.string.label_all))) value = "0";
                        for (StepEngineData engData : stepEngineDataArrayList) {
                            if (engData.getEngineId() == Integer.valueOf(value) || Integer.valueOf(value) == 0) {
                                enginesNumbers.add(engData);
                            }
                        }
                    m_adapter = new RecordsViewHolder(Records.this, R.layout.listview_records, enginesNumbers);
                    setListAdapter(m_adapter);
                }
                isSelected = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
    private Handler handler = new Handler() {
        public void handleMessage(Message msg)
        {
            m_adapter = new RecordsViewHolder(Records.this, R.layout.listview_records, stepEngineDataArrayList);
            // display the list.
            setListAdapter(m_adapter);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        super.onStart();
        updateAdapter();
    }
}