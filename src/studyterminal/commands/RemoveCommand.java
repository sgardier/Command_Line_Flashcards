package studyterminal.commands;
import java.util.Iterator;
import studyterminal.Program;
import studyterminal.consoles.Console;
import studyterminal.data.LearningScheduleStorage;
import studyterminal.data.Storage;
import studyterminal.domain.Card;
import studyterminal.domain.LearningSchedule;

/**
 * @author Simon Gardier
 */

/**
 * Command to remove a card from a learningSchedule 
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
			final String choice = console.readLine("[R]emove the card : "+card.toString()+" ? ");
			if(choice != null && !choice.isEmpty()){
				if(choice.toUpperCase().charAt(0) == choiceChar) {
					learningSchedule.remove(card);
				}
			}
		}
		storage.save(learningSchedule);
	}
}
