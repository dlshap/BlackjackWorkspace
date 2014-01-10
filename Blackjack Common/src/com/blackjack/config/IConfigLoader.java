package com.blackjack.config;

import com.blackjack.gamecontrollers.Drill;
import com.blackjack.strategy.Strategy;

public interface IConfigLoader {

	public abstract int getDeckCount();

	public abstract Strategy getStrategy();

	public abstract Drill getDrill();

	public abstract Platform getPlatform();

}