package studyterminal.commands;
import org.junit.jupiter.api.BeforeEach;
import studyterminal.consoles.FakeConsole;
import studyterminal.domain.Box;
import studyterminal.domain.Card;
import studyterminal.domain.Level;
import studyterminal.statistic.AverageCardsRevisionCount;
import studyterminal.statistic.CardByLevelCount;
import studyterminal.statistic.StatisticEvent;

class ShowStatsBoardCommandTest {
	
	Card card1 = new Card("Question 1", "Answer 1");
	Card card2 = new Card("Question 2", "Answer 2");
	Level niveau1;
	Level niveau2;
	Box b;
	
	FakeConsole console;
	
	ShowStatsBoardCommand cmd;
	CardByLevelCount cardByLevelStats;
	AverageCardsRevisionCount averageCardsRevisionStats ;
	@BeforeEach
	void init(){
		niveau1 = new Level(1);
		niveau1.insert(card1);
		niveau2 = new Level(2);
		niveau2.insert(card2);
		
		b = new Box(niveau1, niveau2);
		
		console = new FakeConsole();
		
		cardByLevelStats = new CardByLevelCount();
		cardByLevelStats.update(new StatisticEvent(card1, 0, 1));
		cardByLevelStats.update(new StatisticEvent(card2, 0, 1));
		
		averageCardsRevisionStats = new AverageCardsRevisionCount();
		averageCardsRevisionStats.update(new StatisticEvent(card1, 0, 1));
		
		cmd = new ShowStatsBoardCommand(console, averageCardsRevisionStats,cardByLevelStats);
	}
}
