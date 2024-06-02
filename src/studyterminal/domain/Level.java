package studyterminal.domain;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Simon Gardier
 */

/**
 * Class representing a box level
 * Implements :
 * - Create level
 * - Insert card
 * - Check if card is in level
 * - Retrieve level number
 */
public class Level implements Iterable<Card>{
	private final int numLevel;
	private final Set<Card> cards = new HashSet<Card>();

	public Level(final int numLevel){
		if(numLevel > 0){
			this.numLevel = numLevel;
		}
		else {
			this.numLevel = 1;
		}
	}

	public int getNumLevel() {
		return numLevel;
	}

	public boolean contains(final Card card){
		return card != null ? cards.contains(card) : false;
	}

	public void insert(final Card card) {
		if(card != null){
			cards.add(card);
		}
	}

	public boolean remove(final Card card) {
		if(contains(card)){
			return cards.remove(card);
		}
		return false;
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}

	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
}
