package ai.domain.algorithm;

import java.util.Arrays;

import ai.Program;
/**
 * 
 * @author Simon Gardier - B1 - GR5
 *	CalendarAlgorithm permettant de calculer les niveaux à réviser pour un jour donnée
 *	Fonctionnalités : 
 *		- récupérer les niveaux à réviser pour un jour donné
 *
 */

public class CalendarAlgorithmComputationnal implements CalendarAlgorithm{
	
	private final int numberOfLevels;
	/**
	 * 
	 * @param nbrLevel nombre de niveaux
	 */
	public CalendarAlgorithmComputationnal(final int nbrLevel){
		this.numberOfLevels = nbrLevel < 2 ? Program.NUMBER_OF_LEVELS : nbrLevel;
	}
	/**
	 * 
	 * @param nbrLevel nombre de niveaux
	 * @return un object de type CalendarAlgorithm de la classe CalendarAlgorithmComputationnal
	 */
	public static CalendarAlgorithm Of(final int nbrLevel) {
		return new CalendarAlgorithmComputationnal(nbrLevel);
	}
	@Override
	public int getNumberOfLevels() {
		return numberOfLevels;
	}
	
	/**
	 * Retourne un tableau d'entiers contenant les indices des niveaux à réviser
	 * 
	 * Algorithme : Premièrement, on crée un tableau avec comme taille le nombre de niveaux de la boite.
	 * 
	 * 				Ensuite j'utilise une boucle "for" commençant à l'indice de niveau maximal de la boîte et se terminant
	 * 				au niveau minimum (càd le niveau 1).
	 * 
	 *				1ERE BOUCLE  : 				
	 * 					- Dans la boucle je calcule la fréquence du niveau correspondant
	 * 
	 * 					-CONDITION : Si le niveau doit être révisé 
	 * 								vérifiation grâce à la formule de révision -> voir les explications dans CalendarAlgorithm) 
	 * 
	 * 								-> je l'ajoute dans mon tableau de niveaux à réviser et j'incrémente la variable
	 * 								qui représente l'indice auquel j'ai stocké le niveau courant
	 * 				2EME BOUCLE : 
	 * 					- Copie du tableau en réduisant sa taille à la quantité de niveaux à réviser (représentée par qttOfLevelToRevise)
	 * 				
	 * 				Retour de la copie du tableau
	 * 				
	 * 				FIN
	 * 
	 * CTT de getLevelsOfDay : 
	 *		Dans le pire des cas, nous avons deux boucles qui doivent s'exécuter, chacune, N fois (N = quantité de niveau dans la box)
	 * 		Donc la CTT dans le pire des cas est O(2N)
	 * 
	 * 		Dans le meilleur des cas, la 2eme boucle sera raccourcie à deux opéraions : 
	 * 				créer un tableau de longueur 1 et copier le niveau dans le tableau
	 * 		La CTT est donc de O(N)
	 * 				
	 *  
	 * 
	 * @return tableau d'entiers représentant les indices des niveaux à réviser
	 */
	@Override
	public int[] getLevelsOfDay(final int day){
		int toReviseQtt = 0;
		int [] levelsToRevise = new int[numberOfLevels];
		for (int i = numberOfLevels; i >= 1; i--){
			final int freqCurrentLevel = (int) Math.pow(2,i - 1);
			if((day + freqCurrentLevel/2) % freqCurrentLevel == 0){
				levelsToRevise[toReviseQtt] = i;
				toReviseQtt++;
			}
		}
		return Arrays.copyOf(levelsToRevise, toReviseQtt);
	}
}
