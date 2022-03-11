/**
 * Models a Poker Hand
 */
package proj4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class PokerHand {
    private ArrayList<Card> hand;

    /**
     * Construct a 5 card poker hand
     *
     * @param cardList an ArrayList with 5 Card objects
     */
    public PokerHand(ArrayList<Card> cardList) {
        hand = new ArrayList<Card>(cardList);
        Collections.sort(hand);
    }


    /**
     * Constructor for passing in an array of Card objects (mainly used for testing)
     *
     * @param cards the array of Card objects
     */
    public PokerHand(Card[] cards) {
        hand = new ArrayList<Card>(Arrays.asList(cards));
        Collections.sort(hand);
    }

    /**
     * Reverse the order of the cards, putting them into an ascending order
     */
    public void ascendingOrder() {
        Collections.sort(this.hand, Collections.reverseOrder());
    }


    /**
     * Compare two different hands by high card
     *
     * @param otherHand the hand to compare (Array List of cards)
     * @return -1, 1, 0 dictating which hand is better
     */
    private int compareHighCards(PokerHand otherHand, PokerHand usableHand) {
        usableHand.ascendingOrder();
        otherHand.ascendingOrder();
        if (usableHand.getHandSize() == otherHand.getHandSize()) {

            for (int i = 0; i < usableHand.getHandSize(); i++) {
                int difference_in_cards = usableHand.getIthIndex(i).getRank() - otherHand.getIthIndex(i).getRank();
                if (difference_in_cards == 0) {
                    continue;
                }

                if (difference_in_cards > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        return 0;
    }

    /**
     * Compare two different hands by high card
     *
     * @param otherHand the hand to compare (Array List of cards)
     * @return -1, 1, 0 dictating which hand is better
     */
    private int compareHighCards(PokerHand otherHand) {
        return compareHighCards(otherHand, this);
    }


    /**
     * Using the Poker Hand (usually a standard 5 card list_of_cards), put all items into a hash map with the keys as the values
     * of the cards and the values being the num of cards in the list of cards that match that value
     *
     * @return hash map with keys as card ranks and values as number of that card in list of cards
     */
    private HashMap<String, Integer> putRanksIntoHashMap() {
        HashMap<String, Integer> ranksOfCards = new HashMap<>();

        for (Card card : this.hand) {
            String rankKey = "" + card.getRank();

            if (ranksOfCards.containsKey(rankKey)) {
                ranksOfCards.put(rankKey, ranksOfCards.get(rankKey) + 1);
            } else {
                ranksOfCards.put(rankKey, 1);
            }
        }

        return ranksOfCards;
    }


    /**
     * return all of the pairs in the last as an array of Integers
     *
     * @param ranksInHashMap hash map with keys as card ranks and values as number of that card in list of cards
     * @return array of integers like [2, 14] where there is a pair of twos and a pair of 14s in the hand
     */
    private static ArrayList<Integer> getPairsInList(HashMap<String, Integer> ranksInHashMap) {
        ArrayList<Integer> highestValueOfPairs = new ArrayList<Integer>();

        for (String key : ranksInHashMap.keySet()) {
            if (ranksInHashMap.get(key) >= 2) {
                highestValueOfPairs.add(parseInt(key));
            }
        }

        Collections.sort(highestValueOfPairs, Collections.reverseOrder());

        return highestValueOfPairs;
    }


    /**
     * Remove any card that repeats its rank in ranks (i.e. pairs, three kind, four kind)
     *
     * @param ranks       List of Integers dictating what ranks to remove
     * @return a new list of Cards with all pairs removed based on ranks in the ranks array
     */
    private ArrayList<Card> removeRecurringNumbersFromHand(ArrayList<Integer> ranks) {
        ArrayList<Card> newCards = new ArrayList<Card>();

        for (Card card : this.hand) {
            if (!ranks.contains(card.getRank())) {
                newCards.add(card);
            }
        }

        return newCards;
    }


    /**
     * Given a new card to add, add it to the list if the list does not have 5 cards in it
     *
     * @param newCard Card to add to list
     */
    public void addCard(Card newCard) {
        if (this.getHandSize() <= 4) {
            hand.add(newCard);
        }
    }


    /**
     * return the size of the poker hand
     *
     * @return return the size of the current hand
     */
    public int getHandSize() {
        return this.hand.size();
    }


    /**
     * Given an int (index), return the card in the hand at that index, null if nothing there
     *
     * @param index int to find card in hand
     * @return Card or null
     */
    public Card getIthIndex(int index) {
        if (index < this.getHandSize()) {
            return hand.get(index);
        }
        return null;
    }

    /**
     * Given an int (index), return the card in the hand at that index, null if nothing there
     *
     * Mainly for backwards compatibility
     *
     * @param index int to find card in hand
     * @return Card or null
     */
    public Card getIthCard(int index) {
        return getIthIndex(index);
    }


    /**
     * Return whether there is a pair or three of a kind in the current Poker Hand
     *
     * @return true if there is one of those kinds, false if not
     */
    private boolean isPair() {
        HashMap<String, Integer> ranksOfCards = this.putRanksIntoHashMap();
        int numPairs = 0;
        boolean isThreeKind = false;

        for (Integer rank : ranksOfCards.values()) {
            if (rank == 2) {
                numPairs += 1;
            }

            if (rank == 3) {
                isThreeKind = true;
            }
        }

        return isThreeKind || numPairs == 1;
    }


    /**
     * Return whether there is a 2 pair, 4 of a kind, or full house in the current Poker Hand
     *
     * @return true if there is one of those hands, false if not
     */
    private boolean isTwoPair() {
        HashMap<String, Integer> ranksOfCards = putRanksIntoHashMap();
        int numPairs = 0;
        boolean isThreeKind = false;
        boolean isFourKind = false;

        for (Integer rank : ranksOfCards.values()) {
            if (rank == 2) {
                numPairs += 1;
            }

            if (rank == 3) {
                isThreeKind = true;
            }

            if (rank == 4) {
                isFourKind = true;
            }
        }

        return (isThreeKind && numPairs == 1) || numPairs >= 2 || isFourKind;
    }


    /**
     * Classify the current Poker Hand as a flush or not a flush
     *
     * @return true if it contains a flush, false if not
     */
    private boolean classifyFlush() {
        String possible_flush = this.getContinuousHand();
        if (possible_flush == null) {
            return false;
        }

        return possible_flush.contains("flush");
    }


    /**
     * Given a different hand where this and other are a full house or four kind, return which one wins
     * @param otherHand the other list of cards (PokerHand) to compare
     * @return 1 if this is greater than otherHand, -1 if this is less than otherHand, 0 if tie
     */
    private int compareFullHouseFourKind(PokerHand otherHand) {
        //full house vs four kind
        this.ascendingOrder();
        otherHand.ascendingOrder();
        for(int i = 0; i < this.getHandSize(); i++) {
            Card thisCard = this.getIthIndex(i);
            Card otherCard = otherHand.getIthIndex(i);

            if(thisCard.getRank() != otherCard.getRank()) {
                int diff = thisCard.getRank() - otherCard.getRank();
                if(diff < 0) {
                    return -1;
                } else if(diff == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }

        return 0;
    }


    /**
     * Given another list_of_cards, compare the current Poker Hand and other_card list_of_cards if
     * both hands are classified as pair/two_pair
     *
     * @param otherHand the other list of cards (PokerHand) to compare
     * @return 1 if this is greater than otherHand, -1 if this is less than otherHand, 0 if tie
     */
    private int comparePair(PokerHand otherHand) {
        // get copy of hands to play around with

        HashMap<String, Integer> ranksOfCards1 = this.putRanksIntoHashMap();
        HashMap<String, Integer> ranksOfCards2 = otherHand.putRanksIntoHashMap();


        // check for which pair/s have higher value
        ArrayList<Integer> highestValueOfPairs1 = PokerHand.getPairsInList(ranksOfCards1);
        ArrayList<Integer> highestValueOfPairs2 = PokerHand.getPairsInList(ranksOfCards2);

        Collections.sort(highestValueOfPairs1, Collections.reverseOrder());
        Collections.sort(highestValueOfPairs2, Collections.reverseOrder());

        int numPasses = highestValueOfPairs1.size();

        if(highestValueOfPairs1.size() != highestValueOfPairs2.size()) {
            if(highestValueOfPairs1.size() > highestValueOfPairs2.size()) {
                numPasses = highestValueOfPairs2.size();
            }
        }

        for (int i = 0; i < numPasses; i++) {
            if (highestValueOfPairs2.get(i) > highestValueOfPairs1.get(i)) {
                return -1;
            }

            if (highestValueOfPairs2.get(i) < highestValueOfPairs1.get(i)) {
                return 1;
            }
        }

        ArrayList<Card> hand1PairsRemoved = removeRecurringNumbersFromHand(highestValueOfPairs1);
        Collections.sort(hand1PairsRemoved, Collections.reverseOrder());

        ArrayList<Card> hand2PairsRemoved = otherHand.removeRecurringNumbersFromHand(highestValueOfPairs2);
        Collections.sort(hand2PairsRemoved, Collections.reverseOrder());

        int comparison = compareHighCards(new PokerHand(hand2PairsRemoved), new PokerHand(hand1PairsRemoved));

        if(comparison == 0) {
            //it's possible that it was a four of a kind and 2 two pairs. could be full house so gotta sort through some things
            int compareRanks = ranksOfCards2.keySet().size() - ranksOfCards1.keySet().size();
            if(compareRanks == 0) {
                return compareFullHouseFourKind(otherHand);
            } else {
                return compareRanks;
            }
        } else {
            return comparison;
        }
    }


    /**
     * Classify the current Poker Hand to see if there are continuous cards (defined by the rank
     * of the previous card being the rank of the next card - 1 e.g. [5,6,7] (true) vs [6,8,9] (false))
     *
     * @return "straight" "flush" "royal flush" "straight flush" or None
     */
    private String getContinuousHand() {
        boolean is_same_suit = true;
        boolean possible_flush = true;

        String initial_suit = this.getIthIndex(0).getSuit();
        int initial_rank = this.getIthIndex(0).getRank();

        // loop through cards but start at second item
        for (int i = 1; i < this.getHandSize(); i++) {
            Card card = this.getIthIndex(i);
            String card_suit = card.getSuit();
            int card_rank = card.getRank();

            //check royal flush
            if (!initial_suit.equals(card_suit)) {
                is_same_suit = false;
            }

            if (card_rank != initial_rank + 1) {
                possible_flush = false;
                initial_rank = card_rank;
            }
        }

        if (possible_flush && is_same_suit && this.getIthIndex(4).getRank() == Card.ACE_RANK) {
            return "royal flush";
        } else if (is_same_suit && possible_flush) {
            return "straight flush";
        } else if (is_same_suit && !possible_flush) {
            return "flush";
        } else if (possible_flush) {
            return "straight";
        } else {
            return null;
        }
    }


    /**
     * Return the list_of_cards category type as a value 1-4 of the current Poker Hand
     * 4 = flush, 3 = two pair, 2 = pair, 1 = high card
     *
     * @return 1 | 2 | 3 | 4
     */
    public int getHandTypeValue() {
        if (this.classifyFlush()) {
            return 4;
        } else if (this.isTwoPair()) {
            return 3;
        } else if (this.isPair()) {
            return 2;
        } else {
            return 1;
        }

    }

    /**
     * Determines how this hand compares to another hand, returns
     * positive, negative, or zero depending on the comparison.
     *
     * @param otherHand The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     * if they are worth the SAME, and a positive number if this is worth * MORE than other
     */
    public int compareTo(PokerHand otherHand) {
        int handValue1 = this.getHandTypeValue();
        int handValue2 = otherHand.getHandTypeValue();

        if (handValue2 != handValue1) {
            return handValue1 - handValue2;
        }

        if (handValue1 == 1 || handValue1 == 4) {
            //either flush or high card category, sort and compare high cards
            return compareHighCards(otherHand);
        }


        if (handValue1 == 2 || handValue1 == 3) {
            return comparePair(otherHand);
        }

        return 0;
    }


    /**
     * Return the current hand as a printable string
     *
     * @return prettified string
     */
    public String toString() {
        String handAsString = "";
        for (int i = 0; i < this.getHandSize(); i++) {
            Card card = this.getIthIndex(i);
            handAsString = handAsString + " | " + card.toString();
        }

        return handAsString;
    }


    /**
     * Local testing
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Card(13, "D"));
        cards1.add(new Card(12, "H"));
        cards1.add(new Card(6, "D"));
        cards1.add(new Card(4, "S"));
        cards1.add(new Card(3, "D"));
        PokerHand hand1 = new PokerHand(cards1);

        ArrayList<Card> cards2 = new ArrayList<Card>();
        cards2.add(new Card(14, "H"));
        cards2.add(new Card(9, "S"));
        cards2.add(new Card(6, "C"));
        cards2.add(new Card(7, "S"));
        cards2.add(new Card(2, "S"));
        PokerHand hand2 = new PokerHand(cards2);

        System.out.println(hand1.compareTo(hand2));
    }
}

