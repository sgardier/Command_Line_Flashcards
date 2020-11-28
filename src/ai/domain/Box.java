package ai.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ai.iterators.CardsIterator;

/**
 * 
 * @author Simon Gardier - B1 - GR5
 * 
 * Classe représentant une boite de Leitner
 * 
 * Fonctionnalités :
 * 		- Ajouter une carte
 * 		- communiquer sa quantité de niveaux
 * 		- Retirer une carte
 * 		- Retourner les cartes d'un ensemble de niveaux de la boite
 * 		- Retourner les cartes d'un niveau particulier
 * 		- Déplcaer une carte au niveau suivant
 * 		- Communiquer sur son contenu (Présence ou non de cartes dans la boite)
 * 
 * 	--- Choix de collection ---
 * 
 *	Implémentation 	-> List : 	- Permet de récupérer un élément sur base de son indice 
 *								- Permet d'ajouter un élément dans la collection
 *
 *	Classe concrète -> ArrayList : 	- Permet de récupérer très rapidement un élément sur base de son indice -> CTT get() : O(1)
 *									- Permet d'ajouter un élément dans la collection de façon optimisée pour les 10 premiers éléments
 *
 *					-> CTT add() :  O(M) où M = nombre de niveaux car il faut parcourir la liste contenant les niveaux (CTT = O(M))
 *									et faire appel à leur méthode contains qui, dans mon cas, possède une CTT O(1)
 *
 *									À chaque redimensionnement de la collection add() passe à O(N + M) où N = nombre de cartes comprise dans le niveau
 *									concerné et m = nombre de niveaux. 
 *									Etant donné qu'à chaque redimensionnement le tableau est étendu de 150% de sa taille et non pas d'un seul élément,
 *									le redimensionnement est peu fréquent. 
 */					 
public class Box implements Iterable<Card>{
	private final List<Level> box = new ArrayList<Level>();
	
	/**
	 * 
	 * @param qqtOfLevel nombre de niveaux que la boite contient
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
	 * @param niveaux : Level's composant la box
	 */
	public Box(final Level ... niveaux){
		if(niveaux.length >= 2){
			for (final Level level : niveaux){
				box.add(level);
			}
		}
		else {
			box.add(new Level(1));
			box.add(new Level(2));
		}
	}
	/**
	 * Pré condition : - la carte n'est pas déjà contenue dans la boite
	 * post condition : - retourne true si la carte a été ajoutée dans la boite sinon false
	 * 					- la carte a été ajoutée dans la boite si la carte n'était pas déjà contenue dedans
	 * @param card carte à ajouter
	 * @return si oui ou non la carte a bien été ajoutée
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
	 * @param card à retirer de la boite de Leitner
	 * @return Retourne true si un niveau a effectivement supprimé la carte et false sinon.
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
	 * @param l index du niveau dans la collection de niveaux (object : Level)
	 * @return Itérateur de la collection du niveau concerné
	 */
	private Iterator<Card> getIteratorForLevel(final int l){
		final Level lv = box.get(l - 1);
		return lv.iterator();
	}
	/**
	 * Choix de l'implémentation de la queue -> LinkedList() :
	 * 		Nous avons besoin d'accéder au premier et au dernier élément de la queue pour lire le 1er élément, le retirer et ajouter
	 * 		des éléments en fin de queue.Or, LinkedList est opmtimisée pour accéder au premier/dernier élément. Ces accès se font 
	 * 		en O(1). 
	 * @param levels tableau d'entiers représentant les niveaux dont on veut 
	 * @return
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
	 * 
	 * @param card carte à déplacer dans la boite de Leitner
	 * @param answerKnown état de la réponse (correcte / fausse)
	 * @return niveau auquel la carte est ajoutée
	 * 
	 */
	public int moveCardToNextLevel(final Card card, final boolean answerKnown) {
		for (int i = 0; i < box.size() - 1; i++){
			if(box.get(i).contains(card)){
				if(box.get(i).remove(card)){
					if(answerKnown){
						if(i+1 != box.size()){
							box.get(i+1).insert(card);
							return i+1;
						}
						return -1;
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
	 * CTT : O(N) où N est le nombre de niveaux : On doit appeler la méthode isEmpty() de chaque level dont la CTT est constante . Cette CTT 
	 * est constante car elle fait appel à la méthode isEmpty() de la collection stockant les cartes )
	 * @return retourne true si tous les niveaux de cette boîte sont vides sinon faux
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
	 * @return quantité de niveaux dans la boite
	 */
	public int getAmountOfLevels() {
		return box.size();
	}
}
