package com.my.eduardarefjev.aviaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by EduardArefjev on 29/10/2017.
 */

public class Records extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_records);

        this.setTitle("Записи");

        //nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextFinish);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(StepFinish.this, MainActivity.class);
                //startActivity(intent);
            }
        });
    }
}