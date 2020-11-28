package ai.domain;

import ai.Program;
import ai.domain.algorithm.CalendarAlgorithm;
import ai.domain.algorithm.CalendarAlgorithmComputationnal;
import ai.iterators.CardsIterator;

/**
 * 
 * @author Simon Gardier - B1 - GR5
 *	Représente un module d'apprentissage
 * 	Fonctionnalités :
 * 		- Retourner le jour courant
 * 		- Ajouter une carte
 * 		- Retirer une carte
 * 		- Parcourir l'ensemble des cartes de la boite de Leitner
 * 		- retourner si oui / non il reste des cartes à réviser dans le LearningSchedule
 * 		- Passer au jour suivant de l'aprentissage
 * 		- Déplacer une carte de la boite de Leitner
 */
public class LearningSchedule {
	
	private final Box leitnerBox;
	private CalendarAlgorithm calendarAlgo;
	private int currentDay;
	
	/**
	 * Préconditions : - le calendarAlgorithm est différent de null, si ce n'est pas la cas on crée un CalendarAlgorithmComputationnal sur base du nombre de niveaux
	 * 				   - la Box est est différente de null, si ce n'est pas le cas on crée une Box sur base du nombre de niveaux
	 * Post condition : 
	 * 				   - Les attributs ont reçu une valeur
	 * @param jour de départ du module d'apprentissag
	 * @param LeitnerBox Boite de Leitner contenant des niveaux contenant eux-mêmes des cartes
	 * @param calendarAlgorithm sur lequel se base l'apprentissage
	 */
	public LearningSchedule(final int departDay, final Box LeitnerBox, final CalendarAlgorithm calendarAlgo){
		this.calendarAlgo = calendarAlgo != null ? calendarAlgo : new CalendarAlgorithmComputationnal(Program.NUMBER_OF_LEVELS);
		this.leitnerBox = LeitnerBox == null ? new Box(Program.NUMBER_OF_LEVELS) : LeitnerBox;
		this.currentDay = departDay > 1 ? departDay : 1;
	}
	/**
	 * @param calendarAlgorithm Calendaralgorithm que l'on souhaite utiliser
	 */
	public void setCalendarAlgorithm(final CalendarAlgorithm calendarAlgorithm) {
		this.calendarAlgo = calendarAlgorithm;
	}
	/**
	 * 
	 * @return un itérateur des cartes à réviser durant la session courante
	 */
	public CardsIterator getCardsToRevise() {
		return leitnerBox.getIteratorForLevels(calendarAlgo.getLevelsOfDay(currentDay));
	}
	/**
	 * 
	 * @return jour courant
	 */
	public int getCurrentDay(){
		return currentDay;
	}
	/**
	 * Pré condition : - la carte n'est pas déjà contenue dans la boite
	 * Post condition: - retourne true si la carte a été ajoutée sinon false
	 * @param card carte à ajouter
	 * @return retourne true si la carta a été ajoutée, sinon false
	 */
	public boolean add(final Card card){
		return leitnerBox.add(card);
	}
	/**
	 * 
	 * @param card carte à retirer
	 * @return retourne true si la carta a été retirée, sinon false
	 */
	public boolean remove(final Card card) {
		return leitnerBox.remove(card);
	}
	/**
	 * 
	 * @return itérateur de l'ensemble des cartes présentes dans la boite de Leitner
	 */
	public Iterable<Card> getAllCards(){
		return leitnerBox;
	}
	
	/**
	 * 
	 * @param card carte à déplacer
	 * @param answerKnown si oui/non l'utilisateur a correctement répondu à la question
	 * @return niveau auquel la carte est ajoutée
	 */
	public int moveCardToNextLevel(final Card card, final boolean answerKnown) {
		return leitnerBox.moveCardToNextLevel(card, answerKnown);
	}

	/**
	 * Incrémente le jour courant du programme d'étude
	 */
	public void nextDay(){
		currentDay++;
	}
	/**
	 * 
	 * @return retourne si true si le learningSchedule est révisable sinon false
	 */
	public boolean isRevisable() {
		return !leitnerBox.isEmpty();
	}
}
