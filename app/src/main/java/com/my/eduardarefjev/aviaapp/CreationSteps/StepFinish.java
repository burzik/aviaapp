package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.MainActivity;
import com.my.eduardarefjev.aviaapp.R;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	23.10.2017		Eduard Arefjev 		Created "StepFinish" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 */

public class StepFinish extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    String parentView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_finish);
        this.setTitle("Финальные данные");

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextFinish);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepFinish.this, MainActivity.class);
                //intent.putExtra("recordId", id);
                //Bundle extra = new Bundle();
                //extra.putParcelable("objects", engineData);
                //intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        EditText eCommon = (EditText) findViewById(R.id.LinearLabelInpCommon);
        EditText eNominal = (EditText) findViewById(R.id.LinearLabelInpNominal);
        EditText eN1StraightRunV2 = (EditText) findViewById(R.id.LinearLabelInpN1StraightRunV2);
        EditText eEngineAI = (EditText) findViewById(R.id.LinearLabelInpEngineAI);
        EditText eVSUSapphire = (EditText) findViewById(R.id.LinearLabelInpVSUSapphire);
        EditText eEngineA2I = (EditText) findViewById(R.id.LinearLabelInpEngineA2I);

        engineData.setModeWorkSum(Float.valueOf(eCommon.getText().toString()));
        engineData.setModeWorkNom(Float.valueOf(eNominal.getText().toString()));
        engineData.setModeWorkMax(Float.valueOf(eN1StraightRunV2.getText().toString()));
        engineData.setModeWorkLaunchCount(Integer.valueOf(eEngineAI.getText().toString()));
        engineData.setModeWorkLaunchVSUCount(Integer.valueOf(eVSUSapphire.getText().toString()));
        engineData.setModeWorkN1Count(Integer.valueOf(eEngineA2I.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInfo")) {
            EditText eCommon = (EditText) findViewById(R.id.LinearLabelInpCommon);
            EditText eNominal = (EditText) findViewById(R.id.LinearLabelInpNominal);
            EditText eN1StraightRunV2 = (EditText) findViewById(R.id.LinearLabelInpN1StraightRunV2);
            EditText eEngineAI = (EditText) findViewById(R.id.LinearLabelInpEngineAI);
            EditText eVSUSapphire = (EditText) findViewById(R.id.LinearLabelInpVSUSapphire);
            EditText eEngineA2I = (EditText) findViewById(R.id.LinearLabelInpEngineA2I);

            eCommon.setText(Float.toString(engineData.getModeWorkSum()));
            eNominal.setText(Float.toString(engineData.getModeWorkNom()));
            eN1StraightRunV2.setText(Float.toString(engineData.getModeWorkMax()));
            eEngineAI.setText(Integer.toString(engineData.getModeWorkLaunchCount()));
            eVSUSapphire.setText(Integer.toString(engineData.getModeWorkLaunchVSUCount()));
            eEngineA2I.setText(Integer.toString(engineData.getModeWorkN1Count()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}