package com.blackjack.strategy;

import com.blackjack.cards.Card;
import com.blackjack.cards.Hand;
import com.blackjack.player.Play;

public class BasicStrategy implements IStrategy {

	private static Play[][] playForPairs = new Play[10][10];
	private static Play[][] playForSoftHands = new Play[10][10];
	private static Play[][] playForHardHands = new Play[19][10];

	private BasicStrategy() {
		super();
		playForPairs = BuildPlayForPairs.build();
		playForSoftHands = BuildPlayForSoftHands.build();
		playForHardHands = BuildPlayForHardHands.build();
	}

	public static BasicStrategy createBasicStrategy() {
		return new BasicStrategy();
	}

	public Play getPlay(Card dealerCard, Hand playerHand) {
		// too complicated...figure a way to make this simple

		Play play = Play.STAND;
		if (playerHand.isAPair())
			play = getPlayForPairs(dealerCard, playerHand);
		else if (playerHand.isSoft())
			play = getPlayForSoft(dealerCard, playerHand);
		else
			play = getPlayForHard(dealerCard, playerHand);
		return play;
	}

	private Play getPlayForHard(Card dealerCard, Hand playerHand) {
		Card playerCard1, playerCard2;
		playerCard1 = playerHand.getHand().get(0);
		playerCard2 = playerHand.getHand().get(1);
		int totalFaceValue = playerCard1.faceValue() + playerCard2.faceValue();
		return playForHardHands[totalFaceValue-3][dealerCard.faceValue() - 2];
	}

	private Play getPlayForSoft(Card dealerCard, Hand playerHand) {
		Card nonAce;
		if (playerHand.getHand().get(0).rank().equals(Card.Rank.ACE))
			nonAce = playerHand.getHand().get(1);
		else
			nonAce = playerHand.getHand().get(0);
		return playForSoftHands[nonAce.faceValue() - 2][dealerCard.faceValue() - 2];
	}

	// TODO Auto-generated method stub

	private Play getPlayForPairs(Card dealerCard, Hand playerHand) {
		return playForPairs[playerHand.getHand().get(0).faceValue() - 2][dealerCard
				.faceValue() - 2];
	}
}
