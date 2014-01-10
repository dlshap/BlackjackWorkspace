package com.blackjack.playerui;

import javax.swing.ImageIcon;

import com.blackjack.cards.Card;
import com.blackjack.cards.Card.Rank;
import com.blackjack.cards.Card.Suit;

public class CardImage {
	
	private CardImage() {
		super();
	}
	
	public static ImageIcon getCardIcon(Card card) {
		Rank rank = card.rank();
		Suit suit = card.suit();
		String imageName = rank.toString().toLowerCase() + "_" + suit.toString().toLowerCase();
		return createImageIcon("resources/cardIcons/" + imageName + ".png", "");
	}

	private static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = PlayerPanel.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public static ImageIcon getJokerIcon() {
		return createImageIcon("resources/cardIcons/joker.png","Joker");
	}
}