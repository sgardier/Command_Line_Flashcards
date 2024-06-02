package studyterminal.statistic;
import java.util.HashSet;
import java.util.Set;
import studyterminal.domain.Card;
import studyterminal.pubsub.RevisionStatistic;

/**
 * @author Simon Gardier
 */

/**
 * Class for the statistic of the average number of revisions per card
 */
public class AverageCardsRevisionCount implements RevisionStatistic{
	
	private double qttOfCardRevions;
	private final Set<Card> cardsRevised = new HashSet<Card>();

	@Override
	public void update(final StatisticEvent revisionEvent) {
		final Card cardMoved = revisionEvent.getCardMoved();
		if(cardsRevised.contains(cardMoved)) {
			qttOfCardRevions++;
		}
		else{
			cardsRevised.add(cardMoved);
		}
	}
	@Override
	public String toString(){
		double ratio;
		if(cardsRevised.isEmpty()) {
			ratio = 0;
		}
		else{
			ratio = (qttOfCardRevions / cardsRevised.size());
		}
		return "Number of revision per card on average today : "+String.format("%.1f", ratio);
	}
}
