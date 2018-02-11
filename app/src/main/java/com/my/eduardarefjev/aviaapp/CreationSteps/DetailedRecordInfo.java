package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.eduardarefjev.aviaapp.EngineInformation;
import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	30.12.2017		Eduard Arefjev 		Created "DetailedRecordInfo" screen
 * 	31.12.2017      Eduard Arefjev      Added updating in FireBase and retrieving data from FireBase
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 * 	08.02.2018      Eduard Arefjev      Engine number as spinner
 */

public class DetailedRecordInfo extends AppCompatActivity {

    String id;
    public StepEngineData engineData;
    DatabaseReference mDatabase;
    int counter = 0;
    int position;
    HashMap<String, Boolean> hashMap = new HashMap<>();
    boolean showValues = true;
    boolean editableValues = false;
    final List<String> list = new ArrayList<>();
    ArrayAdapter<String> myAdapter;
    EngineInformation engineInformation = new EngineInformation();
    ArrayList<EngineInformation> obj = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //EA Inflate the menu
        if(!editableValues)
            getMenuInflater().inflate(R.menu.menu_creation_steps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean itemSelected = super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.UpdateRecord: {
                Intent intent = new Intent(this, UpdateRecordMenu.class);
                intent.putExtra("recordId", id);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);
                break;
            }
            default:
                return itemSelected;
        }
        return true;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_engine_info);
        this.setTitle(R.string.label_base_values);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        if (position == -1)
        {
            id = intent.getStringExtra("recordId");
            Bundle extra = getIntent().getBundleExtra("extra");
            engineData  = extra.getParcelable("objects");
            hashMap = (HashMap<String, Boolean>)intent.getSerializableExtra("map");
            showValues = intent.getBooleanExtra("showValues", true);
            editableValues = intent.getBooleanExtra("editableValues", false);

            if (hashMap != null){
                if (!hashMap.get("checkbox_base_values")){
                    intent = new Intent(DetailedRecordInfo.this, StepStartInfo.class);
                    intent.putExtra("recordId", id);
                    intent.putExtra("showValues", showValues);
                    intent.putExtra("editableValues", editableValues);
                    extra.putParcelable("objects", engineData);
                    intent.putExtra("extra", extra);
                    intent.putExtra("map", hashMap);
                    startActivity(intent);
                }
            }
        }
        else {
            Bundle extra = getIntent().getBundleExtra("extra");
            ArrayList<StepEngineData> objects = (ArrayList<StepEngineData>) extra.getSerializable("objects");
            assert objects != null;
            engineData = objects.get(position);
            getId();
        }

        final Spinner engineNumber = findViewById(R.id.LinearLabelInpSpinnerEngineNumber);
        //EA Create DropDown List
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final String num = String.valueOf(engineData.getEngineId());

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("EngineNumber");
        mDatabase.addChildEventListener(new ChildEventListener() {
            int spinnerPosition = 0;
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getKey());
                engineNumber.setAdapter(myAdapter);
                engineInformation = dataSnapshot.getValue(EngineInformation.class);
                obj.add(engineInformation);
                if (num.equals(dataSnapshot.getKey()) && showValues)
                {
                    spinnerPosition = myAdapter.getPosition(String.valueOf(engineData.getEngineId()));
                    updateEngineUI();
                    TextView resourceBalance = findViewById(R.id.LinearInpResourceBalance);
                    resourceBalance.setText(String.valueOf(engineInformation.getEngineResource() - engineInformation.getEngineRunningTimeAfterRepair()));
                }
                engineNumber.setSelection(spinnerPosition);
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



        calculateBalance();
        updateSelectedEngine();
        updateUI();
        nextSecondStep();
    }

    public void updateUI(){
        if (showValues) {
            Spinner eEngineNumber = findViewById(R.id.LinearLabelInpSpinnerEngineNumber);
            EditText ePlaneBoardId = findViewById(R.id.LinearInpPlaneNumber);
            EditText eWP = findViewById(R.id.LinearInpWP);
            EditText eAtmPressure = findViewById(R.id.LinearInpAtmPressure);
            EditText eAtmTemperature = findViewById(R.id.LinearInpAtmTemperature);
            EditText eWork0Nominal = findViewById(R.id.LinearInpWork0Nominal);
            EditText eWorkNominal = findViewById(R.id.LinearInpWorkNominal);
            EditText eWorkMax = findViewById(R.id.LinearInpWorkMax);
            TextView date = findViewById(R.id.LinearDate);
            Date currentTime = engineData.getLaunchDate();
            date.append(" " + DateFormat.format("dd.MM.yyyy", currentTime).toString());
            //String.format(Locale.ENGLISH,"",engineData.getEngineId());
            EditText eTotalEngineRunningTime = findViewById(R.id.LinearInpTotalEngineRunningTime);
            EditText eEngineRunningTimeAfterRepair = findViewById(R.id.LinearInpEngineRunningTimeAfterRepair);
            EditText eEngineResource = findViewById(R.id.LinearInpEngineResource);

            if (!editableValues) {
                CreationHelper.makeSpinnerReadOnly(eEngineNumber);
                CreationHelper.makeEditTextReadOnly(ePlaneBoardId);
                CreationHelper.makeEditTextReadOnly(eWP);
                CreationHelper.makeEditTextReadOnly(eAtmPressure);
                CreationHelper.makeEditTextReadOnly(eAtmTemperature);
                CreationHelper.makeEditTextReadOnly(eWork0Nominal);
                CreationHelper.makeEditTextReadOnly(eWorkNominal);
                CreationHelper.makeEditTextReadOnly(eWorkMax);
                CreationHelper.makeEditTextReadOnly(eTotalEngineRunningTime);
                CreationHelper.makeEditTextReadOnly(eEngineRunningTimeAfterRepair);
                CreationHelper.makeEditTextReadOnly(eEngineResource);
            }
            int spinnerPosition = myAdapter.getPosition(String.valueOf(engineData.getEngineId()));
            eEngineNumber.setSelection(spinnerPosition);
            ePlaneBoardId.setText(Integer.toString(engineData.getPlaneBoardId()));
            eWP.setText(engineData.getLaunchId());
            eAtmPressure.setText(Integer.toString(engineData.getAtmPressure()));
            eAtmTemperature.setText(Integer.toString(engineData.getAtmTemp()));
            eWork0Nominal.setText(Float.toString(engineData.getWork0Nominal()));
            eWorkNominal.setText(Float.toString(engineData.getWorkNominal()));
            eWorkMax.setText(Float.toString(engineData.getWorkMax()));

            eTotalEngineRunningTime.setText(Integer.toString(engineInformation.getEngineRunningTime()));
            eEngineRunningTimeAfterRepair.setText(Integer.toString(engineInformation.getEngineRunningTimeAfterRepair()));
            eEngineResource.setText(Integer.toString(engineInformation.getEngineResource()));
        }
    }

    public void updateEngineUI(){
        EditText eTotalEngineRunningTime = findViewById(R.id.LinearInpTotalEngineRunningTime);
        EditText eEngineRunningTimeAfterRepair = findViewById(R.id.LinearInpEngineRunningTimeAfterRepair);
        EditText eEngineResource = findViewById(R.id.LinearInpEngineResource);

        if (!editableValues) {
            CreationHelper.makeEditTextReadOnly(eTotalEngineRunningTime);
            CreationHelper.makeEditTextReadOnly(eEngineRunningTimeAfterRepair);
            CreationHelper.makeEditTextReadOnly(eEngineResource);
        }

        eTotalEngineRunningTime.setText(Integer.toString(engineInformation.getEngineRunningTime()));
        eEngineRunningTimeAfterRepair.setText(Integer.toString(engineInformation.getEngineRunningTimeAfterRepair()));
        eEngineResource.setText(Integer.toString(engineInformation.getEngineResource()));
    }

    public void getId(){
        counter = 0;
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
        Button bNextStep = findViewById(R.id.LinearButtonNext);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                CreationHelper.updateEngine(String.valueOf(engineInformation.getEngineNumber()), engineInformation,"EngineNumber");
                Intent intent = new Intent(DetailedRecordInfo.this, StepStartInfo.class);
                intent.putExtra("recordId", id);
                intent.putExtra("showValues", showValues);
                intent.putExtra("editableValues", editableValues);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                intent.putExtra("map", hashMap);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        Spinner eEngineNumber = findViewById(R.id.LinearLabelInpSpinnerEngineNumber);
        EditText ePlaneBoardId = findViewById(R.id.LinearInpPlaneNumber);
        EditText eWP = findViewById(R.id.LinearInpWP);
        EditText eAtmPressure = findViewById(R.id.LinearInpAtmPressure);
        EditText eAtmTemperature = findViewById(R.id.LinearInpAtmTemperature);
        EditText eWork0Nominal = findViewById(R.id.LinearInpWork0Nominal);
        EditText eWorkNominal = findViewById(R.id.LinearInpWorkNominal);
        EditText eWorkMax = findViewById(R.id.LinearInpWorkMax);
        EditText eTotalEngineRunningTime = findViewById(R.id.LinearInpTotalEngineRunningTime);
        EditText eEngineRunningTimeAfterRepair = findViewById(R.id.LinearInpEngineRunningTimeAfterRepair);
        EditText eEngineResource = findViewById(R.id.LinearInpEngineResource);

        String sEngineNumber = eEngineNumber.getSelectedItem().toString();
        engineData.setEngineId(Integer.valueOf(sEngineNumber));
        engineData.setPlaneBoardId(Integer.valueOf(ePlaneBoardId.getText().toString()));
        engineData.setLaunchId(eWP.getText().toString());
        engineData.setAtmPressure(Integer.valueOf(eAtmPressure.getText().toString()));
        engineData.setAtmTemp(Integer.valueOf(eAtmTemperature.getText().toString()));
        engineData.setAtmPressure(Integer.valueOf(eAtmPressure.getText().toString()));
        engineData.setWork0Nominal(Float.valueOf(eWork0Nominal.getText().toString()));
        engineData.setWorkNominal(Float.valueOf(eWorkNominal.getText().toString()));
        engineData.setWorkMax(Float.valueOf(eWorkMax.getText().toString()));
        engineInformation.setEngineRunningTime(Integer.valueOf(eTotalEngineRunningTime.getText().toString()));
        engineInformation.setEngineRunningTimeAfterRepair(Integer.valueOf(eEngineRunningTimeAfterRepair.getText().toString()));
        engineInformation.setEngineResource(Integer.valueOf(eEngineResource.getText().toString()));
    }

    public void updateSelectedEngine(){
        final Spinner engineNumber = findViewById(R.id.LinearLabelInpSpinnerEngineNumber);
        engineNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                engineInformation = obj.get(position);
                updateEngineUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void calculateBalance(){

        final EditText eEngineRunningTimeAfterRepair = findViewById(R.id.LinearInpEngineRunningTimeAfterRepair);
        final EditText eEngineResource = findViewById(R.id.LinearInpEngineResource);

        eEngineRunningTimeAfterRepair.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView resourceBalance = findViewById(R.id.LinearInpResourceBalance);
                resourceBalance.setText(String.valueOf(Integer.valueOf(eEngineResource.getText().toString()) - Integer.valueOf(eEngineRunningTimeAfterRepair.getText().toString())));
            }
        });

        eEngineResource.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView resourceBalance = findViewById(R.id.LinearInpResourceBalance);
                resourceBalance.setText(String.valueOf(Integer.valueOf(eEngineResource.getText().toString()) - Integer.valueOf(eEngineRunningTimeAfterRepair.getText().toString())));
            }
        });
    }
}
