package studyterminal.pubsub;
import studyterminal.statistic.StatisticEvent;

/**
 * @author Simon Gardier
 */

/**
 * Channel interface used to publish events to subscribers (statistics classes)
 */
public interface EventChannel {
	public void publish(StatisticEvent revisionEvent);
	public void subscribe(RevisionStatistic statistic);
}
