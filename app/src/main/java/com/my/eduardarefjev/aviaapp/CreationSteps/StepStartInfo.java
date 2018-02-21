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

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	09.10.2017		Eduard Arefjev 		Created "StepStartInfo" screen, one of steps
 * 	30.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 */

public class StepStartInfo extends AppCompatActivity {

    private String id;
    private StepEngineData engineData;
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
        setContentView(R.layout.linear_step_start_info);
        this.setTitle(R.string.label_base_values_2);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");
        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", true);
        classArrayList = (ArrayList<Class>)intent.getSerializableExtra("classMap");

        EditText eLimb = findViewById(R.id.LinearLabelInpLimbArg);
        CreationHelper.checkValue(eLimb, 18, 24);
        EditText eLaunchingTVSU = findViewById(R.id.LinearLabelInpLaunchingTVSU);
        CreationHelper.checkValue(eLaunchingTVSU, 0, 31);
        EditText eEngineCasting = findViewById(R.id.LinearLabelInpEngineCasting);
        CreationHelper.checkValue(eEngineCasting, 0, 550);
        EditText eVSUDisconnection = findViewById(R.id.LinearLabelInpVSUDisconnection);
        CreationHelper.checkValue(eVSUDisconnection, 41.5, 44.5);
        EditText eTEngine = findViewById(R.id.LinearLabelInpTEngine);
        CreationHelper.checkValue(eTEngine, 0, 50);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextSecondStep);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent;
                if (classArrayList.size() <= 0)
                    intent = new Intent(StepStartInfo.this, StepSmallGas.class);
                else intent = new Intent(StepStartInfo.this, classArrayList.get(0));
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
        EditText eLimbArg = findViewById(R.id.LinearLabelInpLimbArg);
        EditText eLaunchingTVSU = findViewById(R.id.LinearLabelInpLaunchingTVSU);
        EditText eEngineCasting = findViewById(R.id.LinearLabelInpEngineCasting);
        EditText eVSUDisconnection = findViewById(R.id.LinearLabelInpVSUDisconnection);
        EditText eTEngine = findViewById(R.id.LinearLabelInpTEngine);

        if (!eLimbArg.getText().toString().isEmpty())
            engineData.setLimb(Integer.valueOf(eLimbArg.getText().toString()));
        if (!eLaunchingTVSU.getText().toString().isEmpty())
            engineData.setApuTime(Integer.valueOf(eLaunchingTVSU.getText().toString()));
        if (!eEngineCasting.getText().toString().isEmpty())
            engineData.setLaunchEngineTemp(Float.valueOf(eEngineCasting.getText().toString()));
        if (!eVSUDisconnection.getText().toString().isEmpty())
            engineData.setLaunchAPUOffEngine(Integer.valueOf(eVSUDisconnection.getText().toString()));
        if (!eTEngine.getText().toString().isEmpty())
            engineData.setLaunchEngineTime(Integer.valueOf(eTEngine.getText().toString()));
    }

    public void updateUI(){
        if (showValues){
            EditText eLimbArg = findViewById(R.id.LinearLabelInpLimbArg);
            EditText eLaunchingTVSU = findViewById(R.id.LinearLabelInpLaunchingTVSU);
            EditText eEngineCasting = findViewById(R.id.LinearLabelInpEngineCasting);
            EditText eVSUDisconnection = findViewById(R.id.LinearLabelInpVSUDisconnection);
            EditText eTEngine = findViewById(R.id.LinearLabelInpTEngine);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eLimbArg);
                CreationHelper.makeEditTextReadOnly(eLaunchingTVSU);
                CreationHelper.makeEditTextReadOnly(eEngineCasting);
                CreationHelper.makeEditTextReadOnly(eVSUDisconnection);
                CreationHelper.makeEditTextReadOnly(eTEngine);
            }

            eLimbArg.setText(Integer.toString(engineData.getLimb()));
            eLaunchingTVSU.setText(Integer.toString(engineData.getApuTime()));
            eEngineCasting.setText(Float.toString(engineData.getLaunchEngineTemp()));
            eVSUDisconnection.setText(Integer.toString(engineData.getLaunchAPUOffEngine()));
            eTEngine.setText(Integer.toString(engineData.getLaunchEngineTime()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}
