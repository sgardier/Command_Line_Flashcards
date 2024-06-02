package studyterminal.data;
import static org.junit.jupiter.api.Assertions.assertEquals;
import studyterminal.domain.LearningSchedule;
import studyterminal.domain.algorithm.CalendarAlgorithm;

public class FakeStorage implements Storage {

	private int nbrLoad = 0;
	private int nbrSave = 0;

	@Override
	public LearningSchedule load(CalendarAlgorithm ca) {
		if(ca == null)
			throw new IllegalArgumentException("the param must receive a CalendarAlgorithm");
		nbrLoad++;
		return null;
	}

	@Override
	public void save(LearningSchedule t) {
		nbrSave++;
	}
	
	public void verifyLoadCallCount(int expectedNbrLoad) {
		assertEquals(expectedNbrLoad, nbrLoad);
	}

	public void verifySaveCallCount(int expectedNbrSave) {
		assertEquals(expectedNbrSave, nbrSave);
	}

}
