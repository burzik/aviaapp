package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

import static java.lang.Double.MIN_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.10.2017		Eduard Arefjev 		Created "StepPickUp" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 */

public class StepPickUp extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    String parentView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_pick_up);
        this.setTitle(R.string.label_pick_up);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eExitMgMax = (EditText) findViewById(R.id.LinearLabelInpExitMgMax);
        CreationHelper.checkValue(eExitMgMax, 9, 12);
        EditText eResetMaxMg = (EditText) findViewById(R.id.LinearLabelInpResetMaxMg);
        CreationHelper.checkValue(eResetMaxMg, -MIN_VALUE, 5);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextStepPickUp);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepPickUp.this, StepSmallGas2.class);
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
        EditText eExitMgMax = (EditText) findViewById(R.id.LinearLabelInpExitMgMax);
        EditText eResetMaxMg = (EditText) findViewById(R.id.LinearLabelInpResetMaxMg);

        engineData.setModeAccelerationIdleMaxIn(Float.valueOf(eExitMgMax.getText().toString()));
        engineData.setModeAccelerationIdleOut(Float.valueOf(eResetMaxMg.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInfo")) {
            EditText eExitMgMax = (EditText) findViewById(R.id.LinearLabelInpExitMgMax);
            EditText eResetMaxMg = (EditText) findViewById(R.id.LinearLabelInpResetMaxMg);

            eExitMgMax.setText(Float.toString(engineData.getModeAccelerationIdleMaxIn()));
            eResetMaxMg.setText(Float.toString(engineData.getModeAccelerationIdleOut()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}