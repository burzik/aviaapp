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
 * 	23.10.2017		Eduard Arefjev 		Created "StepClosingKVDKPV" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 */

public class StepClosingKVDKPV extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    String parentView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_closing_kvd_kpv);
        this.setTitle(R.string.label_turnover_kvd_n);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eIII = (EditText) findViewById(R.id.LinearLabelInpIII);
        CreationHelper.checkValue(eIII, 86, 90);
        EditText eV = (EditText) findViewById(R.id.LinearLabelInpV);
        CreationHelper.checkValue(eV, 74, 78);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextBackStroke);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepClosingKVDKPV.this, StepPickUp.class);
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
        EditText eIII = (EditText) findViewById(R.id.LinearLabelInpIII);
        EditText eV = (EditText) findViewById(R.id.LinearLabelInpV);

        engineData.setStageN3ModeName(Integer.valueOf(eIII.getText().toString()));
        engineData.setStageN5ModeName(Integer.valueOf(eV.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInformation")) {
            EditText eIII = (EditText) findViewById(R.id.LinearLabelInpIII);
            EditText eV = (EditText) findViewById(R.id.LinearLabelInpV);

            eIII.setText(Integer.toString(engineData.getStageN3ModeName()));
            eV.setText(Integer.toString(engineData.getStageN5ModeName()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}