package ai.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ai.consoles.FakeConsole;
import ai.data.FakeStorage;
import ai.domain.Box;
import ai.domain.Card;
import ai.domain.LearningSchedule;
import ai.domain.Level;
import ai.domain.algorithm.CalendarAlgorithmComputationnal;
import ai.pubsub.FakeEventChannel;

class ReviseCommandTest {
	
	Card card1 = new Card("Question 1", "Réponse 1");
	Card card2 = new Card("Question 2", "Réponse 2");
	Level niveau1;
	Level niveau2;
	Box b;
	
	FakeEventChannel channel;
	FakeConsole console;
	FakeStorage storage;
	LearningSchedule ls;
	
	ReviseCommand cmd;
	
	@BeforeEach
	void initialisationBox(){
		niveau1 = new Level(1);
		niveau1.insert(card1);
		niveau2 = new Level(2);
		niveau2.insert(card2);
		
		b = new Box(niveau1, niveau2);
	}
	@Test 
	void isReviseCorreclyExecuted() {
		ls = new LearningSchedule(1, b, new CalendarAlgorithmComputationnal(2));
		console = new FakeConsole();
		storage = new FakeStorage();
		channel = new FakeEventChannel();
		cmd = new ReviseCommand(console, ls, storage,channel);
		console.addUserInput("Réponse 1", "O", "r1", "O");
		cmd.execute();
		
		storage.verifySaveCallCount(1);
		storage.verifyLoadCallCount(0);
	}
	@Test 
	void isReviseCorreclyExecutedWhenThereIsntCardsInTheBox() {
		ls = new LearningSchedule(1, new Box(2), new CalendarAlgorithmComputationnal(2));
		console = new FakeConsole();
		storage = new FakeStorage();
		cmd = new ReviseCommand(console, ls, storage, channel);
		cmd.execute();
		
		storage.verifySaveCallCount(1);
		storage.verifyLoadCallCount(0);
	}
	@Test 
	void isReviseCorreclyExecutedWhenThereIsntCardsToReviseToday(){
		Box b = new Box(new Level(1), new Level(2),new Level(3));
		b.add(card1);
		b.moveCardToNextLevel(card1, true);
		b.moveCardToNextLevel(card1, true);
		
		ls = new LearningSchedule(1, b, new CalendarAlgorithmComputationnal(2));
		console = new FakeConsole();
		storage = new FakeStorage();
		channel = new FakeEventChannel();
		cmd = new ReviseCommand(console, ls, storage,channel);
		
		cmd.execute();
		
		storage.verifySaveCallCount(1);
		storage.verifyLoadCallCount(0);
	}
}
