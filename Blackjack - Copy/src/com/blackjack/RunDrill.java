package com.blackjack;

import com.blackjack.playerui.PlayerPanel;

public class RunDrill {
	
	public void play() {
		PlayerPanel.createPlayerPanel(new ConfigLoader());
	}

}
