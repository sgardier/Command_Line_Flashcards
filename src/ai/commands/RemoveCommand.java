package ai.commands;

import java.util.Iterator;

import ai.Program;
import ai.consoles.Console;
import ai.data.LearningScheduleStorage;
import ai.data.Storage;
import ai.domain.Card;
import ai.domain.LearningSchedule;

/**
 * 
 * @author simon Gardier - B1 -GR5
 * 
 * Commande permettant de retirer une cartes d'un learningSchedule 
 * 		
 */
public class RemoveCommand extends Command{
	final private Console console;
	final private LearningSchedule learningSchedule;
	final private Storage storage;
	
	/**
	 * 
	 * @param c Console
	 * @param ls LearningSchedule
	 * @param st Storage
	 */
	public RemoveCommand(final Console c, final LearningSchedule ls, final Storage st) {
		super("remove","Remove a card");
		this.console = c;
		this.learningSchedule = ls;
		this.storage = st == null ? new LearningScheduleStorage(Program.LEARNING_SCHEDULE_STORAGE_PATH) : st;
	}

	@Override
	public void execute() {
		
		final char choiceChar = 'R';
		final Iterator<Card> cardsIterator = learningSchedule.getAllCards().iterator();
		
		while(cardsIterator.hasNext()){
			final Card card = cardsIterator.next();
			console.print(card.toString()+"\n");
			final String choice = console.readLine("Pressez [R] pour retirer la carte : \n");
			if(choice != null && !choice.isEmpty()){
				if(choice.toUpperCase().charAt(0) == choiceChar) {
					learningSchedule.remove(card);
					console.print("Carte retirée : "+ card.getQuestion()+"\n");
				}
			}
		}
		storage.save(learningSchedule);
	}
}
