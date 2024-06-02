package studyterminal.domain.algorithm;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalenderAlogrithmComputationnalTest {
	CalendarAlgorithm ca;
	@BeforeEach
	void init() {
		ca = new CalendarAlgorithmComputationnal(7);
	}
	@ParameterizedTest
	@MethodSource("intAndArrayProvider")
	void onDayxReturnsLevels(int day, int[] levels) {
		CalendarAlgorithm alg = CalendarAlgorithmComputationnal.Of(4);
		assertArrayEquals(levels, alg.getLevelsOfDay(day));
	}

	static Stream<Arguments> intAndArrayProvider() {
	    return Stream.of(
	        Arguments.of(1, new int[] {2,1}),
	        Arguments.of(2, new int[] {3,1}),
	        Arguments.of(3, new int[] {2,1}),
	        Arguments.of(4, new int[] {4,1}),
	        Arguments.of(5, new int[] {2,1}),
	        Arguments.of(6, new int[] {3,1}),
	        Arguments.of(7, new int[] {2,1}),
	        Arguments.of(8, new int[] {1}),
	        Arguments.of(9, new int[] {2,1}),
	        Arguments.of(10, new int[] {3,1}),
	        Arguments.of(11, new int[] {2,1}),
	        Arguments.of(12, new int[] {4,1}),
	        Arguments.of(13, new int[] {2,1}),
	        Arguments.of(14, new int[] {3,1}),
	        Arguments.of(15, new int[] {2,1}),
	        Arguments.of(16, new int[] {1})
	    );
	} 

	@Test
	void getNumberOfLevelReturnTheVaue() {
		assertEquals(7, ca.getNumberOfLevels());
	}
}
