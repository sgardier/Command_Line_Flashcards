package studyterminal.domain;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoxTest {
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
	
	@BeforeEach
	void initialisationBox(){
		
		niveau1 = new Level(1);
		niveau2 = new Level(2);
		niveau3 = new Level(3);
		
		niveau1.insert(card1);
		niveau1.insert(card2);
		
		niveau2.insert(card3);
		niveau2.insert(card4);
		
		niveau3.insert(card5);
		niveau3.insert(card6);
		
		b = new Box(niveau1, niveau2, niveau3);
	}
	@Test
	void boxContainsEffectivelyLevels() {
		assertEquals(3,b.getAmountOfLevels());
	}
	@Test
	void getIteratorReturnTheGoodIterator(){
		Iterator<Card> iteratorGetFromBox = b.getIteratorForLevels(1);
		Iterator<Card> iteratorGetFromTheLevel = niveau1.iterator();
		while(iteratorGetFromBox.hasNext()) {
			assertTrue(iteratorGetFromBox.next().equals(iteratorGetFromTheLevel.next()));
		}
		assertSame(iteratorGetFromBox.hasNext(), iteratorGetFromTheLevel.hasNext());
	}
	@Test
	void isTheSizeOfBoxAlwaysBigger2() {
		Box b = new Box();
		assertTrue(b.getAmountOfLevels() == 2);
	}
	@Test
	void areTheCardAddedCorrecly() {
		assertTrue(niveau1.contains(card1));
	}
	@Test
	void cardIsCorreclyRemoved(){
		b.remove(card1);
		for (Card card : b) {
			assertFalse(card.equals(card1));
		}
	}
	@Test
	void cardIsNotIfDoenstExist(){
		Card card = new Card("Login","S'inscrire dans le système d'étude");
		assertFalse(b.remove(card));
	}
	@Test
	void levelIsCorreclyAdded(){
		Card toAdd = new Card("Question7", "reponse7");
		assertTrue(b.add(toAdd));
		assertTrue(niveau1.contains(toAdd));
	}
	@Test
	void levelIsCorreclyNotAdded(){
		assertFalse(b.add(card1));
	}
	@Test
	void boxIsCorrectlyBuild() {
		Box b = new Box(2);
		assertTrue(b.getAmountOfLevels() == 2);
	}
	@Test
	void getIteratorForLevelsReturnTheGoodIterator(){
		Iterator<Card> iteratorGetFromBox = b.getIteratorForLevels(1,2,3);
		List<Iterator<Card>> itList = new ArrayList<Iterator<Card>>();
		itList.add(niveau1.iterator());
		itList.add(niveau2.iterator());
		itList.add(niveau3.iterator());
		Iterator<Iterator<Card>> iteratorGetFromTheLevels = itList.iterator();
		
		while(iteratorGetFromBox.hasNext() && iteratorGetFromTheLevels.hasNext()){
			Iterator<Card> currentLvlItFromList = iteratorGetFromTheLevels.next();
			if(currentLvlItFromList != null) {
				while(currentLvlItFromList.hasNext()){
					assertEquals(currentLvlItFromList.next(), iteratorGetFromBox.next());
				}
			}
		}
		assertSame(iteratorGetFromBox.hasNext(), iteratorGetFromTheLevels.hasNext());
	}
	@Test
	void moveCardTestNormalConditionsIfTrue(){
		b.moveCardToNextLevel(card3, true);
		niveau3.contains(card3);
	}
	@Test
	void moveCardTestNormalConditionsIfFalse(){
		b.moveCardToNextLevel(card3, false);
		niveau1.contains(card3);
	}
	@Test
	void isEmptyReturnTrueIfTheLevelIsEmpty(){
		Box b = new Box(2);
		assertTrue(b.isEmpty());
	}
	@Test
	void isEmptyReturnFalseIfTheLevelIsntEmpty(){
		Box b = new Box(3);
		b.add(card1);
		assertFalse(b.isEmpty());
	}
}
