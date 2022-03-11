package proj4;

public class DeckTester {


    public static void main(String[] args) {
        //Testing.setVerbose(true);
        Testing.startTests();
        testConstructors();
        testDeal();
        testShuffleAndGather();
        testSizeAndEmpty();
        Testing.finishTests();
    }

    private static void testConstructors() {
        Deck deck = new Deck();

        String deckString = "[2 of Spades, 2 of Hearts, 2 of Clubs, 2 of Diamonds, 3 of Spades, 3 of Hearts, 3 of Clubs, 3 of Diamonds, 4 of Spades, 4 of Hearts, 4 of Clubs, 4 of Diamonds, 5 of Spades, 5 of Hearts, 5 of Clubs, 5 of Diamonds, 6 of Spades, 6 of Hearts, 6 of Clubs, 6 of Diamonds, 7 of Spades, 7 of Hearts, 7 of Clubs, 7 of Diamonds, 8 of Spades, 8 of Hearts, 8 of Clubs, 8 of Diamonds, 9 of Spades, 9 of Hearts, 9 of Clubs, 9 of Diamonds, 10 of Spades, 10 of Hearts, 10 of Clubs, 10 of Diamonds, Jack of Spades, Jack of Hearts, Jack of Clubs, Jack of Diamonds, Queen of Spades, Queen of Hearts, Queen of Clubs, Queen of Diamonds, King of Spades, King of Hearts, King of Clubs, King of Diamonds, Ace of Spades, Ace of Hearts, Ace of Clubs, Ace of Diamonds]";

        Testing.assertEquals("Test Constructor with toString", deckString, deck.toString());
    }

    private static void testDeal() {
        Deck deck = new Deck();

        Testing.assertEquals("Test first deal", "2 of Spades", deck.deal().toString());
        Testing.assertEquals("Test second deal", "2 of Hearts", deck.deal().toString());
        deck.deal();
        deck.deal();
        Testing.assertEquals("Test fifth deal", "3 of Spades", deck.deal().toString());

        deck = new Deck();
        Testing.assertEquals("Test deal of first 5 cards", "[2 of Spades, 2 of Hearts, 2 of Clubs, 2 of Diamonds, 3 of Spades]", deck.deal(5).toString());
    }

    private static void testShuffleAndGather() {
        Deck unshuffledDeck = new Deck();
        Deck shuffledDeck = new Deck();
        shuffledDeck.shuffle();

        Testing.assertEquals("Test first card", false,
                (unshuffledDeck.get_ith_index(0).toString().equals(shuffledDeck.get_ith_index(0).toString())));

        Testing.assertEquals("Test middle card", false,
                (unshuffledDeck.get_ith_index(10).toString().equals(shuffledDeck.get_ith_index(10).toString())));

        Testing.assertEquals("Test last card", false,
                (unshuffledDeck.get_ith_index(51).toString().equals(shuffledDeck.get_ith_index(51).toString())));

        shuffledDeck.gather();

        Testing.assertEquals("Test first card", false,
                (unshuffledDeck.get_ith_index(0).toString().equals(shuffledDeck.get_ith_index(0).toString())));

        Testing.assertEquals("Test middle card", false,
                (unshuffledDeck.get_ith_index(21).toString().equals(shuffledDeck.get_ith_index(10).toString())));

        Testing.assertEquals("Test last card", false,
                (unshuffledDeck.get_ith_index(51).toString().equals(shuffledDeck.get_ith_index(51).toString())));
    }


    private static void testSizeAndEmpty() {
        Deck deck = new Deck();

        Testing.assertEquals("Test initial size", 52, deck.size());
        Testing.assertEquals("Test initial empty", false, deck.isEmpty());

        deck.deal();
        deck.deal();

        Testing.assertEquals("Test size after 2 deals", 50, deck.size());

        for (int i = 0; i < 10; i++) {
            deck.deal();
        }

        Testing.assertEquals("Test size after 12 deals", 40, deck.size());
        Testing.assertEquals("Test empty after 12 deals", false, deck.isEmpty());

        deck.gather();

        for (int i = 0; i < 52; i++) {
            deck.deal();
        }

        Testing.assertEquals("Test size after all cards dealt", 0, deck.size());
        Testing.assertEquals("Test empty after all cards dealt", true, deck.isEmpty());

        deck.gather();

        for (int i = 0; i < 50; i++) {
            deck.deal();
        }

        Testing.assertEquals("Test empty deck deal multiple cards", null, deck.deal(4));


    }


}
