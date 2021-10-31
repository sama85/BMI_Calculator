package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private Button goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViews();
        goBtn.setOnClickListener(v -> processClick());
    }

    void findAllViews() {
        name = findViewById(R.id.name_et);
        goBtn = findViewById(R.id.go_button);
    }

    void processClick() {
        if (invalidName()) showToast("Please enter a valid username");
        else startInfoActivity();
    }

    private boolean invalidName() {
        return  (name.getText().toString().length() < 2);
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    void startInfoActivity() {
        Intent intent = new Intent(this, secondActivity.class);
        intent.putExtra("name", name.getText().toString());
        startActivity(intent);
    }

}