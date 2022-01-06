package com.example.quiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.database.ScoresContract;
import com.example.quiz.database.ScoresDbHelper;
import com.example.quiz.scores.Score;

import java.util.ArrayList;

public class ScoresActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ScoresDbHelper scoresDbHelper = new ScoresDbHelper(this);

    ArrayList<Score> scores = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        final Button btnReturn = (Button) findViewById(R.id.btnReturn);

        recyclerView = findViewById(R.id.rvScores);

        readScoreData();

        MyAdapter myAdapter = new MyAdapter(this, scores);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void readScoreData() {
        SQLiteDatabase db = scoresDbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                ScoresContract.ScoreEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ScoresContract.ScoreEntry.COLUMN_NAME_SCORE + " DESC"
        );

        while(cursor.moveToNext()) {
            String playerNick = cursor.getString(
                    cursor.getColumnIndexOrThrow(ScoresContract.ScoreEntry.COLUMN_NAME_NICK));
            String playerScore = cursor.getString(
                    cursor.getColumnIndexOrThrow(ScoresContract.ScoreEntry.COLUMN_NAME_SCORE));
            Score score = new Score(playerNick, playerScore);
            scores.add(score);
        }
        cursor.close();
    }
}
