package com.example.tempconversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private class MainActivityListener implements RadioGroup.OnCheckedChangeListener, TextWatcher {

        private int tmpType;
        //1 means F, 0 means C, -1 means K

        public MainActivityListener() {
            tmpType = 1;
        }

        private void calTemp() {
            double f, c, k;

            try {
                if (tmpType == 1) {
                    f = Double.parseDouble(inputField.getText().toString());
                    c = (f - 32) * 5 / 9;
                } else if (tmpType == 0){
                    c = Double.parseDouble(inputField.getText().toString());
                    f = c * 9 / 5 + 32;
                }
                else {
                    k = Double.parseDouble(inputField.getText().toString());
                    c = k - 273.15;
                    f = c * 9 / 5 + 32;
                }
            }
            catch (Exception e) {
                f = 32;
                c = 0;
            }
            k = c + 273.15;

            degF.setText(String.format("%.1f", f) + getResources().getString(R.string.charF));
            degC.setText(String.format("%.1f", c) + getResources().getString(R.string.charC));
            degK.setText(String.format("%.1f", k) + getResources().getString(R.string.charK));

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            calTemp();
        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.F)
                tmpType = 1;
            else if (i == R.id.C)
                tmpType = 0;
            else
                tmpType = -1;

            calTemp();
        }
    }

    private MainActivityListener listener;
    private RadioGroup tempType;
    private TextView degF;
    private TextView degC;
    private TextView degK;
    private TextView inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempType = findViewById(R.id.tempType);
        inputField = findViewById(R.id.inputField);
        degF = findViewById(R.id.degF);
        degC = findViewById(R.id.degC);
        degK = findViewById(R.id.degK);
        listener = new MainActivityListener();

        tempType.setOnCheckedChangeListener(listener);
        inputField.addTextChangedListener(listener);
    }
}
