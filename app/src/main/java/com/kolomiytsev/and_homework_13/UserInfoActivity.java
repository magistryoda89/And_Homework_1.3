package com.kolomiytsev.and_homework_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kolomiytsev.and_homework_13.health.User;

public class UserInfoActivity extends AppCompatActivity {

    private static final String TAG = "UI_Activity";
    Spinner spinner;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Integer[] years = new Integer[] {5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,
                26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,
                51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,
                76,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100};

        spinner = findViewById(R.id.spin_age);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(UserInfoActivity.this, android.R.layout.simple_spinner_item, years);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        btnSaveUserInfoClick();
        btnPressureClick();
        btnVitalityClick();
    }

    private void btnSaveUserInfoClick() {
        final EditText etFio = findViewById(R.id.et_fio);
        final Spinner spinAge = findViewById(R.id.spin_age);
        Button btnSave = findViewById(R.id.btn_save_user);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Нажатие на кнопку 'Сохранить пользователя'");
                if (!etFio.getText().toString().equals("")) {
                    user = new User(etFio.getText().toString(), (int) spinAge.getSelectedItem());
                    Toast.makeText(UserInfoActivity.this, getString(R.string.user_info_saved) + user, Toast.LENGTH_LONG).show();
                    etFio.setText("");
                } else {
                    Toast.makeText(UserInfoActivity.this, R.string.empty_fio_error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void btnPressureClick() {
        Button btnPressure = findViewById(R.id.btn_pressure);
        btnPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Нажатие на кнопку 'Давление'");
                Intent intent = new Intent(UserInfoActivity.this, PressureActivity.class);
                startActivity(intent);
            }
        });
    }

    private void btnVitalityClick() {
        Button btnVitality = findViewById(R.id.btn_vitality);
        btnVitality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Нажатие на кнопку 'Общие показатели'");
                Intent intent = new Intent(UserInfoActivity.this, VitalityActivity.class);
                startActivity(intent);
            }
        });
    }

}
