package ai.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ai.consoles.FakeConsole;
import ai.domain.Box;
import ai.domain.Card;
import ai.domain.Level;
import ai.statistic.AverageCardsRevisionCount;
import ai.statistic.CardByLevelCount;
import ai.statistic.StatisticEvent;

class ShowStatsBoardCommandTest {
	
	Card card1 = new Card("Question 1", "Réponse 1");
	Card card2 = new Card("Question 2", "Réponse 2");
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

	@Test
	void showStatsisCorreclyCreatedAndExecuted(){
		String[] supposedOutput = 	{"Les statistiques affichées sont :",
									 "- Le nombre moyen de révisions par carte : 0.0",
									 "Les statistiques pour cette cartes sont :  Niveau n*1 : 1 | Niveau n*2 : 0 | Niveau n*3 : 0 | Niveau n*4 : 0 | Niveau n*5 : 0 | Niveau n*6 : 0 | Niveau n*7 : 0 |",
									 "Les statistiques pour cette cartes sont :  Niveau n*1 : 1 | Niveau n*2 : 0 | Niveau n*3 : 0 | Niveau n*4 : 0 | Niveau n*5 : 0 | Niveau n*6 : 0 | Niveau n*7 : 0 |"};
		
		console.addUserInput("C","c");
		cmd.execute();
		
		for (String string : supposedOutput) {
			console.verifyOutputContains(string);
		}
	}
}
