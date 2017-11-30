package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.my.eduardarefjev.aviaapp.MainActivity;
import com.my.eduardarefjev.aviaapp.R;

/**
 * Created by EduardArefjev on 23/10/2017.
 */

public class StepFinish extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_finish);

        this.setTitle("Финальные данные");

        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextFinish);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepFinish.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}