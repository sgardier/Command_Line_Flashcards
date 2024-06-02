package studyterminal.domain;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import studyterminal.iterators.CardsIterator;

/**
 * @author Simon Gardier
 */

/**
 * Box of Leitner
 */					 
public class Box implements Iterable<Card>{
	private final List<Level> box = new ArrayList<Level>();
	
	/**
	 * 
	 * @param qqtOfLevel number of levels for the box
	 */
	public Box(final int qqtOfLevel){
		if(qqtOfLevel >= 2){
			for (int i = 1; i <= qqtOfLevel; i++) {
				box.add(new Level(i));
			}
		}
		else {
			box.add(new Level(1));
			box.add(new Level(2));
		}
	}

	/**
	 * 
	 * @param niveaux : Box levels
	 */
	public Box(final Level ... levels){
		if(levels.length >= 2){
			for (final Level level : levels){
				box.add(level);
			}
		}
		else {
			box.add(new Level(1));
			box.add(new Level(2));
		}
	}
	/**
	 * @Precondition : card not already present in the box
	 * post condition : return true if card added, else false
	 * 					card added to the box
	 * @param card to add
	 * @return true if added, false if not
	 */
	public boolean add(final Card card) {
		for (final Level level : box) {
			if(level.contains(card)){
				return false;
			}
		}
		box.get(0).insert(card);
		return true;
	}
	
	/**
	 * 
	 * @param card to remove
	 * @return return true if removed, false if not
	 */
	public boolean remove(final Card card) {
		for (final Level level : box) {
			if(level.remove(card)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param l level index
	 * @return iterator for the cards of the level
	 */
	private Iterator<Card> getIteratorForLevel(final int l){
		final Level lv = box.get(l - 1);
		return lv.iterator();
	}

	/**
	 * @param levels levels indexes
	 * @return the iterator for the cards of the levels
	 */
	public CardsIterator getIteratorForLevels(final int... levels) {
		final Queue<Card> toRead = new LinkedList<Card>();
		for (final int i : levels) {
			final Iterator<Card> levelIt = getIteratorForLevel(i);
			while(levelIt.hasNext()){
				toRead.add(levelIt.next());
			}
		}
		return new CardsIterator(toRead);
	}

	@Override
	public Iterator<Card> iterator() {
		final List<Card> toIterator = new ArrayList<Card>();
		for (final Level level : box) {
			final Iterator<Card> levelIterator = level.iterator();
			while(levelIterator.hasNext()){
				toIterator.add(levelIterator.next());
			}
		}
		return toIterator.iterator();
	}
	
	/**
	 * @param card card to move
	 * @param answerKnown response state (true if known, false if not known)
	 * @return level in which the card has been added
	 */
	public int moveCardToNextLevel(final Card card, final boolean answerKnown) {
		for (int i = 0; i < box.size(); i++){
			if(box.get(i).contains(card)){
				if(box.get(i).remove(card)){
					if(answerKnown){
						if(i+1 < box.size()){
							box.get(i+1).insert(card);
							return i+1;
						}
						return i+1;
					}
					else{
						box.get(0).insert(card);
						return 1;
					}
				}
			}
		}
		return -1;
	}

	/**
	 * @return true if the box is empty, false if not
	 */
	public boolean isEmpty(){
		for (int i = 0; i < box.size(); i++){
			if(!box.get(i).isEmpty()){
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @return number of levels in the box
	 */
	public int getAmountOfLevels() {
		return box.size();
	}
}
