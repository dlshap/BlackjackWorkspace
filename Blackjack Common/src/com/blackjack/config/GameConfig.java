package com.blackjack.config;

import com.blackjack.gamecontrollers.Drill;
import com.blackjack.strategy.Strategy;

public class GameConfig {
	// Load the configuration file and apply to game
	//
	private int deckCount;
	private Strategy strategy;
	private Drill drill;
	private IConfigLoader configLoader;

	public GameConfig(IConfigLoader configLoader) {
		super();
		this.configLoader = configLoader;
		loadSettings();
	}

	private void loadSettings() {
		deckCount = configLoader.getDeckCount();
		strategy = configLoader.getStrategy();
		drill = configLoader.getDrill();
	}

	public int getDeckCount() {
		return deckCount;
	}

	public Strategy getStrategy() {
		return strategy;
	}
	
	public Drill getDrill() {
		return drill;
	}

	public void drillChange(Drill drillCommand) {
		drill = drillCommand;
	}
}
