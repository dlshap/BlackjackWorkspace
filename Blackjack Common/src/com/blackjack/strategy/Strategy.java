package com.blackjack.strategy;


public enum Strategy {
	BASIC, KNOCKOUT;
	
	
	public static Strategy strategy(String strategyString) {
		for (Strategy s : Strategy.values()) {
			if (strategyString.toUpperCase().equals(s.toString().toUpperCase()))
				return s;
		}
		return Strategy.BASIC;
	}
}
