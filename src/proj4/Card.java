package proj4;

import java.util.Locale;

/**
 * models a playing card
 */
public class Card implements Comparable<Card> {

    public static final String[] SUITS = {"Spades", "Hearts", "Clubs", "Diamonds"};
    public static final String DEFAULT_SUIT = "Spades";
    public static final int DEFAULT_RANK = 14;
    public static final int ACE_RANK = 14;

    private int rank;
    private String suit;

    /**
     * String
     * constructor with rank and suit
     *
     * @param newRank integer from 2-14 (14=Ace)
     * @param newSuit "Hearts","Clubs","Spades",etc.
     */
    public Card(int newRank, String newSuit) {
        rank = newRank;
        suit = newSuit;
    }

    /**
     * constructor
     * @param rank String: whole cards (2-10) can either be spelled * out like "two" or numeric like "2". Case insensitive.
     * @param suit String: "Spades", "Hearts", "Clubs", or "Diamonds"
     */
    public Card(String rank, String suit) {
        this.suit = suit;
        this.rank = parseRank(rank);
    }

    /**
     * constructor
     * @param rank integer between 2-14
     * @param suit integer: 0=Spades, 1=Hearts, 2=Clubs, or 3=Diamonds
     */
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = SUITS[suit];
    }


    /**
     * getter for rank
     *
     * @return int between 2-14
     */
    public int getRank() {
        return rank;
    }

    /**
     * getter for suit
     *
     * @return suit fully written out like "Diamonds"
     */
    public String getSuit() {
        return suit;
    }


    /**
     * return the card as a printable string
     * like "Jack of Clubs"
     *
     * @return printable string
     */
    public String toString() {
        return convertFace(getRank()) + " of " + getSuit();
    }


    /**
     * Given a string such as "two" or 2 or "three" or "king", return the corresponding rank
     * @param rank rank such as "two" or "2" or "jack"
     * @return the rank represented as an integer
     */
    private int parseRank(String rank) {
        switch (rank.toLowerCase(Locale.ROOT)) {
            case "2": case "two":
                return 2;
            case "3": case "three":
                return 3;
            case "4": case "four":
                return 4;
            case "5": case "five":
                return 5;
            case "6": case "six":
                return 6;
            case "7": case "seven":
                return 7;
            case "8": case "eight":
                return 8;
            case "9": case "nine":
                return 9;
            case "10": case "ten":
                return 10;
            case "11": case "jack":
                return 11;
            case "12": case "queen":
                return 12;
            case "13": case "king":
                return 13;
            case "14": case "ace":
                return 14;
            default:
                return 2;
        }
    }


    /**
     * take numeric rank and turn it into
     * a printable string where 11-14 are
     * turned into Face card values
     *
     * @param rank the integer rank of the card (2-14)
     */
    private String convertFace(int rank) {
        switch (rank) {
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            case 14:
                return "Ace";
            default:
                return "" + rank;
        }
    }

    /**
     * Implement compare to in order to compare two cards for sorting
     *
     * @param otherCard the other card to compare to
     * @return an integer: this > 0 means this is higher ranked, < 0 lower ranked, == 0 means equal ranks
     */
    @Override
    public int compareTo(Card otherCard) {
        return this.getRank() - otherCard.getRank();
    }
}

