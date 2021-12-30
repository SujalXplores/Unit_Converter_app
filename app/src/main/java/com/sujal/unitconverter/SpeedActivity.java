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

public class SpeedActivity extends AppCompatActivity {

    String[] speed_unit_list = {"MeterPerSecond", "FootPerSecond", "Knot", "KilometerPerHour", "MilesPerHour"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

        ActionBar action_bar = getSupportActionBar();
        if(action_bar != null) {
            action_bar.setTitle("Speed Converter");
            action_bar.setDisplayShowHomeEnabled(true);
            action_bar.setDisplayHomeAsUpEnabled(true);
            action_bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_500)));
        }

        AutoCompleteTextView autoCompleteTextView_from = findViewById(R.id.autoCompleteTextView_from);
        AutoCompleteTextView autoCompleteTextView_to = findViewById(R.id.autoCompleteTextView_to);

        EditText outlinedTextField_speed_unit = findViewById(R.id.speed_units);
        TextView speed_output_text = findViewById(R.id.textView_speed_output);

        Button convert_btn = findViewById(R.id.button_convert_speed);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, speed_unit_list);

        autoCompleteTextView_from.setAdapter(adapter);
        autoCompleteTextView_to.setAdapter(adapter);

        outlinedTextField_speed_unit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

                    double enteredUnits = Double.parseDouble(outlinedTextField_speed_unit.getText().toString());

                    SpeedConverter.Unit fromUnit1 = SpeedConverter.Unit.fromString(fromUnit);
                    SpeedConverter.Unit toUnit1 = SpeedConverter.Unit.fromString(toUnit);

                    SpeedConverter converter = new SpeedConverter(fromUnit1, toUnit1);
                    double result = converter.convert(enteredUnits);

                    CardView output_card = findViewById(R.id.cardView_speed_output);
                    output_card.setVisibility(View.VISIBLE);
                    speed_output_text.setText(String.valueOf(result) + " " + toUnit);
                } catch (NumberFormatException e) {
                    outlinedTextField_speed_unit.setError("Please enter some value!");
                    outlinedTextField_speed_unit.requestFocus();
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