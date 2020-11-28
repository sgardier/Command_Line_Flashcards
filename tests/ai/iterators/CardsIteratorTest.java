package ai.iterators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ai.domain.Card;

class CardsIteratorTest {
	CardsIterator cardIt;
	Card card1;
	Card card2;
	Card card3;
	
	@BeforeEach
	void init() {
		card1 = new Card("question1","réponse1");
		card2 = new Card("question2","réponse2");
		card3 = new Card("question3","réponse3");
		
		Queue<Card> q = new LinkedList<Card>();
		
		q.add(card1);
		q.add(card2);
		q.add(card3);
		
		cardIt = new CardsIterator(q); 
	}

	@Test
	void hasNextReturnTheCurrentCard() {
		assertEquals(card1,cardIt.next());
	}

	@Test
	void hasNextReturnTrueIfThereIsAnyElementInTheQueue() {
		assertTrue(cardIt.hasNext());
	}
	@Test
	void hasNextReturnFalseIfThereIsNoOneElementInTheQueue(){
		Queue<Card> q = new LinkedList<Card>();
		
		cardIt = new CardsIterator(q);
		assertFalse(cardIt.hasNext());
	}
	@Test
	void cardIsCorreclyAdded(){
		Queue<Card> q = new LinkedList<Card>();
		
		cardIt = new CardsIterator(q);
		cardIt.add(card1);
		assertTrue(cardIt.hasNext());
		assertEquals(card1, cardIt.next());
	}
}
