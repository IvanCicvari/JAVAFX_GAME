package algebra.hr.gamejava.models;

import algebra.hr.gamejava.customExceptions.InvalidCountrySubmissionException;
import algebra.hr.gamejava.customExceptions.InvalidPlayerTurnException;
import algebra.hr.gamejava.customExceptions.InvalidValueOfCardSubmissionException;
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
    private Card.Country validCountry;

    boolean gameDirection;

    /**
     * Constructor for the Game class.
     *
     * @param pids An array of player IDs.
     */
    public Game(String[] pids) {
        // Initialize the game components: deck, stock pile, player hands, etc.
        deck = new Deck();
        deck.shuffle();
        stockPile = new ArrayList<>();

        playerID = pids;
        currentPlayer = 0;
        gameDirection = false;

        playerHand = new ArrayList<>();

        // Deal initial hands to players
        for (int i = 0; i < pids.length; i++) {
            ArrayList<Card> hand = new ArrayList<>(Arrays.asList(deck.drawCard(7)));
            playerHand.add(hand);
        }
    }
    /**
     * Start the game by drawing the initial card and setting the initial player.
     *
     * @param game The current game instance.
     */
    public void start(Game game) {
        // Draw a card, set the initial player, and add the card to the stock pile.
        Card card = deck.drawCard();
        validValueOfCard = card.getValueOfCard();
        validConutry = card.getCountry();

        if (!gameDirection) {
            currentPlayer = (currentPlayer + 1) % playerID.length;
        } else if (gameDirection) {
            currentPlayer = (currentPlayer - 1 + playerID.length) % playerID.length;
        }

        stockPile.add(card);
    }


    /**
     * Get the top card of the stock pile.
     *
     * @return The top card of the stock pile.
     */
    /**
     * Get the image of the top card of the stock pile.
     *
     * @return An ImageView representing the top card image.
     */
    public ImageView getTopCardImage() {
        // Return an ImageView representing the image of the top card.
        return new ImageView(validValueOfCard + "-" + validConutry + ".png");
    }

    // The 'isGameOver' method was commented out since the new logic needs to be implemented.

    /*
    public boolean isGameOver(){
        for (String player:this.playerID){
            if (hasEmptyHand(player)){
                return true;
            }
        }
        return false;
    }*/ //new logic needs to bee
    /**
     * Get the ID of the current player.
     *
     * @return The ID of the current player.
     */
    public String getCurrentPlayer() {
        // Return the ID of the current player.
        return this.playerID[this.currentPlayer];
    }
    /**
     * Get the ID of the player who played before the current player.
     *
     * @param i The number of players to go back.
     * @return The ID of the player who played before the current player.
     */
    public String getPreviousPlayer(int i) {
        // Return the ID of the player who played before the current player, considering circular rotation.
        int index = this.currentPlayer - i;
        if (index == -1) {
            index = playerID.length - 1;
        }
        return this.playerID[index];
    }
    /**
     * Get an array of all player IDs.
     *
     * @return An array of player IDs.
     */
    public String[] getPlayers() {
        // Return an array of all player IDs.
        return playerID;
    }

    /**
     * Get the hand of a specific player.
     *
     * @param pid The ID of the player.
     * @return The ArrayList representing the player's hand.
     */
    public ArrayList<Card> getPlayerHand(String pid) {
        // Return the hand of the specified player.
        int index = Arrays.asList(playerID).indexOf(pid);
        return playerHand.get(index);
    }

    /**
     * Get the size of a specific player's hand.
     *
     * @param pid The ID of the player.
     * @return The size of the player's hand.
     */
    public int getPlayerHandSize(String pid) {
        // Return the size of the specified player's hand.
        return getPlayerHand(pid).size();
    }
    /**
     * Get a specific card from a player's hand.
     *
     * @param pid    The ID of the player.
     * @param choice The index of the card in the player's hand.
     * @return The specified card from the player's hand.
     */
    public Card getPlayerCard(String pid, int choice) {
        // Return the specified card from the specified player's hand.
        ArrayList<Card> hand = getPlayerHand(pid);
        return hand.get(choice);
    }

    /**
     * Check if a player's hand is empty.
     *
     * @param pid The ID of the player.
     * @return True if the player's hand is empty, false otherwise.
     */
    public boolean hasEmptyHand(String pid) {
        // Check if the player's hand is empty.
        return getPlayerHand(pid).isEmpty();
    }

    /**
     * Check if playing a card is a valid move.
     *
     * @param card The card to be played.
     * @return True if the card play is valid, false otherwise.
     */
    public boolean validCardPlay(Card card) {
        // Check if playing the given card is a valid move.
        return card.getValueOfCard() == validValueOfCard || card.getCountry() == validCountry;
    }
    /**
     * Check if it is a specific player's turn to take an action.
     *
     * @param pid The ID of the player attempting to take an action.
     * @throws InvalidPlayerTurnException Thrown if it is not the specified player's turn.
     */
    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException {
        // Check if it is the turn of the specified player to take an action.
        if (!this.playerID[this.currentPlayer].equals(pid)) {
            throw new InvalidPlayerTurnException("It is not " + pid + "'s turn", pid);
        }
    }
    /**
     * Check if it is a specific player's turn, and submit a draw action.
     *
     * @param pid The ID of the player submitting the draw action.
     * @throws InvalidPlayerTurnException If it is not the specified player's turn.
     */
    public void submitDraw(String pid) throws InvalidPlayerTurnException {
        // If the deck is empty, replace it with the stock pile, shuffle, and continue drawing.
        if (deck.isEmpty()) {
            deck.replaceDeckWith(stockPile);
            deck.shuffle();
        }

        // Draw a card and add it to the player's hand.
        getPlayerHand(pid).add(deck.drawCard());

        // Update the current player based on game direction.
        if (!gameDirection) {
            currentPlayer = (currentPlayer + 1) % playerID.length;
        } else if (gameDirection) {
            currentPlayer = (currentPlayer - 1 + playerID.length) % playerID.length;
            if (currentPlayer == -1) {
                currentPlayer = playerID.length - 1;
            }
        }
    }
    /**
     * Set the value of the card to be played.
     *
     * @param cardValue The value to be set.
     */
    public void setCardValue(Card.ValueOfCard cardValue) {
        // Set the value of the card
        validValueOfCard = cardValue;
    }

    public void submitPlayerCard(String pid,Card playerCard,Card.Country declaredValue)
        throws InvalidValueOfCardSubmissionException, InvalidCountrySubmissionException,InvalidPlayerTurnException
        {   checkPlayerTurn(pid);
            ArrayList<Card>pHand =getPlayerHand(pid);

            if (!validCardPlay(playerCard))
            {
                if (playerCard.getCountry() != validCountry)
                {
                    throw new InvalidCountrySubmissionException("Can't place here. Expected: " + validCountry + " but you placed it on " + playerCard.getCountry(),
                            validCountry, playerCard.getCountry());                }
            }
            pHand.remove(playerCard);
            // add later for more checks for place a card
        }
}


