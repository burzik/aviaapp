package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

import static java.lang.Double.MAX_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.10.2017		Eduard Arefjev 		Created "StepN85" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 */

public class StepN85 extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    String parentView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_n_85);
        this.setTitle("n1=85%");

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eFlightTakeoff = (EditText) findViewById(R.id.LinearLabelInpFlightTakeoff);
        CreationHelper.checkValue(eFlightTakeoff, 2, 4);
        EditText eTakeoffLanding = (EditText) findViewById(R.id.LinearLabelInpTakeoffLangding);
        CreationHelper.checkValue(eTakeoffLanding, 2, 3);
        EditText eFlightLanding = (EditText) findViewById(R.id.LinearLabelInpFlightLanding);
        CreationHelper.checkValue(eFlightLanding, 5, 10);
        EditText eLowPrc = (EditText) findViewById(R.id.LinearLabelInpLowPrc);
        CreationHelper.checkValue(eLowPrc, 70, MAX_VALUE); //>70
        EditText eRelease = (EditText) findViewById(R.id.LinearLabelInpRelease);
        CreationHelper.checkValue(eRelease, 1.5, 2.5);
        EditText eCleaning = (EditText) findViewById(R.id.LinearLabelInpCleaning);
        CreationHelper.checkValue(eCleaning, 1.5, 2.5);
        EditText eLowPrc2 = (EditText) findViewById(R.id.LinearLabelInpLowPrc2);
        CreationHelper.checkValue(eLowPrc2, 60, MAX_VALUE);
        EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc);
        CreationHelper.checkValue(eTmc, -5, MAX_VALUE);
        EditText eVGenerator = (EditText) findViewById(R.id.LinearLabelInpVGenerator);
        CreationHelper.checkValue(eVGenerator, 27, 29);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextN85);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepN85.this, StepClosingKVD3.class);
                intent.putExtra("recordId", id);
                intent.putExtra("parentViewName", parentView);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        EditText eFlightTakeoff = (EditText) findViewById(R.id.LinearLabelInpFlightTakeoff);
        EditText eTakeoffLanding = (EditText) findViewById(R.id.LinearLabelInpTakeoffLangding);
        EditText eFlightLanding = (EditText) findViewById(R.id.LinearLabelInpFlightLanding);
        EditText eLowPrc = (EditText) findViewById(R.id.LinearLabelInpLowPrc);
        EditText eRelease = (EditText) findViewById(R.id.LinearLabelInpRelease);
        EditText eCleaning = (EditText) findViewById(R.id.LinearLabelInpCleaning);
        EditText eLowPrc2 = (EditText) findViewById(R.id.LinearLabelInpLowPrc2);
        EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc);
        EditText eVGenerator = (EditText) findViewById(R.id.LinearLabelInpVGenerator);

        engineData.setStage5N1FlightTakeoff(Integer.valueOf(eFlightTakeoff.getText().toString()));
        engineData.setStage5N1TakeoffLanding(Integer.valueOf(eTakeoffLanding.getText().toString()));
        engineData.setStage5N1FlightLanding(Integer.valueOf(eFlightLanding.getText().toString()));
        engineData.setStage5N1Prc(Integer.valueOf(eLowPrc.getText().toString()));
        engineData.setStage5N1Release(Integer.valueOf(eRelease.getText().toString()));
        engineData.setStage5N1Cleaning(Integer.valueOf(eCleaning.getText().toString()));
        engineData.setStage5N1BrakePrc(Integer.valueOf(eLowPrc2.getText().toString()));
        engineData.setStage5N1Tmc(Integer.valueOf(eTmc.getText().toString()));
        engineData.setStage5N1VGenerator(Integer.valueOf(eVGenerator.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInformation")) {
            EditText eFlightTakeoff = (EditText) findViewById(R.id.LinearLabelInpFlightTakeoff);
            EditText eTakeoffLanding = (EditText) findViewById(R.id.LinearLabelInpTakeoffLangding);
            EditText eFlightLanding = (EditText) findViewById(R.id.LinearLabelInpFlightLanding);
            EditText eLowPrc = (EditText) findViewById(R.id.LinearLabelInpLowPrc);
            EditText eRelease = (EditText) findViewById(R.id.LinearLabelInpRelease);
            EditText eCleaning = (EditText) findViewById(R.id.LinearLabelInpCleaning);
            EditText eLowPrc2 = (EditText) findViewById(R.id.LinearLabelInpLowPrc2);
            EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc);
            EditText eVGenerator = (EditText) findViewById(R.id.LinearLabelInpVGenerator);

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