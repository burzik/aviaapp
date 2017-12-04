package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.my.eduardarefjev.aviaapp.R;

import java.util.Date;

/**
 * Created by EduardArefjev on 01/10/2017.
 */

public class StepEngineInfo extends AppCompatActivity {

    private Button bNextStep;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_engine_info);

        this.setTitle("Базовые данные");

        nextSecondStep();

        TextView date = (TextView) findViewById(R.id.LinearDate);
        Date currentTime = new Date();
        date.append(" " + DateFormat.format("dd.MM.yyyy", currentTime).toString());

        //editText.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);

        //final Resources.Theme theme = this.getTheme();

    }

    public void nextSecondStep() {
        bNextStep = (Button) findViewById(R.id.LinearButtonNext);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StepEngineInfo.this, StepStartInfo.class);
                startActivity(intent);

            }
        });
    }
}
