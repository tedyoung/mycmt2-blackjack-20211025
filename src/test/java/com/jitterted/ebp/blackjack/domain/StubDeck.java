package com.jitterted.ebp.blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StubDeck extends Deck {
    private static final Suit DUMMY_SUIT = Suit.HEARTS;
    private final ListIterator<Card> iterator;

    public StubDeck(Rank... ranks) {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : ranks) {
            cards.add(new Card(DUMMY_SUIT, rank));
        }
        this.iterator = cards.listIterator();
    }

    public StubDeck(List<Card> cards) {
        this.iterator = cards.listIterator();
    }

    public static StubDeck playerHitsAndGoesBust() {
        return new StubDeck(Rank.TEN, Rank.EIGHT,
                            Rank.QUEEN, Rank.JACK,
                            Rank.THREE);
    }

    public static StubDeck playerStandsAndBeatsDealer() {
        return new StubDeck(Rank.TEN, Rank.EIGHT,
                            Rank.QUEEN, Rank.JACK);
    }

    public static StubDeck playerHitsAndDoesNotGoBust() {
        return new StubDeck(Rank.TEN, Rank.EIGHT,
                            Rank.SIX, Rank.JACK,
                            Rank.THREE);
    }

    public static StubDeck playerDealtBlackjack() {
        return new StubDeck(Rank.JACK, Rank.SEVEN,
                            Rank.ACE, Rank.TEN);
    }

    public static StubDeck playerNotDealtBlackjack() {
        return new StubDeck(Rank.JACK, Rank.SEVEN,
                            Rank.NINE, Rank.TEN);
    }

    public static StubDeck dealerDrawsAdditionalCard() {
        return new StubDeck(Rank.TEN, Rank.QUEEN,
                            Rank.NINE, Rank.FIVE,
                            Rank.SIX);
    }

    @Override
    public Card draw() {
        return iterator.next();
    }
}
