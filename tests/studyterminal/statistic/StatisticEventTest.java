package studyterminal.statistic;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import studyterminal.Program;
import studyterminal.domain.Card;

class StatisticEventTest {
	StatisticEvent statisticEvent;
	@Test
	void EventIsCorreclyConstrucWithNullCard() {
        statisticEvent = new StatisticEvent(null, 0, 1);
        assertTrue("EmptyCardQuestion".equals(statisticEvent.getCardMoved().getQuestion()));
        assertTrue("EmptyCardAnswer".equals(statisticEvent.getCardMoved().getAnswer()));
	}
	@Test
	void EventIsCorreclyConstrucWithCorrectIndex() {
        statisticEvent = new StatisticEvent(new Card("",""), 0, 1);
        assertTrue(0 == statisticEvent.getLevelIndexDestination());
	}
	@Test
	void EventIsCorreclyConstrucWithTooSmallIndex() {
        statisticEvent = new StatisticEvent(new Card("",""), -1, 1);
        assertTrue(0 == statisticEvent.getLevelIndexDestination());
	}
	@Test
	void EventIsCorreclyConstrucWithTooBigIndex() {
        statisticEvent = new StatisticEvent(new Card("",""), 7, 1);
        assertTrue(Program.NUMBER_OF_LEVELS - 1 == statisticEvent.getLevelIndexDestination());
	}
	@Test
	void EventIsCorreclyConstrucWithTooSmallDay() {
        statisticEvent = new StatisticEvent(new Card("",""), 0, -1);
        assertTrue(1 == statisticEvent.getMoveDay());
	}
}
