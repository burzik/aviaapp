package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.my.eduardarefjev.aviaapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	23.10.2017		Eduard Arefjev 		Created "StepControlKND" screen, one of steps
 * 	31.12.2017      Eduard Arefjev      Added writing data to FireBase and send to next view
 * 	31.12.2017      Eduard Arefjev      Added UpdateUI function
 * 	28.01.2018      Eduard Arefjev      Fixed crash for null numbers
 * 	30.01.2018      Eduard Arefjev      Added Readonly mode, menu, fast forwarding
 * 	16.02.2018      Eduard Arefjev      Added autocalculation
 */

public class StepControlKND extends AppCompatActivity {

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
        setContentView(R.layout.linear_step_control_knd);
        this.setTitle(R.string.label_control_knd);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");
        showValues = intent.getBooleanExtra("showValues", false);
        editableValues = intent.getBooleanExtra("editableValues", false);
        classArrayList = (ArrayList<Class>)intent.getSerializableExtra("classMap");

        updateUI();
        nextSecondStep();
        calculateValues();
    }

    public void calculateValues(){
        EditText eNKVDPHY97 = findViewById(R.id.LinearLabelInpNKVDPHY97);
        EditText eNKVDPHY99 = findViewById(R.id.LinearLabelInpNKVDPHY99);
        EditText eNKVDPHY101 = findViewById(R.id.LinearLabelInpNKVDPHY101);
        final EditText eNKNDPHY97 = findViewById(R.id.LinearLabelInpNKNDPHY97);
        final EditText eNKNDPHY99 = findViewById(R.id.LinearLabelInpNKNDPHY99);
        final EditText eNKNDPHY101 = findViewById(R.id.LinearLabelInpNKNDPHY101);
        final EditText eNKNDPLANE97 = findViewById(R.id.LinearLabelInpNKNDPLANE97);
        final EditText eNKNDPLANE99 = findViewById(R.id.LinearLabelInpNKNDPLANE99);
        final EditText eNKNDPLANE101 = findViewById(R.id.LinearLabelInpNKNDPLANE101);

        eNKVDPHY97.setText(String.format("%.2f", CreationHelper.calcControl(engineData.getAtmTemp(), 97.0d)).replace(",","."));
        eNKVDPHY99.setText(String.format("%.2f", CreationHelper.calcControl(engineData.getAtmTemp(), 99.0d)).replace(",","."));
        eNKVDPHY101.setText(String.format("%.2f", CreationHelper.calcControl(engineData.getAtmTemp(), 101.0d)).replace(",","."));

        eNKNDPHY97.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    eNKNDPLANE97.setText(String.format("%.2f", CreationHelper.calcControl(engineData.getAtmTemp(), Double.valueOf(eNKNDPHY97.getText().toString()))).replace(",","."));
                }
            }
        });

        eNKNDPHY99.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    eNKNDPLANE99.setText(String.format("%.2f", CreationHelper.calcControl(engineData.getAtmTemp(), Double.valueOf(eNKNDPHY99.getText().toString()))).replace(",","."));
                }
            }
        });

        eNKNDPHY101.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    eNKNDPLANE101.setText(String.format("%.2f", CreationHelper.calcControl(engineData.getAtmTemp(), Double.valueOf(eNKNDPHY101.getText().toString()))).replace(",","."));
                }
            }
        });
    }

    public void nextSecondStep() {
        Button bNextStep = findViewById(R.id.LinearButtonNextControlKND);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRecord();
                CreationHelper.updateRecord(id, engineData);
                Intent intent;
                if (classArrayList.size() <= 0)
                    intent = new Intent(StepControlKND.this, StepRunoutOfRotors.class);
                else {
                    intent = new Intent(StepControlKND.this, classArrayList.get(0));
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
        EditText eNKVDPHY97 = findViewById(R.id.LinearLabelInpNKVDPHY97);
        EditText eNKVDPHY99 = findViewById(R.id.LinearLabelInpNKVDPHY99);
        EditText eNKVDPHY101 = findViewById(R.id.LinearLabelInpNKVDPHY101);
        EditText eNKNDPHY97 = findViewById(R.id.LinearLabelInpNKNDPHY97);
        EditText eNKNDPHY99 = findViewById(R.id.LinearLabelInpNKNDPHY99);
        EditText eNKNDPHY101 = findViewById(R.id.LinearLabelInpNKNDPHY101);
        EditText eNKNDPLANE97 = findViewById(R.id.LinearLabelInpNKNDPLANE97);
        EditText eNKNDPLANE99 = findViewById(R.id.LinearLabelInpNKNDPLANE99);
        EditText eNKNDPLANE101 = findViewById(R.id.LinearLabelInpNKNDPLANE101);

        if (!eNKVDPHY97.getText().toString().isEmpty())
            engineData.setModeKNDNKVDPHY97(Float.valueOf(eNKVDPHY97.getText().toString()));
        if (!eNKVDPHY99.getText().toString().isEmpty())
            engineData.setModeKNDNKVDPHY99(Float.valueOf(eNKVDPHY99.getText().toString()));
        if (!eNKVDPHY101.getText().toString().isEmpty())
            engineData.setModeKNDNKVDPHY101(Float.valueOf(eNKVDPHY101.getText().toString()));
        if (!eNKNDPHY97.getText().toString().isEmpty())
            engineData.setModeKNDNKNDPHY97(Float.valueOf(eNKNDPHY97.getText().toString()));
        if (!eNKNDPHY99.getText().toString().isEmpty())
            engineData.setModeKNDNKNDPHY99(Float.valueOf(eNKNDPHY99.getText().toString()));
        if (!eNKNDPHY101.getText().toString().isEmpty())
            engineData.setModeKNDNKNDPHY101(Float.valueOf(eNKNDPHY101.getText().toString()));
        if (!eNKNDPLANE97.getText().toString().isEmpty())
            engineData.setModeKNDNKNDPLANE97(Float.valueOf(eNKNDPLANE97.getText().toString()));
        if (!eNKNDPLANE99.getText().toString().isEmpty())
            engineData.setModeKNDNKNDPLANE99(Float.valueOf(eNKNDPLANE99.getText().toString()));
        if (!eNKNDPLANE101.getText().toString().isEmpty())
            engineData.setModeKNDNKNDPLANE101(Float.valueOf(eNKNDPLANE101.getText().toString()));
    }

    public void updateUI(){
        if(showValues){
            EditText eNKVDPHY97 = findViewById(R.id.LinearLabelInpNKVDPHY97);
            EditText eNKVDPHY99 = findViewById(R.id.LinearLabelInpNKVDPHY99);
            EditText eNKVDPHY101 = findViewById(R.id.LinearLabelInpNKVDPHY101);
            EditText eNKNDPHY97 = findViewById(R.id.LinearLabelInpNKNDPHY97);
            EditText eNKNDPHY99 = findViewById(R.id.LinearLabelInpNKNDPHY99);
            EditText eNKNDPHY101 = findViewById(R.id.LinearLabelInpNKNDPHY101);
            EditText eNKNDPLANE97 = findViewById(R.id.LinearLabelInpNKNDPLANE97);
            EditText eNKNDPLANE99 = findViewById(R.id.LinearLabelInpNKNDPLANE99);
            EditText eNKNDPLANE101 = findViewById(R.id.LinearLabelInpNKNDPLANE101);

            if (!editableValues) {
                CreationHelper.makeEditTextReadOnly(eNKVDPHY97);
                CreationHelper.makeEditTextReadOnly(eNKVDPHY99);
                CreationHelper.makeEditTextReadOnly(eNKVDPHY101);
                CreationHelper.makeEditTextReadOnly(eNKNDPHY97);
                CreationHelper.makeEditTextReadOnly(eNKNDPHY99);
                CreationHelper.makeEditTextReadOnly(eNKNDPHY101);
                CreationHelper.makeEditTextReadOnly(eNKNDPLANE97);
                CreationHelper.makeEditTextReadOnly(eNKNDPLANE99);
                CreationHelper.makeEditTextReadOnly(eNKNDPLANE101);
            }

            eNKVDPHY97.setText(Float.toString(engineData.getModeKNDNKVDPHY97()));
            eNKVDPHY99.setText(Float.toString(engineData.getModeKNDNKVDPHY99()));
            eNKVDPHY101.setText(Float.toString(engineData.getModeKNDNKVDPHY101()));
            eNKNDPHY97.setText(Float.toString(engineData.getModeKNDNKNDPHY97()));
            eNKNDPHY99.setText(Float.toString(engineData.getModeKNDNKNDPHY99()));
            eNKNDPHY101.setText(Float.toString(engineData.getModeKNDNKNDPHY101()));
            eNKNDPLANE97.setText(Float.toString(engineData.getModeKNDNKNDPLANE97()));
            eNKNDPLANE99.setText(Float.toString(engineData.getModeKNDNKNDPLANE99()));
            eNKNDPLANE101.setText(Float.toString(engineData.getModeKNDNKNDPLANE101()));
        }
    }

    @Override
    public void onBackPressed() {
    }
}