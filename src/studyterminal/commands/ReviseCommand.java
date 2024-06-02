package studyterminal.commands;
import studyterminal.Program;
import studyterminal.consoles.Console;
import studyterminal.data.LearningScheduleStorage;
import studyterminal.data.Storage;
import studyterminal.domain.Card;
import studyterminal.domain.LearningSchedule;
import studyterminal.iterators.CardsIterator;
import studyterminal.pubsub.EventChannel;
import studyterminal.statistic.StatisticEvent;

/**
 * @author Simon Gardier
 */

public class ReviseCommand extends Command{
	
	private final Console console;
	private final LearningSchedule learningSchedule;
	private final Storage storage;
	private final EventChannel channel;

	/**
	 * Precondition : LearningScheduleStorage != null
	 * @param c program displayed console
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
		String choice = " ";
		do{	
			choice = console.readLine("Are you sure you want to start day "+ learningSchedule.getCurrentDay() +" revision? ([Y]es or [N]o) : ");
		}while(choice.toUpperCase().charAt(0) != 'Y' && choice.toUpperCase().charAt(0) != 'N' );
		if(choice.toUpperCase().charAt(0) == 'N'){
			return;
		}
		String correctChoiceQuestion = "Did you got the right answer? ([Y]es or [N]o) : ";
		String revisionTitle = " Revision day "+ learningSchedule.getCurrentDay() +" ";
		int revisionTitlePaddingLength = correctChoiceQuestion.length() - revisionTitle.length();
		String revisionTitlePadding = "~".repeat(revisionTitlePaddingLength/2);
		console.print(revisionTitlePadding + revisionTitle + revisionTitlePadding + "\n");
		if(learningSchedule.isRevisable()){
			final CardsIterator cardsIte = learningSchedule.getCardsToRevise();	
			if(cardsIte.hasNext()){
				while(cardsIte.hasNext()){
					final Card currentCard = cardsIte.next();
					console.print("Question : "+ currentCard.getQuestion()+"\n");
					console.readLine("Answer : ");
					String rightAnswer = currentCard.getAnswer();
					choice = " ";
					do{
						console.print("Right answer : "+rightAnswer+"\n");
						choice = console.readLine(correctChoiceQuestion);
					}while(choice.toUpperCase().charAt(0) != 'Y' && choice.toUpperCase().charAt(0) != 'N' );
					int nextLevel = learningSchedule.moveCardToNextLevel(currentCard, "Y".equals(choice));
					channel.publish(new StatisticEvent(currentCard, nextLevel, learningSchedule.getCurrentDay()));
					if(choice.toUpperCase().charAt(0) == 'N'){
						cardsIte.add(currentCard);
					}
				}
				console.print("End of revision day "+learningSchedule.getCurrentDay()+". Come back tomorrow for your daily revision!\n");
				console.print(revisionTitlePadding + "~".repeat(revisionTitle.length()) + revisionTitlePadding + "\n");
			}
			else{
				console.print("No cards to revise today \n");
			}
			learningSchedule.nextDay();
		}
		else {
			learningSchedule.resetCurrentDay();
			console.print("Your box is empty, congratulation! You have learned all the cards!\n");
		}
		storage.save(learningSchedule);
	}
}
