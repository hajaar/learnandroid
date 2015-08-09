
package com.example.android.practiceset2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayForTeam(int score,boolean isTeamA) {
        TextView scoreView;
        if (isTeamA) {
             scoreView = (TextView) findViewById(R.id.team_a_score);
        }
        else {
             scoreView = (TextView) findViewById(R.id.team_b_score);
        }
        scoreView.setText(String.valueOf(Integer.valueOf(scoreView.getText().toString()) + score));
    }

    public void addPoints(View view) {
        int newScore = 0;
        boolean isTeamA = true;
        Button button = (Button) findViewById(view.getId());
        switch(button.getId()) {
            case R.id.add_3_points_a: newScore = 3;
                isTeamA=true;
                break;
            case R.id.add_2_points_a: newScore = 2;
                isTeamA=true;
                break;
            case R.id.add_free_throw_a: newScore = 1;
                isTeamA=true;
                break;
            case R.id.add_3_points_b: newScore = 3;
                isTeamA=false;
                break;
            case R.id.add_2_points_b: newScore = 2;
                isTeamA=false;
                break;
            case R.id.add_free_throw_b: newScore = 1;
                isTeamA=false;
                break;
            default: newScore = 0;
                break;
        }
        displayForTeam(newScore, isTeamA);
    }

    public void resetTeamScores(View view) {
        TextView scoreView;
        scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText("0");
        scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText("0");
    }


}