package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class resultActivity extends AppCompatActivity {

    private String name, gender,bmiS;
    private int height,weight,age,weightDiff;
    private double bmi;
    private TextView mainT,bmiRange, bmiRes,comment;
    private Button recalcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        findAllViews();
        getBMIinfo();
        calculateBMI();
        displayResult();
        recalcBtn.setOnClickListener(v -> returnToSecondActivity());
    }

    private void displayResult() {
        mainT.setText("Your result, " + name + "!");
        if(bmi < 18.5)  displayUnderweightResult();
        else if(bmi > 25) displayOverweightResult();
        else displayNormalResult();
    }

    private void displayNormalResult() {
        bmiRange.setText("Normal");
        bmiRange.setTextColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
        bmiRes.setText(bmiS);
        comment.setText("You've a normal body weight, good job!");
    }

    private void displayOverweightResult() {
        bmiRange.setText("Overweight");
        bmiRange.setTextColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
        bmiRes.setText(bmiS);
        weightDiff = weight - (int) (25 * (height * height / 10000.0));
        comment.setText("You need to lose " + weightDiff + " kilos to have normal BMI.");
    }

    private void displayUnderweightResult() {
        bmiRange.setText("Underweight");
        bmiRange.setTextColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
        bmiRes.setText(bmiS);
        weightDiff = (int) (18.5 * (height * height / 10000.0)) - weight;
        comment.setText("You need to gain " + weightDiff + " kilos to have normal BMI.");
    }

    private void calculateBMI() {
        bmi = weight / ((height * height) / 10000.0);
        System.out.println(bmi);
        DecimalFormat formatter = new DecimalFormat("0.00");
        bmiS = formatter.format(bmi);
    }

    private void returnToSecondActivity() {
        Intent intent = new Intent(this, secondActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    };

    private void getBMIinfo() {
        BMIinfo info = getIntent().getParcelableExtra("info");
        name = info.getName();
        gender = info.getGender();
        height = info.getHeight();
        weight = info.getWeight();
        age = info.getAge();
    }

    private void findAllViews() {
        mainT = findViewById(R.id.result_t);
        bmiRange = findViewById(R.id.bmi_range);
        bmiRes = findViewById(R.id.bmi);
        comment = findViewById(R.id.comment);
        recalcBtn = findViewById(R.id.recalculate_btn);
    }
}