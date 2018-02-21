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
 * 	23.10.2017		Eduard Arefjev 		Created "StepClosingKVDKPV" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 */

public class StepClosingKVDKPV extends AppCompatActivity {

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
        setContentView(R.layout.linear_step_closing_kvd_kpv);
        this.setTitle(R.string.label_turnover_kvd_n);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        //parentView = intent.getStringExtra("parentViewName");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");
        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", false);
        classArrayList = (ArrayList<Class>)intent.getSerializableExtra("classMap");

        EditText eIII = findViewById(R.id.LinearLabelInpIII);
        CreationHelper.checkValue(eIII, 86, 90);
        EditText eV = findViewById(R.id.LinearLabelInpV);
        CreationHelper.checkValue(eV, 74, 78);

        updateUI();
        nextSecondStep();
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextBackStroke);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent;
                if (classArrayList.size() <= 0)
                    intent = new Intent(StepClosingKVDKPV.this, StepPickUp.class);
                else intent = new Intent(StepClosingKVDKPV.this, classArrayList.get(0));
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
        EditText eIII = findViewById(R.id.LinearLabelInpIII);
        EditText eV = findViewById(R.id.LinearLabelInpV);

        if (!eIII.getText().toString().isEmpty())
            engineData.setStageN3ModeName(Integer.valueOf(eIII.getText().toString()));
        if (!eV.getText().toString().isEmpty())
            engineData.setStageN5ModeName(Integer.valueOf(eV.getText().toString()));
    }

    public void updateUI(){
        if(showValues) {
            EditText eIII = findViewById(R.id.LinearLabelInpIII);
            EditText eV = findViewById(R.id.LinearLabelInpV);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eIII);
                CreationHelper.makeEditTextReadOnly(eV);
            }

            eIII.setText(Integer.toString(engineData.getStageN3ModeName()));
            eV.setText(Integer.toString(engineData.getStageN5ModeName()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}