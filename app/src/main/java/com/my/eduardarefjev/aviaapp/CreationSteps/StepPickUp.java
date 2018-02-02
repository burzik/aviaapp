package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.eduardarefjev.aviaapp.R;

import java.util.HashMap;

import static java.lang.Double.MIN_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.10.2017		Eduard Arefjev 		Created "StepPickUp" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 */

public class StepPickUp extends AppCompatActivity {

    private StepEngineData engineData;
    String id;
    boolean showValues;
    boolean editableValues;
    HashMap<String, Boolean> hashMap;

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
        setContentView(R.layout.linear_step_pick_up);
        this.setTitle(R.string.label_pick_up);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        //parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");
        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", false);
        hashMap = (HashMap<String, Boolean>)intent.getSerializableExtra("map");

        if (hashMap != null && hashMap.size() != 0){
            if(!hashMap.get("checkbox_pick_up")) {
                intent = new Intent(this, StepSmallGas2.class);
                intent.putExtra("recordId", id);
                intent.putExtra("showValues", showValues);
                intent.putExtra("editableValues", editableValues);
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                intent.putExtra("map", hashMap);
                startActivity(intent);
            }
        }

        EditText eExitMgMax = findViewById(R.id.LinearLabelInpExitMgMax);
        CreationHelper.checkValue(eExitMgMax, 9, 12);
        EditText eResetMaxMg = findViewById(R.id.LinearLabelInpResetMaxMg);
        CreationHelper.checkValue(eResetMaxMg, -MIN_VALUE, 5);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextStepPickUp);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepPickUp.this, StepSmallGas2.class);
                intent.putExtra("recordId", id);
                intent.putExtra("showValues", showValues);
                intent.putExtra("editableValues", editableValues);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                intent.putExtra("map", hashMap);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        EditText eExitMgMax = findViewById(R.id.LinearLabelInpExitMgMax);
        EditText eResetMaxMg = findViewById(R.id.LinearLabelInpResetMaxMg);

        if (!eExitMgMax.getText().toString().isEmpty())
            engineData.setModeAccelerationIdleMaxIn(Float.valueOf(eExitMgMax.getText().toString()));
        if (!eResetMaxMg.getText().toString().isEmpty())
            engineData.setModeAccelerationIdleOut(Float.valueOf(eResetMaxMg.getText().toString()));
    }

    public void updateUI(){
        if(showValues){
            EditText eExitMgMax = findViewById(R.id.LinearLabelInpExitMgMax);
            EditText eResetMaxMg = findViewById(R.id.LinearLabelInpResetMaxMg);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eExitMgMax);
                CreationHelper.makeEditTextReadOnly(eResetMaxMg);
            }

            eExitMgMax.setText(Float.toString(engineData.getModeAccelerationIdleMaxIn()));
            eResetMaxMg.setText(Float.toString(engineData.getModeAccelerationIdleOut()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}