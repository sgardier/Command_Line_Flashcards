package ai.domain.algorithm;

/**
 * 
 * @author Simon Gardier
 *
 * Classe représant un algorithme de calendrier pour la révision d'une boite de Leitner
 * 
 * Fonctionnalités :
 * 		- communiquer le nombre de niveaux pour lequel l'aglorithme fais ses calculs
 * 		- retourner les niveaux à réviser pour un jour donné
 * 
 * 
 * Formules mqthématiques : 
 * 
 * Fréquence de révision d'un niveau :  les cartes de niveaux N sont révisées toutes les 2^(n-1) séances.
 * 
 * Condition de révision d'un niveau :	Soit une séance s, un niveau n et sa fréquence f. n est à réviser pendant s 
 * 										si : (s + f/2) % f == 0
 * 
 * Durée d'un cycle de réivision     :	la durée d’un cycle pour une boite de niveau N est égal à 2 ^(N-1) 
 */
public interface CalendarAlgorithm {
	
	/**
	 * 
	 * @return retourne le nombre de niveaux d’études 
	 */
	public int getNumberOfLevels();
	/**
	 * 
	 * @param 	day
	 * @return 	retourne les niveaux à étudier, sous la forme d’un tableau d’entiers, pour un jour de révision
	 * 			Normalement, il y a une session de révision par jour, raison pour laquelle nous parlons dans 
	 * 			le code de jours
	 */
	public int[] getLevelsOfDay(int day);
}
