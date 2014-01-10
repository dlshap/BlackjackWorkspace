package com.blackjack.testcases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.blackjack.player.Play;

public class TestPlay {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testActionStrings() {
		assertEquals(Play.HIT, Play.action("Hit"));
		assertEquals(Play.STAND, Play.action("Stand"));
		assertEquals(Play.DEAL, Play.action("Deal"));
		assertEquals(Play.DOUBLE, Play.action("Double"));
		assertEquals(Play.SPLIT, Play.action("Split"));
	}

	

	

}
