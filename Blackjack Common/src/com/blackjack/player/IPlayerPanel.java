package com.blackjack.player;

import com.blackjack.cards.Hand;

public interface IPlayerPanel {

	public abstract void showCards(Hand playerHand, Hand dealerHand);

	public abstract void clearCards();

	public abstract void disableButton(Play disableAction);

	public abstract void enableButton(Play enableAction);

	public abstract void disableAllButtons();

	public abstract void enableAllButtons();
	
	public abstract void showJokers();

}