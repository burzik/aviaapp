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
 * 	29.10.2017		Eduard Arefjev 		Created "StepSmallGas2" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 */

public class StepSmallGas2 extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    String parentView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_small_gas_2);
        this.setTitle(R.string.label_small_gas);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eN1 = (EditText) findViewById(R.id.LinearInpN1);
        CreationHelper.checkValue(eN1, 54.5, 57.5);
        EditText eTRC = (EditText) findViewById(R.id.LinearInpTRC);
        CreationHelper.checkValue(eTRC, -MIN_VALUE, 600);
        EditText ePm = (EditText) findViewById(R.id.LinearInpPm);
        CreationHelper.checkValue(ePm, 2, 4.5);
        EditText eTmC = (EditText) findViewById(R.id.LinearInpTmC);
        CreationHelper.checkValue(eTmC, -5, 90);
        EditText ePt = (EditText) findViewById(R.id.LinearInpPt);
        CreationHelper.checkValue(ePt, -MIN_VALUE, 65);
        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearInpEngineSqrt);
        CreationHelper.checkValue(eEngineSqrt, -MIN_VALUE, 40);
        EditText ePPKSwitchMin = (EditText) findViewById(R.id.LinearInpPPKSwitchMin);
        CreationHelper.checkValue(ePPKSwitchMin, 0.5, 0.8);
        EditText ePPKSwitchMax = (EditText) findViewById(R.id.LinearInpPPKSwitchMax);
        CreationHelper.checkValue(ePPKSwitchMax, 0.08, 1.6);
        EditText eTurnOn = (EditText) findViewById(R.id.RelativeInpTurnOn);
        CreationHelper.checkValue(eTurnOn, -MIN_VALUE, 30);
        EditText eTurnOff = (EditText) findViewById(R.id.RelativeInpTurnOff);
        CreationHelper.checkValue(eTurnOff, -MIN_VALUE, 30);
        EditText eTurnOn_2 = (EditText) findViewById(R.id.RelativeInpTurnOn_2);
        CreationHelper.checkValue(eTurnOn_2, -MIN_VALUE, 45);
        EditText eTurnOff_2 = (EditText) findViewById(R.id.RelativeInpTurnOff_2);
        CreationHelper.checkValue(eTurnOff_2, -MIN_VALUE, 45);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextStepSmallGas2);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepSmallGas2.this, StepControlKND.class);
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
        EditText eN1 = (EditText) findViewById(R.id.LinearInpN1);
        EditText eTRC = (EditText) findViewById(R.id.LinearInpTRC);
        EditText ePm = (EditText) findViewById(R.id.LinearInpPm);
        EditText eTmC = (EditText) findViewById(R.id.LinearInpTmC);
        EditText ePt = (EditText) findViewById(R.id.LinearInpPt);
        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearInpEngineSqrt);
        EditText ePPKSwitchMin = (EditText) findViewById(R.id.LinearInpPPKSwitchMin);
        EditText ePPKSwitchMax = (EditText) findViewById(R.id.LinearInpPPKSwitchMax);
        EditText eTurnOn = (EditText) findViewById(R.id.RelativeInpTurnOn);
        EditText eTurnOff = (EditText) findViewById(R.id.RelativeInpTurnOff);
        EditText eTurnOn_2 = (EditText) findViewById(R.id.RelativeInpTurnOn_2);
        EditText eTurnOff_2 = (EditText) findViewById(R.id.RelativeInpTurnOff_2);

        engineData.setModeSmallGas2HPCSpeed(Float.valueOf(eN1.getText().toString()));
        engineData.setModeSmallGas2Temp(Integer.valueOf(eTRC.getText().toString()));
        engineData.setModeSmallGas2OilPressure(Float.valueOf(ePm.getText().toString()));
        engineData.setModeSmallGas2OilTemp(Integer.valueOf(eTmC.getText().toString()));
        engineData.setModeSmallGas2FuelPressure(Integer.valueOf(ePt.getText().toString()));
        engineData.setModeSmallGas2Vibration(Integer.valueOf(eEngineSqrt.getText().toString()));
        engineData.setModeSmallGas2SwitchMin(Float.valueOf(ePPKSwitchMin.getText().toString()));
        engineData.setModeSmallGas2SwitchMax(Float.valueOf(ePPKSwitchMax.getText().toString()));
        engineData.setModeSmallGas2CondOn(Float.valueOf(eTurnOn.getText().toString()));
        engineData.setModeSmallGas2CondOff(Float.valueOf(eTurnOff.getText().toString()));
        engineData.setModeSmallGas2AntifreezeOn(Float.valueOf(eTurnOn_2.getText().toString()));
        engineData.setModeSmallGas2AntifreezeOff(Float.valueOf(eTurnOff_2.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInformation")) {
            EditText eN1 = (EditText) findViewById(R.id.LinearInpN1);
            EditText eTRC = (EditText) findViewById(R.id.LinearInpTRC);
            EditText ePm = (EditText) findViewById(R.id.LinearInpPm);
            EditText eTmC = (EditText) findViewById(R.id.LinearInpTmC);
            EditText ePt = (EditText) findViewById(R.id.LinearInpPt);
            EditText eEngineSqrt = (EditText) findViewById(R.id.LinearInpEngineSqrt);
            EditText ePPKSwitchMin = (EditText) findViewById(R.id.LinearInpPPKSwitchMin);
            EditText ePPKSwitchMax = (EditText) findViewById(R.id.LinearInpPPKSwitchMax);
            EditText eTurnOn = (EditText) findViewById(R.id.RelativeInpTurnOn);
            EditText eTurnOff = (EditText) findViewById(R.id.RelativeInpTurnOff);
            EditText eTurnOn_2 = (EditText) findViewById(R.id.RelativeInpTurnOn_2);
            EditText eTurnOff_2 = (EditText) findViewById(R.id.RelativeInpTurnOff_2);

            eN1.setText(Float.toString(engineData.getModeSmallGas2HPCSpeed()));
            eTRC.setText(Integer.toString(engineData.getModeSmallGas2Temp()));
            ePm.setText(Float.toString(engineData.getModeSmallGas2OilPressure()));
            eTmC.setText(Integer.toString(engineData.getModeSmallGas2OilTemp()));
            ePt.setText(Integer.toString(engineData.getModeSmallGas2FuelPressure()));
            eEngineSqrt.setText(Integer.toString(engineData.getModeSmallGas2Vibration()));
            ePPKSwitchMin.setText(Float.toString(engineData.getModeSmallGas2SwitchMin()));
            ePPKSwitchMax.setText(Float.toString(engineData.getModeSmallGas2SwitchMax()));
            eTurnOn.setText(Float.toString(engineData.getModeSmallGas2CondOn()));
            eTurnOff.setText(Float.toString(engineData.getModeSmallGas2CondOff()));
            eTurnOn_2.setText(Float.toString(engineData.getModeSmallGas2AntifreezeOn()));
            eTurnOff_2.setText(Float.toString(engineData.getModeSmallGas2AntifreezeOff()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}