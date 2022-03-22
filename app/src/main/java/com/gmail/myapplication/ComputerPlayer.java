package com.gmail.myapplication;

public class ComputerPlayer extends Player{

    public ComputerPlayer(int initTurnID){
        super("Computer "+(initTurnID-1), initTurnID);
    }
    public ComputerPlayer(ComputerPlayer orig){
        super(orig);
    }
}
