package com.kolomiytsev.and_homework_13;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kolomiytsev.and_homework_13.health.Vitality;

import java.util.TreeSet;

public class VitalityActivity extends AppCompatActivity {
    private static final String TAG = "Vitality_Activity";

    EditText etWeigth;
    EditText etSteps;
    Button btnSaveVitality;
    TreeSet<Vitality> vitalities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitality);
        setTitle(R.string.vitality_title);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        vitalities = new TreeSet<>();

        btnSaveVitalityClick();
    }

    private void btnSaveVitalityClick() {
        etWeigth = findViewById(R.id.et_weight);
        etSteps = findViewById(R.id.et_steps);
        btnSaveVitality = findViewById(R.id.btn_save_vitality);

        btnSaveVitality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Нажатие на кнопку 'Сохранить показатели'");
                if (etWeigth.getText().toString().equals("") || etSteps.getText().toString().equals("")) {
                    Toast.makeText(VitalityActivity.this, R.string.empty_field_error, Toast.LENGTH_LONG).show();
                } else {
                    float weight;
                    int steps;
                    try {
                        weight = Float.parseFloat(etWeigth.getText().toString());
                        steps = Integer.parseInt(etSteps.getText().toString());
                        if (weight <= 0) {
                            Toast.makeText(VitalityActivity.this, R.string.weight_low_error, Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (steps < 0) {
                            Toast.makeText(VitalityActivity.this, R.string.steps_below_0_error, Toast.LENGTH_LONG).show();
                            return;
                        }
                        Vitality vitality = new Vitality(weight, steps);
                        if (vitalities.add(vitality)) {
                            Toast.makeText(VitalityActivity.this, getString(R.string.vitality_saved) + vitality, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(VitalityActivity.this, R.string.not_saved_error, Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Ошибка записи показателей в коллекцию");
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(VitalityActivity.this, R.string.numeric_field_error, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "Нажатие на кнопку 'Назад'");
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
