package studyterminal.pubsub;
import org.junit.jupiter.api.Test;
import studyterminal.domain.Card;
import studyterminal.statistic.FakeStatistic;
import studyterminal.statistic.StatisticEvent;

class SequentialEventChannelTest {

	@Test
    public void testPublishDataWithSubscriber() throws Exception {
        SequentialEventChannel sequentialEventChannel = new SequentialEventChannel();
        FakeStatistic statistic = new FakeStatistic();
        sequentialEventChannel.subscribe(statistic);
        StatisticEvent statisticEvent = new StatisticEvent(new Card("",""), 0, 1);
        sequentialEventChannel.publish(statisticEvent);
        statistic.verifyReceiveUpdateDataFor(statisticEvent);
    }
}
