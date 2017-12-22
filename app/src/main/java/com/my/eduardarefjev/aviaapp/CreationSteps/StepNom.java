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

public class StepNom extends AppCompatActivity {

    private Button bNextStep;
    private Spinner spinnerDoNotRunOn;
    private Spinner spinnerEngineParametersOn;
    private Spinner spinnerDoNotRunOff;
    private Spinner spinnerParametersOff;
    private Spinner spinnerTurnOn;
    private Spinner spinnerTurnOff;
    private Spinner spinnerAutomatic;
    private Spinner spinnerHeat_2;
    private Spinner spinnerCold_2;
    private Spinner spinnerAutomatic_2;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_nom);

        this.setTitle("Ном.");

        EditText eN1 = (EditText) findViewById(R.id.LinearLabelInpN1_2);
        CreationHelper.checkValue(eN1, 102, 104);
        EditText eTrc = (EditText) findViewById(R.id.LinearLabelInpTrc_2);
        CreationHelper.checkValue(eTrc, -MIN_VALUE, 625);
        EditText ePm = (EditText) findViewById(R.id.LinearLabelInpPm_2);
        CreationHelper.checkValue(ePm, 3, 4.5);
        EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc_2);
        CreationHelper.checkValue(eTmc, -MIN_VALUE, 90);
        EditText ePt = (EditText) findViewById(R.id.LinearLabelInpPt_2);
        CreationHelper.checkValue(ePt, -MIN_VALUE, 65);
        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearLabelInpEngineSqrt_2);
        CreationHelper.checkValue(eEngineSqrt, -MIN_VALUE, 40);
        EditText eVGenerator = (EditText) findViewById(R.id.LinearLabelInpLabelVGenerator);
        CreationHelper.checkValue(eVGenerator, 27, 29);

        //TODO spinners
        spinnerDoNotRunOn = (Spinner) findViewById(R.id.RelativeSpinnerInpDoNotRunOn);
        spinnerEngineParametersOn = (Spinner) findViewById(R.id.RelativeSpinnerInpEngineParametersOn);
        spinnerDoNotRunOff = (Spinner) findViewById(R.id.RelativeSpinnerInpDoNotRunOff);
        spinnerParametersOff = (Spinner) findViewById(R.id.RelativeSpinnerInpEngineParametersOff);
        spinnerTurnOn = (Spinner) findViewById(R.id.RelativeSpinnerInpTurnOn);
        spinnerTurnOff = (Spinner) findViewById(R.id.RelativeSpinnerInpTurnOff);
        spinnerAutomatic = (Spinner) findViewById(R.id.RelativeSpinnerInpAutomatic);
        spinnerHeat_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpHeat_2);
        spinnerCold_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpCold_2);
        spinnerAutomatic_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpAutomatic_2);

        //EA Create DropDown List
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(StepNom.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.good_bad));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoNotRunOn.setAdapter(myAdapter);
        spinnerEngineParametersOn.setAdapter(myAdapter);
        spinnerDoNotRunOff.setAdapter(myAdapter);
        spinnerParametersOff.setAdapter(myAdapter);
        spinnerTurnOn.setAdapter(myAdapter);
        spinnerTurnOff.setAdapter(myAdapter);
        spinnerAutomatic.setAdapter(myAdapter);
        spinnerHeat_2.setAdapter(myAdapter);
        spinnerCold_2.setAdapter(myAdapter);
        spinnerAutomatic_2.setAdapter(myAdapter);

        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextClosingKVD3Nom);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepNom.this, StepMax.class);
                startActivity(intent);
            }
        });
    }
}