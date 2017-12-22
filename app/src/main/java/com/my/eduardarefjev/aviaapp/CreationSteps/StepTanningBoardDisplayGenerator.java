package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.my.eduardarefjev.aviaapp.R;

/**
 * Created by EduardArefjev on 22/12/2017.
 */

public class StepTanningBoardDisplayGenerator extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_tanning_board_display_generator);

        this.setTitle(R.string.label_board_generator);

        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextTanningBoardDisplayGenerator);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepTanningBoardDisplayGenerator.this, StepFinish.class);
                startActivity(intent);
            }
        });
    }
}