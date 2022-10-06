package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tipcalculator.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String stringInTextField;
    Double cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTip();
            }
        });
    }

    private void calculateTip(){
        stringInTextField = binding.costOfServiceEditText.getText().toString();
        cost = Double.parseDouble(binding.costOfServiceEditText.getText().toString());

        double tipPercentage;
        if (binding.tipOptions.getCheckedRadioButtonId() == R.id.option_twenty_percent){
            tipPercentage = 0.20;
        }
        else if (binding.tipOptions.getCheckedRadioButtonId() == R.id.option_fifteen_percent){
            tipPercentage = 0.15;
        }
        else if (binding.tipOptions.getCheckedRadioButtonId() == R.id.option_eighteen_percent){
            tipPercentage = 0.18;
        }
        else{
            tipPercentage = 0.0;
        }

        double tip = tipPercentage * cost;
        if (binding.roundUpSwitch.isChecked()){
            tip = Math.ceil(tip);
        }

        displayTip(tip);
    }

    private void displayTip(double tip){
        String formattedTip = NumberFormat.getCurrencyInstance().format(tip);
        binding.tipResult.setText(formattedTip);
    }

}