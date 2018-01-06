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
 * 	23.10.2017		Eduard Arefjev 		Created "StepControlKND" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 */

public class StepControlKND extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    String parentView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_control_knd);
        this.setTitle(R.string.label_control_knd);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextControlKND);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepControlKND.this, StepRunoutOfRotors.class);
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
        EditText eNKVDPHY97 = (EditText) findViewById(R.id.LinearLabelInpNKVDPHY97);
        EditText eNKVDPHY99 = (EditText) findViewById(R.id.LinearLabelInpNKVDPHY99);
        EditText eNKVDPHY101 = (EditText) findViewById(R.id.LinearLabelInpNKVDPHY101);
        EditText eNKNDPHY97 = (EditText) findViewById(R.id.LinearLabelInpNKNDPHY97);
        EditText eNKNDPHY99 = (EditText) findViewById(R.id.LinearLabelInpNKNDPHY99);
        EditText eNKNDPHY101 = (EditText) findViewById(R.id.LinearLabelInpNKNDPHY101);
        EditText eNKNDPLANE97 = (EditText) findViewById(R.id.LinearLabelInpNKNDPLANE97);
        EditText eNKNDPLANE99 = (EditText) findViewById(R.id.LinearLabelInpNKNDPLANE99);
        EditText eNKNDPLANE101 = (EditText) findViewById(R.id.LinearLabelInpNKNDPLANE101);

        engineData.setModeKNDNKVDPHY97(Float.valueOf(eNKVDPHY97.getText().toString()));
        engineData.setModeKNDNKVDPHY99(Float.valueOf(eNKVDPHY99.getText().toString()));
        engineData.setModeKNDNKVDPHY101(Float.valueOf(eNKVDPHY101.getText().toString()));
        engineData.setModeKNDNKNDPHY97(Float.valueOf(eNKNDPHY97.getText().toString()));
        engineData.setModeKNDNKNDPHY99(Float.valueOf(eNKNDPHY99.getText().toString()));
        engineData.setModeKNDNKNDPHY101(Float.valueOf(eNKNDPHY101.getText().toString()));
        engineData.setModeKNDNKNDPLANE97(Float.valueOf(eNKNDPLANE97.getText().toString()));
        engineData.setModeKNDNKNDPLANE99(Float.valueOf(eNKNDPLANE99.getText().toString()));
        engineData.setModeKNDNKNDPLANE101(Float.valueOf(eNKNDPLANE101.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInfo")) {
            EditText eNKVDPHY97 = (EditText) findViewById(R.id.LinearLabelInpNKVDPHY97);
            EditText eNKVDPHY99 = (EditText) findViewById(R.id.LinearLabelInpNKVDPHY99);
            EditText eNKVDPHY101 = (EditText) findViewById(R.id.LinearLabelInpNKVDPHY101);
            EditText eNKNDPHY97 = (EditText) findViewById(R.id.LinearLabelInpNKNDPHY97);
            EditText eNKNDPHY99 = (EditText) findViewById(R.id.LinearLabelInpNKNDPHY99);
            EditText eNKNDPHY101 = (EditText) findViewById(R.id.LinearLabelInpNKNDPHY101);
            EditText eNKNDPLANE97 = (EditText) findViewById(R.id.LinearLabelInpNKNDPLANE97);
            EditText eNKNDPLANE99 = (EditText) findViewById(R.id.LinearLabelInpNKNDPLANE99);
            EditText eNKNDPLANE101 = (EditText) findViewById(R.id.LinearLabelInpNKNDPLANE101);

            eNKVDPHY97.setText(Float.toString(engineData.getModeKNDNKVDPHY97()));
            eNKVDPHY99.setText(Float.toString(engineData.getModeKNDNKVDPHY99()));
            eNKVDPHY101.setText(Float.toString(engineData.getModeKNDNKVDPHY101()));
            eNKNDPHY97.setText(Float.toString(engineData.getModeKNDNKNDPHY97()));
            eNKNDPHY99.setText(Float.toString(engineData.getModeKNDNKNDPHY99()));
            eNKNDPHY101.setText(Float.toString(engineData.getModeKNDNKNDPHY101()));
            eNKNDPLANE97.setText(Float.toString(engineData.getModeKNDNKNDPLANE97()));
            eNKNDPLANE99.setText(Float.toString(engineData.getModeKNDNKNDPLANE99()));
            eNKNDPLANE101.setText(Float.toString(engineData.getModeKNDNKNDPLANE101()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}