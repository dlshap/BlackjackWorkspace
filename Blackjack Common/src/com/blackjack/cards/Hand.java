package com.blackjack.cards;

import java.util.ArrayList;

public class Hand {

	private ArrayList<Card> hand = new ArrayList<Card>();

	public Hand(ArrayList<Card> hand) {
		super();
		this.hand = hand;
	}

	public void add(Card card) {
		hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public Hand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String handAsString = null;
		for (Card c : hand) {
			if (handAsString == null)
				handAsString = c.toString();
			else
				handAsString += ("\n" + c.toString());
		}
		return handAsString;
	}

	public int size() {
		return hand.size();
	}

	public void clear() {
		hand.clear();
	}

	public boolean isAPair() {
		if (hand.size() == 2) {
			Card.Rank c1 = hand.get(0).rank();
			Card.Rank c2 = hand.get(1).rank();
			if (c1.equals(c2))
				return true;
		}

		return false;
	}

	public boolean isSoft() {
		// doesn't have to be two cards...
		if ((hand.size() == 2)
				&& (hand.get(0).rank().equals(Card.Rank.ACE) || hand.get(1)
						.rank().equals(Card.Rank.ACE)))
			return true;
		else
			return false;
	}
}
