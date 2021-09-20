package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton maleBtn;
    private RadioButton femaleBtn;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private Button calculateBtn;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllViews();
        setBtnListener();

    }

    private void setBtnListener() {
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmi = calculatBmi();
                String ageText = age.getText().toString();
                int age = Integer.parseInt(ageText);
                
                if(age >= 18)  displayResult(bmi);
                else displayGuidance(bmi);
                
            }
        });
    }

    private void displayGuidance(double bmi) {

        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        String bmiText = decimalFormatter.format(bmi);

        String fullResult = bmiText + " ";
        
        if(maleBtn.isChecked()) fullResult += "- Since you're under 18, kindly consult with your doctor for the healthy range for boys.";
        else if(femaleBtn.isChecked()) fullResult += "- Since you're under 18, kindly consult with your doctor for the healthy range for girls.";
        else fullResult += "- Since you're under 18, kindly consult with your doctor for the healthy range.";

        resultText.setText(fullResult);
    }

    private double calculatBmi() {
        
        String feetText = feet.getText().toString();
        String inchesText = inches.getText().toString();
        String weightText = weight.getText().toString();
        
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;

        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        String bmiText = decimalFormatter.format(bmi);

        String fullResult = bmiText + " ";

        if(bmi < 18.5) fullResult += "- You're underweight!";
        else if(bmi > 25) fullResult += "- You're overweight!";
        else fullResult += "- You're a healthy weight!";

        resultText.setText(fullResult);
    }

    private void findAllViews() {
        maleBtn = findViewById(R.id.radio_button_male);
        femaleBtn = findViewById(R.id.radio_button_female);

        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_weight);

        calculateBtn = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }
}