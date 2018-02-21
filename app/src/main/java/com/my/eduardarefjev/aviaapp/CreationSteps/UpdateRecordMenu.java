package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.my.eduardarefjev.aviaapp.MainActivity;
import com.my.eduardarefjev.aviaapp.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	30.01.2018		Eduard Arefjev 		Created to show only selected steps
 */

public class UpdateRecordMenu extends AppCompatActivity {
    String id;
    StepEngineData engineData;
    ArrayList<Class> classArrayList = new ArrayList<>();
    LinkedHashMap<Class, Boolean> linkedHashMap = new LinkedHashMap<>();


    boolean showValues = true;
    boolean editableValues = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_records_menu);

        linkedHashMap.put(DetailedRecordInfo.class, false);
        linkedHashMap.put(StepStartInfo.class, false);
        linkedHashMap.put(StepSmallGas.class, false);
        linkedHashMap.put(StepClosingKVD5.class, false);
        linkedHashMap.put(StepN85.class, false);
        linkedHashMap.put(StepClosingKVD3.class, false);
        linkedHashMap.put(Step085Nom.class, false);
        linkedHashMap.put(StepN100.class, false);
        linkedHashMap.put(StepNom.class, false);
        linkedHashMap.put(StepMax.class, false);
        linkedHashMap.put(StepClosingKVDKPV.class, false);
        linkedHashMap.put(StepPickUp.class, false);
        linkedHashMap.put(StepSmallGas2.class, false);
        linkedHashMap.put(StepControlKND.class, false);
        linkedHashMap.put(StepRunoutOfRotors.class, false);
        linkedHashMap.put(StepTanningBoardDisplayGenerator.class, false);
        linkedHashMap.put(StepFinish.class, false);
        linkedHashMap.put(MainActivity.class, true);

        //Intent intent = getIntent();
        //id = intent.getStringExtra("recordId");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        UpdateSteps();
    }

    public void UpdateSteps() {
        Button bNextStep = findViewById(R.id.LinearMenuRecordsNext);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = CreationHelper.createRecord(engineData);
                for (Map.Entry<Class, Boolean> entry : linkedHashMap.entrySet()) {
                    if (entry.getValue())
                        classArrayList.add(entry.getKey());
                }
                Intent intent = new Intent(UpdateRecordMenu.this, classArrayList.get(0));
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

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox_base_values:
                if (checked)
                    linkedHashMap.put(DetailedRecordInfo.class, true);
                else
                    linkedHashMap.put(DetailedRecordInfo.class, false);
                break;
            case R.id.checkbox_base_values_2:
                if (checked)
                    linkedHashMap.put(StepStartInfo.class, true);
                else
                    linkedHashMap.put(StepStartInfo.class, false);
                break;
            case R.id.checkbox_small_gas:
                if (checked)
                    linkedHashMap.put(StepSmallGas.class, true);
                else
                    linkedHashMap.put(StepSmallGas.class, false);
                break;
            case R.id.checkbox_turnover_kvd_v:
                if (checked)
                    linkedHashMap.put(StepClosingKVD5.class, true);
                else
                    linkedHashMap.put(StepClosingKVD5.class, false);
                break;
            case R.id.checkbox_n1_equals_85:
                if (checked)
                    linkedHashMap.put(StepN85.class, true);
                else
                    linkedHashMap.put(StepN85.class, false);
                break;
            case R.id.checkbox_turnover_kvd_iii:
                if (checked)
                    linkedHashMap.put(StepClosingKVD3.class, true);
                else
                    linkedHashMap.put(StepClosingKVD3.class, false);
                break;
            case R.id.checkbox_work_0nominal:
                if (checked)
                    linkedHashMap.put(Step085Nom.class, true);
                else
                    linkedHashMap.put(Step085Nom.class, false);
                break;
            case R.id.checkbox_n1_100:
                if (checked)
                    linkedHashMap.put(StepNom.class, true);
                else
                    linkedHashMap.put(StepNom.class, false);
                break;
            case R.id.checkbox_nominal:
                if (checked)
                    linkedHashMap.put(StepNom.class, true);
                else
                    linkedHashMap.put(StepNom.class, false);
                break;
            case R.id.checkbox_max:
                if (checked)
                    linkedHashMap.put(StepMax.class, true);
                else
                    linkedHashMap.put(StepMax.class, false);
                break;
            case R.id.checkbox_turnover_kvd_n:
                if (checked)
                    linkedHashMap.put(StepClosingKVDKPV.class, true);
                else
                    linkedHashMap.put(StepClosingKVDKPV.class, false);
                break;
            case R.id.checkbox_pick_up:
                if (checked)
                    linkedHashMap.put(StepPickUp.class, true);
                else
                    linkedHashMap.put(StepPickUp.class, false);
                break;
            case R.id.checkbox_small_gas_2:
                if (checked)
                    linkedHashMap.put(StepSmallGas2.class, true);
                else
                    linkedHashMap.put(StepSmallGas2.class, false);
                break;
            case R.id.checkbox_control_knd:
                if (checked)
                    linkedHashMap.put(StepControlKND.class, true);
                else
                    linkedHashMap.put(StepControlKND.class, false);
                break;
            case R.id.checkbox_runtime_of_rotors:
                if (checked)
                    linkedHashMap.put(StepRunoutOfRotors.class, true);
                else
                    linkedHashMap.put(StepRunoutOfRotors.class, false);
                break;
            case R.id.checkbox_board_generator:
                if (checked)
                    linkedHashMap.put(StepTanningBoardDisplayGenerator.class, true);
                else
                    linkedHashMap.put(StepTanningBoardDisplayGenerator.class, false);
                break;
            case R.id.checkbox_final_data:
                if (checked)
                    linkedHashMap.put(StepFinish.class, true);
                else
                    linkedHashMap.put(StepFinish.class, false);
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_yes:
                if (checked)
                    showValues = true;
                break;
            case R.id.radio_no:
                if (checked)
                    showValues = false;
                break;
        }
    }
}
