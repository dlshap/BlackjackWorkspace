package com.blackjack.testcases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.blackjack.cards.Shoe;
import com.blackjack.deckstackers.PairsOnlyDeckStacker;

public class TestPairsOnlyDeckStacker {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Shoe shoe = new Shoe(4, new PairsOnlyDeckStacker()); // one-deck shoe
		assertTrue(true);
//		 System.out.println(deck);
		 System.out.println(shoe.toString());
//		 assertEquals(deck,shoe.toString());
	}
}
