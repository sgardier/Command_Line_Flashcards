package studyterminal.pubsub;
import studyterminal.statistic.StatisticEvent;

/**
 * @author Simon Gardier
 */

/**
 * Interface representing a revisiobn statistic, to be updated by an EventChannel
 */
public interface RevisionStatistic{
	void update(StatisticEvent revisionEvent);
}
