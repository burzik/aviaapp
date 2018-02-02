package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

import java.util.HashMap;

import static java.lang.Double.MAX_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.10.2017		Eduard Arefjev 		Created "StepN85" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 */

public class StepN85 extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    //String parentView;
    boolean showValues;
    boolean editableValues;
    HashMap<String, Boolean> hashMap;

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
        setContentView(R.layout.linear_step_n_85);
        this.setTitle(R.string.label_n1_equals_85);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        //parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", false);
        hashMap = (HashMap<String, Boolean>)intent.getSerializableExtra("map");

        if (hashMap != null && hashMap.size() != 0){
            if(!hashMap.get("checkbox_n1_equals_85")) {
                intent = new Intent(this, StepClosingKVD3.class);
                intent.putExtra("recordId", id);
                intent.putExtra("showValues", showValues);
                intent.putExtra("editableValues", editableValues);
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                intent.putExtra("map", hashMap);
                startActivity(intent);
            }
        }

        EditText eFlightTakeoff = findViewById(R.id.LinearLabelInpFlightTakeoff);
        CreationHelper.checkValue(eFlightTakeoff, 2, 4);
        EditText eTakeoffLanding = findViewById(R.id.LinearLabelInpTakeoffLangding);
        CreationHelper.checkValue(eTakeoffLanding, 2, 3);
        EditText eFlightLanding = findViewById(R.id.LinearLabelInpFlightLanding);
        CreationHelper.checkValue(eFlightLanding, 5, 10);
        EditText eLowPrc = findViewById(R.id.LinearLabelInpLowPrc);
        CreationHelper.checkValue(eLowPrc, 70, MAX_VALUE); //>70
        EditText eRelease = findViewById(R.id.LinearLabelInpRelease);
        CreationHelper.checkValue(eRelease, 1.5, 2.5);
        EditText eCleaning = findViewById(R.id.LinearLabelInpCleaning);
        CreationHelper.checkValue(eCleaning, 1.5, 2.5);
        EditText eLowPrc2 = findViewById(R.id.LinearLabelInpLowPrc2);
        CreationHelper.checkValue(eLowPrc2, 60, MAX_VALUE);
        EditText eTmc = findViewById(R.id.LinearLabelInpTmc);
        CreationHelper.checkValue(eTmc, -5, MAX_VALUE);
        EditText eVGenerator = findViewById(R.id.LinearLabelInpVGenerator);
        CreationHelper.checkValue(eVGenerator, 27, 29);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextN85);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepN85.this, StepClosingKVD3.class);
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
        EditText eFlightTakeoff = findViewById(R.id.LinearLabelInpFlightTakeoff);
        EditText eTakeoffLanding = findViewById(R.id.LinearLabelInpTakeoffLangding);
        EditText eFlightLanding = findViewById(R.id.LinearLabelInpFlightLanding);
        EditText eLowPrc = findViewById(R.id.LinearLabelInpLowPrc);
        EditText eRelease = findViewById(R.id.LinearLabelInpRelease);
        EditText eCleaning = findViewById(R.id.LinearLabelInpCleaning);
        EditText eLowPrc2 = findViewById(R.id.LinearLabelInpLowPrc2);
        EditText eTmc = findViewById(R.id.LinearLabelInpTmc);
        EditText eVGenerator = findViewById(R.id.LinearLabelInpVGenerator);

        if (!eFlightTakeoff.getText().toString().isEmpty())
            engineData.setStage5N1FlightTakeoff(Integer.valueOf(eFlightTakeoff.getText().toString()));
        if (!eTakeoffLanding.getText().toString().isEmpty())
            engineData.setStage5N1TakeoffLanding(Integer.valueOf(eTakeoffLanding.getText().toString()));
        if (!eFlightLanding.getText().toString().isEmpty())
            engineData.setStage5N1FlightLanding(Integer.valueOf(eFlightLanding.getText().toString()));
        if (!eLowPrc.getText().toString().isEmpty())
            engineData.setStage5N1Prc(Integer.valueOf(eLowPrc.getText().toString()));
        if (!eRelease.getText().toString().isEmpty())
            engineData.setStage5N1Release(Integer.valueOf(eRelease.getText().toString()));
        if (!eCleaning.getText().toString().isEmpty())
            engineData.setStage5N1Cleaning(Integer.valueOf(eCleaning.getText().toString()));
        if (!eLowPrc2.getText().toString().isEmpty())
            engineData.setStage5N1BrakePrc(Integer.valueOf(eLowPrc2.getText().toString()));
        if (!eTmc.getText().toString().isEmpty())
            engineData.setStage5N1Tmc(Integer.valueOf(eTmc.getText().toString()));
        if (!eVGenerator.getText().toString().isEmpty())
            engineData.setStage5N1VGenerator(Integer.valueOf(eVGenerator.getText().toString()));
    }

    public void updateUI(){
        if(showValues) {
            EditText eFlightTakeoff = findViewById(R.id.LinearLabelInpFlightTakeoff);
            EditText eTakeoffLanding = findViewById(R.id.LinearLabelInpTakeoffLangding);
            EditText eFlightLanding = findViewById(R.id.LinearLabelInpFlightLanding);
            EditText eLowPrc = findViewById(R.id.LinearLabelInpLowPrc);
            EditText eRelease = findViewById(R.id.LinearLabelInpRelease);
            EditText eCleaning = findViewById(R.id.LinearLabelInpCleaning);
            EditText eLowPrc2 = findViewById(R.id.LinearLabelInpLowPrc2);
            EditText eTmc = findViewById(R.id.LinearLabelInpTmc);
            EditText eVGenerator = findViewById(R.id.LinearLabelInpVGenerator);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eFlightTakeoff);
                CreationHelper.makeEditTextReadOnly(eTakeoffLanding);
                CreationHelper.makeEditTextReadOnly(eFlightLanding);
                CreationHelper.makeEditTextReadOnly(eLowPrc);
                CreationHelper.makeEditTextReadOnly(eRelease);
                CreationHelper.makeEditTextReadOnly(eCleaning);
                CreationHelper.makeEditTextReadOnly(eLowPrc2);
                CreationHelper.makeEditTextReadOnly(eTmc);
                CreationHelper.makeEditTextReadOnly(eVGenerator);
            }

            eFlightTakeoff.setText(Integer.toString(engineData.getStage5N1FlightTakeoff()));
            eTakeoffLanding.setText(Integer.toString(engineData.getStage5N1TakeoffLanding()));
            eFlightLanding.setText(Integer.toString(engineData.getStage5N1FlightLanding()));
            eLowPrc.setText(Integer.toString(engineData.getStage5N1Prc()));
            eRelease.setText(Integer.toString(engineData.getStage5N1Release()));
            eCleaning.setText(Integer.toString(engineData.getStage5N1Cleaning()));
            eLowPrc2.setText(Integer.toString(engineData.getStage5N1BrakePrc()));
            eTmc.setText(Integer.toString(engineData.getStage5N1Tmc()));
            eVGenerator.setText(Integer.toString(engineData.getStage5N1VGenerator()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}