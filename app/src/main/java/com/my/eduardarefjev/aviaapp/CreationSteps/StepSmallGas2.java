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
 * Created by EduardArefjev on 29/10/2017.
 */

public class StepSmallGas2 extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_small_gas_2);

        this.setTitle(R.string.label_small_gas);

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


        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextStepSmallGas2);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepSmallGas2.this, StepControlKND.class);
                startActivity(intent);
            }
        });
    }
}