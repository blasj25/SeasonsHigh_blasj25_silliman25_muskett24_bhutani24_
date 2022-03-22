package com.gmail.myapplication;

public class HumanPlayer extends Player{
    public HumanPlayer (String initName, int initTurnID){
        super(initName, initTurnID);
    }
    public HumanPlayer(Player orig){
        super(orig);
    }

}
