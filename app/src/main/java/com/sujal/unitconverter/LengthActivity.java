package com.sujal.unitconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LengthActivity extends AppCompatActivity {

    String[] length_unit_list = {"Inch", "Centimeter", "Meter", "Mile", "Kilometer", "Foot", "Yard"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        AutoCompleteTextView autoCompleteTextView_from = findViewById(R.id.autoCompleteTextView_from);
        AutoCompleteTextView autoCompleteTextView_to = findViewById(R.id.autoCompleteTextView_to);

        EditText outlinedTextField_length_unit = findViewById(R.id.length_units);
        TextView length_output_text = findViewById(R.id.textView_length_output);

        Button convert_btn = findViewById(R.id.button_convert_length);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, length_unit_list);

        autoCompleteTextView_from.setAdapter(adapter);
        autoCompleteTextView_to.setAdapter(adapter);

        outlinedTextField_length_unit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        convert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String fromUnit = autoCompleteTextView_from.getText().toString();
                    String toUnit = autoCompleteTextView_to.getText().toString();

                    double enteredUnits = Double.parseDouble(outlinedTextField_length_unit.getText().toString());

                    AreaConverter.Unit fromUnit1 = AreaConverter.Unit.fromString(fromUnit);
                    AreaConverter.Unit toUnit1 = AreaConverter.Unit.fromString(toUnit);

                    AreaConverter converter = new AreaConverter(fromUnit1, toUnit1);
                    double result = converter.convert(enteredUnits);
                    length_output_text.setText(String.valueOf(result));
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Invalid input entered !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(AreaActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}