package proj4;

import java.util.ArrayList;

public class PokerHandTester {

    public static void main(String[] args) {
        runCompareToTests();
        runAddCardTests();
    }

    private static void runAddCardTests() {
        Testing.startTests();
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(10, "spades"));
        PokerHand hand = new PokerHand(cards);
        Testing.assertEquals("Test initial constructor", 1, hand.getHandSize());
        hand.addCard(new Card(5, "diamonds"));
        Testing.assertEquals("Test add card", 2, hand.getHandSize());
        Testing.finishTests();
    }


    private static void runCompareToTests() {
        runCompareToTestsTypes();
        runCompareToTestFlushes();
        runCompareToTestTwoPairs();
        runCompareToTestPairs();
        runCompareToTestHighCards();
    }


    private static void runCompareToTestsTypes() {
        Testing.startTests();
        compareFlushTwoPair();
        compareFlushPair();
        compareFlushHighCard();
        compareTwoPairPair();
        compareTwoPairHighCard();
        comparePairHighCard();
        compareFullHouseFourKind();
        Testing.finishTests();
    }


    private static void runCompareToTestFlushes() {
        Testing.startTests();
        compareHighFlushes();
        compareOtherFlushes();
        compare4ThHighestFlush();
        Testing.finishTests();
    }


    private static void runCompareToTestTwoPairs() {
        Testing.startTests();
        compareTwoPair1StHighCard();
        compareTwoPairFirstPairHigh();
        compareFullHouseTwoPair();
        compareTwoPairSecondPairHigh();
        compareTwoPair4KindHighCard();
        Testing.finishTests();
    }


    private static void runCompareToTestPairs() {
        Testing.startTests();
        comparePairThreeKind();
        comparePairPair();
        comparePairThreeKind2();
        comparePairPair2();
        Testing.finishTests();
    }


    private static void runCompareToTestHighCards() {
        Testing.startTests();
        compareHighCardStraight();
        compareHighCardToHighCard();
        compareHighCardToHighCard2();
        Testing.finishTests();
    }


    private static void compareHighCardToHighCard2() {
        PokerHand highCard = new PokerHand(new Card[]{new Card(12, "D"), new Card(10, "S"), new Card(8, "D"), new Card(5, "C"), new Card(3, "D")});

        PokerHand highCard2 = new PokerHand(
                new Card[]{new Card(12, "S"), new Card(10, "D"), new Card(8, "S"), new Card(5, "H"), new Card(2, "S")}
        );

        int expected = 1;
        int actual = highCard.compareTo(highCard2);

        Testing.assertEquals("highCard compare_to highCard2 with low 5th card", expected, actual);
    }


    private static void compareHighCardToHighCard() {
        PokerHand highCard = new PokerHand(
                new Card[]{new Card(12, "D"), new Card(10, "S"), new Card(2, "D"), new Card(5, "C"), new Card(8, "D")}
        );

        PokerHand highCard2 = new PokerHand(
                new Card[]{new Card(11, "S"), new Card(3, "D"), new Card(14, "S"), new Card(8, "H"), new Card(12, "S")}
        );

        int expected = -1;
        int actual = highCard.compareTo(highCard2);

        Testing.assertEquals("highCard compare_to highCard2 with low 1st card", expected, actual);
    }


    private static void compareHighCardStraight() {
        PokerHand highCard = new PokerHand(
                new Card[]{new Card(12, "D"), new Card(10, "S"), new Card(2, "D"), new Card(5, "C"), new Card(8, "D")}
        );

        PokerHand straight = new PokerHand(
                new Card[]{new Card(11, "S"), new Card(10, "D"), new Card(9, "S"), new Card(8, "H"), new Card(12, "S")}
        );

        int expected = -1;
        int actual = highCard.compareTo(straight);

        Testing.assertEquals("highCard compare_to straight with low 2nd card", expected, actual);
    }


    private static void comparePairPair2() {
        PokerHand pair = new PokerHand(
                new Card[]{new Card(12, "D"), new Card(12, "S"), new Card(2, "D"), new Card(5, "D"), new Card(8, "D")}
        );

        PokerHand pair2 = new PokerHand(
                new Card[]{new Card(11, "S"), new Card(11, "D"), new Card(9, "S"), new Card(5, "H"), new Card(2, "S")}
        );

        int expected = 1;
        int actual = pair.compareTo(pair2);

        Testing.assertEquals("pair compare_to pair with high pair", expected, actual);
    }


    private static void comparePairThreeKind2() {
        PokerHand pair = new PokerHand(
                new Card[]{new Card(7, "D"), new Card(7, "S"), new Card(2, "D"), new Card(5, "D"), new Card(12, "D")}
        );

        PokerHand threeKind = new PokerHand(
                new Card[]{new Card(11, "S"), new Card(11, "D"), new Card(11, "S"), new Card(5, "H"), new Card(2, "S")}
        );

        int expected = -1;
        int actual = pair.compareTo(threeKind);

        Testing.assertEquals("pair compare_to threeKind with high threeKind pair", expected, actual);
    }


    private static void comparePairThreeKind() {
        PokerHand pair = new PokerHand(
                new Card[]{new Card(13, "D"), new Card(13, "S"), new Card(2, "D"), new Card(5, "D"), new Card(12, "D")}
        );

        PokerHand threeKind = new PokerHand(
                new Card[]{new Card(11, "S"), new Card(11, "D"), new Card(11, "S"), new Card(5, "H"), new Card(2, "S")}
        );

        int expected = 1;
        int actual = pair.compareTo(threeKind);

        Testing.assertEquals("pair compare_to threeKind with high pair", expected, actual);
    }


    private static void comparePairPair() {
        PokerHand pair = new PokerHand(
                new Card[]{new Card(6, "D"), new Card(6, "S"), new Card(2, "D"), new Card(5, "D"), new Card(12, "D")}
        );

        PokerHand pair2 = new PokerHand(
                new Card[]{new Card(11, "S"), new Card(11, "D"), new Card(9, "S"), new Card(5, "H"), new Card(2, "S")}
        );

        int expected = -1;
        int actual = pair.compareTo(pair2);

        Testing.assertEquals("pair compare_to pair with low pair", expected, actual);
    }


    private static void compareTwoPair1StHighCard() {
        PokerHand twoPair = new PokerHand(
                new Card[]{new Card(13, "D"), new Card(13, "S"), new Card(5, "D"), new Card(5, "D"), new Card(12, "D")}
        );

        PokerHand twoPair2 = new PokerHand(
                new Card[]{new Card(13, "S"), new Card(13, "D"), new Card(5, "S"), new Card(5, "H"), new Card(2, "S")}
        );

        int expected = 1;
        int actual = twoPair.compareTo(twoPair2);

        Testing.assertEquals("twoPair compare_to twoPair with 2 pairs same and high card last", expected, actual);
    }


    private static void compareTwoPairFirstPairHigh() {
        PokerHand twoPair = new PokerHand(
                new Card[]{new Card(13, "D"), new Card(13, "S"), new Card(5, "D"), new Card(5, "D"), new Card(12, "D")}
        );

        PokerHand twoPair2 = new PokerHand(
                new Card[]{new Card(14, "S"), new Card(14, "D"), new Card(5, "S"), new Card(5, "H"), new Card(2, "S")}
        );

        int expected = -1;
        int actual = twoPair.compareTo(twoPair2);

        Testing.assertEquals("twoPair compare_to twoPair with first high pair", expected, actual);
    }


    private static void compareFullHouseTwoPair() {
        PokerHand fullHouse = new PokerHand(
                new Card[]{new Card(13, "D"), new Card(13, "S"), new Card(13, "D"), new Card(5, "D"), new Card(5, "D")}
        );

        PokerHand twoPair2 = new PokerHand(
                new Card[]{new Card(14, "S"), new Card(14, "D"), new Card(5, "S"), new Card(5, "H"), new Card(2, "S")}
        );

        int expected = -1;
        int actual = fullHouse.compareTo(twoPair2);

        Testing.assertEquals("fullHouse compare_to fullHouse with first pair low", expected, actual);
    }


    private static void compareTwoPairSecondPairHigh() {
        PokerHand twoPair = new PokerHand(
                new Card[]{new Card(13, "D"), new Card(13, "S"), new Card(12, "D"), new Card(12, "D"), new Card(5, "D")}
        );

        PokerHand twoPair2 = new PokerHand(
                new Card[]{new Card(13, "S"), new Card(13, "D"), new Card(11, "S"), new Card(11, "H"), new Card(2, "S")}
        );

        int expected = 1;
        int actual = twoPair.compareTo(twoPair2);

        Testing.assertEquals("twoPair compare_to twoPair with second pair high", expected, actual);
    }


    private static void compareFlushTwoPair() {
        PokerHand flush = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(7, "D"), new Card(6, "D"), new Card(5, "D"), new Card(4, "D")}
        );

        PokerHand twoPair = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(8, "S"), new Card(12, "D"), new Card(12, "C"), new Card(3, "D")}
        );

        int expected = 1;
        int actual = flush.compareTo(twoPair);

        Testing.assertEquals("flush compare_to twoPair", expected, actual);
    }


    private static void compareFlushPair() {
        PokerHand flush = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(7, "D"), new Card(6, "D"), new Card(5, "D"), new Card(4, "D")}
        );

        PokerHand pair = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(9, "S"), new Card(12, "D"), new Card(12, "C"), new Card(3, "D")}
        );

        int expected = 2;
        int actual = flush.compareTo(pair);

        Testing.assertEquals("flush compare_to pair", expected, actual);
    }


    private static void compareFlushHighCard() {
        PokerHand flush = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(7, "D"), new Card(6, "D"), new Card(5, "D"), new Card(4, "D")}
        );

        PokerHand highCard = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(2, "S"), new Card(12, "D"), new Card(14, "C"), new Card(3, "D")}
        );

        int expected = -3;
        int actual = highCard.compareTo(flush);

        Testing.assertEquals("flush compare_to highCard", expected, actual);
    }


    private static void compareTwoPairPair() {
        PokerHand twoPair = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(8, "S"), new Card(12, "D"), new Card(12, "C"), new Card(3, "D")}
        );

        PokerHand pair = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(9, "S"), new Card(12, "D"), new Card(12, "C"), new Card(3, "D")}
        );

        int expected = 1;
        int actual = twoPair.compareTo(pair);

        Testing.assertEquals("twoPair compare_to pair", expected, actual);
    }


    private static void compareTwoPairHighCard() {
        PokerHand twoPair = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(8, "S"), new Card(12, "D"), new Card(12, "C"), new Card(3, "D")}
        );

        PokerHand highCard = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(2, "S"), new Card(11, "D"), new Card(12, "C"), new Card(3, "D")}
        );

        int expected = 2;
        int actual = twoPair.compareTo(highCard);

        Testing.assertEquals("twoPair compare_to highCard", expected, actual);
    }


    private static void comparePairHighCard() {
        PokerHand pair = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(2, "S"), new Card(12, "D"), new Card(12, "C"), new Card(3, "D")}
        );

        PokerHand highCard = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(2, "S"), new Card(11, "D"), new Card(12, "C"), new Card(3, "D")}
        );

        int expected = 1;
        int actual = pair.compareTo(highCard);

        Testing.assertEquals("twoPair compare_to highCard", expected, actual);

    }

    private static void compareHighFlushes() {
        PokerHand flush1 = new PokerHand(
                new Card[]{new Card(8, "D"), new Card(2, "D"), new Card(12, "D"), new Card(12, "D"), new Card(3, "D")}
        );

        PokerHand flush2 = new PokerHand(
                new Card[]{new Card(8, "S"), new Card(2, "S"), new Card(11, "S"), new Card(11, "S"), new Card(3, "S")}
        );

        int expected = 1;
        int actual = flush1.compareTo(flush2);

        Testing.assertEquals("twoPair compare_to highCard", expected, actual);
    }


    private static void compareOtherFlushes() {
        PokerHand flush1 = new PokerHand(
                new Card[]{new Card(13, "D"), new Card(7, "D"), new Card(5, "D"), new Card(10, "D"), new Card(3, "D")}
        );

        PokerHand flush2 = new PokerHand(
                new Card[]{new Card(13, "S"), new Card(7, "S"), new Card(5, "S"), new Card(10, "S"), new Card(2, "S")}
        );

        int expected = 1;
        int actual = flush1.compareTo(flush2);

        Testing.assertEquals("twoPair compare_to highCard", expected, actual);
    }


    private static void compare4ThHighestFlush() {
        PokerHand flush1 = new PokerHand(
                new Card[]{new Card(4, "D"), new Card(11, "D"), new Card(12, "D"), new Card(14, "D"), new Card(5, "D")}
        );

        PokerHand flush2 = new PokerHand(
                new Card[]{new Card(3, "S"), new Card(12, "S"), new Card(11, "S"), new Card(14, "S"), new Card(2, "S")}
        );

        int expected = 1;
        int actual = flush1.compareTo(flush2);

        Testing.assertEquals("twoPair compare_to highCard", expected, actual);
    }


    private static void compareTwoPair4KindHighCard() {
        PokerHand twoPair = new PokerHand(
                new Card[]{new Card(14, "D"), new Card(14, "S"), new Card(3, "C"), new Card(3, "D"), new Card(5, "D")}
        );

        PokerHand fourKind = new PokerHand(
                new Card[]{new Card(13, "S"), new Card(13, "D"), new Card(13, "C"), new Card(13, "H"), new Card(6, "S")}
        );

        int expected = 1;
        int actual = twoPair.compareTo(fourKind);

        Testing.assertEquals("twoPair compare_to fourKind", expected, actual);
    }

    private static void compareFullHouseFourKind() {
        PokerHand fullHouse = new PokerHand(
                new Card[]{new Card(13, "D"), new Card(13, "S"), new Card(14, "C"), new Card(14, "S"), new Card(14, "D")}
        );

        PokerHand fourKind = new PokerHand(
                new Card[]{new Card(11, "S"), new Card(14, "D"), new Card(14, "C"), new Card(14, "H"), new Card(14, "S")}
        );

        int expected = -1;
        int actual = fullHouse.compareTo(fourKind);

        Testing.assertEquals("fullHouse compare_to fourKind", expected, actual);
    }


}