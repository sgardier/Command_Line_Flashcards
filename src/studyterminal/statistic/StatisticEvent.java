package studyterminal.statistic;
import studyterminal.Program;
import studyterminal.domain.Card;

/**
 * @author Simon Gardier
 */

/**
 * @author Simon Gardier - B1 - GR5
 * Event whose data can be analyzed by statistics
 */
public class StatisticEvent {
	private final Card cardMoved;
	private final int indexDestination;
	private final int moveDay;

	public StatisticEvent(final Card cardMoved, final int indexDestination, final int moveDay){
		this.cardMoved = cardMoved == null ? new Card("EmptyCardQuestion","EmptyCardAnswer") : cardMoved;
		this.indexDestination = indexDestination < 0  ? 0 : indexDestination > Program.NUMBER_OF_LEVELS - 1 ?  Program.NUMBER_OF_LEVELS - 1 : indexDestination;
		this.moveDay = moveDay < 1 ? 1 : moveDay;
	}

	public Card getCardMoved() {
		return cardMoved;
	}
	
	public int getLevelIndexDestination() {
		return indexDestination;
	}
	
	public int getMoveDay() {
		return moveDay;
	}
	
}
