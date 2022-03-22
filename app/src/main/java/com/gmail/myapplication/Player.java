package com.gmail.myapplication;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    int score;
    int turnID;
    List<Card> cards = new ArrayList<>(4);
    boolean isFolded;

    public Player(String initName, int initScore, int initTurnID){
        this.name = initName;
        this.score = initScore;
        this.turnID = initTurnID;
        isFolded = false;
    }

    public Player(Player orig){
        this.name = orig.name;
        this.score = orig.score;
        this.turnID = orig.turnID;
        this.isFolded = orig.isFolded;
        this.cards = new ArrayList<>();
            for(Card c : orig.cards){
                this.cards.add(new Card(c));
            }
    }

    public int getTurnID(){
        return turnID;
    }
    public void addCard(int index, Card card){
        cards.add(index, card);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setScore(int newScore){
        this.score = newScore;
    }

    public void setFolded(boolean newStat){
        isFolded = newStat;
    }

    @NonNull
    @Override
    public String toString(){
        return cards.toString();
    }
}
