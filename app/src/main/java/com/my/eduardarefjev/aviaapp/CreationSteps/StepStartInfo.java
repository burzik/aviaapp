package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	09.10.2017		Eduard Arefjev 		Created "StepStartInfo" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 */

public class StepStartInfo extends AppCompatActivity {

    String id;
    public StepEngineData engineData;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_start_info);
        this.setTitle("Базовые данные 2");

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eLimb = (EditText) findViewById(R.id.LinearLabelInpLimbArg);
        CreationHelper.checkValue(eLimb, 18, 24);
        EditText eLaunchingTVSU = (EditText) findViewById(R.id.LinearLabelInpLaunchingTVSU);
        CreationHelper.checkValue(eLaunchingTVSU, 0, 31);
        EditText eEngineCasting = (EditText) findViewById(R.id.LinearLabelInpEngineCasting);
        CreationHelper.checkValue(eEngineCasting, 0, 550);
        EditText eVSUDisconnection = (EditText) findViewById(R.id.LinearLabelInpVSUDisconnection);
        CreationHelper.checkValue(eVSUDisconnection, 41.5, 44.5);
        EditText eTEngine = (EditText) findViewById(R.id.LinearLabelInpTEngine);
        CreationHelper.checkValue(eTEngine, 0, 50);

        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextSecondStep);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepStartInfo.this, StepSmallGas.class);
                intent.putExtra("recordId", id);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);

            }
        });
    }

    public void setRecord(){
        EditText eLimbArg = (EditText) findViewById(R.id.LinearLabelInpLimbArg);
        EditText eLaunchingTVSU = (EditText) findViewById(R.id.LinearLabelInpLaunchingTVSU);
        EditText eEngineCasting = (EditText) findViewById(R.id.LinearLabelInpEngineCasting);
        EditText eVSUDisconnection = (EditText) findViewById(R.id.LinearLabelInpVSUDisconnection);
        EditText eTEngine = (EditText) findViewById(R.id.LinearLabelInpTEngine);

        engineData.setLimb(Integer.valueOf(eLimbArg.getText().toString()));
        engineData.setApuTime(Integer.valueOf(eLaunchingTVSU.getText().toString()));
        engineData.setLaunchEngineTemp(Float.valueOf(eEngineCasting.getText().toString()));
        engineData.setLaunchAPUOffEngine(Integer.valueOf(eVSUDisconnection.getText().toString()));
        engineData.setLaunchEngineTime(Integer.valueOf(eTEngine.getText().toString()));
    }

    @Override
    public void onBackPressed() {
    }
}
