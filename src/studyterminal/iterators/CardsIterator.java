package studyterminal.iterators;
import java.util.Iterator;
import java.util.Queue;
import studyterminal.domain.Card;
/**
 * @author Simon Gardier
 */

/**
 * Class representing cards to revise
 */
public class CardsIterator implements Iterator<Card>{

	private final Queue<Card> toRead;
	
	/**
	 * @param cardsToRead queue of cards to revise
	 */
	public CardsIterator(final Queue<Card> cardsToRead) {
		this.toRead = cardsToRead;
	}

	public void add(Card card) {
		toRead.add(card);
	}

	@Override
	public boolean hasNext() {
		return !toRead.isEmpty();
	}

	@Override
	public Card next() {
		return toRead.poll();
	}
}
