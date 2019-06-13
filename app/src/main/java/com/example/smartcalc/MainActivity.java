package com.example.smartcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_PLUS = "plus";
    private static final String ACTION_MINUS = "minus";
    private static final String ACTION_MULTIPLY = "multiply";
    private static final String ACTION_DIVIDE = "divide";

    public static final String APP_PREFERENCES = "cache";
    public static final String APP_PREFERENCES_EXPRESSION = "expression";

    SharedPreferences cache;

    private EditText result;

    private String action = "default";

    private ArrayList<Double> expressions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cache = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        result = findViewById(R.id.result);
    }

    public void onCalcClick(View view) {
        String expression = result.getText().toString();
        switch (view.getId()) {
            case R.id.one:
                result.setText(result.getText() + "1");
                break;
            case R.id.two:
                result.setText(result.getText() + "2");
                break;
            case R.id.three:
                result.setText(result.getText() + "3");
                break;
            case R.id.four:
                result.setText(result.getText() + "4");
                break;
            case R.id.five:
                result.setText(result.getText() + "5");
                break;
            case R.id.six:
                result.setText(result.getText() + "6");
                break;
            case R.id.seven:
                result.setText(result.getText() + "7");
                break;
            case R.id.eight:
                result.setText(result.getText() + "8");
                break;
            case R.id.nine:
                result.setText(result.getText() + "9");
                break;
            case R.id.zero:
                result.setText(result.getText() + "0");
                break;
            case R.id.plus:
                if (!expression.isEmpty()) {
                    expressions.add(Double.parseDouble(expression));
                    action = ACTION_PLUS;
                    result.setText("");
                }
                break;
            case R.id.minus:
                if (!expression.isEmpty()) {
                    expressions.add(Double.parseDouble(expression));
                    action = ACTION_MINUS;
                    result.setText("");
                }
                break;
            case R.id.multiply_:
                if (!expression.isEmpty()) {
                    expressions.add(Double.parseDouble(expression));
                    action = ACTION_MULTIPLY;
                    result.setText("");
                }
                break;
            case R.id.divide:
                if (!expression.isEmpty()) {
                    expressions.add(Double.parseDouble(expression));
                    action = ACTION_DIVIDE;
                    result.setText("");
                }
                break;
            case R.id.percent:
                if (!expression.isEmpty()) {
                    result.setText(String.valueOf(percent(Double.parseDouble(expression))));
                }
                break;
            case R.id.plusminus:
                if (!expression.isEmpty()) {
                    result.setText(String.valueOf(plusminus(Double.parseDouble(expression))));
                }
                break;
            case R.id.dot:
                if (!expression.isEmpty()) {
                    result.setText(result.getText() + ".");
                }
                break;
            case R.id.clean:
                result.setText("");
                break;
            case R.id.cleanAll:
                result.setText("");
                expressions.clear();
                break;
            case R.id.equals:
                result(action);
                break;
            case R.id.history:
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
        }
    }

    private void result(String action) {
        String expression = result.getText().toString();
        String cache = "";
        if (!expression.isEmpty()) {
            switch (action) {
                case ACTION_PLUS:
                    expressions.add(Double.parseDouble(expression));
                    Double sum = sum();
                    for (int i = 0; i < expressions.size(); i++) {
                        if (i == expressions.size() - 1) {
                            cache = cache.concat(expressions.get(i) + "");
                        } else {
                            cache = cache.concat(expressions.get(i) + " + ");
                        }
                    }
                    saveCache(cache + " = " + sum);
                    expressions.clear();
                    expressions.add(sum);
                    result.setText(String.valueOf(expressions.get(0)));
                    break;
                case ACTION_MINUS:
                    expressions.add(Double.parseDouble(expression));
                    Double minus = minus();
                    for (int i = 0; i < expressions.size(); i++) {
                        if (i == expressions.size() - 1) {
                            cache = cache.concat(expressions.get(i) + "");
                        } else {
                            cache = cache.concat(expressions.get(i) + " - ");
                        }
                    }
                    saveCache(cache + " = " + minus);
                    expressions.clear();
                    expressions.add(minus);
                    result.setText(String.valueOf(expressions.get(0)));
                    break;
                case ACTION_MULTIPLY:
                    expressions.add(Double.parseDouble(expression));
                    Double multiply = multiply();
                    for (int i = 0; i < expressions.size(); i++) {
                        if (i == expressions.size() - 1) {
                            cache = cache.concat(expressions.get(i) + "");
                        } else {
                            cache = cache.concat(expressions.get(i) + " * ");
                        }
                    }
                    saveCache(cache + " = " + multiply);
                    expressions.clear();
                    expressions.add(multiply);
                    result.setText(String.valueOf(expressions.get(0)));
                    break;
                case ACTION_DIVIDE:
                    expressions.add(Double.parseDouble(expression));
                    Double divide = divide();
                    for (int i = 0; i < expressions.size(); i++) {
                        if (i == expressions.size() - 1) {
                            cache = cache.concat(expressions.get(i) + "");
                        } else {
                            cache = cache.concat(expressions.get(i) + " / ");
                        }
                    }
                    saveCache(cache + " = " + divide);
                    expressions.clear();
                    expressions.add(divide);
                    result.setText(String.valueOf(expressions.get(0)));
                    break;
                default:
                    break;
            }
        }
    }

    private Double sum() {
        double sum = 0;
        for (int i = 0; i < expressions.size(); i++)
            sum += expressions.get(i);
        return sum;
    }

    private Double minus() {
        double minus = expressions.get(0);
        for (int i = 1; i < expressions.size(); i++)
            minus -= expressions.get(i);
        return minus;
    }

    private Double multiply() {
        double multiply = 1;
        for (int i = 0; i < expressions.size(); i++)
            multiply *= expressions.get(i);
        return multiply;
    }

    private Double divide() {
        double multiply = expressions.get(0);
        for (int i = 1; i < expressions.size(); i++)
            multiply /= expressions.get(i);
        return multiply;
    }

    private Double percent(Double num) {
        return num / 100;
    }

    private Double plusminus(Double num) {
        return num * -1;
    }

    private void saveCache(String ex) {
        SharedPreferences.Editor editor = cache.edit();
        editor.putString(APP_PREFERENCES_EXPRESSION, ex);
        editor.apply();
    }
}
