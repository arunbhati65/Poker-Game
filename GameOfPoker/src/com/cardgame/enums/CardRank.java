package com.cardgame.enums;

public enum CardRank {
	ACE(14),DEUCE(2),THREE(4),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),JACK(11),QUEEN(12),KING(13);
	
	private Integer cardNumber;
	
	private CardRank(Integer cardNumber) {
		this.cardNumber=cardNumber;
	}

	public Integer getCardNumber() {
		return cardNumber;
	}
	
}
