package com.blackjack.gamecontrollers;


public enum Drill {
	PAIRS, SOFT, HARD, ALL, NONE;
	
	public static Drill drill(String drillString) {
		for (Drill d : Drill.values()) {
			if (drillString.toUpperCase().equals(d.toString().toUpperCase()))
				return d;
		}
		return Drill.NONE;
	}

	

}