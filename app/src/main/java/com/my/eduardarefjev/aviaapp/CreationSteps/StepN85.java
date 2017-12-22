package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

import static java.lang.Double.MAX_VALUE;

/**
 * Created by EduardArefjev on 29/10/2017.
 */

public class StepN85 extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_n_85);

        this.setTitle("n1=85%");

        EditText eFlightTakeoff = (EditText) findViewById(R.id.LinearLabelInpFlightTakeoff);
        CreationHelper.checkValue(eFlightTakeoff, 2, 4);
        EditText eTakeoffLangding = (EditText) findViewById(R.id.LinearLabelInpTakeoffLangding);
        CreationHelper.checkValue(eTakeoffLangding, 2, 3);
        EditText eFlightLanding = (EditText) findViewById(R.id.LinearLabelInpFlightLanding);
        CreationHelper.checkValue(eFlightLanding, 5, 10);
        EditText eLowPrc = (EditText) findViewById(R.id.LinearLabelInpLowPrc);
        CreationHelper.checkValue(eLowPrc, 70, MAX_VALUE); //>70
        EditText eRelease = (EditText) findViewById(R.id.LinearLabelInpRelease);
        CreationHelper.checkValue(eRelease, 1.5, 2.5);
        EditText eCleaning = (EditText) findViewById(R.id.LinearLabelInpCleaning);
        CreationHelper.checkValue(eCleaning, 1.5, 2.5);

        EditText eLowPrc2 = (EditText) findViewById(R.id.LinearLabelInpLowPrc2);
        CreationHelper.checkValue(eLowPrc2, 60, MAX_VALUE);
        EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc);
        CreationHelper.checkValue(eTmc, -5, MAX_VALUE);
        EditText eVGenerator = (EditText) findViewById(R.id.LinearLabelInpVGenerator);
        CreationHelper.checkValue(eVGenerator, 27, 29);



        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextN85);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepN85.this, StepClosingKVD3.class);
                startActivity(intent);

            }
        });
    }
}