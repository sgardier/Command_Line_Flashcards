package studyterminal.data;

import studyterminal.domain.LearningSchedule;
import studyterminal.domain.algorithm.CalendarAlgorithm;

/**
 * @author Simon Gardier
 */

/**
 * Define interactions with the fs
 */
public interface Storage {

	/**
	 * Loads object from fs
	 * @return object saved in the file
	 */
	public LearningSchedule load(CalendarAlgorithm ca);

	/**
	 * Save ojbect in fs
	 * @param LearningSchedule to save
	 */
	public void save(LearningSchedule ls);
}
