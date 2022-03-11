/**
 * Model a standard deck
 */
package proj4; // do not erase. Gradescope expects this.

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {

    private static final int HIGHEST_RANK = 14;
    private static final int STARTING_RANK = 2;
    private static final int HIGHEST_SUIT = 3;
    private static final int NUM_CARDS = 52;

    private int nextToDeal;
    private ArrayList<Card> cards;

    /**
     * Default constructor - construct a standard deck with all 52 playing cards
     */
    public Deck() {
        nextToDeal = 0;
        cards = new ArrayList<Card>();

        for (int rank = STARTING_RANK; rank <= HIGHEST_RANK; rank++) {
            for (int suit = 0; suit <= HIGHEST_SUIT; suit++) {
                cards.add(new Card(rank, Card.SUITS[suit]));
            }
        }
    }


    /**
     * Return a card from the deck
     *
     * @return the next Card to deal
     */
    public Card deal() {
        if (this.isEmpty()) {
            return null;
        }

        Card cardToDeal = cards.get(nextToDeal);
        nextToDeal++;
        return cardToDeal;
    }


    /**
     * Return a list of n cards from the deck
     * if n > number of cards left, return null
     *
     * @param n the number of cards to deal
     * @return a list of Cards
     */
    public ArrayList<Card> deal(int n) {
        ArrayList<Card> newCards = new ArrayList<Card>(n);
        if (n > this.size()) {
            return null;
        }

        for (int i = 0; i < n; i++) {
            Card cardToDeal = cards.get(nextToDeal);
            nextToDeal++;
            newCards.add(cardToDeal);
        }

        return newCards;
    }


    /**
     * Shuffle all the cards in remaining deck
     */
    public void shuffle() {
        for (int i = nextToDeal; i < NUM_CARDS; i++) {
            int randomInt = ThreadLocalRandom.current().nextInt(nextToDeal, NUM_CARDS);
            Collections.swap(cards, i, randomInt);
        }
    }


    /**
     * Given an index, return the Card at that index, regardless of the cards remaining in the deck
     *
     * @param index the index to use to get the Card
     * @return Card or null if index >= size of cards
     */
    public Card get_ith_index(int index) {
        if (index >= NUM_CARDS) {
            return null;
        }
        return cards.get(index);
    }


    /**
     * Regather all the cards (might be shuffled, might not be)
     */
    public void gather() {
        nextToDeal = 0;
        this.shuffle();
    }


    /**
     * Return the size of how many cards are left to deal
     *
     * @return integer telling # of cards left to deal (size of deck)
     */
    public int size() {
        return NUM_CARDS - nextToDeal;
    }


    /**
     * Return whether the deck is empty or not
     *
     * @return boolean telling if there are cards to deal or not
     */
    public boolean isEmpty() {
        return nextToDeal == NUM_CARDS;
    }


    /**
     * return the deck as a printable string
     *
     * @return ["Jack of spades", "10 of Diamonds"]
     */
    public String toString() {
        return cards.toString();
    }
}

