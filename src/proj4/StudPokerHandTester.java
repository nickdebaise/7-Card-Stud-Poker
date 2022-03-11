package proj4;

import java.util.ArrayList;

public class StudPokerHandTester {


    public static void main(String[] args) {
        Testing.startTests();
        testGettersAndConstructors();
        twoPairLosesFourKind();
        testTwoPairLosesFourKind2();
        testPairLosesTwoPair();
        testPairLosesFourKind();
        Testing.finishTests();
    }

    private static void testGettersAndConstructors() {
        Deck deck = new Deck();
        CommunityCardSet communitySet = new CommunityCardSet(deck.deal(5));
        StudPokerHand studHand = new StudPokerHand(communitySet, deck.deal(1));
        Testing.assertEquals("first card in hand is 3 of clubs", "3 of Hearts", studHand.getIthCard(0).toString());
        Testing.assertEquals("get hand size is 1", 1, studHand.getHandSize());
        studHand.addCard(new Card(5, "diamonds"));
        Testing.assertEquals("last card in hand is 5 of diamonds", "5 of diamonds", studHand.getIthCard(1).toString());
        studHand.addCard(new Card(7, "diamonds"));
        Testing.assertEquals("get hand size is still 2", 2, studHand.getHandSize());
        Testing.assertEquals("toString test", "3 of Hearts | 5 of diamonds", studHand.toString());
        Testing.assertEquals("add card returns null if out of bounds", null, studHand.getIthCard(7));
        Testing.assertEquals("add card returns null if out of bounds", null, studHand.getIthCard(-2));

    }


    private static void twoPairLosesFourKind() {

        ArrayList<Card> ccHand = new ArrayList<>();
        ccHand.add(new Card(13, "D"));
        ccHand.add(new Card(7, "H"));
        ccHand.add(new Card(10, "S"));
        ccHand.add(new Card(7, "D"));
        ccHand.add(new Card(6, "C"));
        CommunityCardSet cc = new CommunityCardSet(ccHand);

        ArrayList<Card> handAHand = new ArrayList<>();
        handAHand.add(new Card(7, "S"));
        handAHand.add(new Card(7, "C"));
        StudPokerHand handA = new StudPokerHand(cc, handAHand);

        ArrayList<Card> handBHand = new ArrayList<>();
        handBHand.add(new Card(7, "S"));
        handBHand.add(new Card(10, "C"));
        StudPokerHand handB = new StudPokerHand(cc, handBHand);

        Testing.assertEquals("test two 2 pairs worse than 4 kind 1", -1, handA.compareTo(handB));

    }


    private static void testTwoPairLosesFourKind2() {
        ArrayList<Card> ccHand = new ArrayList<>();
        ccHand.add(new Card(13, "D"));
        ccHand.add(new Card(13, "H"));
        ccHand.add(new Card(13, "S"));
        ccHand.add(new Card(12, "D"));
        ccHand.add(new Card(6, "C"));
        CommunityCardSet cc = new CommunityCardSet(ccHand);

        ArrayList<Card> handAHand = new ArrayList<>();
        handAHand.add(new Card(10, "S"));
        handAHand.add(new Card(10, "C"));
        StudPokerHand handA = new StudPokerHand(cc, handAHand);

        ArrayList<Card> handBHand = new ArrayList<>();
        handBHand.add(new Card(13, "S"));
        handBHand.add(new Card(3, "C"));
        StudPokerHand handB = new StudPokerHand(cc, handBHand);

        Testing.assertEquals("test two 2 pairs worse than 4 kind 2", -1, handA.compareTo(handB));
    }

    private static void testPairLosesFourKind() {
        ArrayList<Card> ccHand = new ArrayList<>();
        ccHand.add(new Card(14, "D"));
        ccHand.add(new Card(14, "H"));
        ccHand.add(new Card(11, "S"));
        ccHand.add(new Card(6, "D"));
        ccHand.add(new Card(3, "C"));
        CommunityCardSet cc = new CommunityCardSet(ccHand);

        ArrayList<Card> handAHand = new ArrayList<>();
        handAHand.add(new Card(14, "S"));
        handAHand.add(new Card(14, "C"));
        StudPokerHand handA = new StudPokerHand(cc, handAHand);

        ArrayList<Card> handBHand = new ArrayList<>();
        handBHand.add(new Card(5, "S"));
        handBHand.add(new Card(3, "C"));
        StudPokerHand handB = new StudPokerHand(cc, handBHand);

        Testing.assertEquals("test pair loses two pair", 1, handA.compareTo(handB));
    }


    private static void testPairLosesTwoPair() {
        ArrayList<Card> ccHand = new ArrayList<>();
        ccHand.add(new Card(14, "D"));
        ccHand.add(new Card(13, "H"));
        ccHand.add(new Card(11, "S"));
        ccHand.add(new Card(6, "D"));
        ccHand.add(new Card(3, "C"));
        CommunityCardSet cc = new CommunityCardSet(ccHand);

        ArrayList<Card> handAHand = new ArrayList<>();
        handAHand.add(new Card(14, "S"));
        handAHand.add(new Card(13, "C"));
        StudPokerHand handA = new StudPokerHand(cc, handAHand);

        ArrayList<Card> handBHand = new ArrayList<>();
        handBHand.add(new Card(13, "S"));
        handBHand.add(new Card(3, "C"));
        StudPokerHand handB = new StudPokerHand(cc, handBHand);

        Testing.assertEquals("test pair loses two pair", 1, handA.compareTo(handB));
    }


}
