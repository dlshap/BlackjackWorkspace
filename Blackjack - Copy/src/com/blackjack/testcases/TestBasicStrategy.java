package com.blackjack.testcases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.blackjack.cards.Card;
import com.blackjack.cards.Hand;
import com.blackjack.cards.Card.Rank;
import com.blackjack.cards.Card.Suit;
import com.blackjack.player.Play;
import com.blackjack.strategy.BasicStrategy;
import com.blackjack.strategy.IStrategy;

public class TestBasicStrategy {

	ArrayList<Card> deck = Card.newDeck();
	IStrategy basicStrategy = BasicStrategy.createBasicStrategy();
	Hand playerHand = new Hand();
	Card dealerCard;

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testSoft19Against6() {
		Card playerCard1 = Card.makeCard(Rank.ACE, Suit.CLUBS);
		Card playerCard2 = Card.makeCard(Rank.EIGHT, Suit.CLUBS);
		Card dealerCard = Card.makeCard(Rank.SIX, Suit.CLUBS);
		playerHand.clear();
		playerHand.add(playerCard1);
		playerHand.add(playerCard2);
		assertEquals(Play.DOUBLE,basicStrategy.getPlay(dealerCard,playerHand));
	}

	@Test
	public void testAceThree() {
		Card playerCard1 = Card.makeCard(Rank.ACE, Suit.SPADES);
		Card playerCard2 = Card.makeCard(Rank.THREE, Suit.DIAMONDS);
		Card dealerCard = Card.makeCard(Rank.SIX, Suit.SPADES);
		playerHand.add(playerCard1);
		playerHand.add(playerCard2);
		assertEquals(Play.DOUBLE, basicStrategy.getPlay(dealerCard, playerHand));
		
		// make the other card an ace
		playerHand.clear();
		playerHand.add(playerCard2);
		playerHand.add(playerCard1);
		assertEquals(Play.DOUBLE, basicStrategy.getPlay(dealerCard, playerHand));
		
	}

	@Test
	public void testAceTen() {
		Card playerCard1 = Card.makeCard(Rank.ACE, Suit.SPADES);
		Card playerCard2 = Card.makeCard(Rank.TEN, Suit.HEARTS);
		Card dealerCard = Card.makeCard(Rank.ACE, Suit.CLUBS);
		playerHand.add(playerCard1);
		playerHand.add(playerCard2);
		assertEquals(Play.STAND, basicStrategy.getPlay(dealerCard, playerHand));

		playerHand.clear();
		playerHand.add(playerCard2);
		playerHand.add(playerCard1);
		assertEquals(Play.STAND, basicStrategy.getPlay(dealerCard, playerHand));
	}

	@Test
	public void testPairAces() {

		// System.out.println(deck);

		playerHand.add(deck.get(12)); // Ace of spades
		System.out.println(deck.get(12));
		playerHand.add(deck.get(12)); // Ace of spades
		// playerHand.add(deck.get(0)); // Ace of spades
		for (int i = 0; i < 10; i++) {
			dealerCard = deck.get(i); // Ace of spades
			assertEquals(Play.SPLIT,
					basicStrategy.getPlay(dealerCard, playerHand));
		}
	}

	@Test
	public void testNines() {

		playerHand.add(deck.get(7)); // Nine of spades
		playerHand.add(deck.get(7)); // Nine of spades
		dealerCard = deck.get(4); // Six of spades
		assertEquals(Play.SPLIT, basicStrategy.getPlay(dealerCard, playerHand));

		dealerCard = deck.get(5); // Seven of spades
		assertEquals(Play.STAND, basicStrategy.getPlay(dealerCard, playerHand));
	}

}
