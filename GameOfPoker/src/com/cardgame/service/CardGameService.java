package com.cardgame.service;

import java.util.LinkedList;
import java.util.List;

import com.cardgame.bean.Card;
import com.cardgame.bean.Player;


public interface CardGameService {

	LinkedList<Card> cardDeck();

	LinkedList<Player> getPlayers();

	LinkedList<Card> cardDistributionToPlayer(LinkedList<Card> cardDeck,
			List<Player> players);

	List<Player> decideWinner(List<Player> players);
	

}
