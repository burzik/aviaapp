package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

import static java.lang.Double.MIN_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.10.2017		Eduard Arefjev 		Created "Step085Nom" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 */

public class Step085Nom extends AppCompatActivity {

    private StepEngineData engineData;
    String id;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_085_nom);
        this.setTitle(R.string.label_work_0nominal);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eN1 = (EditText) findViewById(R.id.LinearLabelInpN1);
        CreationHelper.checkValue(eN1, 99, 100);
        EditText eTrc = (EditText) findViewById(R.id.LinearLabelInpTrc);
        CreationHelper.checkValue(eTrc, -MIN_VALUE, 590);
        EditText ePm = (EditText) findViewById(R.id.LinearLabelInpPm);
        CreationHelper.checkValue(ePm, 3, 4.5);
        EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc);
        CreationHelper.checkValue(eTmc, -MIN_VALUE, 90);
        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearLabelInpEngineSqrt);
        CreationHelper.checkValue(eEngineSqrt, -MIN_VALUE, 40);
        EditText ePt = (EditText) findViewById(R.id.LinearLabelInpPt);
        CreationHelper.checkValue(ePt, -MIN_VALUE, 65);

        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextStep085Nom);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(Step085Nom.this, StepN100.class);
                intent.putExtra("recordId", id);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        EditText eN1 = (EditText) findViewById(R.id.LinearLabelInpN1);
        EditText eTrc = (EditText) findViewById(R.id.LinearLabelInpTrc);
        EditText ePm = (EditText) findViewById(R.id.LinearLabelInpPm);
        EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc);
        EditText ePt = (EditText) findViewById(R.id.LinearLabelInpPt);
        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearLabelInpEngineSqrt);

        engineData.setMode85NomHPCSpeed(Float.valueOf(eN1.getText().toString()));
        engineData.setMode85NomTemp(Integer.valueOf(eTrc.getText().toString()));
        engineData.setMode85NomOilPressure(Float.valueOf(ePm.getText().toString()));
        engineData.setMode85NomOilTemp(Integer.valueOf(eTmc.getText().toString()));
        engineData.setMode85NomFuelPressure(Integer.valueOf(eEngineSqrt.getText().toString()));
        engineData.setMode85NomVibration(Integer.valueOf(ePt.getText().toString()));
    }
}