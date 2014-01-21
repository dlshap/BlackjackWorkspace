package com.blackjack.testcases;

import org.junit.Before;
import org.junit.Test;

import com.blackjack.cards.Card;
import com.blackjack.cards.Hand;
import com.blackjack.strategy.BasicStrategy;
import com.blackjack.strategy.IStrategy;

public class TestStrategy {
	
	private Card dealerCard;
	private Hand playerHand = new Hand();
	private IStrategy strategy;


	@Before
	public void setUp() throws Exception {
	}

	
	
	@Test
	public void testBasicStrategy() {
		
		strategy = BasicStrategy.createBasicStrategy();
		
		
		
//		assertEquals(true,strategy.checkPlay(new Play(), dealerCard, playerHand));

				
	}


}
