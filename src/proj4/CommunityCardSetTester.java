package proj4;

import java.util.ArrayList;

/**
 * Test community card set
 */

public class CommunityCardSetTester {

    public static void main(String[] args) {
        Testing.startTests();
        testCommunityCards();
        Testing.finishTests();
    }

    private static void testCommunityCards() {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(2, "spades"));
        cards.add(new Card(3, "hearts"));
        cards.add(new Card(6, "clubs"));
        cards.add(new Card(10, "diamonds"));

        CommunityCardSet set = new CommunityCardSet(cards);

        Testing.assertEquals("Test 3rd card is 6 clubs", "6 of clubs", set.getIthCard(2).toString());
        Testing.assertEquals("First card is 2 spades", "2 of spades", set.getIthCard(0).toString());
        Testing.assertEquals("Last card is 10 diamonds", "10 of diamonds", set.getIthCard(3).toString());
        Testing.assertEquals("Hand size is 4", 4, set.getHandSize());
        set.addCard(new Card(14, "hearts"));
        Testing.assertEquals("Hand size is now 5", 5, set.getHandSize());
        Testing.assertEquals("Last card is now ace hearts", "Ace of hearts", set.getIthCard(4).toString());

    }
}
