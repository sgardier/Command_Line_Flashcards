package ai;

import ai.commands.AddCommand;
import ai.commands.CommandMap;
import ai.commands.ExitCommand;
import ai.commands.RemoveCommand;
import ai.commands.ReviseCommand;
import ai.commands.ShowStatsBoardCommand;
import ai.consoles.Console;
import ai.consoles.UserConsole;
import ai.data.LearningScheduleStorage;
import ai.data.Storage;
import ai.domain.Box;
import ai.domain.LearningSchedule;
import ai.domain.algorithm.CalendarAlgorithm;
import ai.domain.algorithm.CalendarAlgorithmComputationnal;
import ai.pubsub.SequentialEventChannel;
import ai.statistic.AverageCardsRevisionCount;
import ai.statistic.CardByLevelCount;
/**
 * 
 * @author simon
 *	Classe princale du programme. 
 *	
 *	Fonctionnalités :
 *		- Instancie - la collection de commandes
 *				- la console dans laquelle le programme intéragit avec l'utilisateur
 *				- le FrontController qui gère les interractions avec l'utilisateur
 *		- Lance l'exécution du programme via la méthode loop() du FrontController 
 */
public class Program {
	public static final String LEARNING_SCHEDULE_STORAGE_PATH = "res/learningSchedule.json"; 
	public static final int NUMBER_OF_LEVELS = 7; 

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
		
		if (learningSchedule == null) { //Impossible de charger le fichier
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
