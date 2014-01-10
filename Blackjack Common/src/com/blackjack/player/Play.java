package com.blackjack.player;

public enum Play {
	HIT(0), STAND(1), SPLIT(2), DOUBLE(3), DEAL(4), NONE(-1);

	private int index;

	Play(int index) {
		this.index = index;
	}

	public int index() {
		return this.index;
	}

	public static Play action(int index) {
		for (Play a : Play.values()) {
			if (a.index() == index)
				return a;
		}
		return Play.NONE;
	}

	public static Play action(String playString) {
		for (Play a : Play.values()) {
			if (a.toString().toUpperCase().equals(playString.toUpperCase()))
				return a;
		}
		return Play.NONE;
	}

	public String toString() {
		String label = this.name().substring(0, 1)
				+ this.name().substring(1).toLowerCase();
		return label;
	}
}