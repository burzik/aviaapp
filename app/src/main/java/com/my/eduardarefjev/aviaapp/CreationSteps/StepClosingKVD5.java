package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

/**
 * Created by EduardArefjev on 23/10/2017.
 */

public class StepClosingKVD5 extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_closing_kvd_5);

        this.setTitle(R.string.label_turnover_kvd_v);

        EditText eN1StraightRun = (EditText) findViewById(R.id.LinearLabelInpN1StraightRun);
        CreationHelper.checkValue(eN1StraightRun, 74, 78);

        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextClosingKVD);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepClosingKVD5.this, StepN85.class);
                startActivity(intent);

            }
        });
    }
}