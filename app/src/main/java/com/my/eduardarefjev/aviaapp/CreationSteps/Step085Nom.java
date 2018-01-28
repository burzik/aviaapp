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
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 */

public class Step085Nom extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    String parentView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_085_nom);
        this.setTitle(R.string.label_work_0nominal);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eN1 = findViewById(R.id.LinearLabelInpN1);
        CreationHelper.checkValue(eN1, 99, 100);
        EditText eTrc = findViewById(R.id.LinearLabelInpTrc);
        CreationHelper.checkValue(eTrc, -MIN_VALUE, 590);
        EditText ePm = findViewById(R.id.LinearLabelInpPm);
        CreationHelper.checkValue(ePm, 3, 4.5);
        EditText eTmc = findViewById(R.id.LinearLabelInpTmc);
        CreationHelper.checkValue(eTmc, -MIN_VALUE, 90);
        EditText eEngineSqrt = findViewById(R.id.LinearLabelInpEngineSqrt);
        CreationHelper.checkValue(eEngineSqrt, -MIN_VALUE, 40);
        EditText ePt = findViewById(R.id.LinearLabelInpPt);
        CreationHelper.checkValue(ePt, -MIN_VALUE, 65);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextStep085Nom);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(Step085Nom.this, StepN100.class);
                intent.putExtra("recordId", id);
                intent.putExtra("parentViewName", parentView);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        EditText eN1 = findViewById(R.id.LinearLabelInpN1);
        EditText eTrc = findViewById(R.id.LinearLabelInpTrc);
        EditText ePm = findViewById(R.id.LinearLabelInpPm);
        EditText eTmc = findViewById(R.id.LinearLabelInpTmc);
        EditText ePt = findViewById(R.id.LinearLabelInpPt);
        EditText eEngineSqrt = findViewById(R.id.LinearLabelInpEngineSqrt);

        if (!eN1.getText().toString().isEmpty())
            engineData.setMode85NomHPCSpeed(Float.valueOf(eN1.getText().toString()));
        if (!eTrc.getText().toString().isEmpty())
            engineData.setMode85NomTemp(Integer.valueOf(eTrc.getText().toString()));
        if (!ePm.getText().toString().isEmpty())
            engineData.setMode85NomOilPressure(Float.valueOf(ePm.getText().toString()));
        if (!eTmc.getText().toString().isEmpty())
            engineData.setMode85NomOilTemp(Integer.valueOf(eTmc.getText().toString()));
        if (!eEngineSqrt.getText().toString().isEmpty())
            engineData.setMode85NomFuelPressure(Integer.valueOf(eEngineSqrt.getText().toString()));
        if (!ePt.getText().toString().isEmpty())
            engineData.setMode85NomVibration(Integer.valueOf(ePt.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInfo")) {
            EditText eN1 = findViewById(R.id.LinearLabelInpN1);
            EditText eTrc = findViewById(R.id.LinearLabelInpTrc);
            EditText ePm = findViewById(R.id.LinearLabelInpPm);
            EditText eTmc = findViewById(R.id.LinearLabelInpTmc);
            EditText ePt = findViewById(R.id.LinearLabelInpPt);
            EditText eEngineSqrt = findViewById(R.id.LinearLabelInpEngineSqrt);

            eN1.setText(Float.toString(engineData.getMode85NomHPCSpeed()));
            eTrc.setText(Integer.toString(engineData.getMode85NomTemp()));
            ePm.setText(Float.toString(engineData.getMode85NomOilPressure()));
            eTmc.setText(Integer.toString(engineData.getMode85NomOilTemp()));
            ePt.setText(Integer.toString(engineData.getMode85NomFuelPressure()));
            eEngineSqrt.setText(Integer.toString(engineData.getMode85NomVibration()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}