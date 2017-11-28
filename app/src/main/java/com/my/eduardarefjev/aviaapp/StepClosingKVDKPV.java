package com.my.eduardarefjev.aviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by EduardArefjev on 23/10/2017.
 */

public class StepClosingKVDKPV extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_closing_kvd_kpv);

        this.setTitle(R.string.label_turnover_kvd_n);

        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextBackStroke);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepClosingKVDKPV.this, StepPickUp.class);
                startActivity(intent);
            }
        });
    }
}