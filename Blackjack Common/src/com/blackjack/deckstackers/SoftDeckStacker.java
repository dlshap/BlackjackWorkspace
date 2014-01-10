package com.blackjack.deckstackers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import com.blackjack.cards.Card;
import com.blackjack.cards.Card.Rank;

public class SoftDeckStacker extends DeckStacker {

	// shoe is declared in SuperClass (DeckStacker)
	private int numberOfDecks;
	private ArrayList<Card> decks = new ArrayList<Card>();
	// private ArrayList<Card> stackedDeck = new ArrayList<Card>();
	private ArrayList<Card> aces = new ArrayList<Card>(); // take out the aces
	private ArrayList<Card> dealerCards = new ArrayList<Card>(); // dealer cards

	public SoftDeckStacker() {
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
		// 2) pull out (numberOfDecks * 4) dealer cards (up to that many aces
		// (e.g., soft hands) in shoe)
		// 3) pull the remaining aces into the aces array
		// 4) pick an ace, then a dealer card, then a non-ace until no more aces

		numberOfDecks = shoe.getNumberOfDecks();
		decks.clear();
		aces.clear();
		dealerCards.clear();

		buildDecks(numberOfDecks); // build original decks
		pullDealerCards(); // pick the dealer cards
		pullOutRemainingAces(); // get the rest of the aces
		removeTens(); // no Blackjacks
		buildTheShoe(); // pick an ace and another card (random order) and
						// dealer card
	}

	private void removeTens() {
		// remove Tens, Jacks, Queens, and Kings so we don't have a Blackjack
		// (waste of time)
		int deckSize = decks.size();
		for (int i = deckSize-1; i >= 0; i--) {
			if (decks.get(i).faceValue() == 10)
				decks.remove(i);
		}
	}

	private void buildTheShoe() {
		Collections.shuffle(decks);
		Iterator<Card> acesIter = aces.iterator();
		while (acesIter.hasNext()) {
			Card c = acesIter.next();
			int randomCard = (int) (Math.random() * 2);
			if (randomCard == 0) {
				shoe.add(c);
				shoe.add(dealerCards.remove(0));
				shoe.add(decks.remove(0));
			} else {
				shoe.add(decks.remove(0));
				shoe.add(dealerCards.remove(0));
				shoe.add(c);
			}
		}
//		System.out.println(shoe.toString());
	}

	private void pullOutRemainingAces() {
		sortDecksAcesLow();
		Card c = decks.remove(0);
		while (c.isRank(Rank.ACE)) {
			aces.add(c);
			c = decks.remove(0);
		}
		decks.add(c); // put back last drawn card (not an ace)
	}

	private void pullDealerCards() {
		// remove numberOfDecks*4 dealer cards (maximum if no aces drawn)
		for (int i = 0; i < (numberOfDecks * 4); i++) {
			dealerCards.add(decks.remove(0));
		}
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

	private void buildDecks(int numberOfDecks2) {
		// //shuffle the suits and sort in rank (2-A) order
		// ArrayList<Card> shuffleSuits = new ArrayList<Card>();
		for (int i = 0; i < numberOfDecks; i++) {
			decks.addAll(newDeck);
		}
		Collections.shuffle(decks); // shuffle the suits
	}
}
