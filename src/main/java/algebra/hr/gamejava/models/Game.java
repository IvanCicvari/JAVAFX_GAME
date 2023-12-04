package algebra.hr.gamejava.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private int currentPlayer;
    private String[] playerID;

    private Deck deck;
    private ArrayList<ArrayList<Card>> playerHand;
    private ArrayList<Card> stockPile;
    private Card.ValueOfCard validValueOfCard;
    private Card.Country validConutry;

    boolean gameDirection;

    public Game(String[] pids){
        deck =new Deck();
        deck.shuffle();
        stockPile =new ArrayList<Card>();

        playerID =pids;
        currentPlayer =0;
        gameDirection = false;

        playerHand = new ArrayList<ArrayList<Card>>();

        for (int i =0; i< pids.length; i++){
            ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }
    }
    public void  start(Game game){
        Card card = deck.drawCard();
        validValueOfCard = card.getValueOfCard();
        validConutry = card.getCountry();

        if(!gameDirection){
            currentPlayer =(currentPlayer+1)% playerID.length;
        } else if (gameDirection) {
            currentPlayer =(currentPlayer-1)% playerID.length;
            if (currentPlayer == -1){
                currentPlayer = playerID.length -1;
            }
        }
        stockPile.add(card);
    }
}
