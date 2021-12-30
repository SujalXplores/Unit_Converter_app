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

public class FrequencyActivity extends AppCompatActivity {

    String[] frequency_unit_list = {"Hertz", "KiloHertz", "MegaHertz", "GigaHertz"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequency);

        ActionBar action_bar = getSupportActionBar();
        if(action_bar != null) {
            action_bar.setTitle("Frequency Converter");
            action_bar.setDisplayShowHomeEnabled(true);
            action_bar.setDisplayHomeAsUpEnabled(true);
            action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_500)));
        }

        AutoCompleteTextView autoCompleteTextView_from = findViewById(R.id.autoCompleteTextView_from);
        AutoCompleteTextView autoCompleteTextView_to = findViewById(R.id.autoCompleteTextView_to);

        EditText outlinedTextField_frequency_unit = findViewById(R.id.frequency_units);
        TextView frequency_output_text = findViewById(R.id.textView_frequency_output);

        Button convert_btn = findViewById(R.id.button_convert_frequency);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, frequency_unit_list);

        autoCompleteTextView_from.setAdapter(adapter);
        autoCompleteTextView_to.setAdapter(adapter);

        outlinedTextField_frequency_unit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

                    double enteredUnits = Double.parseDouble(outlinedTextField_frequency_unit.getText().toString());

                    FrequencyConverter.Unit fromUnit1 = FrequencyConverter.Unit.fromString(fromUnit);
                    FrequencyConverter.Unit toUnit1 = FrequencyConverter.Unit.fromString(toUnit);

                    FrequencyConverter converter = new FrequencyConverter(fromUnit1, toUnit1);
                    double result = converter.convert(enteredUnits);

                    CardView output_card = findViewById(R.id.cardView_frequency_output);
                    output_card.setVisibility(View.VISIBLE);
                    frequency_output_text.setText(String.valueOf(result) + " " + toUnit);
                } catch (NumberFormatException e) {
                    outlinedTextField_frequency_unit.setError("Please enter some value!");
                    outlinedTextField_frequency_unit.requestFocus();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getBaseContext(), "Select option from dropdown first!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Error in conversion!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(AreaActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}