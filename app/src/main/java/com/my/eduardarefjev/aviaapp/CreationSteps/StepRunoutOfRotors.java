package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.MainActivity;
import com.my.eduardarefjev.aviaapp.R;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;

/**
 * Created by EduardArefjev on 22/12/2017.
 */

public class StepRunoutOfRotors extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_runout_of_rotors);

        this.setTitle("Выбег роторов");

        EditText eRotorVD = (EditText) findViewById(R.id.LinearLabelInpRotorVD);
        CreationHelper.checkValue(eRotorVD, 20, MAX_VALUE);
        EditText eRotorND = (EditText) findViewById(R.id.LinearLabelInpRotorND);
        CreationHelper.checkValue(eRotorND, 25, MAX_VALUE);

        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextRunOutOfRotors);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepRunoutOfRotors.this, StepTanningBoardDisplayGenerator.class);
                startActivity(intent);
            }
        });
    }
}