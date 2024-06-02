package studyterminal.domain;
import java.util.*;

public class CardListFactory {
	public static List<Card> asList(final Card... cards) {
		return Arrays.asList(cards);
	}

	public static List<Card> asList(final Iterable<Card> cards) {
		final List<Card> result = new LinkedList<>();
		for (Card c : cards) {
			result.add(c);
		}
		return result;
	}
}
