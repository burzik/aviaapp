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
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev     Fixed crash for null numbers
 */

public class StepSmallGas extends AppCompatActivity {

    private Spinner spinnerClosingLantern;
    private Spinner spinnerAirCond;
    private StepEngineData engineData;
    String id;
    String parentView;
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_small_gas);
        this.setTitle(R.string.label_small_gas);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eInpN1 = findViewById(R.id.LinearInpN1);
        CreationHelper.checkValue(eInpN1, 54.5, 57.5);

        EditText eTRC = findViewById(R.id.LinearInpTRC);
        CreationHelper.checkValue(eTRC, 0, 600);
        EditText ePm = findViewById(R.id.LinearInpPm);
        CreationHelper.checkValue(ePm, 2, 4.5);
        EditText eTmC = findViewById(R.id.LinearInpTmC);
        CreationHelper.checkValue(eTmC, -5, 90);
        EditText ePt = findViewById(R.id.LinearInpPt);
        CreationHelper.checkValue(ePt, 0, 65);
        EditText eEngineSqrt = findViewById(R.id.LinearInpEngineSqrt);
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
        EditText eVGenerator = findViewById(R.id.LinearInpVGenerator);
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
        spinnerClosingLantern = findViewById(R.id.LinearLabelInpSpinnerClosingLantern);
        spinnerAirCond = findViewById(R.id.LinearLabelInpSpinnerAirCond);
        //EA Create DropDown List
        myAdapter = new ArrayAdapter<>(StepSmallGas.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.good_bad));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClosingLantern.setAdapter(myAdapter);
        spinnerAirCond.setAdapter(myAdapter);

        EditText ePCabin = findViewById(R.id.LinearInpPCabin);
        CreationHelper.checkValue(ePCabin, 0.021, 0.041);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextSmallGas);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepSmallGas.this, StepClosingKVD5.class);
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
        EditText eInpN1 = findViewById(R.id.LinearInpN1);
        EditText eTRC = findViewById(R.id.LinearInpTRC);
        EditText ePm = findViewById(R.id.LinearInpPm);
        EditText eTmC = findViewById(R.id.LinearInpTmC);
        EditText ePt = findViewById(R.id.LinearInpPt);
        EditText eEngineSqrt = findViewById(R.id.LinearInpEngineSqrt);
        EditText eMainSystem = findViewById(R.id.LinearInpMainSystem);
        EditText eEmergency = findViewById(R.id.LinearInpEmergency);
        EditText eBasic = findViewById(R.id.LinearInpBasic);
        EditText eParking = findViewById(R.id.LinearInpParking);
        EditText eVGenerator = findViewById(R.id.LinearInpVGenerator);
        EditText eKrTank = findViewById(R.id.LinearInpKrTank);
        EditText eGAccum = findViewById(R.id.LinearInpGAccum);
        EditText eGTank = findViewById(R.id.LinearInpGTank);
        spinnerClosingLantern = findViewById(R.id.LinearLabelInpSpinnerClosingLantern);
        spinnerAirCond = findViewById(R.id.LinearLabelInpSpinnerAirCond);
        //EA Create DropDown List
        //ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepSmallGas.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.good_bad));
        //myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnerClosingLantern.setAdapter(myAdapter);
        //spinnerAirCond.setAdapter(myAdapter);
        EditText ePCabin = findViewById(R.id.LinearInpPCabin);

        if (!eInpN1.getText().toString().isEmpty())
            engineData.setModeSmallGasHPCSpeed(Float.valueOf(eInpN1.getText().toString()));
        if (!eTRC.getText().toString().isEmpty())
            engineData.setModeSmallGasTemp(Integer.valueOf(eTRC.getText().toString()));
        if (!ePm.getText().toString().isEmpty())
            engineData.setModeSmallGasOilPressure(Float.valueOf(ePm.getText().toString()));
        if (!eTmC.getText().toString().isEmpty())
            engineData.setModeSmallGasOilTemp(Integer.valueOf(eTmC.getText().toString()));
        if (!ePt.getText().toString().isEmpty())
            engineData.setModeSmallGasFuelPressure(Integer.valueOf(ePt.getText().toString()));
        if (!eEngineSqrt.getText().toString().isEmpty())
            engineData.setModeSmallGasVibration(Integer.valueOf(eEngineSqrt.getText().toString()));
        if (!eMainSystem.getText().toString().isEmpty())
            engineData.setModeSmallGasHSPressure(Integer.valueOf(eMainSystem.getText().toString()));
        if (!eEmergency.getText().toString().isEmpty())
            engineData.setModeSmallGasHSPressureEmergency(Integer.valueOf(eEmergency.getText().toString()));
        if (!eBasic.getText().toString().isEmpty())
            engineData.setModeSmallGasBrakePressure(Integer.valueOf(eBasic.getText().toString()));
        if (!eParking.getText().toString().isEmpty())
            engineData.setModeSmallGasBrakePressureStop(Integer.valueOf(eParking.getText().toString()));
        if (!eVGenerator.getText().toString().isEmpty())
            engineData.setModeSmallGasGenerator(Integer.valueOf(eVGenerator.getText().toString()));
        if (!eKrTank.getText().toString().isEmpty())
            engineData.setModeSmallGasPressureWing(Float.valueOf(eKrTank.getText().toString()));
        if (!eGAccum.getText().toString().isEmpty())
            engineData.setModeSmallGasPressureHA(Float.valueOf(eGAccum.getText().toString()));
        if (!eGTank.getText().toString().isEmpty())
            engineData.setModeSmallGasPressureHT(Float.valueOf(eGTank.getText().toString()));

        String closingLantern = spinnerClosingLantern.getSelectedItem().toString();
        engineData.setModeSmallGasLightsClosure(closingLantern.equals("Норма"));
        String airCond = spinnerAirCond.getSelectedItem().toString();
        engineData.setModeSmallGasConditioning(airCond.equals("Норма"));
        if (!ePCabin.getText().toString().isEmpty())
            engineData.setModeSmallGasCabin(Float.valueOf(ePCabin.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInfo")) {
            EditText eInpN1 = findViewById(R.id.LinearInpN1);
            EditText eTRC = findViewById(R.id.LinearInpTRC);
            EditText ePm = findViewById(R.id.LinearInpPm);
            EditText eTmC = findViewById(R.id.LinearInpTmC);
            EditText ePt = findViewById(R.id.LinearInpPt);
            EditText eEngineSqrt = findViewById(R.id.LinearInpEngineSqrt);
            EditText eMainSystem = findViewById(R.id.LinearInpMainSystem);
            EditText eEmergency = findViewById(R.id.LinearInpEmergency);
            EditText eBasic = findViewById(R.id.LinearInpBasic);
            EditText eParking = findViewById(R.id.LinearInpParking);
            EditText eVGenerator = findViewById(R.id.LinearInpVGenerator);
            EditText eKrTank = findViewById(R.id.LinearInpKrTank);
            EditText eGAccum = findViewById(R.id.LinearInpGAccum);
            EditText eGTank = findViewById(R.id.LinearInpGTank);
            spinnerClosingLantern = findViewById(R.id.LinearLabelInpSpinnerClosingLantern);
            spinnerAirCond = findViewById(R.id.LinearLabelInpSpinnerAirCond);
            EditText ePCabin = findViewById(R.id.LinearInpPCabin);
            //float num = engineData.getModeSmallGasHPCSpeed();
            //eInpN1.setText(String.format(Locale.ENGLISH,"%f",num));

            eInpN1.setText(Float.toString(engineData.getModeSmallGasHPCSpeed()));
            eTRC.setText(Integer.toString(engineData.getModeSmallGasTemp()));
            ePm.setText(Float.toString(engineData.getModeSmallGasOilPressure()));
            eTmC.setText(Integer.toString(engineData.getModeSmallGasOilTemp()));
            ePt.setText(Integer.toString(engineData.getModeSmallGasFuelPressure()));
            eEngineSqrt.setText(Integer.toString(engineData.getModeSmallGasVibration()));
            eMainSystem.setText(Integer.toString(engineData.getModeSmallGasHSPressure()));
            eEmergency.setText(Integer.toString(engineData.getModeSmallGasHSPressureEmergency()));
            eBasic.setText(Integer.toString(engineData.getModeSmallGasBrakePressure()));
            eParking.setText(Integer.toString(engineData.getModeSmallGasBrakePressureStop()));
            eVGenerator.setText(Integer.toString(engineData.getModeSmallGasGenerator()));
            eKrTank.setText(Float.toString(engineData.getModeSmallGasPressureWing()));
            eGAccum.setText(Float.toString(engineData.getModeSmallGasPressureHA()));
            eGTank.setText(Float.toString(engineData.getModeSmallGasPressureHT()));
            int spinnerPosition = myAdapter.getPosition(engineData.isModeSmallGasLightsClosure() ? "Норма" : "Не норма");
            spinnerClosingLantern.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeSmallGasConditioning() ? "Норма" : "Не норма");
            spinnerAirCond.setSelection(spinnerPosition);
            ePCabin.setText(Float.toString(engineData.getModeSmallGasCabin()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}