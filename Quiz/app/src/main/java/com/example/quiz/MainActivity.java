package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean multipleAnswersOn = false;
    private boolean playerScoreOn = false;
    private boolean correctAnswersShowOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnNewGame = (Button) findViewById(R.id.btnNewGame);
        final Button btnOptions = (Button) findViewById(R.id.btnOptions);
        final Button btnScores = (Button) findViewById(R.id.btnScores);
        final Button btnExit = (Button) findViewById(R.id.btnExit);

        // Starting new game activity
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newGameIntent = new Intent(getApplicationContext(), SingleAnswerActivity.class);
                newGameIntent.putExtra("multipleAnswersOn", multipleAnswersOn);
                newGameIntent.putExtra("playerScoreOn", playerScoreOn);
                newGameIntent.putExtra("correctAnswersShowOn", correctAnswersShowOn);
                startActivity(newGameIntent);
            }
        });

        // Starting options activity
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OptionsActivity.class));
            }
        });

        // Starting scores activity
        btnScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScoresActivity.class));
            }
        });

        // Closing application
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle options = getIntent().getExtras();
        if (options != null) {
            multipleAnswersOn = options.getBoolean("multipleAnswersOn");
            playerScoreOn = options.getBoolean("playerScoreOn");
            correctAnswersShowOn = options.getBoolean("correctAnswersShowOn");
            System.out.println("Opcje: " + multipleAnswersOn + playerScoreOn + correctAnswersShowOn);
        }
    }
}