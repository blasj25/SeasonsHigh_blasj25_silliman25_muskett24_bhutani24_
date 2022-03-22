package com.gmail.myapplication;

public class HumanPlayer extends Player{
    public HumanPlayer (String initName, int initScore, int initTurnID){
        super(initName, initScore, initTurnID);
    }
    public HumanPlayer(HumanPlayer orig){
        super(orig);
    }

}
