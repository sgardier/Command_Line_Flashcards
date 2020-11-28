package ai.statistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import ai.Program;
import ai.domain.Card;
import ai.pubsub.RevisionStatistic;
/**
 * 
 * @author Simon Gardier - B1 - GR5
 * 
 * Classe implémentant RevisionStatistic. Elle représente une statistique du nombre de fois qu’une carte passe par chaque niveau.
 * 
 * Fonctionnalités :
 * 
 * 		-Mise à jour de la statistique
 * 		-renvoyer les statistiques pour chaque carte
 * 
 * 	--- Choix de collection ---
 * 
 * 	Implémentation 	-> 		Map : Permet d'associer des valeurs (listes) à une clé (la carte correspondant aux données de la liste). Cela facilite 
 * 								  leur recherche dans la collection de listes de données
 * 
 *	Classe concrète	-> 	HashMap : CTT de get() : Meilleur cas : O(1) car l'accès à l'élément est direct. L'accès fait appel à la méthode hashCode()
 *																de Card, méthode dont la CTT est constante.
 *												 Pire cas 	  : O(Log2B) où b = taille du bucket. Si la méthode hashCode est mal implémentée et que 
 *																tous les éléments se retrouvent dans le même bucket, le bucket devient un arbre.
 *																Or l'accès à un élément dans un arbre se fait moyennant une ctt de O(Log2B).
 *																N'ayant pas d'autres boucle / code impactant la ctt de get(), sa ctt est O(Log2B)
 *								  La méthode ContainsKey() est en O(1) or HashMap est la seule collection à posséder une méthode Contains dont la CTT est constante dans
 *								  les collections implémentant Map. 
 *								  Cela est très utile car à chaque appel de la méthode update() de notre statistique, nous devons vérifier si la carte existe déjà 
 *								  dans la collection 
 *				
 *	
 *	--- Choix de sous-collection ---
 *
 *	Implémentation 	-> 		List : 	- Aucun besoin particulier. List possède déjà tous les besoins principaux (accéder à un élément, modifier un élément sur base 
 *									  de son indide).
 *
 *	Classe concrète -> ArrayList : 	- Permet de récupérer très rapidement un élément sur base de son indice -> CTT get() : O(1)
 * 									- Permet de modifier très rapidement un élément sur base de son indice () -> CTT set() : O(1)
 *									- Permet d'ajouter un élément dans la collection de façon optimisée pour les 10 premiers éléments et étant 
 *									  donné que (du moins dans la situation actuelle) nous n'avons que 7 éléments, la CTT de add est constante
 *									  car l'ArrayList possède déjà une taille de 10 éléments (pas de redimensionnement à faire)
 */
public class CardByLevelCount implements RevisionStatistic, Iterable<Card>{

	private final Map<Card, List<Integer>> cardsData;
	/**
	 * Construction de la statistique
	 */
	public CardByLevelCount(){
		cardsData = new HashMap<Card,List<Integer>>();
	}
	
	/**
	 * 		 Si la collection connait la carte étant déplacée 					-> CTT = O(1)
	 * 
	 * 		 	- Les méthodes de la Map utilisées (dans le cas où la carte est déjà connue) sont :
 	 * 		 	  contains(), get() et set(). Comme déterminé et développé dans la documentation de classe, leur CTT est constante.
 	 * 
 	 * 
	 * 
	 * 		 Si la collection ne connait pas encore la carte étant déplacée 	-> CTT = O(L) où L = nombre de niveaux dans notre boite de Leitner
	 * 
	 * 		 	- La seul méthode de Map utilisée est put() (dont la ctt est constante). En dehors de cet appel, le seul segement de code impactant la ctt
	 * 		 	  de la méthode est la boucle permettant de créer autant d'élément dans la liste de données qu'il y a de niveaux dans la boite de Leitner (d'où
	 * 			  L = nombre de niveaux dans notre boite de Leitner dans la CTT)		
	 * 				
	 */
	@Override
	public void update(final StatisticEvent revisionEvent) {
		final Card cardMoved = revisionEvent.getCardMoved();
		
		if(cardsData.containsKey(cardMoved)){
			//Liste contenant à chaque indice la quantité de fois où une carte est passée dans le niveau ayant ce dit-indice
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
	/**
	 * 
	 * @return la description de la statistique
	 */
	public String description(){
		return "- Quantity of passages of the cards in each level :";
	}
	/**
	 * @return  les statistiques d'une carte (nombre de fois passées dans chaque niveau)
	 */
	public String toString(final Card card){
		final StringJoiner currentLine = new StringJoiner(" ");
		final List<Integer> currantCardData = cardsData.get(card);
		
		currentLine.add("The statistics for this card are : ");
		
		for (int i = 0; i < currantCardData.size(); i++) {
			currentLine.add("Level n*"+(i + 1)+" : "+currantCardData.get(i)+" |");
		}
		
		return currentLine.toString();
	}
	@Override
	public Iterator<Card> iterator(){
		return this.cardsData.keySet().iterator();
	}
}
