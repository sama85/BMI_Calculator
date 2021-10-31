package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class secondActivity extends AppCompatActivity {

    private CardView maleCard, femaleCard;
    private SeekBar heightBar;
    private int height, weight, age;
    private TextView heightText;
    private EditText weightEt, ageEt;
    private Button calculateBtn;
    private String name, weightS, ageS,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findAllViews();
        name = getIntent().getStringExtra("name");
        maleCard.setOnClickListener(v -> checkAsMale());
        femaleCard.setOnClickListener(v -> checkAsFemale());
        heightBar.setMax(250);
        heightBar.setProgress(170);
        setHeightBarListener();
        calculateBtn.setOnClickListener(v -> sendBMIinfo());
    }

    private void sendBMIinfo() {
        weightS = weightEt.getText().toString().trim();
        ageS = ageEt.getText().toString().trim();
        if (gender == null) showToast("Please select your gender");
        else if (weightS.length() == 0 || Integer.parseInt(weightS) < 10) showToast("Please enter a valid weight");
        else if (ageS.length() == 0 || Integer.parseInt(ageS) < 5) showToast("Please enter a valid age");
        else if(height < 50) showToast("Please enter a valid height");
        else {
            weight = Integer.parseInt(weightS);
            age = Integer.parseInt(ageS);
            BMIinfo info = new BMIinfo(name, gender,height, weight,age);
            Intent intent = new Intent(this, resultActivity.class);
            intent.putExtra("info",info);
            startActivity(intent);
        }
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void getWeight() {
        weight = Integer.parseInt(weightEt.getText().toString());
    }

    private void getAge() {
        age = Integer.parseInt(ageEt.getText().toString());
    }

    private void setHeightBarListener() {
        heightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                height = progress;
                heightText.setText(String.valueOf(height));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void checkAsMale() {
        gender = "male";
        maleCard.setCardBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.selected_bg, null));
        femaleCard.setCardBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.card_bg, null));
    }

    private void checkAsFemale() {
        gender = "female";
        femaleCard.setCardBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.selected_bg, null));
        maleCard.setCardBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.card_bg, null));
    }

    void findAllViews() {
        maleCard = findViewById(R.id.male_card);
        femaleCard = findViewById(R.id.female_card);
        heightBar = findViewById(R.id.height_bar);
        heightText = findViewById(R.id.height);
        weightEt = findViewById(R.id.weight_et);
        ageEt = findViewById(R.id.age_et);
        calculateBtn = findViewById(R.id.calculate_btn);
    }
}
