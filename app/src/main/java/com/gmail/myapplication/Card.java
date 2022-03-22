package com.gmail.myapplication;

import androidx.annotation.NonNull;

public class Card {

    public static final String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
    public static final String[] nameValues = {"Ace", "Two", "Three", "Four", "Five", "Six",
                                    "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private final String cardName;
    private int value;
    private boolean isDealt;

    public Card(int suit, int value){
        cardName = suits[suit] + " of " + nameValues[value];
        setCardValue(cardName);
        isDealt = false;
    }

    public Card(Card orig){
        this.cardName = orig.cardName;
        this.value = orig.value;
        this.isDealt = orig.isDealt;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardValue(String cardName){
        if(cardName.equalsIgnoreCase("Ace")) {
            value = 1;
        }else if(cardName.equalsIgnoreCase("King")){
            value = 13;
        }else if(cardName.equalsIgnoreCase("Queen")){
            value = 12;
        }
    }

    public void setIsDealt(boolean status){
        isDealt = status;
    }

    public boolean getIsDealt(){
        return isDealt;
    }

    @NonNull
    @Override
    public String toString(){
        return cardName;
    }
}
