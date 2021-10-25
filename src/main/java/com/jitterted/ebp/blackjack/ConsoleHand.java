package com.jitterted.ebp.blackjack;

public class ConsoleHand {

    // TRANSFORMING Domain (Hand) --> ADAPTER (String)
    static String displayFirstCard(Hand hand) {
        return ConsoleCard.display(hand.firstCard());
    }

}
