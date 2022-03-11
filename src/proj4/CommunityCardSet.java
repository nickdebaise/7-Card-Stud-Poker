package proj4;

import java.util.ArrayList;

/**
 * Models a community card set
 */

public class CommunityCardSet {

    private ArrayList<Card> cards;

    /**
     * Given a list of cards, model a typical Community Card Set
     * @param cards the list of cards to be used as the community set
     */
    public CommunityCardSet(ArrayList<Card> cards) {
        this.cards = new ArrayList<Card>(cards);
    }


    /**
     * Given a new card to add, add it to the community set if the set does not have 5 cards in it
     *
     * @param newCard Card to add to list
     */
    public void addCard(Card newCard) {
        if (this.getHandSize() <= 4) {
            this.cards.add(newCard);
        }
    }


    /**
     * Given an int (index), return the card in the set at that index, null if nothing there
     *
     * @param index int to find card in set
     * @return Card or null
     */
    public Card getIthCard(int index) {
        if (index < this.getHandSize()) {
            return this.cards.get(index);
        }
        return null;
    }


    /**
     * return the size of the community set
     *
     * @return return the size of the current set
     */
    public int getHandSize() {
        return cards.size();
    }

    /**
     * Return the current set as a printable string
     *
     * @return prettified string
     */
    public String toString() {
        ArrayList<String> handsAsString = new ArrayList<>();
        for (int i = 0; i < this.getHandSize(); i++) {
            Card card = this.getIthCard(i);
            handsAsString.add(card.toString());
        }

        return String.join(" | ", handsAsString);
    }

}
