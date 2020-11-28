package ai.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author simoSimon Gardier - B1 - GR5
 * 
 * Classe représentant un niveau d'une boite de Leitner
 * 
 * Fonctionnalités :
 * 
 *  	- créer des objects Level
 *  	- insérer un objet Card dans sa collection de cartes
 *  	- vérifier si un objet Card existe déjà dans sa collectio de Card's
 *  	- récupérer son numéro de niveau
 *
 *	Choix de la collection : 
 *		Interface : Set, impossible d'avoir deux cartes les mêmes dans un même niveau
 *		Classe concrète : HashSet, permet de stocker/retirer/rechercher une carte très efficacemment
 *		CTT de la méthode contains() : O(1) car il suffit "d'hasher l'objet Card" et % cette valeur pour récupérer l'emplacement auquel il se devrait se trouver
 *								  mais O(N) si tous les objets sont en collisions et ont été stocké dans le même bucket
 */		
public class Level implements Iterable<Card>{
	private final int numLevel;
	private final Set<Card> cards = new HashSet<Card>();
	
	/**
	 * 
	 * @param numLevel : numéro du niveau
	 */
	public Level(final int numLevel){
		if(numLevel > 0){
			this.numLevel = numLevel;
		}
		else {
			this.numLevel = 1;
		}
	}
	
	/**
	 * 
	 * @return le numéro du niveau
	 */
	public int getNumLevel() {
		return numLevel;
	}
	
	/**
	 * Recherche un objet dans la collection d'objets Card
	 * @param card : objet à rechercher dans la collection
	 * @return Booléen : résultat de la recherche dans la collection
	 */
	public boolean contains(final Card card){
		return card != null ? cards.contains(card) : false;
	}
	
	/**
	 * 
	 * Pré condition : - la carte n'est pas déjà contenue dans le niveau
	 * 				   - la carte n'est pas null
	 * Post condition: - la carte a été ajoutée au niveau si elle n'y était pas déjà et si elle n'était pas null
	 * Insère un objet Card dans la collection de d'objet Card du niveau
	 * @param card : object Card à ajouter
	 */
	public void insert(final Card card) {
		if(card != null){
			cards.add(card);
		}
	}
	/**
	 * @param card carte à retirer
	 * @returnsi cette dernière se trouve dans ce niveau, retourne true. Si la carte ne s’y trouve pas, remove retourne false.
	 */
	public boolean remove(final Card card) {
		if(contains(card)){
			cards.remove(card);
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * 
	 * @return vrai si le niveau est vide, faux sinon
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
}
