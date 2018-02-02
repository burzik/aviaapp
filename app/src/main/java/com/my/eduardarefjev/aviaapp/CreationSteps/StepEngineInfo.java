package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.my.eduardarefjev.aviaapp.FirebaseManager;
import com.my.eduardarefjev.aviaapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	01.10.2017		Eduard Arefjev 		Created "StepEngineInfo" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	30.12.2017      Eduard Arefjev      Added new fields
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Changed title from hardcorded value
 */

public class StepEngineInfo extends AppCompatActivity {

    Date currentTime;
    public StepEngineData engineData;
    HashMap<String, Boolean> hashMap = new HashMap<>();
    boolean showValues = false;
    boolean editableValues = true;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_engine_info);
        this.setTitle(R.string.label_base_values);

        TextView date = findViewById(R.id.LinearDate);
        currentTime = Calendar.getInstance().getTime();
        date.append(" " + DateFormat.format("dd.MM.yyyy", currentTime).toString());

        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNext);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                String id = CreationHelper.createRecord(engineData);
                Intent intent = new Intent(StepEngineInfo.this, StepStartInfo.class);
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
        EditText eEngineNumber = findViewById(R.id.LinearInpEngineNumber);
        EditText ePlaneBoardId = findViewById(R.id.LinearInpPlaneNumber);
        EditText eWP = findViewById(R.id.LinearInpWP);
        EditText eAtmPressure = findViewById(R.id.LinearInpAtmPressure);
        EditText eAtmTemperature = findViewById(R.id.LinearInpAtmTemperature);
        EditText eWork0Nominal = findViewById(R.id.LinearInpWork0Nominal);
        EditText eWorkNominal = findViewById(R.id.LinearInpWorkNominal);
        EditText eWorkMax = findViewById(R.id.LinearInpWorkMax);

        engineData = new StepEngineData();
        if (!eEngineNumber.getText().toString().isEmpty())
            engineData.setEngineId(Integer.valueOf(eEngineNumber.getText().toString()));
        if (!ePlaneBoardId.getText().toString().isEmpty())
            engineData.setPlaneBoardId(Integer.valueOf(ePlaneBoardId.getText().toString()));
        engineData.setLaunchDate(currentTime);
        engineData.setLaunchId(eWP.getText().toString());
        if (!eAtmPressure.getText().toString().isEmpty())
            engineData.setAtmPressure(Integer.valueOf(eAtmPressure.getText().toString()));
        if (!eAtmTemperature.getText().toString().isEmpty())
            engineData.setAtmTemp(Integer.valueOf(eAtmTemperature.getText().toString()));
        if (!eWork0Nominal.getText().toString().isEmpty())
            engineData.setWork0Nominal(Float.valueOf(eWork0Nominal.getText().toString()));
        if (!eWorkNominal.getText().toString().isEmpty())
            engineData.setWorkNominal(Float.valueOf(eWorkNominal.getText().toString()));
        if (!eWorkMax.getText().toString().isEmpty())
            engineData.setWorkMax(Float.valueOf(eWorkMax.getText().toString()));
        engineData.setCurrentUserId(FirebaseManager.getCurrentUserId());
    }
}
