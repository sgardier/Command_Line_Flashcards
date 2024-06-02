package studyterminal.data;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import studyterminal.domain.LearningSchedule;
import studyterminal.domain.algorithm.CalendarAlgorithm;

/**
 * @author Simon Gardier
 */

/**
 * Load and save LearningSchedules (flahscards) in the fs
 */
public class LearningScheduleStorage implements Storage {

	private String jsonSourcePath;

	/**
	 * @param jsonSource patht ot the json backup file
	 */
	public LearningScheduleStorage(String jsonSource) {
		jsonSourcePath = jsonSource;
		try{
			Path jsonPath = Paths.get(jsonSource);
			if (Files.notExists(jsonPath)) {
				Files.createDirectories(jsonPath.getParent());
				Files.createFile(jsonPath);
			}
		} catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	/**
	 * Load LearningSchedule from the fs
	 * @return loaded LearningSchedule
	 * */
	public LearningSchedule load(CalendarAlgorithm ca) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create();
		File jsonSource = new File(jsonSourcePath);
		FileReader reader = null;
		try {
			reader = new FileReader(jsonSource);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		try {
			LearningSchedule learningSchedule = gson.fromJson(reader, LearningSchedule.class);
			if (learningSchedule == null) {
				this.save(new LearningSchedule(0, null, ca));
				learningSchedule = gson.fromJson(reader, LearningSchedule.class);
			}
			learningSchedule.setCalendarAlgorithm(ca);
			return learningSchedule;
		} catch (JsonSyntaxException e) {
			try {
				reader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("[LearningScheduleStorage]::load() => invalid JSON document");
			return null;
		}
	}

	/**
	 * Save a LearningSchedule in the fs
	 * @param LearningSchedule to save
	 * */
	public void save(LearningSchedule learningSchedule) {
		try {
			ExclusionStrategy strategy = new ExclusionStrategy() {
			    @Override
			    public boolean shouldSkipField(FieldAttributes field) {
					if (field.getDeclaredClass() == CalendarAlgorithm.class) {
			            return true;
			        }
			        return false;
			    }
			 
			    @Override
			    public boolean shouldSkipClass(Class<?> toSkipClass) {
			        return false;
			    }
			};
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
					  .addSerializationExclusionStrategy(strategy)
					  .setPrettyPrinting()
					  .create();
			File jsonDestination = new File(jsonSourcePath);
			FileWriter writer = new FileWriter(jsonDestination);
			gson.toJson(learningSchedule, writer);
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
