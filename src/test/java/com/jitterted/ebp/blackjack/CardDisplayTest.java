package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDisplayTest {

    @Test
    public void displayTenCard() throws Exception {
        Card card = new Card(Suit.CLUBS, Rank.TEN);

        assertThat(card.display())
                .isEqualTo("[30m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }

    @Test
    public void displayNonTenCard() throws Exception {
        Card card = new Card(Suit.DIAMONDS, Rank.FIVE);

        assertThat(card.display())
                .isEqualTo("[31m┌─────────┐[1B[11D│5        │[1B[11D│         │[1B[11D│    ♦    │[1B[11D│         │[1B[11D│        5│[1B[11D└─────────┘");
    }
}
