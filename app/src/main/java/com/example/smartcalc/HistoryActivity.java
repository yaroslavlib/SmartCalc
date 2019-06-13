package com.example.smartcalc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.smartcalc.MainActivity.APP_PREFERENCES;
import static com.example.smartcalc.MainActivity.APP_PREFERENCES_EXPRESSION;

public class HistoryActivity extends AppCompatActivity {

    private TextView expression;

    SharedPreferences cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        cache = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        expression = findViewById(R.id.expression);
        expression.setText(cache.getString(APP_PREFERENCES_EXPRESSION, "Нет результатов"));

    }
}
