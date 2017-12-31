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

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	01.10.2017		Eduard Arefjev 		Created "StepEngineInfo" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	30.12.2017      Eduard Arefjev      Added new fields
 */

public class StepEngineInfo extends AppCompatActivity {

    Date currentTime;
    public StepEngineData engineData;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_step_engine_info);
        this.setTitle("Базовые данные");

        TextView date = (TextView) findViewById(R.id.LinearDate);
        currentTime = Calendar.getInstance().getTime();
        date.append(" " + DateFormat.format("dd.MM.yyyy", currentTime).toString());

        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = (Button) findViewById(R.id.LinearButtonNext);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                String id = CreationHelper.createRecord(engineData);
                Intent intent = new Intent(StepEngineInfo.this, StepStartInfo.class);
                intent.putExtra("recordId", id);
                intent.putExtra("parentViewName", "StepEngineInfo");
                Bundle extra = new Bundle();
                extra.putParcelable("objects", engineData);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        });
    }

    public void setRecord(){
        EditText eEngineNumber = (EditText) findViewById(R.id.LinearInpEngineNumber);
        EditText ePlaneBoardId = (EditText) findViewById(R.id.LinearInpPlaneNumber);
        EditText eWP = (EditText) findViewById(R.id.LinearInpWP);
        EditText eAtmPressure = (EditText) findViewById(R.id.LinearInpAtmPressure);
        EditText eAtmTemperature = (EditText) findViewById(R.id.LinearInpAtmTemperature);
        EditText eWork0Nominal = (EditText) findViewById(R.id.LinearInpWork0Nominal);
        EditText eWorkNominal = (EditText) findViewById(R.id.LinearInpWorkNominal);
        EditText eWorkMax = (EditText) findViewById(R.id.LinearInpWorkMax);

        engineData = new StepEngineData();
        engineData.setEngineId(Integer.valueOf(eEngineNumber.getText().toString()));
        engineData.setPlaneBoardId(Integer.valueOf(ePlaneBoardId.getText().toString()));
        engineData.setLaunchDate(currentTime);
        engineData.setLaunchId(eWP.getText().toString());
        engineData.setAtmPressure(Integer.valueOf(eAtmPressure.getText().toString()));
        engineData.setAtmTemp(Integer.valueOf(eAtmTemperature.getText().toString()));
        engineData.setAtmPressure(Integer.valueOf(eAtmPressure.getText().toString()));
        engineData.setWork0Nominal(Float.valueOf(eWork0Nominal.getText().toString()));
        engineData.setWorkNominal(Float.valueOf(eWorkNominal.getText().toString()));
        engineData.setWorkMax(Float.valueOf(eWorkMax.getText().toString()));
        engineData.setCurrentUserId(FirebaseManager.getCurrentUserId());
    }
}
