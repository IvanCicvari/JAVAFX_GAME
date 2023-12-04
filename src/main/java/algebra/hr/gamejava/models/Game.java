package algebra.hr.gamejava.models;

import javafx.scene.image.ImageView;

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

    public Card getTopCard(){
        return new Card(validValueOfCard,validConutry);
    }
    public ImageView getTopCardImage(){
        return new ImageView(validValueOfCard+"-"+validConutry+".png");
    }
    /*
    public boolean isGameOver(){
        for (String player:this.playerID){
            if (hasEmptyHand(player)){
                return true;
            }
        }
        return false;
    }*/ //new logic needs to bee
    public String getCurrentPlayer(){
        return this.playerID[this.currentPlayer];
    }
    public String getPreviousPlayer(int i){
        int index = this.currentPlayer-i;
        if (index == -1)
        {
            index =playerID.length-1;
        }
        return this.playerID[index];
    }
    public String[] getPlayers(){
        return playerID;
    }
    public ArrayList<Card>getPlayerHand(String pid){
        int index =Arrays.asList(playerID).indexOf(pid);
        return playerHand.get(index);
    }

    public int getPlayerHandSize(String pid)
    {
        return getPlayerHand(pid).size();
    }
    public Card getPlayerCard(String pid,int choice){
        ArrayList<Card> hand = getPlayerHand(pid);
        return hand.get(choice);
    }

    public boolean hasEmptyHand(String pid){
        return getPlayerHand(pid).isEmpty();
    }
    public boolean validCardPlay(Card card){
        return card.getValueOfCard() == validValueOfCard || card.getCountry() == validConutry;
    }
    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException{
        if (this.playerID[this.currentPlayer] != pid){
            throw new InvalidPlayerTurnException("It is not" +pid+" 's turn",pid);
        }
    }
    public void  submitDraw(String pid) throws InvalidPlayerTurnException{
        if (deck.isEmpty()){
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }
        getPlayerHand(pid).add(deck.drawCard());
        if (!gameDirection){
            currentPlayer = (currentPlayer+1)%playerID.length;
        } else if (gameDirection) {
            currentPlayer = (currentPlayer-1)%playerID.length;
            if (currentPlayer ==-1)
            {
                currentPlayer =playerID.length -1;
            }

        }
    }
    public void setCardValue (Card.ValueOfCard cardValue){
        validValueOfCard = cardValue;
    }
}
