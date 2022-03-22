package com.gmail.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GameController implements View.OnClickListener{
    GameState gameState;
    Player localPlayer;
    Button foldButt;
    Button drawButt;
    Button betButt;
    Button checkButt;
    Button holdButt;
    ImageView CardOneView;
    ImageView CardTwoView;
    ImageView CardThreeView;
    ImageView CardFourView;

    public GameController(GameState gs, Player initLocalPlayer, Button initFoldButt, Button initDrawButt,
                          Button initBetButt, Button initCheckButt, Button initHoldButt,
                          ImageView initCardOneView, ImageView initCardTwoView,
                          ImageView initCardThreeView, ImageView initCardFourView){
        /* TODO add the rest of the views to the controller constructor */
        gameState = new GameState(gs);
        localPlayer = initLocalPlayer;
        this.foldButt = initFoldButt;
        this.drawButt = initDrawButt;
        this.betButt = initBetButt;
        this.checkButt = initCheckButt;
        this.holdButt = initHoldButt;
        this.CardOneView = initCardOneView;
        this.CardTwoView = initCardTwoView;
        this.CardThreeView = initCardThreeView;
        this.CardFourView = initCardFourView;
    }

    @Override
    public void onClick(View view) {
        /* TODO implement the actions with the corresponding button */
        if(view == foldButt){
            gameState.foldAction(localPlayer);
        }else if(view == drawButt){
            //gameState.drawCard(localPlayer, x);
        }
    }
}
