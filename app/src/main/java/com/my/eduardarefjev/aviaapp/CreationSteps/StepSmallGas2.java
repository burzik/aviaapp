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

import java.util.ArrayList;

import static java.lang.Double.MIN_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	29.10.2017		Eduard Arefjev 		Created "StepSmallGas2" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 */

public class StepSmallGas2 extends AppCompatActivity {

    private StepEngineData engineData;
    private String id;
    private boolean showValues;
    private boolean editableValues;
    private ArrayList<Class> classArrayList = new ArrayList<>();

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
        setContentView(R.layout.linear_step_small_gas_2);
        this.setTitle(R.string.label_small_gas);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        //parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");
        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", false);
        classArrayList = (ArrayList<Class>)intent.getSerializableExtra("classMap");

        EditText eN1 = findViewById(R.id.LinearInpN1);
        CreationHelper.checkValue(eN1, 54.5, 57.5);
        EditText eTRC = findViewById(R.id.LinearInpTRC);
        CreationHelper.checkValue(eTRC, -MIN_VALUE, 600);
        EditText ePm = findViewById(R.id.LinearInpPm);
        CreationHelper.checkValue(ePm, 2, 4.5);
        EditText eTmC = findViewById(R.id.LinearInpTmC);
        CreationHelper.checkValue(eTmC, -5, 90);
        EditText ePt = findViewById(R.id.LinearInpPt);
        CreationHelper.checkValue(ePt, -MIN_VALUE, 65);
        EditText eEngineSqrt = findViewById(R.id.LinearInpEngineSqrt);
        CreationHelper.checkValue(eEngineSqrt, -MIN_VALUE, 40);
        EditText ePPKSwitchMin = findViewById(R.id.LinearInpPPKSwitchMin);
        CreationHelper.checkValue(ePPKSwitchMin, 0.5, 0.8);
        EditText ePPKSwitchMax = findViewById(R.id.LinearInpPPKSwitchMax);
        CreationHelper.checkValue(ePPKSwitchMax, 0.08, 1.6);
        EditText eTurnOn = findViewById(R.id.RelativeInpTurnOn);
        CreationHelper.checkValue(eTurnOn, -MIN_VALUE, 30);
        EditText eTurnOff = findViewById(R.id.RelativeInpTurnOff);
        CreationHelper.checkValue(eTurnOff, -MIN_VALUE, 30);
        EditText eTurnOn_2 = findViewById(R.id.RelativeInpTurnOn_2);
        CreationHelper.checkValue(eTurnOn_2, -MIN_VALUE, 45);
        EditText eTurnOff_2 = findViewById(R.id.RelativeInpTurnOff_2);
        CreationHelper.checkValue(eTurnOff_2, -MIN_VALUE, 45);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextStepSmallGas2);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent;
                if (classArrayList.size() <= 0)
                    intent = new Intent(StepSmallGas2.this, StepControlKND.class);
                else intent = new Intent(StepSmallGas2.this, classArrayList.get(0));
                classArrayList.remove(0);
                intent.putExtra("recordId", id);
                intent.putExtra("showValues", showValues);
                intent.putExtra("editableValues", editableValues);
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                intent.putExtra("classMap", classArrayList);
                startActivity(intent);
            }
        });
    }
    public void setRecord(){
        EditText eN1 = findViewById(R.id.LinearInpN1);
        EditText eTRC = findViewById(R.id.LinearInpTRC);
        EditText ePm = findViewById(R.id.LinearInpPm);
        EditText eTmC = findViewById(R.id.LinearInpTmC);
        EditText ePt = findViewById(R.id.LinearInpPt);
        EditText eEngineSqrt = findViewById(R.id.LinearInpEngineSqrt);
        EditText ePPKSwitchMin = findViewById(R.id.LinearInpPPKSwitchMin);
        EditText ePPKSwitchMax = findViewById(R.id.LinearInpPPKSwitchMax);
        EditText eTurnOn = findViewById(R.id.RelativeInpTurnOn);
        EditText eTurnOff = findViewById(R.id.RelativeInpTurnOff);
        EditText eTurnOn_2 = findViewById(R.id.RelativeInpTurnOn_2);
        EditText eTurnOff_2 = findViewById(R.id.RelativeInpTurnOff_2);

        if (!eN1.getText().toString().isEmpty())
            engineData.setModeSmallGas2HPCSpeed(Float.valueOf(eN1.getText().toString()));
        if (!eTRC.getText().toString().isEmpty())
            engineData.setModeSmallGas2Temp(Integer.valueOf(eTRC.getText().toString()));
        if (!ePm.getText().toString().isEmpty())
            engineData.setModeSmallGas2OilPressure(Float.valueOf(ePm.getText().toString()));
        if (!eTmC.getText().toString().isEmpty())
            engineData.setModeSmallGas2OilTemp(Integer.valueOf(eTmC.getText().toString()));
        if (!ePt.getText().toString().isEmpty())
            engineData.setModeSmallGas2FuelPressure(Integer.valueOf(ePt.getText().toString()));
        if (!eEngineSqrt.getText().toString().isEmpty())
            engineData.setModeSmallGas2Vibration(Integer.valueOf(eEngineSqrt.getText().toString()));
        if (!ePPKSwitchMin.getText().toString().isEmpty())
            engineData.setModeSmallGas2SwitchMin(Float.valueOf(ePPKSwitchMin.getText().toString()));
        if (!ePPKSwitchMax.getText().toString().isEmpty())
            engineData.setModeSmallGas2SwitchMax(Float.valueOf(ePPKSwitchMax.getText().toString()));
        if (!eTurnOn.getText().toString().isEmpty())
            engineData.setModeSmallGas2CondOn(Float.valueOf(eTurnOn.getText().toString()));
        if (!eTurnOff.getText().toString().isEmpty())
            engineData.setModeSmallGas2CondOff(Float.valueOf(eTurnOff.getText().toString()));
        if (!eTurnOn_2.getText().toString().isEmpty())
            engineData.setModeSmallGas2AntifreezeOn(Float.valueOf(eTurnOn_2.getText().toString()));
        if (!eTurnOff_2.getText().toString().isEmpty())
            engineData.setModeSmallGas2AntifreezeOff(Float.valueOf(eTurnOff_2.getText().toString()));
    }

    public void updateUI(){
        if(showValues){
            EditText eN1 = findViewById(R.id.LinearInpN1);
            EditText eTRC = findViewById(R.id.LinearInpTRC);
            EditText ePm = findViewById(R.id.LinearInpPm);
            EditText eTmC = findViewById(R.id.LinearInpTmC);
            EditText ePt = findViewById(R.id.LinearInpPt);
            EditText eEngineSqrt = findViewById(R.id.LinearInpEngineSqrt);
            EditText ePPKSwitchMin = findViewById(R.id.LinearInpPPKSwitchMin);
            EditText ePPKSwitchMax = findViewById(R.id.LinearInpPPKSwitchMax);
            EditText eTurnOn = findViewById(R.id.RelativeInpTurnOn);
            EditText eTurnOff = findViewById(R.id.RelativeInpTurnOff);
            EditText eTurnOn_2 = findViewById(R.id.RelativeInpTurnOn_2);
            EditText eTurnOff_2 = findViewById(R.id.RelativeInpTurnOff_2);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eN1);
                CreationHelper.makeEditTextReadOnly(eTRC);
                CreationHelper.makeEditTextReadOnly(ePm);
                CreationHelper.makeEditTextReadOnly(eTmC);
                CreationHelper.makeEditTextReadOnly(ePt);
                CreationHelper.makeEditTextReadOnly(eEngineSqrt);
                CreationHelper.makeEditTextReadOnly(ePPKSwitchMin);
                CreationHelper.makeEditTextReadOnly(ePPKSwitchMax);
                CreationHelper.makeEditTextReadOnly(eTurnOn);
                CreationHelper.makeEditTextReadOnly(eTurnOff);
                CreationHelper.makeEditTextReadOnly(eTurnOn_2);
                CreationHelper.makeEditTextReadOnly(eTurnOff_2);
            }

            eN1.setText(Float.toString(engineData.getModeSmallGas2HPCSpeed()));
            eTRC.setText(Integer.toString(engineData.getModeSmallGas2Temp()));
            ePm.setText(Float.toString(engineData.getModeSmallGas2OilPressure()));
            eTmC.setText(Integer.toString(engineData.getModeSmallGas2OilTemp()));
            ePt.setText(Integer.toString(engineData.getModeSmallGas2FuelPressure()));
            eEngineSqrt.setText(Integer.toString(engineData.getModeSmallGas2Vibration()));
            ePPKSwitchMin.setText(Float.toString(engineData.getModeSmallGas2SwitchMin()));
            ePPKSwitchMax.setText(Float.toString(engineData.getModeSmallGas2SwitchMax()));
            eTurnOn.setText(Float.toString(engineData.getModeSmallGas2CondOn()));
            eTurnOff.setText(Float.toString(engineData.getModeSmallGas2CondOff()));
            eTurnOn_2.setText(Float.toString(engineData.getModeSmallGas2AntifreezeOn()));
            eTurnOff_2.setText(Float.toString(engineData.getModeSmallGas2AntifreezeOff()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}