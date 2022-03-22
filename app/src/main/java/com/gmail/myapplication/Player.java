package com.gmail.myapplication;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    int balance;
    int turnID;
    Card[] hand;
    boolean isFolded;

    public Player(String initName, int initTurnID){
        this.name = initName;
        this.balance = 200000; // $200k
        this.turnID = initTurnID;
        isFolded = false;
    }

    public Player(Player orig){
        this.name = orig.name;
        this.balance = orig.balance;
        this.turnID = orig.turnID;
        this.isFolded = orig.isFolded;
        this.hand = new Card[4];
            for(int i = 0; i < orig.hand.length; i++){
                this.hand[i] = new Card(orig.hand[i]);
            }
    }

    public int getTurnID(){
        return this.turnID;
    }

    public void replaceCard(int index, Card card){
        //this.hand.add(index, card);
        this.hand[index] = card;

    }

    public int getBalance() {
        return this.balance;
    }

    public String getName() {
        return this.name;
    }

    public Card[] getHand() {
    return this.hand;
    }

    public void setScore(int newScore){
        this.balance = newScore;
    }

    public void setFolded(boolean newStat){
        this.isFolded = newStat;
    }

    @NonNull
    @Override
    public String toString(){
        return this.hand.toString();
    }
}
