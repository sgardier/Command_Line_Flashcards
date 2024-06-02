package studyterminal.commands;
import java.util.Iterator;
import studyterminal.consoles.Console;
import studyterminal.domain.Card;
import studyterminal.statistic.AverageCardsRevisionCount;
import studyterminal.statistic.CardByLevelCount;

/**
 * @author Simon Gardier
 */

/**
 * Command capable of displaying the statistics of the current execution of the program
 * Implemements :
 * - Display of statistics
 */

public class ShowStatsBoardCommand extends Command{
	private final Console console;

	//Statistics objects
	private final AverageCardsRevisionCount averageCardRevision;
	private final CardByLevelCount cardByLevel;
	
	/**
	 * @param console
	 * @param averageCardRevision avg revision by card stat
	 * @param cardByLevel revisoon by level stats
	 * 
	 */
	public ShowStatsBoardCommand(final Console console, final AverageCardsRevisionCount averageCardRevision, final CardByLevelCount cardByLevel) {
		super("stats", "Display the statistics");
		this.console = console;
		this.averageCardRevision = averageCardRevision;
		this.cardByLevel = cardByLevel;
	}
	
	@Override
	public void execute() {
		console.printLine(	"   _____ _        _             \n" +
							"  / ____| |      | |            \n" +
							" | (___ | |_ __ _| |_ ___       \n" +
							"  \\___ \\| __/ _` | __/ __|    \n" +
							"  ____) | || (_| | |_\\__ \\    \n" +
							" |_____/ \\__\\__,_|\\__|___/   ");
		console.printLine("-".repeat(averageCardRevision.toString().length()));
		
		// Average card revision statistic
		console.printLine(averageCardRevision.toString());
		console.printLine("-".repeat(averageCardRevision.toString().length()));

		//Card by level count statistic
		console.printLine(cardByLevel.description());
		final Iterator<Card> cardsHavingData = cardByLevel.iterator();
		while(cardsHavingData.hasNext()){
			final Card currentCard = cardsHavingData.next();
			final String choice = console.readLine("Show revisions for [Question : "+ currentCard.getQuestion()+", Answer : "+ currentCard.getAnswer()+"]? ([Y]es or [N]o) : ");
			if("Y".compareToIgnoreCase(choice) == 0){
				console.printLine(cardByLevel.toString(currentCard));
			}
		}
		console.printLine("No more cards to show");
		console.printLine("-".repeat(averageCardRevision.toString().length()));
		
	}
}
