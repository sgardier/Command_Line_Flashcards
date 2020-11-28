package ai.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ai.consoles.FakeConsole;
import ai.data.FakeStorage;
import ai.domain.Box;
import ai.domain.Card;
import ai.domain.CardListFactory;
import ai.domain.LearningSchedule;
import ai.domain.algorithm.FakeCalendarAlgorithm;

class RemoveCommandTest {

	public static final String LEARNING_SCHEDULE_STORAGE_PATH = "res/learningSchedule.json"; 
	@Test
	void removeSeveralCardsFromTheBox() {
		
		FakeConsole console = new FakeConsole();
		Box box = new Box();
		LearningSchedule learningSchedule = new LearningSchedule(1, box, new FakeCalendarAlgorithm(3));
		FakeStorage storage = new FakeStorage();
		List<Card> cards = Arrays.asList(
			new Card("L'aéroport est à gauche", "The airport is to the left"),
			new Card("My taxi is here", "Mon taxi est ici"),
			new Card("Here is your bus", "Voilà ton bus"),
			new Card("Here is the taxi", "Voilà le taxi"),
			new Card("Est-il à l'aéroport ?", "Is he at the airport?"),
			new Card("Êtes-vous David ?", "Are you David?")
		);
		for (Card card : cards){
			learningSchedule.add(card);
		}
		
		final String[] userInput = { "R", "", "", "R", "", "R" };

		RemoveCommand cmd = new RemoveCommand(console, learningSchedule, storage);
		console.addUserInput(userInput);
		cmd.execute();
		
		//There should be three cards left
		assertEquals(3, CardListFactory.asList(learningSchedule.getAllCards()).size());
	    final List<Card> cardsAsList = CardListFactory.asList(cards);
		for (Card card: learningSchedule.getAllCards()) {
	        assertTrue(cardsAsList.contains(card));
	    }
			
		storage.verifySaveCallCount(1);
		storage.verifyLoadCallCount(0);
	}

}
