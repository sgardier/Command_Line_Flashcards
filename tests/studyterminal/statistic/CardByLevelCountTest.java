package studyterminal.statistic;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studyterminal.domain.Card;

class CardByLevelCountTest {

	Card card1 = new Card("Question 1", "Answer 1");
	Card card2 = new Card("Question 2", "Answer 2");
	Card card3 = new Card("Question 3", "Answer 3");
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
	            "Level 1     | 1",
	            "Level 1     | 1",
	            "Level 1     | 2",
	    };

        String output1 = CardByLevelCount.toString(card1);
        String output2 = CardByLevelCount.toString(card2);
        String output3 = CardByLevelCount.toString(card3);

        assertTrue(output1.contains(correctOutput[0]));
        assertTrue(output2.contains(correctOutput[1]));
        assertTrue(output3.contains(correctOutput[2]));
	}
}
