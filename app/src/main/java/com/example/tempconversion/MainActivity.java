package com.example.tempconversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private class MainActivityListener implements RadioGroup.OnCheckedChangeListener, TextWatcher {

        private boolean isF;

        public MainActivityListener() {
            isF = true;
        }

        private void calTemp() {
            double f, c;

            try {
                if (isF) {
                    f = Double.parseDouble(inputField.getText().toString());
                    c = (f - 32) * 5 / 9;
                } else {
                    c = Double.parseDouble(inputField.getText().toString());
                    f = c * 9 / 5 + 32;
                }
            }
            catch (Exception e) {
                f = 32;
                c = 0;
            }

            degF.setText(String.format("%.1f", f) + getResources().getString(R.string.charF));
            degC.setText(String.format("%.1f", c) + getResources().getString(R.string.charC));

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
            isF = !isF;
            calTemp();
        }
    }

    private MainActivityListener listener;
    private RadioGroup tempType;
    private TextView degF;
    private TextView degC;
    private TextView inputField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempType = findViewById(R.id.tempType);
        inputField = findViewById(R.id.inputField);
        degF = findViewById(R.id.degF);
        degC = findViewById(R.id.degC);
        listener = new MainActivityListener();

        tempType.setOnCheckedChangeListener(listener);
        inputField.addTextChangedListener(listener);
    }
}
