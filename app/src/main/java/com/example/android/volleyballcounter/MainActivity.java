package com.example.android.volleyballcounter;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scorePointsTeamA = 0;
    int scorePointsTeamB = 0;
    int scoreSetsTeamA = 0;
    int scoreSetsTeamB = 0;
    int maxScore = 25;
    int lastGame = 0;
    boolean gameSet3 = true;
    int setsMax = 3;
    int winSet = setsMax % 2 + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button setMatch3 = (Button) findViewById(R.id.setMatch3);
        Button setMatch5 = (Button) findViewById(R.id.setMatch5);
        if (gameSet3) {
            setMatch3.setBackgroundColor(Color.parseColor("#FB8C00"));
            setMatch5.setBackgroundColor(Color.parseColor("#039BE5"));
        } else {
            setMatch3.setBackgroundColor(Color.parseColor("#039BE5"));
            setMatch5.setBackgroundColor(Color.parseColor("FB8C00"));
        }
    }

    public void displayInformation(String line) {
        TextView scoreView = (TextView) findViewById(R.id.information);
        scoreView.setText(line);
    }

    public void displayPointsForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.teamAPointsScore);
        scoreView.setText(String.valueOf(score));
    }

    public void displaySetsForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.teamASetsScore);
        scoreView.setText(String.valueOf(score));
    }

    public void addPointForTeamA(View view) {
        scorePointsTeamA += 1;
        displayPointsForTeamA(scorePointsTeamA);
        displaySetsForTeamA(scoreSetsTeamA);
        displaySetsForTeamB(scoreSetsTeamB);
        displayInformation("");
        finalPointerCheck(view);
    }

    public void displayPointsForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.teamBPointsScore);
        scoreView.setText(String.valueOf(score));
    }

    public void displaySetsForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.teamBSetsScore);
        scoreView.setText(String.valueOf(score));
    }

    public void addPointForTeamB(View view) {
        scorePointsTeamB += 1;
        displayPointsForTeamB(scorePointsTeamB);
        displaySetsForTeamB(scoreSetsTeamB);
        displaySetsForTeamA(scoreSetsTeamA);
        displayInformation("");
        finalPointerCheck(view);
    }

    public void teamAWin(View view) {
        scorePointsTeamA = 0;
        scorePointsTeamB = 0;
        scoreSetsTeamA += 1;
        maxScore = 25;
        displayPointsForTeamA(scorePointsTeamA);
        displayPointsForTeamB(scorePointsTeamB);
        displaySetsForTeamA(scoreSetsTeamA);
        displayInformation("Team A has won this set!!!");
        if (scoreSetsTeamA == winSet) {
            scoreSetsTeamB = 0;
            scoreSetsTeamA = 0;
            lastGame = 0;
            displayInformation("Team A has won the game!!!");
        }
    }

    public void teamBWin(View view) {
        scorePointsTeamA = 0;
        scorePointsTeamB = 0;
        scoreSetsTeamB += 1;
        maxScore = 25;
        displayPointsForTeamA(scorePointsTeamA);
        displayPointsForTeamB(scorePointsTeamB);
        displaySetsForTeamB(scoreSetsTeamB);
        displayInformation("Team B has won this set!!!");
        if (scoreSetsTeamB == winSet) {
            scoreSetsTeamB = 0;
            scoreSetsTeamA = 0;
            lastGame = 0;
            displayInformation("Team B has won the game!!!");
        }
    }

    public void finalPointerCheck(View view) {
        if (lastGame == 0 && scoreSetsTeamA + scoreSetsTeamB + 1 == setsMax) {
            maxScore = 15;
            lastGame = 1;
        }
        if (scorePointsTeamA == scorePointsTeamB && maxScore - scorePointsTeamA == 1) {
            maxScore += 1;
            displayInformation("The game will be continued to " + String.valueOf(maxScore) + " points");
        }
        if (scorePointsTeamA == maxScore)
            teamAWin(view);
        else if (scorePointsTeamB == maxScore)
            teamBWin(view);
    }

    public void reset(View view) {
        scorePointsTeamA = 0;
        scorePointsTeamB = 0;
        scoreSetsTeamA = 0;
        scoreSetsTeamB = 0;
        maxScore = 25;
        lastGame = 0;
        displaySetsForTeamA(scoreSetsTeamA);
        displaySetsForTeamB(scoreSetsTeamB);
        displayPointsForTeamA(scorePointsTeamA);
        displayPointsForTeamB(scorePointsTeamB);
        displayInformation("");
    }

    public void setMatches3(View view) {
        Button setMatch3 = (Button) findViewById(R.id.setMatch3);
        Button setMatch5 = (Button) findViewById(R.id.setMatch5);
        if (gameSet3 != true) {
            gameSet3 = true;
            setMatch3.setBackgroundColor(Color.parseColor("#FB8C00"));
            setMatch5.setBackgroundColor(Color.parseColor("#039BE5"));
            setsMax = 3;
            winSet = 2;
            reset(view);
        }
    }

    public void setMatches5(View view) {
        Button setMatch3 = (Button) findViewById(R.id.setMatch3);
        Button setMatch5 = (Button) findViewById(R.id.setMatch5);
        if (gameSet3 == true) {
            gameSet3 = false;
            setMatch5.setBackgroundColor(Color.parseColor("#FB8C00"));
            setMatch3.setBackgroundColor(Color.parseColor("#039BE5"));
            setsMax = 5;
            winSet = 3;
            reset(view);
        }
    }
}