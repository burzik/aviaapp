package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;

/**
 * Created by EduardArefjev on 29/10/2017.
 */

public class Step085Nom extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_085_nom);

        this.setTitle(R.string.label_work_0nominal);

        EditText eN1 = (EditText) findViewById(R.id.LinearLabelInpN1);
        CreationHelper.checkValue(eN1, 99, 100);
        EditText eTrc = (EditText) findViewById(R.id.LinearLabelInpTrc);
        CreationHelper.checkValue(eTrc, -MIN_VALUE, 590);
        EditText ePm = (EditText) findViewById(R.id.LinearLabelInpPm);
        CreationHelper.checkValue(ePm, 3, 4.5);
        EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc);
        CreationHelper.checkValue(eTmc, -MIN_VALUE, 90);        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearLabelInpEngineSqrt);
        CreationHelper.checkValue(eEngineSqrt, -MIN_VALUE, 40);
        EditText ePt = (EditText) findViewById(R.id.LinearLabelInpPt);
        CreationHelper.checkValue(ePt, -MIN_VALUE, 65);


        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextStep085Nom);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Step085Nom.this, StepN100.class);
                startActivity(intent);

            }
        });
    }
}