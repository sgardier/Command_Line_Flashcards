package ai.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ai.consoles.FakeConsole;
import ai.data.FakeStorage;
import ai.domain.Box;
import ai.domain.Card;
import ai.domain.LearningSchedule;
import ai.domain.Level;
import ai.domain.algorithm.FakeCalendarAlgorithm;
import ai.pubsub.FakeEventChannel;

class AddCommandTest {
	Card card1 = new Card("Question 1", "Réponse 1");
	Card card2 = new Card("Question 2", "Réponse 2");
	Card card3 = new Card("Question 3", "Réponse 3");
	Card card4 = new Card("Question 4", "Réponse 4");
	Card card5 = new Card("Question 5", "Réponse 5");
	Card card6 = new Card("Question 6", "Réponse 6");
	
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
		         //Sera utilisé pour simuler une entrée de l’utilisateur
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
