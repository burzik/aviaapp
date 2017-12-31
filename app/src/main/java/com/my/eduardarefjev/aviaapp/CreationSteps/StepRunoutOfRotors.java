package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

import static java.lang.Double.MAX_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	22.12.2017		Eduard Arefjev 		Created "StepRunoutOfRotors" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 */

public class StepRunoutOfRotors extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    String parentView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_runout_of_rotors);
        this.setTitle("Выбег роторов");

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eRotorVD = (EditText) findViewById(R.id.LinearLabelInpRotorVD);
        CreationHelper.checkValue(eRotorVD, 20, MAX_VALUE);
        EditText eRotorND = (EditText) findViewById(R.id.LinearLabelInpRotorND);
        CreationHelper.checkValue(eRotorND, 25, MAX_VALUE);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextRunOutOfRotors);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepRunoutOfRotors.this, StepTanningBoardDisplayGenerator.class);
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
        EditText eRotorND = (EditText) findViewById(R.id.LinearLabelInpRotorND);
        EditText eRotorVD = (EditText) findViewById(R.id.LinearLabelInpRotorVD);

        engineData.setModeRotorND(Integer.valueOf(eRotorND.getText().toString()));
        engineData.setModeRotorVD(Integer.valueOf(eRotorVD.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInformation")) {
            EditText eRotorND = (EditText) findViewById(R.id.LinearLabelInpRotorND);
            EditText eRotorVD = (EditText) findViewById(R.id.LinearLabelInpRotorVD);

            eRotorND.setText(Integer.toString(engineData.getModeRotorND()));
            eRotorVD.setText(Integer.toString(engineData.getModeRotorVD()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}