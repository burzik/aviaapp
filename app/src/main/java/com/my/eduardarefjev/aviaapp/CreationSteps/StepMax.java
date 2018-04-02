package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.Double.MIN_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	23.10.2017		Eduard Arefjev 		Created "StepMax" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 * 	17.02.2018      Eduard Arefjev      Added time
 */

public class StepMax extends AppCompatActivity{

    private Spinner spinnerKrTank;
    private StepEngineData engineData;
    private String id;
    private ArrayAdapter<String> myAdapter;
    private boolean showValues;
    private boolean editableValues;
    private ArrayList<Class> classArrayList = new ArrayList<>();
    private Date start = new Date();

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
        setContentView(R.layout.linear_step_max);
        this.setTitle(R.string.label_max);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");
        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", false);
        classArrayList = (ArrayList<Class>)intent.getSerializableExtra("classMap");

        EditText eN1 = findViewById(R.id.LinearInpN1);
        CreationHelper.checkValue(eN1, 105, 107);
        //from graphic?
        //EditText eN2_4 = (EditText) findViewById(R.id.LinearInpN2_4);
        //CreationHelper.checkValue(eN2_4, 102, 104);
        EditText eTRC = findViewById(R.id.LinearInpTRC);
        CreationHelper.checkValue(eTRC, -MIN_VALUE, 660);
        EditText ePm = findViewById(R.id.LinearInpPm);
        CreationHelper.checkValue(ePm, 3, 4.5);
        EditText eTmC = findViewById(R.id.LinearInpTmC);
        CreationHelper.checkValue(eTmC, -MIN_VALUE, 90);
        EditText ePt = findViewById(R.id.LinearInpPt);
        CreationHelper.checkValue(ePt, -MIN_VALUE, 65);
        EditText eEngineSqrt = findViewById(R.id.LinearInpEngineSqrt);
        CreationHelper.checkValue(eEngineSqrt, -MIN_VALUE, 40);
        EditText eVGenerator = findViewById(R.id.LinearInpVGenerator);
        CreationHelper.checkValue(eVGenerator, 27, 29);
        EditText ePCabin = findViewById(R.id.LinearInpPCabin);
        CreationHelper.checkValue(ePCabin, -MIN_VALUE, 0.05);

