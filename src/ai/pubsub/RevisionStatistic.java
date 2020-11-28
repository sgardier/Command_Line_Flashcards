package ai.pubsub;

import ai.statistic.StatisticEvent;

/**
 * 
 * @author Simon Gardier - B1 - GR5
 *	
 * Interface représentant une statistique
 */
public interface RevisionStatistic{
	void update(StatisticEvent revisionEvent);
}
