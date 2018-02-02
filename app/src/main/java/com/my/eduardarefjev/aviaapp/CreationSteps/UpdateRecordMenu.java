package com.my.eduardarefjev.aviaapp.CreationSteps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.my.eduardarefjev.aviaapp.R;

import java.util.HashMap;

/**
 * HISTORY
 * 	Date			Author				Comments
 * 	30.01.2018		Eduard Arefjev 		Created to show only selected steps
 */

public class UpdateRecordMenu extends AppCompatActivity {
    String id;
    StepEngineData engineData;
    HashMap<String, Boolean> hashMap = new HashMap<>();
    boolean showValues = true;
    boolean editableValues = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_records_menu);

        hashMap.put("checkbox_base_values", false);
        hashMap.put("checkbox_base_values_2", false);
        hashMap.put("checkbox_small_gas", false);
        hashMap.put("checkbox_turnover_kvd_v", false);
        hashMap.put("checkbox_n1_equals_85", false);
        hashMap.put("checkbox_turnover_kvd_iii", false);
        hashMap.put("checkbox_work_0nominal", false);
        hashMap.put("checkbox_nominal", false);
        hashMap.put("checkbox_max", false);
        hashMap.put("checkbox_turnover_kvd_n", false);
        hashMap.put("checkbox_pick_up", false);
        hashMap.put("checkbox_small_gas_2", false);
        hashMap.put("checkbox_control_knd", false);
        hashMap.put("checkbox_runtime_of_rotors", false);
        hashMap.put("checkbox_board_generator", false);
        hashMap.put("checkbox_final_data", false);

        Intent intent = getIntent();
        id = intent.getStringExtra("recordId");
        Bundle extra = getIntent().getBundleExtra("extra");
        engineData  = extra.getParcelable("objects");

        UpdateSteps();
    }

    public void UpdateSteps() {
        Button bNextStep = findViewById(R.id.LinearMenuRecordsNext);
        bNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreationHelper.updateRecord(id, engineData);
                Intent intent = new Intent(UpdateRecordMenu.this, DetailedRecordInfo.class);
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

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox_base_values:
                if (checked)
                    hashMap.put("checkbox_base_values", true);
                else
                    hashMap.put("checkbox_base_values", false);
                break;
            case R.id.checkbox_base_values_2:
                if (checked)
                    hashMap.put("checkbox_base_values_2", true);
                else
                    hashMap.put("checkbox_base_values_2", false);
                break;
            case R.id.checkbox_small_gas:
                if (checked)
                    hashMap.put("checkbox_small_gas", true);
                else
                    hashMap.put("checkbox_small_gas", false);
                break;
            case R.id.checkbox_turnover_kvd_v:
                if (checked)
                    hashMap.put("checkbox_turnover_kvd_v", true);
                else
                    hashMap.put("checkbox_turnover_kvd_v", false);
                break;
            case R.id.checkbox_n1_equals_85:
                if (checked)
                    hashMap.put("checkbox_n1_equals_85", true);
                else
                    hashMap.put("checkbox_n1_equals_85", false);
                break;
            case R.id.checkbox_turnover_kvd_iii:
                if (checked)
                    hashMap.put("checkbox_turnover_kvd_iii", true);
                else
                    hashMap.put("checkbox_turnover_kvd_iii", false);
                break;
            case R.id.checkbox_work_0nominal:
                if (checked)
                    hashMap.put("checkbox_work_0nominal", true);
                else
                    hashMap.put("checkbox_work_0nominal", false);
                break;
            case R.id.checkbox_nominal:
                if (checked)
                    hashMap.put("checkbox_nominal", true);
                else
                    hashMap.put("checkbox_nominal", false);
                break;
            case R.id.checkbox_max:
                if (checked)
                    hashMap.put("checkbox_max", true);
                else
                    hashMap.put("checkbox_max", false);
                break;
            case R.id.checkbox_turnover_kvd_n:
                if (checked)
                    hashMap.put("checkbox_turnover_kvd_n", true);
                else
                    hashMap.put("checkbox_turnover_kvd_n", false);
                break;
            case R.id.checkbox_pick_up:
                if (checked)
                    hashMap.put("checkbox_pick_up", true);
                else
                    hashMap.put("checkbox_pick_up", false);
                break;
            case R.id.checkbox_small_gas_2:
                if (checked)
                    hashMap.put("checkbox_small_gas_2", true);
                else
                    hashMap.put("checkbox_small_gas_2", false);
                break;
            case R.id.checkbox_control_knd:
                if (checked)
                    hashMap.put("checkbox_control_knd", true);
                else
                    hashMap.put("checkbox_control_knd", false);
                break;
            case R.id.checkbox_runtime_of_rotors:
                if (checked)
                    hashMap.put("checkbox_runtime_of_rotors", true);
                else
                    hashMap.put("checkbox_runtime_of_rotors", false);
                break;
            case R.id.checkbox_board_generator:
                if (checked)
                    hashMap.put("checkbox_board_generator", true);
                else
                    hashMap.put("checkbox_board_generator", false);
                break;
            case R.id.checkbox_final_data:
                if (checked)
                    hashMap.put("checkbox_final_data", true);
                else
                    hashMap.put("checkbox_final_data", false);
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
