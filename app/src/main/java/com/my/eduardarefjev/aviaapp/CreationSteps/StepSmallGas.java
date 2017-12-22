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
 * Created by EduardArefjev on 09/10/2017.
 */

public class StepSmallGas extends AppCompatActivity {

    private Button bNextStep;
    private Spinner spinnerClosingLantern;
    private Spinner spinnerAirCond;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_small_gas);

        this.setTitle(R.string.label_small_gas);

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
        bNextStep = (Button) findViewById(R.id.LinearButtonNextSmallGas);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepSmallGas.this, StepClosingKVD5.class);
                startActivity(intent);

            }
        });
    }
}