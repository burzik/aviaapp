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

import static java.lang.Double.MIN_VALUE;

/**
 * Created by EduardArefjev on 23/10/2017.
 */

public class StepMax extends AppCompatActivity{

    private Button bNextStep;
    private Spinner spinnerKrTank;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_max);

        this.setTitle("Макс.");

        EditText eN1 = (EditText) findViewById(R.id.LinearInpN1);
        CreationHelper.checkValue(eN1, 105, 107);
        //from graphic?
        //EditText eN2_4 = (EditText) findViewById(R.id.LinearInpN2_4);
        //CreationHelper.checkValue(eN2_4, 102, 104);
        EditText eTRC = (EditText) findViewById(R.id.LinearInpTRC);
        CreationHelper.checkValue(eTRC, -MIN_VALUE, 660);
        EditText ePm = (EditText) findViewById(R.id.LinearInpPm);
        CreationHelper.checkValue(ePm, 3, 4.5);
        EditText eTmC = (EditText) findViewById(R.id.LinearInpTmC);
        CreationHelper.checkValue(eTmC, -MIN_VALUE, 90);
        EditText ePt = (EditText) findViewById(R.id.LinearInpPt);
        CreationHelper.checkValue(ePt, -MIN_VALUE, 65);
        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearInpEngineSqrt);
        CreationHelper.checkValue(eEngineSqrt, -MIN_VALUE, 40);
        EditText eVGenerator = (EditText) findViewById(R.id.LinearInpVGenerator);
        CreationHelper.checkValue(eVGenerator, 27, 29);
        EditText ePCabin = (EditText) findViewById(R.id.LinearInpPCabin);
        CreationHelper.checkValue(ePCabin, -MIN_VALUE, 0.05);

        spinnerKrTank = (Spinner) findViewById(R.id.RelativeSpinnerInpKrTank);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepMax.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.good_bad));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKrTank.setAdapter(myAdapter);

        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextClosingKVD3Max);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepMax.this, StepClosingKVDKPV.class);
                startActivity(intent);
            }
        });
    }
}