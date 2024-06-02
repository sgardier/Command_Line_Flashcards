package studyterminal.domain.algorithm;

/**
 * @author Simon Gardier
 *
 * Class implementing the calendar algorithm to revise a Leitner Box
 * 
 * Implement :
 * - Communicates the number of levels of revisions
 * - Communicates the levels to revise for a given day
 * 
 * Level N cards are revised every 2^(N-1) sessions.
 * 
 * Given a revision r, a level n and its frequency f. n is to be revised during r if : (r + f/2) % f == 0
 * 
 * A revision cycle for a given level N is equal to 2^(N-1)
 */
public interface CalendarAlgorithm {
	
	/**
	 * 
	 * @return returns the number of revisions levels
	 */
	public int getNumberOfLevels();

	/**
	 * 
	 * @param 	day
	 * @return 	array of levels to revise today
	 */
	public int[] getLevelsOfDay(int day);
}
