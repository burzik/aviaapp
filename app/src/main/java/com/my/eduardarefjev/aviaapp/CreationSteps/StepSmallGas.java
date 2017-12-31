package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.my.eduardarefjev.aviaapp.R;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	09.10.2017		Eduard Arefjev 		Created "StepSmallGas" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 */

public class StepSmallGas extends AppCompatActivity {

    private Spinner spinnerClosingLantern;
    private Spinner spinnerAirCond;
    private StepEngineData engineData;
    String id;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_small_gas);
        this.setTitle(R.string.label_small_gas);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eInpN1 = (EditText) findViewById(R.id.LinearInpN1);
        CreationHelper.checkValue(eInpN1, 54.5, 57.5);

        EditText eTRC = (EditText) findViewById(R.id.LinearInpTRC);
        CreationHelper.checkValue(eTRC, 0, 600);
        EditText ePm = (EditText) findViewById(R.id.LinearInpPm);
        CreationHelper.checkValue(ePm, 2, 4.5);
        EditText eTmC = (EditText) findViewById(R.id.LinearInpTmC);
        CreationHelper.checkValue(eTmC, -5, 90);
        EditText ePt = (EditText) findViewById(R.id.LinearInpPt);
        CreationHelper.checkValue(ePt, 0, 65);
        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearInpEngineSqrt);
        CreationHelper.checkValue(eEngineSqrt, 0, 40);
        /*
        EditText eMainSystem = (EditText) findViewById(R.id.LinearInpMainSystem);
        CreationHelper.checkValue(eMainSystem, 0, 0);
        EditText eEmergency = (EditText) findViewById(R.id.LinearInpEmergency);
        CreationHelper.checkValue(eEmergency, 0, 0);
        EditText eBasic = (EditText) findViewById(R.id.LinearInpBasic);
        CreationHelper.checkValue(eBasic, 0, 0);
        EditText eParking = (EditText) findViewById(R.id.LinearInpParking);
        CreationHelper.checkValue(eParking, 0, 0);
        */
        EditText eVGenerator = (EditText) findViewById(R.id.LinearInpVGenerator);
        CreationHelper.checkValue(eVGenerator, 27, 29);
        /*
        EditText eKrTank = (EditText) findViewById(R.id.LinearInpKrTank);
        CreationHelper.checkValue(eKrTank, 0, 0);
        EditText eGAccum = (EditText) findViewById(R.id.LinearInpGAccum);
        CreationHelper.checkValue(eGAccum, 0, 0);
        EditText eGTank = (EditText) findViewById(R.id.LinearInpGTank);
        CreationHelper.checkValue(eGTank, 0, 0);
        EditText eClosingLantern = (EditText) findViewById(R.id.LinearInpClosingLantern);
        CreationHelper.checkValue(eClosingLantern, 0, 0);
        EditText eAirCond = (EditText) findViewById(R.id.LinearInpAirCond);
        CreationHelper.checkValue(eAirCond, 0, 0);
        */
        spinnerClosingLantern = (Spinner) findViewById(R.id.LinearLabelInpSpinnerClosingLantern);
        spinnerAirCond = (Spinner) findViewById(R.id.LinearLabelInpSpinnerAirCond);
        //EA Create DropDown List
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepSmallGas.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.good_bad));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClosingLantern.setAdapter(myAdapter);
        spinnerAirCond.setAdapter(myAdapter);

        EditText ePCabin = (EditText) findViewById(R.id.LinearInpPCabin);
        CreationHelper.checkValue(ePCabin, 0.021, 0.041);

        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextSmallGas);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepSmallGas.this, StepClosingKVD5.class);
                intent.putExtra("recordId", id);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);

            }
        });
    }

    public void setRecord(){
        EditText eInpN1 = (EditText) findViewById(R.id.LinearInpN1);
        EditText eTRC = (EditText) findViewById(R.id.LinearInpTRC);
        EditText ePm = (EditText) findViewById(R.id.LinearInpPm);
        EditText eTmC = (EditText) findViewById(R.id.LinearInpTmC);
        EditText ePt = (EditText) findViewById(R.id.LinearInpPt);
        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearInpEngineSqrt);
        EditText eMainSystem = (EditText) findViewById(R.id.LinearInpMainSystem);
        EditText eEmergency = (EditText) findViewById(R.id.LinearInpEmergency);
        EditText eBasic = (EditText) findViewById(R.id.LinearInpBasic);
        EditText eParking = (EditText) findViewById(R.id.LinearInpParking);
        EditText eVGenerator = (EditText) findViewById(R.id.LinearInpVGenerator);
        EditText eKrTank = (EditText) findViewById(R.id.LinearInpKrTank);
        EditText eGAccum = (EditText) findViewById(R.id.LinearInpGAccum);
        EditText eGTank = (EditText) findViewById(R.id.LinearInpGTank);
        spinnerClosingLantern = (Spinner) findViewById(R.id.LinearLabelInpSpinnerClosingLantern);
        spinnerAirCond = (Spinner) findViewById(R.id.LinearLabelInpSpinnerAirCond);
        //EA Create DropDown List
        //ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepSmallGas.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.good_bad));
        //myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnerClosingLantern.setAdapter(myAdapter);
        //spinnerAirCond.setAdapter(myAdapter);
        EditText ePCabin = (EditText) findViewById(R.id.LinearInpPCabin);

        engineData.setModeSmallGasHPCSpeed(Float.valueOf(eInpN1.getText().toString()));
        engineData.setModeSmallGasTemp(Integer.valueOf(eTRC.getText().toString()));
        engineData.setModeSmallGasOilPressure(Float.valueOf(ePm.getText().toString()));
        engineData.setModeSmallGasOilTemp(Integer.valueOf(eTmC.getText().toString()));
        engineData.setModeSmallGasFuelPressure(Integer.valueOf(ePt.getText().toString()));
        engineData.setModeSmallGasVibration(Integer.valueOf(eEngineSqrt.getText().toString()));
        engineData.setModeSmallGasHSPressure(Integer.valueOf(eMainSystem.getText().toString()));
        engineData.setModeSmallGasHSPressureEmergency(Integer.valueOf(eEmergency.getText().toString()));
        engineData.setModeSmallGasBrakePressure(Integer.valueOf(eBasic.getText().toString()));
        engineData.setModeSmallGasBrakePressureStop(Integer.valueOf(eParking.getText().toString()));
        engineData.setModeSmallGasGenerator(Integer.valueOf(eVGenerator.getText().toString()));
        engineData.setModeSmallGasPressureWing(Float.valueOf(eKrTank.getText().toString()));
        engineData.setModeSmallGasPressureHA(Float.valueOf(eGAccum.getText().toString()));
        engineData.setModeSmallGasPressureHT(Float.valueOf(eGTank.getText().toString()));

        String closingLantern = spinnerClosingLantern.getSelectedItem().toString();
        engineData.setModeSmallGasLightsClosure(closingLantern.equals("Норма"));
        String airCond = spinnerAirCond.getSelectedItem().toString();
        engineData.setModeSmallGasConditioning(airCond.equals("Норма"));
        engineData.setModeSmallGasCabin(Float.valueOf(ePCabin.getText().toString()));
    }
}