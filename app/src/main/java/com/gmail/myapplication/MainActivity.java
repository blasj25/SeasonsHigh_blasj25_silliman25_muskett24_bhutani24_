package com.gmail.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameState goldenGS = new GameState();
        GameState copyGS = new GameState(goldenGS);
        HumanPlayer localPlayer = new HumanPlayer(copyGS.player0);

        Button foldButt = findViewById(R.id.foldButton);
        Button drawButt = findViewById(R.id.drawButton);
        Button betButt = findViewById(R.id.betButton);
        Button checkButt = findViewById(R.id.checkButton);
        Button holdButt = findViewById(R.id.holdButton);

        TextView currentBetTV = findViewById(R.id.currentBetTV);
        TextView potAmountTV = findViewById(R.id.potAmountTV);
        TextView playerNameTV = findViewById(R.id.playerTV);

        GameController gameController = new GameController(copyGS, localPlayer, foldButt, drawButt, betButt,
                checkButt, holdButt);

        foldButt.setOnClickListener(gameController);
        drawButt.setOnClickListener(gameController);
        betButt.setOnClickListener(gameController);
        checkButt.setOnClickListener(gameController);
        holdButt.setOnClickListener(gameController);
    }

}