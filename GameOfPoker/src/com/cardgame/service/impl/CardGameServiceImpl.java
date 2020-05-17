package com.cardgame.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.cardgame.bean.Card;
import com.cardgame.bean.Player;
import com.cardgame.enums.CardRank;
import com.cardgame.enums.CardSuit;
import com.cardgame.service.CardGameService;

public class CardGameServiceImpl implements CardGameService {
	@Override
	public LinkedList<Card> cardDeck() {
		LinkedList<Card> cardDeck = new LinkedList<Card>();
		// used linkedList as lot of deletion operation on cardDeck list
		List<CardSuit> cardSuits = Arrays.asList(CardSuit.values());
		List<CardRank> cardRanks = Arrays.asList(CardRank.values());
		System.out.println(cardSuits.size() + " " + cardRanks.size());
		for (CardSuit suit : cardSuits) {
			for (CardRank rank : cardRanks) {
				cardDeck.add(new Card(suit, rank));
			}
		}
		// got deck of 52 cards
		System.out.println("Prepared Card Deck :: " + cardDeck);
		Collections.shuffle(cardDeck); // always unique sequence
		return cardDeck;
	}

	@Override
	public LinkedList<Player> getPlayers() {
		LinkedList<Player> players = new LinkedList<Player>();
		players.add(new Player(101));
		players.add(new Player(102));

		return players;
	}

	@Override
	public LinkedList<Card> cardDistributionToPlayer(LinkedList<Card> cardDeck,
			List<Player> players) {
		for (Player player : players) {
			List<Card> playerCards = cardDeck.stream().limit(3)
					.collect(Collectors.toList());
			player.setPlayerCards(playerCards); // giving each players 3 cards
			cardDeck.removeAll(playerCards);
			System.out.println(cardDeck.size() + "after removing");
		}
		return cardDeck;
	}
	@Override
	public List<Player> decideWinner(List<Player> players) {
		List<Player> winners = new LinkedList<Player>();

		// same suit condition check
		winners=decideFlushWinner(players);
		if (winners.size() > 0)
			return winners;

		// sequence condition check 
		winners=decideSequenceWinner(players);
		if (winners.size() > 0)
			return winners;

		// pair condition check
		winners = decidePairWinner(players);
		if (winners.size() > 0)
			return winners;

		// higher card condition check 
		winners=decideHigherCardWinner(players);
		
		if (winners.size() > 0)
			return winners;

		return winners; // winner
	}
	

	Comparator<Player> playersSortBasedOnCards = (p1, p2) -> {

		return p1.getHigherCard().getCardRank().getCardNumber()
				.compareTo(p2.getHigherCard().getCardRank().getCardNumber());
	};
	
	private List<Player> decideHigherCardWinner(List<Player> players) {
		List<Player> winnerPlayers = new LinkedList<Player>();

		Function<Player, Boolean> setHigherCardForPlayer = (p) -> {

			List<Card> playerCards = p.getPlayerCards();
			playerCards.sort(cardsSort);
			p.setHigherCard(playerCards.get(playerCards.size() - 1));

			return true;
		};

		players.stream().filter(p -> setHigherCardForPlayer.apply(p))
				.collect(Collectors.toList());

		players.sort(playersSortBasedOnCards);
		winnerPlayers = players
				.stream()
				.filter(p -> p
						.getHigherCard()
						.getCardRank()
						.getCardNumber()
						.equals(players.get(players.size() - 1).getHigherCard()
								.getCardRank().getCardNumber()))
				.collect(Collectors.toList());

		System.out.println(winnerPlayers);
		return winnerPlayers;// winners can be one person or more than one.

	}

	Comparator<Player> playersSortBasedOnPairedCardRank = (p1, p2) -> {

		return p1.getPairedCardRank().compareTo(p2.getPairedCardRank());
	};

	private List<Player> decidePairWinner(List<Player> players) {
		List<Player> winnerPlayers = new LinkedList<Player>();

		Function<Player, Boolean> pairChecker = (p) -> {

			Map<CardRank, List<Card>> rankWisePlayerCards = p.getPlayerCards()
					.stream().collect(Collectors.groupingBy(Card::getCardRank));
			for (CardRank cardRank : rankWisePlayerCards.keySet()) {
				if (rankWisePlayerCards.get(cardRank).size() >= 2){
					p.setPairedCardRank(cardRank);
					return true;
				}
					
			}

			return false;
		};

		winnerPlayers = players.stream().filter(p -> pairChecker.apply(p))
				.collect(Collectors.toList());
		if(winnerPlayers.size()>0){
			winnerPlayers.sort(playersSortBasedOnPairedCardRank);

			winnerPlayers = winnerPlayers
					.stream()
					.filter(p -> p.getPairedCardRank().equals(
							players.get(players.size() - 1).getPairedCardRank()))
					.collect(Collectors.toList());

			System.out.println(winnerPlayers);
		}
		
		return winnerPlayers;// winners can be one person or more than one.
								// ex: if two players got pair. both are winner.
								// Bigger PAIR option can be given later
	}

	// comparator to sort on the basis of cardNumber
	Comparator<Card> cardsSort = (c1, c2) -> {

		return c1.getCardRank().getCardNumber()
				.compareTo(c2.getCardRank().getCardNumber());
	};

	private List<Player> decideSequenceWinner(List<Player> players) {
		List<Player> winnerPlayers = new LinkedList<Player>();

		Function<Player, Boolean> sequenceChecker = (p) -> {
			List<Card> playerCards = p.getPlayerCards();
			playerCards.sort(cardsSort);

			if (Math.abs(playerCards.get(0).getCardRank().getCardNumber()
					- playerCards.get(1).getCardRank().getCardNumber()) == 1) {
				if (Math.abs(playerCards.get(1).getCardRank().getCardNumber()
						- playerCards.get(2).getCardRank().getCardNumber()) == 1) {
					return true;
				}
			} else if (playerCards.get(0).getCardRank().getCardNumber()
					.equals(2)
					&& playerCards.get(1).getCardRank().getCardNumber()
							.equals(4)
					&& playerCards.get(2).getCardRank().getCardNumber()
							.equals(14)) {
				// ACE(14),DEUCE(2),THREE(4)
				return true;
			}
			return false;// if both condition fails

		};

		winnerPlayers = players.stream().filter(p -> sequenceChecker.apply(p))
				.collect(Collectors.toList());

		winnerPlayers = this.decideHigherCardWinner(winnerPlayers);

		System.out.println("winner players : " + winnerPlayers);
		return winnerPlayers;// winners can be one person or more than one.
								// ex: if two players got SEQUENCE. both are
								// winner. Bigger SEQUENCE option can be given
								// later
	}

	private List<Player> decideFlushWinner(List<Player> players) {
		List<Player> winnerPlayers = new LinkedList<Player>();
		Function<Player, Boolean> flushChecker = (p) -> {
			Map<CardSuit, List<Card>> suitWisePlayerCards = p.getPlayerCards()
					.stream().collect(Collectors.groupingBy(Card::getCardSuit));
			if (suitWisePlayerCards.keySet().size() == 1) {
				return true;
			} else
				return false;
		};

		winnerPlayers = players.stream().filter(p -> flushChecker.apply(p))
				.collect(Collectors.toList());

		winnerPlayers = this.decideSequenceWinner(winnerPlayers);// if multiple
																// flush then
																// higher card
																// wins
		System.out.println(winnerPlayers);
		return winnerPlayers;// winners can be one person or more than one.
								// ex: if two players got FLUSH. both are
								// winner. Bigger FLUSH option can be given
								// later
	}
}
