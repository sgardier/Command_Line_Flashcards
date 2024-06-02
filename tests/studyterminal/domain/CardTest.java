package studyterminal.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void comparingTheSameCard() {
		Card carte = new Card("Netwon's first law","Sum f = 0");
		Card carteToCompare = carte;
		
		assertTrue(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithDifferentCard() {
		Card carte = new Card("Netwon's first law","Sum f = 0");
		Card carteToCompare = new Card("Netwon second law", "f = ma");
		
		assertFalse(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithSameQuestionButDifferentAnswer() {
		Card carte = new Card("Netwon's first law","Sum f = 0");
		Card carteToCompare = new Card("Netwon's first law","Sum f = 1");
		
		assertFalse(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithSameAnswerButDifferentQuestion() {
		Card carte = new Card("Netwon's first law","Sum f = 0");
		Card carteToCompare = new Card("Netwon's second law","Sum f = 0");
		
		assertFalse(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithNullName() {
		Card carte = new Card(null,"Sum f = 0");
		Card carteToCompare = new Card("Netwon's second law","Sum f = 0");
		assertFalse(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithNullObject() {
		Card carte = new Card("Netwon's second law","Sum f = 0");
		Card carteToCompare = null;
		assertFalse(carte.equals(carteToCompare));
	}

}
