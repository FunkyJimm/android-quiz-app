package com.example.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.scores.Score;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;

    private ArrayList<Score> playersScores = new ArrayList<>();

    public MyAdapter(Context ctx, ArrayList<Score> scores) {
        context = ctx;
        playersScores = scores;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.score_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.playerNick.setText(playersScores.get(position).getNick());
        holder.playerScore.setText(playersScores.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        int count;

        if (playersScores.size() > 10)
            count = 10;
        else
            count = playersScores.size();

        return count;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView playerNick, playerScore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            playerNick = itemView.findViewById(R.id.tvPlayerNick);
            playerScore = itemView.findViewById(R.id.tvPlayerScore);
        }
    }
}
