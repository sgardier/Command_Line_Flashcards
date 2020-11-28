package ai.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void comparingTheSameCard() {
		Card carte = new Card("Qu'est ce qui est jaune et qui attend?","Tes dents");
		Card carteToCompare = carte;
		
		assertTrue(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithDifferentCard() {
		Card carte = new Card("Qu'est ce qui est jaune et qui attend?","Tes dents");
		Card carteToCompare = new Card("Mes coups sont fatals", "Une épée");
		
		assertFalse(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithSameQuestionButDifferentAnswer() {
		Card carte = new Card("Qu'est ce qui est jaune et qui attend?","Tes dents");
		Card carteToCompare = new Card("Qu'est ce qui est jaune et qui attend?","Jonhatan");
		
		assertFalse(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithSameAnswerButDifferentQuestion() {
		Card carte = new Card("Qu'est ce qui est jaune et qui attend?","Tes dents");
		Card carteToCompare = new Card("Qu'est ce qui est blanc et qui attend","Tes dents");
		
		assertFalse(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithNullName() {
		Card carte = new Card(null,"Tes dents");
		Card carteToCompare = new Card("Qu'est ce qui est blanc et qui attend","Tes dents");
		assertFalse(carte.equals(carteToCompare));
	}
	@Test
	void comparingWithNullObject() {
		Card carte = new Card("Qu'est ce qui est blanc et qui attend","Tes dents");
		Card carteToCompare = null;
		assertFalse(carte.equals(carteToCompare));
	}

}
