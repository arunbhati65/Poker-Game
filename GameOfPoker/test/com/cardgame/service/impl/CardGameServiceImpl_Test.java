package com.cardgame.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.cardgame.bean.Card;
import com.cardgame.bean.Player;
import com.cardgame.enums.CardRank;
import com.cardgame.enums.CardSuit;

public class CardGameServiceImpl_Test {
	
	static CardGameServiceImpl service =new CardGameServiceImpl();
	static LinkedList<Card> cardDeck ;
	static LinkedList<Player> players ;
	
	@BeforeClass
	static public void setup(){
		cardDeck = service.cardDeck();
		players = service.getPlayers();
	}
	@Test
	public void cardDeck_Test(){
		
		int expectedCardCount=52;
		int actualCardCount = service.cardDeck().size();
		Assert.assertEquals(expectedCardCount, actualCardCount);
	}
	
	@Test
	public void getPlayers_Test(){
		
		int expectedPlayerCount=2;
		int actualPlayerCount = service.getPlayers().size();
		Assert.assertEquals(expectedPlayerCount, actualPlayerCount);
	}
	
	@Test
	public void cardDistributionToPlayer_Test(){
		
		int expectedPlayerCount=46;
		int actualPlayerCount = service.cardDistributionToPlayer(cardDeck, players).size();
		Assert.assertEquals(expectedPlayerCount, actualPlayerCount);//checkingCardCountInDECK
	}
	
	@Test
	public void decideWinner_CardsFromSameSuit_Test(){
		

		LinkedList<Card> player1Cards=new LinkedList<Card>();
		player1Cards.add(new Card(CardSuit.CLUBS, CardRank.ACE));
		player1Cards.add(new Card(CardSuit.CLUBS, CardRank.THREE));
		player1Cards.add(new Card(CardSuit.CLUBS, CardRank.DEUCE));
		
		LinkedList<Card> player2Cards=new LinkedList<Card>();
		player2Cards.add(new Card(CardSuit.SPADES, CardRank.EIGHT));
		player2Cards.add(new Card(CardSuit.SPADES, CardRank.SEVEN));
		player2Cards.add(new Card(CardSuit.SPADES, CardRank.SIX));
		
		LinkedList<Player> players = new LinkedList<Player>();
		Player p1=new Player(101);
		Player p2=new Player(102);
		p1.setPlayerCards(player1Cards);
		p2.setPlayerCards(player2Cards);
		players.add(p1);
		players.add(p2);
		
		Player actualWinnerPlayer = service.decideWinner(players).get(0);
		Player expectedWinnerPlayer =p1;
		Assert.assertEquals(expectedWinnerPlayer.getPlayerID(), actualWinnerPlayer.getPlayerID());
	}
	
	@Test
	public void decideWinner_CardsInSequence_Test(){
		

		LinkedList<Card> player1Cards=new LinkedList<Card>();
		player1Cards.add(new Card(CardSuit.CLUBS, CardRank.EIGHT));
		player1Cards.add(new Card(CardSuit.HEARTS, CardRank.SEVEN));
		player1Cards.add(new Card(CardSuit.HEARTS, CardRank.SIX));
		
		LinkedList<Card> player2Cards=new LinkedList<Card>();
		player2Cards.add(new Card(CardSuit.CLUBS, CardRank.ACE));
		player2Cards.add(new Card(CardSuit.DIAMONDS, CardRank.THREE));
		player2Cards.add(new Card(CardSuit.SPADES, CardRank.DEUCE));
		
		LinkedList<Player> players = new LinkedList<Player>();
		Player p1=new Player(101);
		Player p2=new Player(102);
		p1.setPlayerCards(player1Cards);
		p2.setPlayerCards(player2Cards);
		players.add(p1);
		players.add(p2);
		
		Player actualWinnerPlayer = service.decideWinner(players).get(0);
		Player expectedWinnerPlayer =p2;
		Assert.assertEquals(expectedWinnerPlayer.getPlayerID(), actualWinnerPlayer.getPlayerID());
	}
	
	@Test
	public void decideWinner_CardsInPairs_Test(){
		

		LinkedList<Card> player1Cards=new LinkedList<Card>();
		player1Cards.add(new Card(CardSuit.CLUBS, CardRank.ACE));
		player1Cards.add(new Card(CardSuit.DIAMONDS, CardRank.ACE));
		player1Cards.add(new Card(CardSuit.SPADES, CardRank.FOUR));
		
		LinkedList<Card> player2Cards=new LinkedList<Card>();
		player2Cards.add(new Card(CardSuit.SPADES, CardRank.ACE));
		player2Cards.add(new Card(CardSuit.HEARTS, CardRank.ACE));
		player2Cards.add(new Card(CardSuit.CLUBS, CardRank.FOUR));
		
		LinkedList<Player> players = new LinkedList<Player>();
		Player p1=new Player(101);
		Player p2=new Player(102);
		p1.setPlayerCards(player1Cards);
		p2.setPlayerCards(player2Cards);
		players.add(p1);
		players.add(p2);
		
		int actualWinnerPlayerCount = service.decideWinner(players).size();
		int expectedWinnerPlayerCount =2;
		Assert.assertEquals(expectedWinnerPlayerCount, actualWinnerPlayerCount);
	}
	
	@Test
	public void decideWinner_HighestRank_Test(){
		

		LinkedList<Card> player1Cards=new LinkedList<Card>();
		player1Cards.add(new Card(CardSuit.CLUBS, CardRank.FOUR));
		player1Cards.add(new Card(CardSuit.DIAMONDS, CardRank.THREE));
		player1Cards.add(new Card(CardSuit.SPADES, CardRank.KING));
		
		LinkedList<Card> player2Cards=new LinkedList<Card>();
		player2Cards.add(new Card(CardSuit.CLUBS, CardRank.EIGHT));
		player2Cards.add(new Card(CardSuit.SPADES, CardRank.SEVEN));
		player2Cards.add(new Card(CardSuit.DIAMONDS, CardRank.FOUR));
		
		LinkedList<Player> players = new LinkedList<Player>();
		Player p1=new Player(101);
		Player p2=new Player(102);
		p1.setPlayerCards(player1Cards);
		p2.setPlayerCards(player2Cards);
		players.add(p1);
		players.add(p2);
		
		Player actualWinnerPlayer = service.decideWinner(players).get(0);
		Player expectedWinnerPlayer =p1;
		Assert.assertEquals(expectedWinnerPlayer.getPlayerID(), actualWinnerPlayer.getPlayerID());
	}
	
	
	
}
