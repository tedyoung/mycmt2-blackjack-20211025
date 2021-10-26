package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BlackjackControllerTest {

    @Test
    public void newBlackjackControllerNoCardsDealtToPlayer() throws Exception {
        Game game = new Game();

        BlackjackController blackjackController = new BlackjackController(game);

        assertThat(game.playerHand().cards())
                .isEmpty();
    }

    @Test
    public void startGameResultsInInitialDeal() throws Exception {
        Game game = new Game();
        BlackjackController blackjackController = new BlackjackController(game);

        blackjackController.startGame();

        assertThat(game.playerHand().cards())
                .hasSize(2);
    }
}