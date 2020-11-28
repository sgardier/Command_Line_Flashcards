package ai.commands;

import ai.consoles.Console;
import ai.data.Storage;
import ai.domain.Card;
import ai.domain.LearningSchedule;
import ai.pubsub.EventChannel;
import ai.statistic.StatisticEvent;

/**
 * 
 * @author Simon Gardier - B1 - GR5
 * 
 * Commande permettant d'ajouter des cartes à un learningSchedule
 * 
 * Fonctionnalité : 
 * 		- ajouter une carte à LearningSchedule
 *
 */
public class AddCommand extends Command{
	private final Console console;
	private final LearningSchedule learningSchedule;
	private final Storage storage;
	private final EventChannel channel;
	
	/**
	 * @param c Console
	 * @param ls LearningSchedule
	 * @param st Storage
	 */
	public AddCommand(final Console c, final LearningSchedule ls, final Storage st, final EventChannel ec){
		super("add", "Add a card to the system");
		this.console = c;
		this.learningSchedule = ls;
		this.storage = st;
		this.channel = ec;
	}
	@Override
	public void execute(){
		final String question = console.readLine("Card question :\n");
		final String reponse = console.readLine("Card answer :\n");
		final Card toAdd = new Card(question, reponse);
		if(learningSchedule.add(toAdd)){
			console.printLine("Card added to level 1 : "+toAdd.getQuestion());
			channel.publish(new StatisticEvent(toAdd, 0, learningSchedule.getCurrentDay()));
			storage.save(learningSchedule);
		}
		else{
			console.printLine("Card already added sorry :( ");
		}
	}
}
