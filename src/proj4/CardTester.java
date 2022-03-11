package proj4;

public class CardTester {

    public static void main(String[] args) {
        Testing.startTests();
        testConstructors();
        testGetters();
        testCompare();
        Testing.finishTests();
    }

    private static void testCompare() {
        Card card1 = new Card(10, "Diamonds");
        Card card2 = new Card(4, "Spades");
        Card card3 = new Card(10, "Hearts");

        Testing.assertEquals("Test compareTo with high card first", 6, card1.compareTo(card2));
        Testing.assertEquals("Test compareTo with high card second", -6, card2.compareTo(card1));
        Testing.assertEquals("Test compareTo with equal cards", 0, card1.compareTo(card3));

    }

    private static void testConstructors() {
        Card card1 = new Card(10, 3);
        Card card2 = new Card(2, "Spades");
        Card card3 = new Card("14", "Hearts");
        Card card4 = new Card("Jack", "Diamonds");
        Card card5 = new Card("13", "Clubs");


        Testing.assertEquals("Test Constructor with toString 1", "10 of Diamonds", card1.toString());
        Testing.assertEquals("Test Constructor with toString 2", "2 of Spades", card2.toString());
        Testing.assertEquals("Test Constructor with toString 3", "Ace of Hearts", card3.toString());
        Testing.assertEquals("Test Constructor with toString 4", "Jack of Diamonds", card4.toString());
        Testing.assertEquals("Test Constructor with toString 5", "King of Clubs", card5.toString());
    }

    private static void testGetters() {
        Card card1 = new Card(10, "Diamonds");
        Card card2 = new Card(2, "Spades");
        Card card3 = new Card(14, "Hearts");

        Testing.assertEquals("Test getSuit 1", "Diamonds", card1.getSuit());
        Testing.assertEquals("Test getRank 1", 10, card1.getRank());

        Testing.assertEquals("Test getSuit 2", "Spades", card2.getSuit());
        Testing.assertEquals("Test getRank 2", 2, card2.getRank());

        Testing.assertEquals("Test getSuit 3", "Hearts", card3.getSuit());
        Testing.assertEquals("Test getRank 3", 14, card3.getRank());
    }
}
