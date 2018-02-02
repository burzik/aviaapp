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

import static java.lang.Double.MAX_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	22.12.2017		Eduard Arefjev 		Created "StepRunoutOfRotors" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 */

public class StepRunoutOfRotors extends AppCompatActivity {

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
        setContentView(R.layout.linear_step_runout_of_rotors);
        this.setTitle(R.string.label_runtime_of_rotors);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        //parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");
        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", false);
        hashMap = (HashMap<String, Boolean>)intent.getSerializableExtra("map");

        if (hashMap != null && hashMap.size() != 0){
            if(!hashMap.get("checkbox_runtime_of_rotors")) {
                intent = new Intent(this, StepTanningBoardDisplayGenerator.class);
                intent.putExtra("recordId", id);
                intent.putExtra("showValues", showValues);
                intent.putExtra("editableValues", editableValues);
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                intent.putExtra("map", hashMap);
                startActivity(intent);
            }
        }

        EditText eRotorVD = findViewById(R.id.LinearLabelInpRotorVD);
        CreationHelper.checkValue(eRotorVD, 20, MAX_VALUE);
        EditText eRotorND = findViewById(R.id.LinearLabelInpRotorND);
        CreationHelper.checkValue(eRotorND, 25, MAX_VALUE);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextRunOutOfRotors);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepRunoutOfRotors.this, StepTanningBoardDisplayGenerator.class);
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
        EditText eRotorND = findViewById(R.id.LinearLabelInpRotorND);
        EditText eRotorVD = findViewById(R.id.LinearLabelInpRotorVD);

        if (!eRotorND.getText().toString().isEmpty())
            engineData.setModeRotorND(Integer.valueOf(eRotorND.getText().toString()));
        if (!eRotorVD.getText().toString().isEmpty())
            engineData.setModeRotorVD(Integer.valueOf(eRotorVD.getText().toString()));
    }

    public void updateUI(){
        if(showValues){
            EditText eRotorND = findViewById(R.id.LinearLabelInpRotorND);
            EditText eRotorVD = findViewById(R.id.LinearLabelInpRotorVD);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eRotorND);
                CreationHelper.makeEditTextReadOnly(eRotorVD);
            }

            eRotorND.setText(Integer.toString(engineData.getModeRotorND()));
            eRotorVD.setText(Integer.toString(engineData.getModeRotorVD()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}