package studyterminal.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studyterminal.domain.algorithm.FakeCalendarAlgorithm;

class LearningScheduleTest {

	Card card1 = new Card("Question 1", "Answer 1");
	Card card2 = new Card("Question 2", "Answer 2");
	Card card3 = new Card("Question 3", "Answer 3");
	Card card4 = new Card("Question 4", "Answer 4");
	Card card5 = new Card("Question 5", "Answer 5");
	Card card6 = new Card("Question 6", "Answer 6");
	
	Level niveau1;
	Level niveau2;
	Level niveau3;
	
	Box b;
	private LearningSchedule ls;
	private FakeCalendarAlgorithm calendarAlgo;
	
	@BeforeEach
	void initialization() {
		niveau1 = new Level(1);
		niveau2 = new Level(2);
		niveau3 = new Level(3);
		
		niveau1.insert(card1);
		assertTrue(niveau1.contains(card1));
		niveau1.insert(card2);
		
		niveau2.insert(card3);
		niveau2.insert(card4);
		
		niveau3.insert(card5);
		niveau3.insert(card6);
		
		b = new Box(niveau1, niveau2, niveau3);
		calendarAlgo = new FakeCalendarAlgorithm(3);
		ls = new LearningSchedule(1,b,calendarAlgo);
	}
	@Test
	void testGetCurrentDay() {
		ls = new LearningSchedule(1,b,calendarAlgo);
		assertEquals(1,ls.getCurrentDay());
	}
	@Test
	void testAddCardToBoxInNormalConditions() {
		final Card card = new Card("Question1", "Answer1");
		ls = new LearningSchedule(1,b,calendarAlgo);
		ls.add(card);
		assertTrue(niveau1.contains(card));
	}
	@Test
	void moveCardWorkInNormalConditions(){
		ls.moveCardToNextLevel(card1, true);
		assertFalse(niveau1.contains(card1));
		assertTrue(niveau2.contains(card1));
		ls.moveCardToNextLevel(card4, false);
		assertFalse(niveau2.contains(card4));
		assertTrue(niveau1.contains(card4));
	}
	@Test
	void learningScheduleIsRevisableIfThereIsCardsInIt(){
		assertTrue(ls.isRevisable());
	}
	@Test
	void learningScheduleIsRevisableIfThereIsntCardsInIt(){
		b = new Box(3);
		calendarAlgo = new FakeCalendarAlgorithm(3);
		ls = new LearningSchedule(1,b,calendarAlgo);
		assertFalse(ls.isRevisable());
	}
}