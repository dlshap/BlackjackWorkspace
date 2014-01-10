package com.blackjack;

import com.blackjack.config.IConfigLoader;
import com.blackjack.config.Platform;
import com.blackjack.gamecontrollers.Drill;
import com.blackjack.strategy.Strategy;

public class ConfigLoader implements IConfigLoader   {
	// Load configuration file
	//
	// For now, just return some "default" values
		
	
	/* (non-Javadoc)
	 * @see com.blackjack.IConfigLoader#getDeckCount()
	 */
	@Override
	public int getDeckCount() {
		return 1;
	}
	
	
	/* (non-Javadoc)
	 * @see com.blackjack.IConfigLoader#getStrategy()
	 */
	@Override
	public Strategy getStrategy() {
		return Strategy.BASIC;
	}
	
	/* (non-Javadoc)
	 * @see com.blackjack.IConfigLoader#getDrill()
	 */
	@Override
	public Drill getDrill() {
		return Drill.PAIRS;
	}
	
	
	/* (non-Javadoc)
	 * @see com.blackjack.IConfigLoader#getPlatform()
	 */
	@Override
	public Platform getPlatform() {
		return Platform.Android;
	}
	

}
