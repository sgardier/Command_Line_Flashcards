package studyterminal.pubsub;
import java.util.ArrayList;
import java.util.List;
import studyterminal.statistic.StatisticEvent;

/**
 * @author Simon Gardier
 */

/**
 * Class implementing EventChannel
 */
public class SequentialEventChannel implements EventChannel{

	private final List<RevisionStatistic> statistics = new ArrayList<RevisionStatistic>();
	
	@Override
	public void publish(final StatisticEvent revisionEvent) {
		for (final RevisionStatistic revisionStatistic : statistics) {
			revisionStatistic.update(revisionEvent);
		}
	}

	@Override
	public void subscribe(final RevisionStatistic statistic) {
		statistics.add(statistic);
	}
}
