package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;

public class Game {

    private final Deck deck;
    private final GameMonitor gameMonitor;

    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();

    private boolean playerDone;

    public Game() {
        this(new Deck());
    }

    public Game(Deck deck) {
        this(deck,
             game -> {});
    }

    public Game(Deck deck, GameMonitor gameMonitor) {
        this.deck = deck;
        this.gameMonitor = gameMonitor;
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }


    public GameOutcome determineOutcome() {
        // if player is NOT done -> throw exception
        if (playerHand.isBusted()) {
            return GameOutcome.PLAYER_BUSTED;
        } else if (dealerHand.isBusted()) {
            return GameOutcome.DEALER_BUSTED;
        } else if (playerHand.isBlackjack()) {
            return GameOutcome.PLAYER_WINS_BLACKJACK;
        } else if (playerHand.beats(dealerHand)) {
            return GameOutcome.PLAYER_WINS;
        } else if (playerHand.pushes(dealerHand)) {
            return GameOutcome.PLAYER_PUSHES;
        } else {
            return GameOutcome.PLAYER_LOSES;
        }
    }

    private void dealerTurn() {
        // Dealer makes its choice automatically based on a simple heuristic (<=16 must hit, =>17 must stand)
        if (!playerHand.isBusted()) {
            while (dealerHand.dealerMustDrawCard()) {
                dealerHand.drawFrom(deck);
            }
        }
    }

    // QUERY: must return SNAPSHOT and not allow indirect change of state
    // HOW to make this pure query?
    // 1. Implement clone() - implies that the consumer WANTS a HAND
    // 2. Create a "view" (Domain "Payload" Object), "projection"
    //    HandView: value(), cards()
    public Hand playerHand() {
        return playerHand;
    }

    public Hand dealerHand() {
        return dealerHand;
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
        updatePlayerDone(playerHand.isBlackjack());
    }

    public void playerHits() {
        playerHand.drawFrom(deck);
        updatePlayerDone(playerHand.isBusted());
    }

    public void playerStands() {
        dealerTurn();
        updatePlayerDone(true);
    }

    public boolean isPlayerDone() {
        return playerDone;
    }

    // in reality, in a GameService:
    // retrieve game from database by its ID
    // game.playerStands()
    // game.isPlayerDone() -> if true, call GameMonitor

    private void updatePlayerDone(boolean playerIsDone) {
        playerDone = playerIsDone;
        if (playerDone) {
            gameMonitor.roundCompleted(this);
        }
    }

}
