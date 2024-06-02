package studyterminal.statistic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import studyterminal.Program;
import studyterminal.domain.Card;
import studyterminal.pubsub.RevisionStatistic;

/**
 * @author Simon Gardier
 */

/**
 * Class representing the number of revisions by level for each card
 */
public class CardByLevelCount implements RevisionStatistic, Iterable<Card>{
	private final Map<Card, List<Integer>> cardsData;
	
	public CardByLevelCount(){
		cardsData = new HashMap<Card,List<Integer>>();
	}

	@Override
	public void update(final StatisticEvent revisionEvent) {
		final Card cardMoved = revisionEvent.getCardMoved();
		
		if(cardsData.containsKey(cardMoved)){
			final List<Integer> cardData = cardsData.get(cardMoved);
			final int indexDestination = revisionEvent.getLevelIndexDestination();
			cardData.set(indexDestination, cardData.get(indexDestination) + 1);
		}
		else{
			final List<Integer> cardDataArray = new ArrayList<Integer>();
			cardDataArray.add(1);
			for(int i = 1; i < Program.NUMBER_OF_LEVELS; i++) {
				cardDataArray.add(0);
			}
			cardsData.put(cardMoved, cardDataArray);
		}
	}

	public String description(){
		return "Revisions by level for each card";
	}

	public String toString(final Card card){
		final StringJoiner representation = new StringJoiner(" ");
		final List<Integer> currentCardData = cardsData.get(card);

		String description = "Number of revisions";
		String title = String.format(" %-10s  | %s\n", "Level", description);
		representation.add(" "+"-".repeat(title.length())+"\n");
		representation.add(title);
		representation.add("-".repeat(title.length())+"\n");

		for (int i = 0; i < currentCardData.size(); i++) {
			representation.add(String.format(" %-10s  | %s\n", "Level "+(i + 1), currentCardData.get(i)));
		}
		representation.add("-".repeat(title.length()));
		
		return representation.toString();
	}

	@Override
	public Iterator<Card> iterator(){
		return this.cardsData.keySet().iterator();
	}
}
