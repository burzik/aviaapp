package com.my.eduardarefjev.aviaapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        final EditText editText = (EditText) findViewById(R.id.LinearInpAtmPressure);
        //editText.getBackground().setColorFilter(Color.parseColor("#00ff00"), PorterDuff.Mode.DARKEN);

        final Resources.Theme theme = this.getTheme();
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if( editText.getText().toString().length() != 0 )
                if (Integer.parseInt(editText.getText().toString()) > 10)
                {
                    editText.getBackground().setColorFilter(Color.parseColor("#ffff00"), PorterDuff.Mode.DARKEN);
                    editText.setError("почти критично");
                }
                else if (Integer.parseInt((editText.getText().toString())) < 1)
                {
                    editText.getBackground().setColorFilter(Color.parseColor("#ff0000"), PorterDuff.Mode.DARKEN);
                    editText.setError("критичное значение");
                }
                else editText.getBackground().setColorFilter(Color.parseColor("#9b9b9b"), PorterDuff.Mode.SRC_ATOP);
            }
        });
        //


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
