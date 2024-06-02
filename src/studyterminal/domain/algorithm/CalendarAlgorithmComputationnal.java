package studyterminal.domain.algorithm;

import java.util.Arrays;
import studyterminal.Program;

/**
 * @author Simon Gardier
 *	CalendarAlgorithm which calculates the levels to revise for a given day in the revisions
 */
public class CalendarAlgorithmComputationnal implements CalendarAlgorithm{
	private final int numberOfLevels;

	/**
	 * 
	 * @param nbrLevel number of levels
	 */
	public CalendarAlgorithmComputationnal(final int nbrLevel){
		this.numberOfLevels = nbrLevel < 2 ? Program.NUMBER_OF_LEVELS : nbrLevel;
	}

	/**
	 * 
	 * @param nbrLevel number of levels
	 * @return CalendarAlgorithm of type CalendarAlgorithmComputationnal
	 */
	public static CalendarAlgorithm Of(final int nbrLevel) {
		return new CalendarAlgorithmComputationnal(nbrLevel);
	}

	@Override
	public int getNumberOfLevels() {
		return numberOfLevels;
	}

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
