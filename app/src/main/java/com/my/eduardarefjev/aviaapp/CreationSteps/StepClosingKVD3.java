package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.my.eduardarefjev.aviaapp.R;

/**
 * Created by EduardArefjev on 23/10/2017.
 *
 */

public class StepClosingKVD3 extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_closing_kvd_3);

        this.setTitle(R.string.label_turnover_kvd_iii);

        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextClosingKVD3);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepClosingKVD3.this, Step085Nom.class);
                startActivity(intent);

            }
        });
    }
}