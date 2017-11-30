package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.my.eduardarefjev.aviaapp.R;

/**
 * Created by EduardArefjev on 29/10/2017.
 */

public class StepN100 extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_n_100);

        this.setTitle(R.string.label_n1_100);

        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextStepN100);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepN100.this, StepNom.class);
                startActivity(intent);

            }
        });
    }
}