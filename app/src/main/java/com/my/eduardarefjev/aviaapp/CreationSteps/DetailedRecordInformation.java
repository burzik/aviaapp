package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	30.12.2017		Eduard Arefjev 		Created "DetailedRecordInformation" screen
 * 	31.12.2017      Eduard Arefjev      Added updating in FireBase and retrieving data from FireBase
 */

public class DetailedRecordInformation extends AppCompatActivity {

    String id;
    public StepEngineData engineData;
    private static DatabaseReference mDatabase;
    private static int counter = 0;
    int position;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_record_detailed_info);
        this.setTitle("Detailed info");

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        Bundle extra = getIntent().getBundleExtra("extra");
        ArrayList<StepEngineData> objects = (ArrayList<StepEngineData>) extra.getSerializable("objects");
        assert objects != null;
        engineData = objects.get(position);

        getId();
        updateUI();
        nextSecondStep();
    }

    public void updateUI(){
        EditText eEngineNumber = (EditText) findViewById(R.id.LinearInpEngineNumber);
        EditText ePlaneBoardId = (EditText) findViewById(R.id.LinearInpPlaneNumber);
        EditText eWP = (EditText) findViewById(R.id.LinearInpWP);
        EditText eAtmPressure = (EditText) findViewById(R.id.LinearInpAtmPressure);
        EditText eAtmTemperature = (EditText) findViewById(R.id.LinearInpAtmTemperature);
        EditText eWork0Nominal = (EditText) findViewById(R.id.LinearInpWork0Nominal);
        EditText eWorkNominal = (EditText) findViewById(R.id.LinearInpWorkNominal);
        EditText eWorkMax = (EditText) findViewById(R.id.LinearInpWorkMax);
        TextView date = (TextView) findViewById(R.id.LinearDate);
        Date currentTime = engineData.getLaunchDate();
        date.append(" " + DateFormat.format("dd.MM.yyyy", currentTime).toString());
        //String.format(Locale.ENGLISH,"",engineData.getEngineId());

        eEngineNumber.setText(Integer.toString(engineData.getEngineId()));
        ePlaneBoardId.setText(Integer.toString(engineData.getPlaneBoardId()));
        eWP.setText(engineData.getLaunchId());
        eAtmPressure.setText(Integer.toString(engineData.getAtmPressure()));
        eAtmTemperature.setText(Integer.toString(engineData.getAtmTemp()));
        eWork0Nominal.setText(Float.toString(engineData.getWork0Nominal()));
        eWorkNominal.setText(Float.toString(engineData.getWorkNominal()));
        eWorkMax.setText(Float.toString(engineData.getWorkMax()));
    }

    public void getId(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineLaunches");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (position == counter) {
                    id = dataSnapshot.getKey();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineLaunches").child(id);
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
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNext);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(DetailedRecordInformation.this, StepStartInfo.class);
                intent.putExtra("recordId", id);
                intent.putExtra("parentViewName", "DetailedRecordInformation");
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        EditText eEngineNumber = (EditText) findViewById(R.id.LinearInpEngineNumber);
        EditText ePlaneBoardId = (EditText) findViewById(R.id.LinearInpPlaneNumber);
        EditText eWP = (EditText) findViewById(R.id.LinearInpWP);
        EditText eAtmPressure = (EditText) findViewById(R.id.LinearInpAtmPressure);
        EditText eAtmTemperature = (EditText) findViewById(R.id.LinearInpAtmTemperature);
        EditText eWork0Nominal = (EditText) findViewById(R.id.LinearInpWork0Nominal);
        EditText eWorkNominal = (EditText) findViewById(R.id.LinearInpWorkNominal);
        EditText eWorkMax = (EditText) findViewById(R.id.LinearInpWorkMax);

        engineData.setEngineId(Integer.valueOf(eEngineNumber.getText().toString()));
        engineData.setPlaneBoardId(Integer.valueOf(ePlaneBoardId.getText().toString()));
        engineData.setLaunchId(eWP.getText().toString());
        engineData.setAtmPressure(Integer.valueOf(eAtmPressure.getText().toString()));
        engineData.setAtmTemp(Integer.valueOf(eAtmTemperature.getText().toString()));
        engineData.setAtmPressure(Integer.valueOf(eAtmPressure.getText().toString()));
        engineData.setWork0Nominal(Float.valueOf(eWork0Nominal.getText().toString()));
        engineData.setWorkNominal(Float.valueOf(eWorkNominal.getText().toString()));
        engineData.setWorkMax(Float.valueOf(eWorkMax.getText().toString()));
    }
}
