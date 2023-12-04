package algebra.hr.gamejava.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private Card[] cards;
    private int CardsInDeck;

    public Deck()
    {
        cards= new Card[110];

    }
    public void rest() {
        // Get all possible values of cards
        Card.ValueOfCard[] valueOfCards = Card.ValueOfCard.values();

        // Reset the count of cards in the deck
        CardsInDeck = 0;

        // Iterate over each value of the card
        for (int i = 0; i < valueOfCards.length; i++) {
            // Get the current value of the card
            Card.ValueOfCard valueOfCard = valueOfCards[i];

            // Iterate over each country to associate the value with cards for both USA and USSR
            for (int j = 0; j < Card.Country.values().length; j++) {
                // Add two cards for each combination of value and country
                cards[CardsInDeck++] = new Card(valueOfCard, Card.Country.getCountry(j));
                cards[CardsInDeck++] = new Card(valueOfCard, Card.Country.getCountry(j));
            }
        }
    }

    /**
     * Replaces the current deck with a new set of cards.
     *
     * @param cards ArrayList of cards to replace the deck with.
     */
    public void replaceDeckWith(ArrayList<Card> cards) {
        // Convert the ArrayList to an array and update the deck
        this.cards = cards.toArray(new Card[cards.size()]);
        // Update the count of cards in the deck
        this.CardsInDeck = this.cards.length;
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if the deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        // Check if the deck is empty by comparing the count of cards
        return CardsInDeck == 0;
    }

    /**
     * Shuffles the cards in the deck using the Fisher-Yates shuffle algorithm.
     */
    public void shuffle() {
        int m = cards.length;
        Random random = new Random();

        // Iterate through each card in the deck
        for (int i = 0; i < cards.length; i++) {
            // Generate a random index to swap with the current card
            int randomValue = i + random.nextInt(m - i);
            // Swap the current card with the randomly selected card
            Card randomCard = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomCard;
        }
    }

    /**
     * Draws a single card from the deck.
     *
     * @return The drawn card.
     * @throws IllegalArgumentException If the deck is empty.
     */
    public Card drawCard() throws IllegalArgumentException {
        // Check if the deck is empty before drawing a card
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot draw a card since there are no cards in the deck");
        }
        // Return the top card from the deck and decrement the count
        return cards[--CardsInDeck];
    }

    /**
     * Draws a specified number of cards from the deck.
     *
     * @param i The number of cards to draw.
     * @return An array of drawn cards.
     * @throws IllegalArgumentException If the requested number of cards is invalid.
     */
    public Card[] drawCard(int i) {
        // Validate the requested number of cards
        if (i <= 0) {
            throw new IllegalArgumentException("Must draw a positive number of cards, but tried to draw " + i + " cards");
        }
        if (i > CardsInDeck) {
            throw new IllegalArgumentException("Cannot draw " + i + " cards since there are only " + CardsInDeck + " cards");
        }

        // Draw the specified number of cards and return them in an array
        Card[] ret = new Card[i];
        for (int n = 0; n < i; n++) {
            ret[n] = cards[CardsInDeck--];
        }
        return ret;
    }

    /**
     * Draws and returns an ImageView of a card's image.
     *
     * @return An ImageView of the drawn card's image.
     * @throws IllegalArgumentException If the deck is empty.
     */
    public ImageView drawCardImage() throws IllegalArgumentException {
        // Check if the deck is empty before drawing a card
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot draw a card since there are no cards in the deck");
        }
        // Assuming the card's toString() method returns a valid image file name
        return new ImageView(cards[CardsInDeck--].toString() + ".png");
    }

}
