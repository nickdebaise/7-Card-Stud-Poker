package proj4;

import java.util.ArrayList;

/**
 * Model a 2 card stud poker hand with a community set
 */

public class StudPokerHand {

    private final static int SEVEN_CARD_HAND_NUM = 7;
    private CommunityCardSet communitySet;
    private ArrayList<Card> hand;

    private final ArrayList<Card> sevenCardHand;

    /**
     * Model a stud poker hand
     *
     * @param cc the community set of cards
     * @param cardList the cards that are the user's hand
     */
    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList) {
        this.communitySet = cc;
        this.hand = new ArrayList<>(cardList);

        sevenCardHand = getSevenCardHand();
    }


    /**
     * Given a new card to add, add it to the community set if the set does not have 2 cards in it
     *
     * @param newCard Card to add to list
     */
    public void addCard(Card newCard) {
        if (this.getHandSize() <= 1) {
            this.hand.add(newCard);
        }
    }


    /**
     * Given an int (index), return the card in the set at that index, null if nothing there
     *
     * @param index int to find card in set
     * @return Card or null
     */
    public Card getIthCard(int index) {
        if (index < this.getHandSize() && index >= 0) {
            return this.hand.get(index);
        }
        return null;
    }


    /**
     * return the size of the community set
     *
     * @return return the size of the current set
     */
    public int getHandSize() {
        return hand.size();
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


    /**
     * Get the 7 card hand (combining the cc and the hand)
     *
     * @return a new array list of size 7 of the cards combined
     */
    private ArrayList<Card> getSevenCardHand() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for(int i = 0; i < communitySet.getHandSize(); i++) {
            cards.add(communitySet.getIthCard(i));
        }

        for(int j = 0; j < hand.size(); j++) {
            cards.add(hand.get(j));
        }

        return cards;
    }


    /**
     * Determines how this hand compares to another hand, using the
     * community card set to determine the best 5-card hand it can
     * make. Returns positive, negative, or zero depending on the comparison.
     *
     * @param other The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     * if they are worth the SAME, and a positive number if this is worth * MORE than other
     */
    public int compareTo(StudPokerHand other) {
        PokerHand bestHand = getBestFiveCardHand();
        PokerHand otherBestHand = other.getBestFiveCardHand();

        return bestHand.compareTo(otherBestHand);
    }


    /**
     * Return the best five card hand that can be made from the community set and the stud hand
     *
     * @return the best (most valuable) 5 card Poker Hand
     */
    private PokerHand getBestFiveCardHand()
    {
        ArrayList<PokerHand> hands = getAllFiveCardHands();
        PokerHand bestSoFar = hands.get(0);
        for (int i = 1; i < hands.size(); i++) {
            if (hands.get(i).compareTo(bestSoFar) > 0) {
                bestSoFar = hands.get(i);
            }
        }
        return bestSoFar;
    }


    /**
     * Given an i and j, return the 5 card hand with the i and j indices removed from the 7 card hand
     *
     * @param i the first index to remove
     * @param j the second index to remove
     * @return a new 5 card array list
     */
    private ArrayList<Card> getFiveCardHand(int i, int j) {
        ArrayList<Card> cards = new ArrayList<>(sevenCardHand);

        cards.remove(i);
        cards.remove(j);

        return cards;
    }


    /**
     * Get all possible 5 card poker hands
     *
     * @return a list of all possible poker hands using the CC and the stud hand
     */
    private ArrayList<PokerHand> getAllFiveCardHands() {
        ArrayList<PokerHand> hands = new ArrayList<PokerHand>();

        for(int i = 0; i < SEVEN_CARD_HAND_NUM - 1; i++) {
            for(int j=i; j < SEVEN_CARD_HAND_NUM - 1; j++) {
                hands.add(new PokerHand(getFiveCardHand(i, j)));
            }
        }

        return hands;
    }


    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        CommunityCardSet communitySet = new CommunityCardSet(deck.deal(5));
        StudPokerHand studHand = new StudPokerHand(communitySet, deck.deal(2));
        StudPokerHand studHand2 = new StudPokerHand(communitySet, deck.deal(2));
        System.out.println(studHand.getBestFiveCardHand());
        System.out.println(studHand2.getBestFiveCardHand());
        System.out.println(studHand.compareTo(studHand2));
        System.out.println(studHand.getAllFiveCardHands().size());
    }
}
