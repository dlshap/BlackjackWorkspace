package com.blackjack.testcases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;

import com.blackjack.cards.Card;
import com.blackjack.cards.Shoe;
import com.blackjack.deckstackers.FairDeckStacker;

public class TestFairDeckStacker {

	// TODO:
	// Need a reference deck string to compare the shoe to (i.e.,
	// {"Ace of SPADES","Ace of HEARTS",...})

	@Test
	public void test1Deck() {
		String deckCheck = null;
		for (Card.Suit s : Card.Suit.values()) {
			for (Card.Rank r : Card.Rank.values()) {
				if (deckCheck == null)
					deckCheck = (r + " of " + s);
				else
					deckCheck += ("\n" + r + " of " + s);
			}
		}
		// deckCheck = "ACE of SPADES";
		Shoe shoe = new Shoe(1,new FairDeckStacker(false)); // one-deck shoe, no shuffle
		assertEquals(deckCheck, shoe.toString());
		System.out.println(shoe.toString());
	}

	@Test
	public void testRankComparator() {
		class RankComparator implements Comparator<Card> {
			public int compare(Card c1, Card c2) {
				return (c1.rank().compareTo(c2.rank()));
			}
		}
	RankComparator rc = new RankComparator();
	ArrayList<Card> newDeck = Card.newDeck();
	Collections.sort(newDeck, rc);
	System.out.println(newDeck.toString());
	}

}
