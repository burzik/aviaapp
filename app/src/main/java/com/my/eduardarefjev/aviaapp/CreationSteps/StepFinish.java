package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.MainActivity;
import com.my.eduardarefjev.aviaapp.R;

import java.util.Date;
import java.util.HashMap;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	23.10.2017		Eduard Arefjev 		Created "StepFinish" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 * 	17.02.2018      Eduard Arefjev      Added time
 */

public class StepFinish extends AppCompatActivity {

    private StepEngineData engineData;
    private String id;
    private boolean showValues;
    private boolean editableValues;
    private long diff;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //EA Inflate the menu
        if(!editableValues)
            getMenuInflater().inflate(R.menu.menu_creation_steps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean itemSelected = super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.UpdateRecord: {
                Intent intent = new Intent(this, UpdateRecordMenu.class);
                intent.putExtra("recordId", id);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);
                break;
            }
            default:
                return itemSelected;
        }
        return true;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_finish);
        this.setTitle(R.string.label_final_data);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        //parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");
        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", false);
        HashMap<String, Boolean> hashMap = (HashMap<String, Boolean>) intent.getSerializableExtra("map");

        if (hashMap != null && hashMap.size() != 0){
            if(!hashMap.get("checkbox_final_data")) {
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("recordId", id);
                intent.putExtra("showValues", showValues);
                intent.putExtra("editableValues", editableValues);
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                intent.putExtra("map", hashMap);
                startActivity(intent);
            }
        }
        Date start = engineData.getLaunchDate();
        Date end = new Date();
        diff = (end.getTime() - start.getTime() * (60)/1000);

        EditText eCommon = findViewById(R.id.LinearLabelInpCommon);
        EditText eNominal = findViewById(R.id.LinearLabelInpNominal);
        EditText eN1StraightRunV2 = findViewById(R.id.LinearLabelInpN1StraightRunV2);

        eCommon.setText(String.valueOf(diff));
        eNominal.setText(Float.toString(engineData.getModeWorkNom()));
        eN1StraightRunV2.setText(Float.toString(engineData.getModeWorkMax()));

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextFinish);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepFinish.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        engineData.setModeWorkSum(diff);

        EditText eCommon = findViewById(R.id.LinearLabelInpCommon);
        EditText eNominal = findViewById(R.id.LinearLabelInpNominal);
        EditText eN1StraightRunV2 = findViewById(R.id.LinearLabelInpN1StraightRunV2);
        EditText eEngineAI = findViewById(R.id.LinearLabelInpEngineAI);
        EditText eVSUSapphire = findViewById(R.id.LinearLabelInpVSUSapphire);
        EditText eEngineA2I = findViewById(R.id.LinearLabelInpEngineA2I);

        if (!eCommon.getText().toString().isEmpty())
            engineData.setModeWorkSum(Float.valueOf(eCommon.getText().toString()));
        if (!eNominal.getText().toString().isEmpty())
            engineData.setModeWorkNom(Float.valueOf(eNominal.getText().toString()));
        if (!eN1StraightRunV2.getText().toString().isEmpty())
            engineData.setModeWorkMax(Float.valueOf(eN1StraightRunV2.getText().toString()));
        if (!eEngineAI.getText().toString().isEmpty())
            engineData.setModeWorkLaunchCount(Integer.valueOf(eEngineAI.getText().toString()));
        if (!eVSUSapphire.getText().toString().isEmpty())
            engineData.setModeWorkLaunchVSUCount(Integer.valueOf(eVSUSapphire.getText().toString()));
        if (!eEngineA2I.getText().toString().isEmpty())
            engineData.setModeWorkN1Count(Integer.valueOf(eEngineA2I.getText().toString()));
    }

    public void updateUI(){
        if(showValues){
            EditText eCommon = findViewById(R.id.LinearLabelInpCommon);
            EditText eNominal = findViewById(R.id.LinearLabelInpNominal);
            EditText eN1StraightRunV2 = findViewById(R.id.LinearLabelInpN1StraightRunV2);
            EditText eEngineAI = findViewById(R.id.LinearLabelInpEngineAI);
            EditText eVSUSapphire = findViewById(R.id.LinearLabelInpVSUSapphire);
            EditText eEngineA2I = findViewById(R.id.LinearLabelInpEngineA2I);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eCommon);
                CreationHelper.makeEditTextReadOnly(eNominal);
                CreationHelper.makeEditTextReadOnly(eN1StraightRunV2);
                CreationHelper.makeEditTextReadOnly(eEngineAI);
                CreationHelper.makeEditTextReadOnly(eVSUSapphire);
                CreationHelper.makeEditTextReadOnly(eEngineA2I);
            }

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