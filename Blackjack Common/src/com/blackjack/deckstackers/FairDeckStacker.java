package com.blackjack.deckstackers;

import com.blackjack.cards.Card;


public class FairDeckStacker extends DeckStacker {

	public FairDeckStacker() {
		super();
	}

	public FairDeckStacker(boolean shuffle) {
		super(shuffle);
	}

	public void buildShoe() {
		for  (int i=0; i< getShoe().getNumberOfDecks(); i++) {
			for (Card c:newDeck) {
				getShoe().add(c);
			}
		}
		if (getShuffle())
			shoe.shuffle();
	}

}
