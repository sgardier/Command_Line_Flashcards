package studyterminal.commands;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Simon Gardier
 */

/**
 * Handle commands collection
 * Implements :
 * - Create a collection of commands
 * - Get a command given its name
 */
public class CommandMap implements Iterable<Command>{

	final private Map <String,Command>commands = new HashMap<String,Command>();
	
	/**
	 * 
	 * @param commands collection of commands
	 */
	public CommandMap(final Command ...commands){
		for (final Command command : commands){
			this.commands.put(command.getName(), command);
		}
	}
	
	/**
	 * Returns a command given its name
	 * @param key the command name
	 * @return the command
	 */
	public Command get(final String key) {
		return commands.get(key);
	}

	@Override
	public Iterator<Command> iterator() {
		return commands.values().iterator();
	}
}
