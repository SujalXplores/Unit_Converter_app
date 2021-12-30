package com.sujal.unitconverter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TemperatureActivity extends AppCompatActivity {

    String[] temperature_unit_list = {"Celsius", "Fahrenheit", "Kelvin", "Rankine", "Reaumur"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        ActionBar action_bar = getSupportActionBar();
        if(action_bar != null) {
            action_bar.setTitle("Temperature Converter");
            action_bar.setDisplayShowHomeEnabled(true);
            action_bar.setDisplayHomeAsUpEnabled(true);
            action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_500)));
        }

        AutoCompleteTextView autoCompleteTextView_from = findViewById(R.id.autoCompleteTextView_from);
        AutoCompleteTextView autoCompleteTextView_to = findViewById(R.id.autoCompleteTextView_to);

        EditText outlinedTextField_temperature_unit = findViewById(R.id.temperature_units);
        TextView temperature_output_text = findViewById(R.id.textView_temperature_output);

        Button convert_btn = findViewById(R.id.button_convert_temperature);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, temperature_unit_list);

        autoCompleteTextView_from.setAdapter(adapter);
        autoCompleteTextView_to.setAdapter(adapter);

        outlinedTextField_temperature_unit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

                    double enteredUnits = Double.parseDouble(outlinedTextField_temperature_unit.getText().toString());

                    TemperatureConverter.Unit fromUnit1 = TemperatureConverter.Unit.fromString(fromUnit);
                    TemperatureConverter.Unit toUnit1 = TemperatureConverter.Unit.fromString(toUnit);

                    TemperatureConverter converter = new TemperatureConverter(fromUnit1, toUnit1);
                    double result = converter.convert(enteredUnits);

                    CardView output_card = findViewById(R.id.cardView_temperature_output);
                    output_card.setVisibility(View.VISIBLE);
                    temperature_output_text.setText(String.valueOf(result) + " " + toUnit);
                } catch (NumberFormatException e) {
                    outlinedTextField_temperature_unit.setError("Please enter some value!");
                    outlinedTextField_temperature_unit.requestFocus();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getBaseContext(), "Select option from dropdown first!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Error in conversion!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(AreaActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}