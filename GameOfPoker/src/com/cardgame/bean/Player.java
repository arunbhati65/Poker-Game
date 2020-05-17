package com.cardgame.bean;

import java.util.List;

import com.cardgame.enums.CardRank;

public class Player {
	private int PlayerID;
	private List<Card> playerCards;
	private Card higherCard;
	private CardRank pairedCardRank;

	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card getHigherCard() {
		return higherCard;
	}

	public void setHigherCard(Card higherCard) {
		this.higherCard = higherCard;
	}

	public Player(int playerID) {
		super();
		PlayerID = playerID;
	}

	public CardRank getPairedCardRank() {
		return pairedCardRank;
	}

	public void setPairedCardRank(CardRank pairedCardRank) {
		this.pairedCardRank = pairedCardRank;
	}

	public int getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(int playerID) {
		PlayerID = playerID;
	}

	public List<Card> getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(List<Card> playerCards) {
		this.playerCards = playerCards;
	}

	@Override
	public String toString() {
		return "\n Player [PlayerID=" + PlayerID + ", playerCards=" + playerCards
				+ ", higherCard=" + higherCard + ", pairedCardRank="
				+ pairedCardRank + "]";
	}

	
	
}
