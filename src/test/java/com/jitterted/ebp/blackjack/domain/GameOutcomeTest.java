package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    public void playerHitsGoesBustThenOutcomeIsPlayerIsBustedAndLoses() throws Exception {
        Game game = new Game(StubDeck.playerHitsAndGoesBust());
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_BUSTED);
    }

    @Test
    public void playerDealtBetterHandThanDealerAndStandsThenPlayerBeatsDealer() throws Exception {
        Game game = new Game(StubDeck.playerStandsAndBeatsDealer());
        game.initialDeal();

        game.playerStands();

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_WINS);
    }

    @Test
    public void playerDealtBlackjackUponInitialDealThenPlayerWinsBlackjack() throws Exception {
        Deck playerDealtBlackjack = StubDeck.playerDealtBlackjack();
        Game game = new Game(playerDealtBlackjack);

        game.initialDeal();

        assertThat(game.isPlayerDone())
                .isTrue();
        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_WINS_BLACKJACK);
    }

    @Test
    public void initialDealWithNoBlackjackThenPlayerIsNotDone() throws Exception {
        Game game = new Game(StubDeck.playerStandsAndBeatsDealer());

        game.initialDeal();

        assertThat(game.isPlayerDone())
                .isFalse();
    }

    @Test
    public void standResultsInDealerDrawingCardOnTheirTurn() throws Exception {
        Deck dealerDrawsAdditionalCard = StubDeck.dealerDrawsAdditionalCard();
        Game game = new Game(dealerDrawsAdditionalCard);
        game.initialDeal();

        game.playerStands();

        assertThat(game.dealerHand().cards())
                .hasSize(3);
    }

}