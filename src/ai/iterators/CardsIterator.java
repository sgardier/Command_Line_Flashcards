package ai.iterators;

import java.util.Iterator;
import java.util.Queue;

import ai.domain.Card;
/**
 * 
 * @author Simon Gardier - B1 - GR5
 * 
 * Classe représentant un itérateur de cartes à lire
 * 
 * Fonctionnalités : 
 *		- ajouter une carte en fin d'une queue de cartes 
 *		- récupérer la carte courante dans la queue  
 *		- Vérifier que la queue n'est pas vide
 */
public class CardsIterator implements Iterator<Card>{

	private final Queue<Card> toRead;
	
	/**
	 * 
	 * @param cardsToRead liste de carte à réviser
	 */
	public CardsIterator(final Queue<Card> cardsToRead) {
		this.toRead = cardsToRead;
	}
	
	/**
	 * 
	 * @param card carte à ajouter
	 */
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
