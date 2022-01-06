package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    private boolean multipleAnswersOn = false;
    private boolean playerScoreOn = false;
    private boolean correctAnswersShowOn = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        final RadioButton rbSingleAnswers = (RadioButton) findViewById(R.id.rbSingleAnswers);
        final RadioButton rbMultipleAnswers = (RadioButton) findViewById(R.id.rbMultipleAnswers);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        final Switch swPointsOn = (Switch) findViewById(R.id.swPointsOn);
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        final Switch swCorrectOn = (Switch) findViewById(R.id.swCorrectOn);
        final Button btnReturn = (Button) findViewById(R.id.btnReturn);

        swPointsOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    playerScoreOn = true;
                } else {
                    playerScoreOn = false;
                }
            }
        });

        swCorrectOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    correctAnswersShowOn = true;
                } else {
                    correctAnswersShowOn = false;
                }
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbSingleAnswers.isChecked()) {
                    multipleAnswersOn = false;
                } else if (rbMultipleAnswers.isChecked()) {
                    multipleAnswersOn = true;
                }

                Intent optionsIntent = new Intent(getApplicationContext(), MainActivity.class);
                optionsIntent.putExtra("multipleAnswersOn", multipleAnswersOn);
                optionsIntent.putExtra("playerScoreOn", playerScoreOn);
                optionsIntent.putExtra("correctAnswersShowOn", correctAnswersShowOn);
                startActivity(optionsIntent);
            }
        });
    }
}
