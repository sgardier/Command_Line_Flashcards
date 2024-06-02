package studyterminal;
import studyterminal.commands.AddCommand;
import studyterminal.commands.CommandMap;
import studyterminal.commands.ExitCommand;
import studyterminal.commands.RemoveCommand;
import studyterminal.commands.ReviseCommand;
import studyterminal.commands.ShowStatsBoardCommand;
import studyterminal.consoles.Console;
import studyterminal.consoles.UserConsole;
import studyterminal.data.LearningScheduleStorage;
import studyterminal.data.Storage;
import studyterminal.domain.Box;
import studyterminal.domain.LearningSchedule;
import studyterminal.domain.algorithm.CalendarAlgorithm;
import studyterminal.domain.algorithm.CalendarAlgorithmComputationnal;
import studyterminal.pubsub.SequentialEventChannel;
import studyterminal.statistic.AverageCardsRevisionCount;
import studyterminal.statistic.CardByLevelCount;

/**
 * @author Simon Gardier
 */

/**
 * Main class
 * Create the collection of commands that can be executed by the user, 
 * the console in which the program interacts with the user and 
 * the FrontController that manages the interactions with the user
 */
public class Program {
	public static final String LEARNING_SCHEDULE_STORAGE_PATH = "res/learningSchedule.json"; 
	public static final int NUMBER_OF_LEVELS = 2;

	public static void main(String[] args) {
		
		final Console console = new UserConsole();
		final Storage storage = new LearningScheduleStorage(LEARNING_SCHEDULE_STORAGE_PATH);
		final CalendarAlgorithm calendarAlgo = new CalendarAlgorithmComputationnal(NUMBER_OF_LEVELS);
		LearningSchedule learningSchedule = storage.load(calendarAlgo);
		

		final SequentialEventChannel eventChannel = new SequentialEventChannel();
		final AverageCardsRevisionCount averageCardRevision = new AverageCardsRevisionCount();
		final CardByLevelCount  cardByLevel = new CardByLevelCount();
		eventChannel.subscribe(averageCardRevision);
		eventChannel.subscribe(cardByLevel);
	
		if (learningSchedule == null) {
			learningSchedule = new LearningSchedule(1, new Box(NUMBER_OF_LEVELS), calendarAlgo);
		}
		final CommandMap map = new CommandMap(
                    new ExitCommand(console), 
                    new ShowStatsBoardCommand(console, averageCardRevision, cardByLevel),
                    new AddCommand(console, learningSchedule, storage, eventChannel), 
                    new RemoveCommand(console, learningSchedule, storage),
                    new ReviseCommand(console, learningSchedule, storage, eventChannel)
        );
		final FrontController controller = new FrontController(console, map);		
		controller.loop();
	}
}
