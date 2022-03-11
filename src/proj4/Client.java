/**
 * Author: Nick DeBaise
 * Honor Code: I affirm that I have carried out the attached academic endeavors with full academic honesty, in
 * accordance with the Union College Honor Code and the course syllabus.
 */
package proj4;

import java.util.Locale;
import java.util.Scanner;

/**
 * Model a simple poker game
 */

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck gameDeck = new Deck();
        gameDeck.shuffle();
        CommunityCardSet cc = new CommunityCardSet(gameDeck.deal(5));

        int numPoints = 0;
        boolean gameOver = false;

        while(!gameOver) {
            System.out.println("The community cards are:\n" + cc.toString());
            System.out.println("\nWhich of the following hands are better?\n");
            StudPokerHand handA = new StudPokerHand(cc, gameDeck.deal(2));
            StudPokerHand handB = new StudPokerHand(cc, gameDeck.deal(2));
            System.out.println("Hand A: \n" + handA.toString());
            System.out.println("Hand B: \n" + handB.toString());

            System.out.println("Enter a or b to indicate they win, or anything else to indicate a tie: ");
            String userGuess = scanner.nextLine();

            if(userGuess.toLowerCase(Locale.ROOT).equals("a")) {
                if(handA.compareTo(handB) > 0) {
                    numPoints += 1;
                    System.out.println("Correct!");
                } else {
                    gameOver=true;
                }
            } else if(userGuess.toLowerCase(Locale.ROOT).equals("b")) {
                if(handA.compareTo(handB) < 0) {
                    numPoints += 1;
                    System.out.println("Correct!");
                } else {
                    gameOver=true;
                }
            } else {
                if(handA.compareTo(handB) == 0) {
                    numPoints += 1;
                    System.out.println("Correct!");
                } else {
                    gameOver=true;
                }
            }
        }

        System.out.println("Game over! Number of points: " + numPoints);


    }

}
