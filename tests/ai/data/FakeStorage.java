package ai.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ai.data.Storage;
import ai.domain.LearningSchedule;
//import ai.domain.algorithm.CalendarAlgorithm;
import ai.domain.algorithm.CalendarAlgorithm;

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
