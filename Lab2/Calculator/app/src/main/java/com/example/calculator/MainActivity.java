package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator;
    private TextView text;
    private EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] numberIds = new int[]{
                R.id.zero,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine,
                R.id.comma,
                R.id.clear
        };

        int[] actionIds = new int[]{
                R.id.plus,
                R.id.minus,
                R.id.multiply,
                R.id.division,
                R.id.equals,
        };

        calculator = new Calculator();
        text = findViewById(R.id.text);
//        ed = findViewById(R.id.viewId);
        View.OnClickListener numberButtonClickListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                calculator.onNumPressed(v.getId());
                text.setText(calculator.getText());
            }
        };

        View.OnClickListener actionButtonClickListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                calculator.onActionPressed(v.getId());
                text.setText(calculator.getText());

            }
        };

        View.OnClickListener clearButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.clearBox(v.getId());
            }
        };
        findViewById(R.id.clear).setOnClickListener(clearButtonClickListener);

        for (int i = 0; i < numberIds.length; i++){
            findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }

        for (int i = 0; i < actionIds.length; i++){
            findViewById(actionIds[i]).setOnClickListener(actionButtonClickListener);
        }

//        View.OnClickListener checkId = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                calculator.onNumPressed(v.getId());
//                ed.setText(v.getId());
//            }
//        };
//        ed.setText(numberIds[1]);
    }
}
