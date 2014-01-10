package com.blackjack.deckstackers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.blackjack.cards.Card;

public class PairsOnlyDeckStacker extends DeckStacker {

	private int numberOfDecks, numberOfPairs, cardsLeft;
	private ArrayList<Card> decks = new ArrayList<Card>();
	private ArrayList<Card> stackedDeck = new ArrayList<Card>();

	public PairsOnlyDeckStacker() {
		super(false); // never shuffle pairs-only
		
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
		// 2) choose random even card {0,2,...(52*deckCount)-2} and next
		// sequential card
		// 3) repeat step 2 for (deckCount*13) pairs
		// 4) pick deckCount*13 dealer cards and insert between each pair

		numberOfDecks = shoe.getNumberOfDecks();
		numberOfPairs = numberOfDecks * 52 / 3;

		buildDecks(numberOfDecks); // build original decks
		pickPairs(); // pick the pairs
		putCardsInShoeWithDealerCard(); // load the shoe

	}

	private void putCardsInShoeWithDealerCard() {
		for (int i = 0; i < numberOfPairs; i++) {
			shoe.add(stackedDeck.remove(0));
			shoe.add(getDealerCard());
			shoe.add(stackedDeck.remove(0));
		}
	}

	private Card getDealerCard() {
		int randomCard = (((int) Math.round(Math.random()
				* ((cardsLeft - 2) / 2))) * 2);
		Card dealerCard = decks.remove(randomCard);
		cardsLeft -= 1;
		return (dealerCard);
	}

	private void pickPairs() {
		cardsLeft = numberOfDecks * 52;
		for (int i = 0; i < numberOfPairs; i++) {
			int randomCard = (((int) Math.round(Math.random()
					* ((cardsLeft - 2) / 2))) * 2);
			stackedDeck.add(decks.remove(randomCard)); // add the pair
			stackedDeck.add(decks.remove(randomCard)); // ditto
			cardsLeft -= 2;
		}
	}

	private void buildDecks(int numberOfDecks) {
		// //shuffle the suits and sort in rank (2-A) order
		// ArrayList<Card> shuffleSuits = new ArrayList<Card>();
		for (int i = 0; i < numberOfDecks; i++) {
			decks.addAll(newDeck);
		}
		Collections.shuffle(decks); // shuffle the suits
		class RankComparator implements Comparator<Card> {
			public int compare(Card c1, Card c2) {
				return (c1.rank().compareTo(c2.rank()));
			}
		}
		RankComparator rc = new RankComparator();
		Collections.sort(decks, rc);
	}
}
