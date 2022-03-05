package com.example.drivingquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiModeManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String QUESTION_AMOUNT_EXTRA = "com.example.drivingquiz.QUESTION_AMOUNT";
    private String[] spinnerItems = {"5 Questions", "10 Questions", "15 Questions", "20 Questions"};
    private int questionAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_item,spinnerItems);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

    }
    public void startQuiz(View view){
        Intent intent = new Intent(this,QuizActivity.class);
        intent.putExtra(QUESTION_AMOUNT_EXTRA,questionAmount);
        startActivity(intent);
    }
    public void setRB(View view){
        RadioGroup rg = findViewById(R.id.rg);
        rg.check(R.id.radioButton);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        switch(position){
            case 0:
                questionAmount = 5;
                break;
            case 1:
                questionAmount = 10;
                break;
            case 2:
                questionAmount = 15;
                break;
            case 3:
                questionAmount = 20;
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}