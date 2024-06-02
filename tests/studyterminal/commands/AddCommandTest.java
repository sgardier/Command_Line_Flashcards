package studyterminal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studyterminal.consoles.FakeConsole;
import studyterminal.data.FakeStorage;
import studyterminal.domain.Box;
import studyterminal.domain.Card;
import studyterminal.domain.LearningSchedule;
import studyterminal.domain.Level;
import studyterminal.domain.algorithm.FakeCalendarAlgorithm;
import studyterminal.pubsub.FakeEventChannel;

class AddCommandTest {
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
	
	FakeEventChannel channel;
	LearningSchedule ls;
	FakeConsole console;
	FakeStorage storage;
	AddCommand cmd;
	
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
	void addCardAndSaveCard() {
		final String[][] userInput = { 
		        { "Who let the dogs out ?", "Who who whoo !" }
		};
		
		ls = new LearningSchedule(1, b, new FakeCalendarAlgorithm(3));
		console = new FakeConsole();
		storage = new FakeStorage();
		channel = new FakeEventChannel();
		cmd = new AddCommand(console, ls, storage, channel);
		
		console.addUserInput(userInput[0]);
		cmd.execute();
		
		channel.verifyNumberPublished(1);
		storage.verifySaveCallCount(1);
		storage.verifyLoadCallCount(0);
	}
}
