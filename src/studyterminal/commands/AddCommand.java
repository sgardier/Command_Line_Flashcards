package studyterminal.commands;
import studyterminal.consoles.Console;
import studyterminal.data.Storage;
import studyterminal.domain.Card;
import studyterminal.domain.LearningSchedule;
import studyterminal.pubsub.EventChannel;
import studyterminal.statistic.StatisticEvent;

/**
 * @author Simon Gardier
 */

/**
 * Command used to add a card to a learningSchedule
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
		final String question = console.readLine("Card question : ");
		final String reponse = console.readLine("Card answer : ");
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
