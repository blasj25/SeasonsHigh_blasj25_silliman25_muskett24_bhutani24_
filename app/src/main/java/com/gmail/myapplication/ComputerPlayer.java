package com.gmail.myapplication;

public class ComputerPlayer extends Player{

    public ComputerPlayer(int initScore, int initTurnID){
        super("Computer "+(initTurnID-1) , initScore, initTurnID);
    }
    public ComputerPlayer(ComputerPlayer orig){
        super(orig);
    }
}
