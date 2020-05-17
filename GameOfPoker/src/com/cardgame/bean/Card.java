package com.cardgame.bean;

import com.cardgame.enums.CardRank;
import com.cardgame.enums.CardSuit;

public class Card {
	private CardSuit cardSuit;
	private CardRank cardRank;

	public Card() {
	}

	public Card(CardSuit cardSuit, CardRank cardRank) {
		super();
		this.cardSuit = cardSuit;
		this.cardRank = cardRank;
	}

	public CardSuit getCardSuit() {
		return cardSuit;
	}

	public void setCardSuit(CardSuit cardSuit) {
		this.cardSuit = cardSuit;
	}

	public CardRank getCardRank() {
		return cardRank;
	}

	public void setCardRank(CardRank cardRank) {
		this.cardRank = cardRank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardRank == null) ? 0 : cardRank.hashCode());
		result = prime * result
				+ ((cardSuit == null) ? 0 : cardSuit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardRank != other.cardRank)
			return false;
		if (cardSuit != other.cardSuit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [cardSuit=" + cardSuit + ", cardRank=" + cardRank + "]";
	}

}
