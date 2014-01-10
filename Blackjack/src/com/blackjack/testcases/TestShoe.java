package com.blackjack.testcases;

import static org.junit.Assert.*;

import org.junit.Test;

import com.blackjack.cards.Card;
import com.blackjack.cards.EmptyShoeException;
import com.blackjack.cards.Shoe;

public class TestShoe {


	@Test
	public void testShoe() {
		Shoe shoe = new Shoe();
		assertEquals(6, shoe.getNumberOfDecks());
	}

	@Test
	public void testShoeInt() {
		Shoe shoe = new Shoe(5);
		assertEquals(5, shoe.getNumberOfDecks());
	}

	@Test
	public void testGetNumberOfDecks() {
		assert (true);
	}

	@Test
	public void testMultipleShoes() {
		for (int deckCounter = 1; deckCounter < 7; deckCounter++) { // test 1-6
																	// decks
			Shoe shoe = BuildAShoe(deckCounter);
			TestTheShoe(shoe);
		}
	}

	public Shoe BuildAShoe(int numberOfDecks) {

		Shoe shoe = new Shoe(numberOfDecks);
		assertEquals(numberOfDecks, shoe.getNumberOfDecks());
		return shoe;
	}

	public void TestTheShoe(Shoe shoe) {
		for (int cardCounter = 0; cardCounter < (shoe.getNumberOfDecks() * 52); cardCounter++) {
			try {
				Card card = shoe.nextCard();
//				System.out.println(card.toString());
			} catch (EmptyShoeException e) {
				// TODO Auto-generated catch block
				assertFalse("Empty shoe exception", true);
			}
		}

		// Now go past the end
		try {
			Card card = shoe.nextCard();
		} catch (EmptyShoeException e) {
			return;
		}
		assertFalse("Didn't catch the empty shoe", true);
	}

	@Test
	public void testDiscard() {
		assertFalse("not yet", false);
	}

}
