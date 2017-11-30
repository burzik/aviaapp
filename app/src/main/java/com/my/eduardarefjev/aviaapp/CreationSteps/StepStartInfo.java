package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.my.eduardarefjev.aviaapp.FirebaseManager;
import com.my.eduardarefjev.aviaapp.R;

/**
 * Created by EduardArefjev on 09/10/2017.
 */

public class StepStartInfo extends AppCompatActivity {

    private Button bNextStep;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_start_info);

        this.setTitle("Базовые данные 2");
        String name = FirebaseManager.currentUser();
        nextSecondStep();
    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNextSecondStep);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepStartInfo.this, StepSmallGas.class);
                startActivity(intent);

            }
        });
    }
}
