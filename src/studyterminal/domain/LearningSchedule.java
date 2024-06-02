package studyterminal.domain;
import studyterminal.Program;
import studyterminal.domain.algorithm.CalendarAlgorithm;
import studyterminal.domain.algorithm.CalendarAlgorithmComputationnal;
import studyterminal.iterators.CardsIterator;

/**
 * @author Simon Gardier
 */

/**
 * Represent a learning schedule
 * Implement :
 * - Return the current day
 * - Add a card
 * - Remove a card
 * - Iterate in all the box cards
 * - Check if there are still cards to revise today
 * - Go to next day
 * - Move card
 */
public class LearningSchedule {
	
	private final Box leitnerBox;
	private CalendarAlgorithm calendarAlgo;
	private int currentDay;
	
	/**
	 * Preconditions : - calendarAlgorithm != null else CalendarAlgorithmComputationnal created based on number of levels
	 * 				   - LeitnerBox != null else LeitnerBox created based on number of levels
	 * Post condition : attrbiutes correctly initialized
	 * @param jour starting day for learning
	 * @param LeitnerBox box with levels containing cards
	 * @param calendarAlgorithm learning algorithm
	 */
	public LearningSchedule(final int departDay, final Box LeitnerBox, final CalendarAlgorithm calendarAlgo){
		this.calendarAlgo = calendarAlgo != null ? calendarAlgo : new CalendarAlgorithmComputationnal(Program.NUMBER_OF_LEVELS);
		this.leitnerBox = LeitnerBox == null ? new Box(Program.NUMBER_OF_LEVELS) : LeitnerBox;
		this.currentDay = departDay > 1 ? departDay : 1;
	}

	/**
	 * @param calendarAlgorithm Calendaralgorithm to use
	 */
	public void setCalendarAlgorithm(final CalendarAlgorithm calendarAlgorithm) {
		this.calendarAlgo = calendarAlgorithm;
	}

	/**
	 * @return iterator for the cards of the day
	 */
	public CardsIterator getCardsToRevise() {
		return leitnerBox.getIteratorForLevels(calendarAlgo.getLevelsOfDay(currentDay));
	}

	public int getCurrentDay(){
		return currentDay;
	}

	public void resetCurrentDay(){
		currentDay = 1;
	}

	public boolean add(final Card card){
		return leitnerBox.add(card);
	}

	public boolean remove(final Card card) {
		return leitnerBox.remove(card);
	}

	public Iterable<Card> getAllCards(){
		return leitnerBox;
	}

	public int moveCardToNextLevel(final Card card, final boolean answerKnown) {
		return leitnerBox.moveCardToNextLevel(card, answerKnown);
	}

	public void nextDay(){
		currentDay++;
	}

	public boolean isRevisable() {
		return !leitnerBox.isEmpty();
	}
}
