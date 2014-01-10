package com.blackjack.deckstackers;

import com.blackjack.gamecontrollers.Drill;

public class DeckStackerFactory {

	public static DeckStacker getPairsOnlyDeckStacker() {
		return new PairsOnlyDeckStacker();
	}

	public static DeckStacker getFairDeckStacker() {
		return new FairDeckStacker();
	}

	public static DeckStacker getSoftDeckStacker() {
		return new SoftDeckStacker();
	}

	public static DeckStacker getHardDeckStacker() {
		return new HardDeckStacker();
	}

	public static DeckStacker getDeckStacker(Drill drill) {
		switch (drill) {
		case PAIRS:
			return new PairsOnlyDeckStacker();
		case HARD:
			return new HardDeckStacker();
		case SOFT:
			return new SoftDeckStacker();
		default:
			return new FairDeckStacker();
		}
	}

}
