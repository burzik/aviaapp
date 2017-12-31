package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.my.eduardarefjev.aviaapp.R;

import static java.lang.Double.MIN_VALUE;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	23.10.2017		Eduard Arefjev 		Created "StepNom" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 */

public class StepNom extends AppCompatActivity {

    private Spinner spinnerDoNotRunOn;
    private Spinner spinnerEngineParametersOn;
    private Spinner spinnerDoNotRunOff;
    private Spinner spinnerParametersOff;
    private Spinner spinnerTurnOn;
    private Spinner spinnerTurnOff;
    private Spinner spinnerAutomatic;
    private Spinner spinnerHeat_2;
    private Spinner spinnerCold_2;
    private Spinner spinnerAutomatic_2;
    private StepEngineData engineData;
    String id;
    String parentView;
    ArrayAdapter<String> myAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_nom);
        this.setTitle("Ном.");

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        EditText eN1 = (EditText) findViewById(R.id.LinearLabelInpN1_2);
        CreationHelper.checkValue(eN1, 102, 104);
        EditText eTrc = (EditText) findViewById(R.id.LinearLabelInpTrc_2);
        CreationHelper.checkValue(eTrc, -MIN_VALUE, 625);
        EditText ePm = (EditText) findViewById(R.id.LinearLabelInpPm_2);
        CreationHelper.checkValue(ePm, 3, 4.5);
        EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc_2);
        CreationHelper.checkValue(eTmc, -MIN_VALUE, 90);
        EditText ePt = (EditText) findViewById(R.id.LinearLabelInpPt_2);
        CreationHelper.checkValue(ePt, -MIN_VALUE, 65);
        EditText eEngineSqrt = (EditText) findViewById(R.id.LinearLabelInpEngineSqrt_2);
        CreationHelper.checkValue(eEngineSqrt, -MIN_VALUE, 40);
        EditText eVGenerator = (EditText) findViewById(R.id.LinearLabelInpLabelVGenerator);
        CreationHelper.checkValue(eVGenerator, 27, 29);

        spinnerDoNotRunOn = (Spinner) findViewById(R.id.RelativeSpinnerInpDoNotRunOn);
        spinnerEngineParametersOn = (Spinner) findViewById(R.id.RelativeSpinnerInpEngineParametersOn);
        spinnerDoNotRunOff = (Spinner) findViewById(R.id.RelativeSpinnerInpDoNotRunOff);
        spinnerParametersOff = (Spinner) findViewById(R.id.RelativeSpinnerInpEngineParametersOff);
        spinnerTurnOn = (Spinner) findViewById(R.id.RelativeSpinnerInpTurnOn);
        spinnerTurnOff = (Spinner) findViewById(R.id.RelativeSpinnerInpTurnOff);
        spinnerAutomatic = (Spinner) findViewById(R.id.RelativeSpinnerInpAutomatic);
        spinnerHeat_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpHeat_2);
        spinnerCold_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpCold_2);
        spinnerAutomatic_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpAutomatic_2);

        //EA Create DropDown List
        myAdapter = new ArrayAdapter<>(StepNom.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.good_bad));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoNotRunOn.setAdapter(myAdapter);
        spinnerEngineParametersOn.setAdapter(myAdapter);
        spinnerDoNotRunOff.setAdapter(myAdapter);
        spinnerParametersOff.setAdapter(myAdapter);
        spinnerTurnOn.setAdapter(myAdapter);
        spinnerTurnOff.setAdapter(myAdapter);
        spinnerAutomatic.setAdapter(myAdapter);
        spinnerHeat_2.setAdapter(myAdapter);
        spinnerCold_2.setAdapter(myAdapter);
        spinnerAutomatic_2.setAdapter(myAdapter);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNextClosingKVD3Nom);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(StepNom.this, StepMax.class);
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
            EditText eN1 = (EditText) findViewById(R.id.LinearLabelInpN1_2);
            EditText eTrc = (EditText) findViewById(R.id.LinearLabelInpTrc_2);
            EditText ePm = (EditText) findViewById(R.id.LinearLabelInpPm_2);
            EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc_2);
            EditText ePt = (EditText) findViewById(R.id.LinearLabelInpPt_2);
            EditText eEngineSqrt = (EditText) findViewById(R.id.LinearLabelInpEngineSqrt_2);
            EditText eVGenerator = (EditText) findViewById(R.id.LinearLabelInpLabelVGenerator);
            spinnerDoNotRunOn = (Spinner) findViewById(R.id.RelativeSpinnerInpDoNotRunOn);
            spinnerEngineParametersOn = (Spinner) findViewById(R.id.RelativeSpinnerInpEngineParametersOn);
            spinnerDoNotRunOff = (Spinner) findViewById(R.id.RelativeSpinnerInpDoNotRunOff);
            spinnerParametersOff = (Spinner) findViewById(R.id.RelativeSpinnerInpEngineParametersOff);
            spinnerTurnOn = (Spinner) findViewById(R.id.RelativeSpinnerInpTurnOn);
            spinnerTurnOff = (Spinner) findViewById(R.id.RelativeSpinnerInpTurnOff);
            spinnerAutomatic = (Spinner) findViewById(R.id.RelativeSpinnerInpAutomatic);
            spinnerHeat_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpHeat_2);
            spinnerCold_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpCold_2);
            spinnerAutomatic_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpAutomatic_2);

            engineData.setModeNomHPCSpeed(Float.valueOf(eN1.getText().toString()));
            engineData.setModeNomTemp(Integer.valueOf(eTrc.getText().toString()));
            engineData.setModeNomOilPressure(Float.valueOf(ePm.getText().toString()));
            engineData.setModeNomOilTemp(Integer.valueOf(eTmc.getText().toString()));
            engineData.setModeNomFuelPressure(Integer.valueOf(ePt.getText().toString()));
            engineData.setModeNomVibration(Integer.valueOf(eEngineSqrt.getText().toString()));
            engineData.setModeNom4001OnSignal(spinnerDoNotRunOn.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNom4001OnEngine(spinnerEngineParametersOn.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNom4001OffSignal(spinnerDoNotRunOff.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNom4001OffEngine(spinnerParametersOff.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNomPTBKHeat(spinnerTurnOn.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNomPTBKCold(spinnerTurnOff.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNomPTBKAutomation(spinnerAutomatic.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNomShowerHeat(spinnerHeat_2.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNomShowerCold(spinnerCold_2.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNomShowerAutomation(spinnerAutomatic_2.getSelectedItem().toString().equals("Норма"));
            engineData.setModeNomVGenerator(Integer.valueOf(eVGenerator.getText().toString()));
    }

    public void updateUI(){
        if(parentView.equals("DetailedRecordInformation")) {
            EditText eN1 = (EditText) findViewById(R.id.LinearLabelInpN1_2);
            EditText eTrc = (EditText) findViewById(R.id.LinearLabelInpTrc_2);
            EditText ePm = (EditText) findViewById(R.id.LinearLabelInpPm_2);
            EditText eTmc = (EditText) findViewById(R.id.LinearLabelInpTmc_2);
            EditText ePt = (EditText) findViewById(R.id.LinearLabelInpPt_2);
            EditText eEngineSqrt = (EditText) findViewById(R.id.LinearLabelInpEngineSqrt_2);
            EditText eVGenerator = (EditText) findViewById(R.id.LinearLabelInpLabelVGenerator);
            spinnerDoNotRunOn = (Spinner) findViewById(R.id.RelativeSpinnerInpDoNotRunOn);
            spinnerEngineParametersOn = (Spinner) findViewById(R.id.RelativeSpinnerInpEngineParametersOn);
            spinnerDoNotRunOff = (Spinner) findViewById(R.id.RelativeSpinnerInpDoNotRunOff);
            spinnerParametersOff = (Spinner) findViewById(R.id.RelativeSpinnerInpEngineParametersOff);
            spinnerTurnOn = (Spinner) findViewById(R.id.RelativeSpinnerInpTurnOn);
            spinnerTurnOff = (Spinner) findViewById(R.id.RelativeSpinnerInpTurnOff);
            spinnerAutomatic = (Spinner) findViewById(R.id.RelativeSpinnerInpAutomatic);
            spinnerHeat_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpHeat_2);
            spinnerCold_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpCold_2);
            spinnerAutomatic_2 = (Spinner) findViewById(R.id.RelativeSpinnerInpAutomatic_2);

            eN1.setText(Float.toString(engineData.getModeNomHPCSpeed()));
            eTrc.setText(Integer.toString(engineData.getModeNomTemp()));
            ePm.setText(Float.toString(engineData.getModeNomOilPressure()));
            eTmc.setText(Integer.toString(engineData.getModeNomOilTemp()));
            ePt.setText(Integer.toString(engineData.getModeNomFuelPressure()));
            eEngineSqrt.setText(Integer.toString(engineData.getModeNomVibration()));
            int spinnerPosition = myAdapter.getPosition(engineData.isModeNom4001OnSignal() ? "Норма" : "Не норма");
            spinnerDoNotRunOn.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeNom4001OnEngine() ? "Норма" : "Не норма");
            spinnerEngineParametersOn.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeNom4001OffSignal() ? "Норма" : "Не норма");
            spinnerDoNotRunOff.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeNom4001OffEngine() ? "Норма" : "Не норма");
            spinnerParametersOff.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeNomPTBKHeat() ? "Норма" : "Не норма");
            spinnerTurnOn.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeNomPTBKCold() ? "Норма" : "Не норма");
            spinnerTurnOff.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeNomPTBKAutomation() ? "Норма" : "Не норма");
            spinnerAutomatic.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeNomShowerHeat() ? "Норма" : "Не норма");
            spinnerHeat_2.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeNomShowerCold() ? "Норма" : "Не норма");
            spinnerCold_2.setSelection(spinnerPosition);
            spinnerPosition = myAdapter.getPosition(engineData.isModeNomShowerAutomation() ? "Норма" : "Не норма");
            spinnerAutomatic_2.setSelection(spinnerPosition);
            eVGenerator.setText(Integer.toString(engineData.getModeNomVGenerator()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}