        spinnerKrTank = findViewById(R.id.RelativeSpinnerInpKrTank);
        myAdapter = new ArrayAdapter<>(StepMax.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.good_bad));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKrTank.setAdapter(myAdapter);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextClosingKVD3Max);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent;
                if (classArrayList == null || classArrayList.size() <= 0)
                    intent = new Intent(StepMax.this, StepClosingKVDKPV.class);
                else {
                    intent = new Intent(StepMax.this, classArrayList.get(0));
                    classArrayList.remove(0);
                }
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
        Date end = new Date();
        long diff = (end.getTime() - start.getTime())/1000 ;
        engineData.setModeWorkMax(diff);
        EditText eN1 = findViewById(R.id.LinearInpN1);
        EditText eN2_4 = findViewById(R.id.LinearInpN2_4);
        EditText eTRC = findViewById(R.id.LinearInpTRC);
        EditText ePm = findViewById(R.id.LinearInpPm);
        EditText eTmC = findViewById(R.id.LinearInpTmC);
        EditText ePt = findViewById(R.id.LinearInpPt);
        EditText eEngineSqrt = findViewById(R.id.LinearInpEngineSqrt);
        EditText eVGenerator = findViewById(R.id.LinearInpVGenerator);
        EditText ePCabin = findViewById(R.id.LinearInpPCabin);
        spinnerKrTank = findViewById(R.id.RelativeSpinnerInpKrTank);
        EditText ePVozdKrTank = findViewById(R.id.LinearInpPVozdKrTank);
        EditText ePtPreembossed = findViewById(R.id.LinearInpPtPreembossed);

        if (!eN1.getText().toString().isEmpty())
            engineData.setModeMaxHPCSpeed(Float.valueOf(eN1.getText().toString()));
        if (!eN2_4.getText().toString().isEmpty())
            engineData.setModeMaxHPCSpeedN2(Float.valueOf(eN2_4.getText().toString()));
        if (!eTRC.getText().toString().isEmpty())
            engineData.setModeMaxTemp(Integer.valueOf(eTRC.getText().toString()));
        if (!ePm.getText().toString().isEmpty())
            engineData.setModeMaxOilPressure(Float.valueOf(ePm.getText().toString()));
        if (!eTmC.getText().toString().isEmpty())
            engineData.setModeMaxOilTemp(Integer.valueOf(eTmC.getText().toString()));
        if (!ePt.getText().toString().isEmpty())
            engineData.setModeMaxFuelPressure(Integer.valueOf(ePt.getText().toString()));
        if (!eEngineSqrt.getText().toString().isEmpty())
            engineData.setModeMaxVibration(Integer.valueOf(eEngineSqrt.getText().toString()));
        if (!eVGenerator.getText().toString().isEmpty())
            engineData.setModeMaxVGenerator(Integer.valueOf(eVGenerator.getText().toString()));
            engineData.setModeMaxGeneratorSpeedConst(spinnerKrTank.getSelectedItem().toString().equals("Норма"));
        if (!ePCabin.getText().toString().isEmpty())
            engineData.setModeMaxPressureCabin(Float.valueOf(ePCabin.getText().toString()));
        if (!ePVozdKrTank.getText().toString().isEmpty())
            engineData.setModeMaxPressureWings(Float.valueOf(ePVozdKrTank.getText().toString()));
        if (!ePtPreembossed.getText().toString().isEmpty())
            engineData.setModeMaxPressureNozzles(Integer.valueOf(ePtPreembossed.getText().toString()));
    }

    public void updateUI(){
        if(showValues) {
            EditText eN1 = findViewById(R.id.LinearInpN1);
            EditText eN2_4 = findViewById(R.id.LinearInpN2_4);
            EditText eTRC = findViewById(R.id.LinearInpTRC);
            EditText ePm = findViewById(R.id.LinearInpPm);
            EditText eTmC = findViewById(R.id.LinearInpTmC);
            EditText ePt = findViewById(R.id.LinearInpPt);
            EditText eEngineSqrt = findViewById(R.id.LinearInpEngineSqrt);
            EditText eVGenerator = findViewById(R.id.LinearInpVGenerator);
            EditText ePCabin = findViewById(R.id.LinearInpPCabin);
            spinnerKrTank = findViewById(R.id.RelativeSpinnerInpKrTank);
            EditText ePVozdKrTank = findViewById(R.id.LinearInpPVozdKrTank);
            EditText ePtPreembossed = findViewById(R.id.LinearInpPtPreembossed);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eN1);
                CreationHelper.makeEditTextReadOnly(eN2_4);
                CreationHelper.makeEditTextReadOnly(eTRC);
                CreationHelper.makeEditTextReadOnly(ePm);
                CreationHelper.makeEditTextReadOnly(eTmC);
                CreationHelper.makeEditTextReadOnly(ePt);
                CreationHelper.makeEditTextReadOnly(eEngineSqrt);
                CreationHelper.makeEditTextReadOnly(eVGenerator);
                CreationHelper.makeEditTextReadOnly(ePCabin);
                CreationHelper.makeSpinnerReadOnly(spinnerKrTank);
                CreationHelper.makeEditTextReadOnly(ePVozdKrTank);
                CreationHelper.makeEditTextReadOnly(ePtPreembossed);
            }

            eN1.setText(Float.toString(engineData.getModeMaxHPCSpeed()));
            eN2_4.setText(Float.toString(engineData.getModeMaxHPCSpeedN2()));
            eTRC.setText(Integer.toString(engineData.getModeMaxTemp()));
            ePm.setText(Float.toString(engineData.getModeMaxOilPressure()));
            eTmC.setText(Integer.toString(engineData.getModeMaxOilTemp()));
            ePt.setText(Integer.toString(engineData.getModeMaxFuelPressure()));
            eEngineSqrt.setText(Integer.toString(engineData.getModeMaxVibration()));
            eVGenerator.setText(Integer.toString(engineData.getModeMaxVGenerator()));
            ePCabin.setText(Float.toString(engineData.getModeMaxPressureCabin()));
            int spinnerPosition = myAdapter.getPosition(engineData.isModeMaxGeneratorSpeedConst() ? "Норма" : "Не норма");
            spinnerKrTank.setSelection(spinnerPosition);
            ePVozdKrTank.setText(Float.toString(engineData.getModeMaxPressureWings()));
            ePtPreembossed.setText(Integer.toString(engineData.getModeMaxPressureNozzles()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}