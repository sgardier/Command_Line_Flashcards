package ai.statistic;

import ai.Program;
import ai.domain.Card;
/**
 * @author Simon Gardier - B1 - GR5
 * 
 * Classe représentant un évênement dont les données sont analysables par des statistiques
 * 
 * Fonctionnalités :
 * 		- Renvoyer : - La carte liée à l'évênement 
 * 					 - L'indice de niveau liée à l'évênement
 * 					 - Le jour liée à l'évênement
 * 
 */
public class StatisticEvent {
	private final Card cardMoved;
	private final int indexDestination;
	private final int moveDay;
	

	/**
	 * Préconditions : 	-> Le constructeur reçoit en paramètre 	une carte contenant une question, une réponse et n'était pas null
	 * 					->						//				un index de niveau compris entre 0 et le niveau maximum - 1
	 * 					->						//				un jour de départ positif
	 * Post-condition : Les attributs sont initialisés avec une valeur valide 			
	 * @param cardMoved carte à déplacer
	 * @param indexDestination index du niveau où l'on déplca la carte
	 * @param moveDay jour du déplacement
	 */
	public StatisticEvent(final Card cardMoved, final int indexDestination, final int moveDay){
		this.cardMoved = cardMoved == null ? new Card("EmptyCardQuestion","EmptyCardAnswer") : cardMoved;
		this.indexDestination = indexDestination < 0  ? 0 : indexDestination > Program.NUMBER_OF_LEVELS - 1 ?  Program.NUMBER_OF_LEVELS - 1 : indexDestination;
		this.moveDay = moveDay < 1 ? 1 : moveDay;
	}
	
	/**
	 * 
	 * @return carte déplacée
	 */
	public Card getCardMoved() {
		return cardMoved;
	}
	
	/**
	 * 
	 * @return indice du niveau auquel la carte est déplacée
	 */
	public int getLevelIndexDestination() {
		return indexDestination;
	}
	
	/**
	 * 
	 * @return jour durant lequel la carte est déplacée
	 */
	public int getMoveDay() {
		return moveDay;
	}
	
}
