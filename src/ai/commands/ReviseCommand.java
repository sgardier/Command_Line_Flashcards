package ai.commands;

import ai.Program;
import ai.consoles.Console;
import ai.data.LearningScheduleStorage;
import ai.data.Storage;
import ai.domain.Card;
import ai.domain.LearningSchedule;
import ai.iterators.CardsIterator;
import ai.pubsub.EventChannel;
import ai.statistic.StatisticEvent;

/**
 * 
 * @author Simon Gardier - B1 - GR5
 *	
 *	Commande permettant de réviser les cartes du jour
 */
public class ReviseCommand extends Command{
	
	private final Console console;
	private final LearningSchedule learningSchedule;
	private final Storage storage;
	private final EventChannel channel;
	
	/**
	 * Pré condition : - Le LearningScheduleStorage est différent de null
	 * @param c console dans laquelle on affiche
	 * @param ls LearningSchedule 
	 * @param st storage 
	 * @param ec event channel 
	 */
	public ReviseCommand(final Console c, final LearningSchedule ls, final Storage st, final EventChannel ec) {
		super("revise", "Revise the cards of the day");
		this.console = c;
		this.learningSchedule = ls;
		this.storage = st == null ? new LearningScheduleStorage(Program.LEARNING_SCHEDULE_STORAGE_PATH) : st;
		this.channel = ec;
	}
	@Override
	public void execute() {
		console.print("---Revision "+ learningSchedule.getCurrentDay()+"---\n");
		if(learningSchedule.isRevisable()){
			final CardsIterator cardsIte = learningSchedule.getCardsToRevise();	
			if(cardsIte.hasNext()){
				while(cardsIte.hasNext()){
					final Card currentCard = cardsIte.next();
					console.print("Question : "+ currentCard.getQuestion() +" ? \n");
					console.readLine("Your answer -> ");
					String réponseJuste = currentCard.getAnswer();
					String choice = " ";
					do{
						console.print("The right answer was : \" "+réponseJuste+" \" \n");
						choice = console.readLine("Did you got the right answer? ([Y]es ou [N]o) \n");
					}while(choice.toUpperCase().charAt(0) != 'Y' && choice.toUpperCase().charAt(0) != 'N' );
					int nextLevel = learningSchedule.moveCardToNextLevel(currentCard, "Y".equals(choice));
					channel.publish(new StatisticEvent(currentCard, nextLevel, learningSchedule.getCurrentDay()));
					if(choice.toUpperCase().charAt(0) == 'N'){
						cardsIte.add(currentCard);
					}
				}
			}
			else{
				console.print("No cards to revise today \n");
			}
		}
		else {
			console.print("Your box is empty, congratulation ! You've learn all the cards ! \n");
		}
		learningSchedule.nextDay();
		storage.save(learningSchedule);
	}
}
