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

public class StepPickUp extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_pick_up);

        this.setTitle(R.string.label_pick_up);

        EditText eIII = (EditText) findViewById(R.id.LinearLabelInpExitMgMax);
        CreationHelper.checkValue(eIII, 9, 12);
        EditText eV = (EditText) findViewById(R.id.LinearLabelInpResetMaxMg);
        CreationHelper.checkValue(eV, -MIN_VALUE, 5);

        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextStepPickUp);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepPickUp.this, StepSmallGas2.class);
                startActivity(intent);
            }
        });
    }
}