package com.blackjack.deckstackers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.blackjack.cards.Card;
import com.blackjack.cards.Card.Rank;

public class HardDeckStacker extends DeckStacker {

	// shoe is declared in SuperClass (DeckStacker)
	private Card playerCard;
	private int numberOfDecks;
	private ArrayList<Card> decks = new ArrayList<Card>();
	// private ArrayList<Card> stackedDeck = new ArrayList<Card>();
	private ArrayList<Card> aces = new ArrayList<Card>(); // take out the aces
//	private ArrayList<Card> playerCards = new ArrayList<Card>(); // dealer cards
	private ArrayList<Card> pairCards = new ArrayList<Card>(); // store the
																// pairs
	
	public HardDeckStacker() {
		super(false); // never shuffle soft-deck
	}

	@Override
	public void buildShoe() {
		// each sequence of three cards will be a pair surrounding dealer's card
		// (any other)
		// i.e., dealer's card in middle
		// example: 2 Clubs, Queen Diamonds, 2 Hearts
		// for more than one deck the pair can be same suit

		// strategy:
		// 1) arrange decks in numerical sequence
		// 2) pull out aces into aces array
		// 3) deal player card (no aces) and dealer card (which might include
		// ace)
		// 4) pull all identical cards to player card and deal another player
		// card
		// 5) put back the identical cards
		// 6) build the shoe

		numberOfDecks = shoe.getNumberOfDecks();
		decks.clear();
		aces.clear();
		
		buildDecks(); // build original decks
		pullOutAces(); // pull the aces
		dealCards(); // no Blackjacks
	}

	private void dealCards() {
		// allow for pair removal
		while (decks.size() > 6) {
			Collections.shuffle(decks);
			getPlayerCard();
			getDealerCard();
			removePairs();
			getPlayerCard();
			putPairsBack();
		}
	}

	private void putPairsBack() {
		while (pairCards.size() > 0) {
			decks.add(pairCards.remove(0));
		}
	}

	private void removePairs() {
		Collections.sort(decks);
		for (int i = decks.size()-1; i >= 0; i--) {
			if (decks.get(i).rank().equals(playerCard.rank())) {
				pairCards.add(decks.remove(i));
			}
		}
//		System.out.println(playerCard.toString());
//		System.out.println(decks.toString());
	}

	private void getDealerCard() {
		// Chance of an ace is # aces/# cards + # aces
		int acesLeft = aces.size();
		if (acesLeft == 0)
			shoe.add(decks.remove(0));
		else if (decks.size()==0)
			shoe.add(aces.remove(0));
		else
		{
			double aceRatio = (double) aces.size()
					/ (double) (aces.size() + decks.size());
			int aceChance = (int) (aceRatio * 100.);
			int acePick = (int) (Math.random() * 100);
			if (acePick <= aceChance)
				shoe.add(aces.remove(0));
			else
				shoe.add(decks.remove(0));
		}
	}

	private void getPlayerCard() {
		playerCard = decks.remove(0);
		shoe.add(playerCard);
	}

	private void pullOutAces() {
		sortDecksAcesLow();
		Card c = decks.remove(0);
		while (c.isRank(Rank.ACE)) {
			aces.add(c);
			c = decks.remove(0);
		}
		decks.add(c); // put back last drawn card (not an ace)
	}

	private void sortDecksAcesLow() {
		class RankComparator implements Comparator<Card> {
			public int compare(Card c1, Card c2) {
				if (c1.isRank(Rank.ACE) && c2.isNotRank(Rank.ACE)) {
					return -1;
				} else if (c1.isNotRank(Rank.ACE) && c2.isRank(Rank.ACE))
					return 1;
				else
					return (c1.rank().compareTo(c2.rank()));
			}
		}
		RankComparator rc = new RankComparator();
		Collections.sort(decks, rc);
		// System.out.println(decks);
	}

	private void buildDecks() {
		// //shuffle the suits and sort in rank (2-A) order
		// ArrayList<Card> shuffleSuits = new ArrayList<Card>();
		for (int i = 0; i < numberOfDecks; i++) {
			decks.addAll(newDeck);
		}
		Collections.shuffle(decks); // shuffle the suits
	}

}
