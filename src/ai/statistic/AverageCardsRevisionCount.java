package ai.statistic;

import java.util.HashSet;
import java.util.Set;

import ai.domain.Card;
import ai.pubsub.RevisionStatistic;


/**
 * 
* @author Simon Gardier - B1 - GR5
 * 
 * Classe implémentant RevisionStatistic. Elle représente une statistique du nombre moyen de révisions par carte 
 * 
 * Fonctionnalités :
 * 
 * 		-Mise à jour de la statistique
 * 		-Renvoyer le résulalt de la statistique
 * 
 * 	--- Choix de collection ---
 * 
 * 	Implémentation 	-> 		Set : Permet de représenter un ensemble mathématique. Ce qui est pratique car les cartes doivent être unique dans la collection
 * 
 *	Classe concrète	-> 	HashSet : CTT de add() : Meilleur cas : O(1) car l'accès un l'élément est direct. L'accès fait appel à la méthode hashCode()
 *																de Card, méthode dont la CTT est constante.
 *												 Pire cas 	  : O(N) où N = nombre de cartes. Si la méthode hashCode est mal implémentée et que 
 *																tous les éléments se retrouvent dans le même bucket et add() perd son efficacité
 *								  La méthode Contains() est en O(1) si il n'y a aucune collisions, sinon O(N). Cela est intéressant car à chaque appel d'update(), 
 *								  nous devons appeler Contains() et il est donc préférable d'avoir une méthode Contains() dont la ctt est constante. 
 *
 */
public class AverageCardsRevisionCount implements RevisionStatistic{
	
	private double qttOfCardRevions;
	private final Set<Card> cardsRevised = new HashSet<Card>();
	
	/**
	 * CTT : Si la collection connait la carte étant déplacée 				-> O(1)
	 * 
	 * 		 	- La méthode du Set utilisée (dans le cas le cas où la carte est déjà connue) est :
 	 * 		 	  contains(). Comme déterminé et développé dans la documentation de classe, Sa CTT est constante.
	 * 
	 * 		 Si la collection ne connait pas encore la carte étant déplacée -> O(1) sans collisions, O(N) dans le cas contraire
	 * 
	 * 		 	- La seul méthode du Set utilisée est add() dont la ctt est constante si il n'y a pas de collisions		
	 * 				
	 */
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
		double ratio = qttOfCardRevions / cardsRevised.size();
		if(cardsRevised.isEmpty()) {
			ratio = 0;
		}
		return "- The average quantity of revision by card is : "+ratio;
	}
}
