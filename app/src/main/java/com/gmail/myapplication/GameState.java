package com.gmail.myapplication;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameState {
    int roundCount;
    int minBet; //ante
    int currentBet;
    int currentPot;
    int playerTurnID;
    List <Player> players;
    List <Card> cardList;

    String[] phases = {"Opening", "Betting", "Drawing", "Showing"};
    //TODO: see setNextPlayer()
    String currentPhase;

    /**
     * ctor makes a new a game
     */
    public GameState(){
        players = new ArrayList<Player>();
        cardList = new ArrayList<Card>();
        roundCount = 1;
        minBet = 2500; //ante
        currentBet = 0;
        currentPot = 0;
        playerTurnID = 0;
        setPhase(0);

        //sets up three player objects, one human, two computer.
        players.add(new HumanPlayer("Player0", 0));
        players.add(new ComputerPlayer(1));
        players.add(new ComputerPlayer(2));

        initDeck();

        if(getCurrentPhase().equals("Opening")){
            dealCards();
        }
    }

    /**
     * Copy ctor
     * @param orig is the original GameState that is being copied
     */
    public GameState(GameState orig){
        this.minBet = orig.minBet;
        this.currentBet = orig.currentBet;
        this.currentPot = orig.currentPot;
        this.players = new ArrayList<Player>();
            for(int i = 0; i < 3; i++){
                this.players.add(new Player(orig.players.get(i)));
            }
        this.currentPhase = orig.getCurrentPhase();
        this.playerTurnID = orig.playerTurnID;
        this.phases = orig.phases;
        this.roundCount = orig.roundCount;
        this.cardList = new ArrayList<>();
        for(Card c : orig.cardList){
            this.cardList.add(new Card(c));
        }

    }

    /**
     * foldAction method is used to check if the player is making and valid
     * move and allow the player to fold if it is their turn.
     *
     * @param p player who is trying to make the move
     * @return true if user can make the move
     */
    public boolean foldAction(Player p){
        if(p.getTurnID() == playerTurnID){
            if(!p.isFolded) {
                if (getCurrentPhase().equals("Betting")) {
                    for(int i = 0; i < p.hand.length; i++){
                        p.hand[i] = null;
                    }
                    p.setFolded(true);
                    setNextPlayer();
                    setNextPhase();
                    if(allPlayersFolded()){
                        setPhase(3);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * allows the player to place a bet if it's their turn
     * @param p is the player that is attempting to make the move
     * @param betAmount is the amount that the player is trying to bet
     * @return true if the move was valid || false otherwise
     */
    public boolean betAction(Player p, int betAmount){
        if(p.getTurnID() == playerTurnID){
            if(getCurrentPhase().equals("Betting")){
                if(betAmount > minBet && betAmount >= currentBet){
                    if(betAmount > currentBet){
                        currentBet = betAmount;
                        p.setScore(p.getBalance()-betAmount);
                        currentPot += currentBet;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean drawAction(Player p){
        if(p.getTurnID() == playerTurnID){
            return getCurrentPhase().equals("Drawing");
        }
        return false;
    }

    /**
     * checks if all players but one has folded
     * @return true is all but one player has folded
     */
    public boolean allPlayersFolded(){
        int numFolded = 0;
        for(Player player: players){
            if(player.isFolded){
                numFolded += 1;
            }
        }
        return numFolded < (players.size() - 2);
    }

    /**
     * sets the playerTurnID to the next player in the sequence
     */
    public void setNextPlayer(){
        int numPlayersFolded = 0;
        playerTurnID += 1;
        if(playerTurnID > players.size()-1){
            playerTurnID = 0;
        }
        //checks to see if
        if(players.get(playerTurnID).isFolded){
            for(Player i: players){
                if(i.isFolded == true){
                    numPlayersFolded ++;
                }
            }
            if(numPlayersFolded == players.size()-1){
                setPhase(0);
                //TODO: phases[] does not have a phase for if a player has won but shouldn't show
                //  hand (i.e. everyone else has folded)
            }
            setNextPlayer();
        }
    }

    /**
     * sets phase to the next phase in the sequence
     */
    public void setNextPhase(){
        if(currentPhase.equalsIgnoreCase("Opening")){
            setPhase(1);
        }else if(currentPhase.equalsIgnoreCase("Betting")){
            if(roundCount <= 2){
                setPhase(2);
            }else{
                setPhase(3);
            }
        }else if(currentPhase.equalsIgnoreCase("Drawing")){
                            setPhase(1);
                roundCount += 1;

        }else if(currentPhase.equalsIgnoreCase("Showing")){
            setPhase(0);
            roundCount = 1;
        }

    }

    /**
     *  called when user attempts to draw a card(s).  checks if users move is valid,
     *  adds a new card to the slot the choose to swap
     *
     * @param p is the player that is trying to draw cards
     * @param index is the index in which that are attempting to swap
     */
    public void drawCard(Player p, int index){
        Random gen = new Random();
        int newCard = gen.nextInt(cardList.size());

        while(cardList.get(newCard).getIsDealt()){
            newCard = gen.nextInt(cardList.size());
        }
        if(drawAction(p)){
            if (!cardList.get(newCard).getIsDealt()) {
                p.replaceCard(index, cardList.get(newCard));
                cardList.get(newCard).setIsDealt(true);
            }
        }
    }

    /**
     * this method deals new cards to each player in game
     */
    public void dealCards(){
        Random gen = new Random();
        int newCard = gen.nextInt(4);
        for (Player player : players) {
            for(int i = 0; i < player.hand.length; i++){
                player.hand[i] = null;
            }
        }// clears players current hand
        for (Player player : players) {
            for (int j = 0; j < 4; j++) {// 4 is player hand size
                if (!cardList.get(newCard).getIsDealt()) {
                    continue;
                }
                player.replaceCard(j, cardList.get(newCard));
                cardList.get(newCard).setIsDealt(true);

            }
        }
    }

    /**
     * initDeck method initializes the deck as all new and unused, also
     * serves as shuffle.
     */
    public void initDeck(){
        int index = 0;
        //sets names for all the cards
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card(i, j);
                cardList.add(index, card);
                index++;
            }
        }
        for(int card = 0; card < cardList.size(); card++){
            cardList.get(card).setIsDealt(false);
        }
    }

    public void setPhase(int index){
        this.currentPhase = this.phases[index];
    }
    public String getCurrentPhase(){
        return this.currentPhase;
    }
    public int getCurrentBet() {
        return this.currentBet;
    }
    public int getMinBet(){
        return this.currentBet;
    }
    public int getPlayerBalance(int index){
        return this.players.get(index).getBalance();
    }
    public int getPlayerTurnID(){
        return this.playerTurnID;
    }
    public List<Player> getPlayers() {
        return this.players;
    }
    public List<Card> getCardList() {
        return this.cardList;
    }

    @NonNull
    @Override
    public String toString(){
        String playerBalances = "";
        String playerHands = "";
        String hand;
        for(int i = 0; i < players.size(); i++){
            hand = Arrays.toString(players.get(i).getHand());
            playerBalances += "\nPlayer " + i + " balance:" + getPlayerBalance(i);
            playerHands += "\nPlayer " + i + " Hand:" + hand;
        }
        String cardsBeenDrawn = "";
        String cardsNotBeenDrawn = "";
        for(Card h: getCardList()){
            if(h.getIsDealt()){
                cardsBeenDrawn += h.toString() + ", ";
            } else {
                cardsNotBeenDrawn += h.toString() + ", ";
            }
        }

        return "Current Players: " + getPlayers().toString()
            + "\n" + "Cards in deck:" + cardsNotBeenDrawn
                   + playerHands/* \n is included in this string */
            + "\n" + "Discarded Cards:" + cardsBeenDrawn
            + "\n" + "It is player " + getPlayerTurnID() + "'s turn"
            + "\n" + "Player Balances:"
                   + playerBalances/* \n is included in this string */
            + "\n" + "Min bet/Ante: " + getMinBet()
            + "\n" + "Current Bet: " + getCurrentBet()
            + "\n" + "Current Phase: " + getCurrentPhase();
    }
}
