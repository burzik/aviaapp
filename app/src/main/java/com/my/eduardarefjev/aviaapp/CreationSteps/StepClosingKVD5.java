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
 * 	23.10.2017		Eduard Arefjev 		Created "StepClosingKVD5" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 */

public class StepClosingKVD5 extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    String parentView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_closing_kvd_5);
        this.setTitle(R.string.label_turnover_kvd_v);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eN1StraightRun = findViewById(R.id.LinearLabelInpN1StraightRun);
        CreationHelper.checkValue(eN1StraightRun, 74, 78);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextClosingKVD);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepClosingKVD5.this, StepN85.class);
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
        EditText eN1StraightRun = findViewById(R.id.LinearLabelInpN1StraightRun);

        if (!eN1StraightRun.getText().toString().isEmpty())
            engineData.setStage5ModeName(Float.valueOf(eN1StraightRun.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInfo")) {
            EditText eN1StraightRun = findViewById(R.id.LinearLabelInpN1StraightRun);

            eN1StraightRun.setText(Float.toString(engineData.getStage5ModeName()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}