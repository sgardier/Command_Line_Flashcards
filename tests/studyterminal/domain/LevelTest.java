package studyterminal.domain;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LevelTest {
	
	Level niveau1;
	Card carte1;
	Card carte2;
	Card carte3;
	@BeforeEach
	void initialization(){
		niveau1 = new Level(1);
		
		carte1 = new Card("Netwon's first law","Sum f = 0");
		carte2 = new Card("Arme du Moyen-âge, symbole des chevaliers", "L'épée");
		carte3 = new Card("Quelle est la formule de Pythagore?", "a2+b2 = c2");
		
		niveau1.insert(carte1);
		niveau1.insert(carte2);
		niveau1.insert(carte3);
	}
	@Test
	void checkEachCardIsAdded(){
		assertTrue(niveau1.contains(carte1));
		assertTrue(niveau1.contains(carte2));
		assertTrue(niveau1.contains(carte3));
	}
	@Test
	void checkNoNulllObjectIsAdded(){
		Card carte2 = null;
		
		niveau1.insert(carte1);
		niveau1.insert(carte2);
		niveau1.insert(carte3);
		
		assertTrue(niveau1.contains(carte1));
		assertFalse(niveau1.contains(carte2));
		assertTrue(niveau1.contains(carte3));
	}
	@Test
	void cardIsRemoved(){
		niveau1.remove(carte1);
		assertFalse(niveau1.contains(carte1));
	}
	@Test
	void isEmptyReturnTrueIfTheLevelIsEmpty(){
		Level lvl = new Level(0);
		assertTrue(lvl.isEmpty());
	}
	@Test
	void isEmptyReturnFalseIfTheLevelIsntEmpty(){
		Level lvl = new Level(1);
		lvl.insert(carte1);
		assertFalse(lvl.isEmpty());
	}
}
