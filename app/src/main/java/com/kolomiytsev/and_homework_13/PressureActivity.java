package com.kolomiytsev.and_homework_13;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.kolomiytsev.and_homework_13.health.Pressure;

import java.util.TreeSet;

public class PressureActivity extends AppCompatActivity {
    private static final String TAG = "Pressure_Activity";

    EditText etPressureDown;
    EditText etPressureUp;
    EditText etPulse;
    CheckBox cbTachycardia;
    Button btnSavePressure;

    TreeSet<Pressure> pressures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);
        setTitle(R.string.pressure_title);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        pressures = new TreeSet<>();

        btnSavePressureClick();
    }

    private void btnSavePressureClick() {
        etPressureDown = findViewById(R.id.et_pressure_down);
        etPressureUp = findViewById(R.id.et_pressure_up);
        etPulse = findViewById(R.id.et_pulse);
        cbTachycardia = findViewById(R.id.cb_tachycardia);
        btnSavePressure = findViewById(R.id.btn_save_pressure);

        btnSavePressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Нажатие на кнопку 'Сохранить показатели давления'");
                if (etPressureDown.getText().toString().equals("") ||
                        etPressureUp.getText().toString().equals("") ||
                        etPulse.getText().toString().equals("")) {
                    Toast.makeText(PressureActivity.this, R.string.empty_field_error, Toast.LENGTH_LONG).show();
                    return;
                }
                int pressureUp, pressureDown, pulse;
                try {
                    pressureDown = Integer.parseInt(etPressureDown.getText().toString());
                    pressureUp = Integer.parseInt(etPressureUp.getText().toString());
                    pulse = Integer.parseInt(etPulse.getText().toString());
                    if (pressureDown <= 0) {
                        Toast.makeText(PressureActivity.this, R.string.pressure_down_low_error, Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (pressureUp <= 0) {
                        Toast.makeText(PressureActivity.this, R.string.pressure_up_low_error, Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (pulse <= 0) {
                        Toast.makeText(PressureActivity.this, R.string.pulse_low_error, Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (pressureUp <= pressureDown) {
                        Toast.makeText(PressureActivity.this, R.string.pressure_updown_error, Toast.LENGTH_LONG).show();
                        return;
                    }
                    Pressure pressure = new Pressure(pressureDown, pressureUp, pulse, cbTachycardia.isChecked());
                    if (pressures.add(pressure)) {
                        Toast.makeText(PressureActivity.this, getString(R.string.pressure_saved) + pressure, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(PressureActivity.this, R.string.not_saved_error, Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Ошибка записи показателей в коллекцию");
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(PressureActivity.this, R.string.numeric_field_error, Toast.LENGTH_LONG).show();
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
