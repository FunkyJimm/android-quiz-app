package com.example.quiz;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.database.ScoresContract;
import com.example.quiz.database.ScoresDbHelper;

public class ResultActivity extends AppCompatActivity {

    private String playerScore;

    ScoresDbHelper scoresDbHelper = new ScoresDbHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final TextView tvScore = (TextView) findViewById(R.id.tvScore);
        final Button btnReturn = (Button) findViewById(R.id.btnReturn);
        final EditText etNick = (EditText) findViewById(R.id.etNick);

        Bundle extras = getIntent().getExtras();
        playerScore = Integer.toString(extras.getInt("playerScore"));
        tvScore.setText(playerScore);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerNick = etNick.getText().toString();

                ContentValues values = new ContentValues();
                values.put(ScoresContract.ScoreEntry.COLUMN_NAME_NICK, playerNick);
                values.put(ScoresContract.ScoreEntry.COLUMN_NAME_SCORE, playerScore);

                scoresDbHelper.getWritableDatabase().insert(ScoresContract.ScoreEntry.TABLE_NAME, null, values);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
