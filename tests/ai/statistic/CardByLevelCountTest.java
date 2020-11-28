package ai.statistic;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ai.domain.Card;

class CardByLevelCountTest {

	Card card1 = new Card("Question 1", "Réponse 1");
	Card card2 = new Card("Question 2", "Réponse 2");
	Card card3 = new Card("Question 3", "Réponse 3");
	StatisticEvent dataUpdate1 = new StatisticEvent(card1, 0, 1);
	StatisticEvent dataUpdate2 = new StatisticEvent(card2, 0, 1);
	StatisticEvent dataUpdate3 = new StatisticEvent(card3, 0, 1);
	CardByLevelCount CardByLevelCount;
	
	@BeforeEach
	void init(){
		CardByLevelCount = new CardByLevelCount();
		CardByLevelCount.update(dataUpdate1);
		CardByLevelCount.update(dataUpdate2);
		//Double card3 update
		CardByLevelCount.update(dataUpdate3);
		CardByLevelCount.update(dataUpdate3);
	}
	
	@Test
	void normalCaseStatisticUpdateAndDisplay(){
		String[] correctOutput = {
	            "Les statistiques pour cette cartes sont :  Niveau n*1 : 1 | Niveau n*2 : 0 | Niveau n*3 : 0 | Niveau n*4 : 0 | Niveau n*5 : 0 | Niveau n*6 : 0 | Niveau n*7 : 0 |",
	            "Les statistiques pour cette cartes sont :  Niveau n*1 : 1 | Niveau n*2 : 0 | Niveau n*3 : 0 | Niveau n*4 : 0 | Niveau n*5 : 0 | Niveau n*6 : 0 | Niveau n*7 : 0 |",
	            "Les statistiques pour cette cartes sont :  Niveau n*1 : 2 | Niveau n*2 : 0 | Niveau n*3 : 0 | Niveau n*4 : 0 | Niveau n*5 : 0 | Niveau n*6 : 0 | Niveau n*7 : 0 |",
	    };
        

        String output1 = CardByLevelCount.toString(card1);
        String output2 = CardByLevelCount.toString(card2);
        String output3 = CardByLevelCount.toString(card3);
        assertTrue(output1.indexOf(correctOutput[0]) == 0);
        assertTrue(output2.indexOf(correctOutput[1]) == 0);
        assertTrue(output3.indexOf(correctOutput[2]) == 0);
	}
}
