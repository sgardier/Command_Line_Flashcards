package studyterminal.commands;

/**
 * @author Simon Gardier
 */

/**
 * Command asbtract class, commands are actions that the user can perform in the terminal.
 *
 * Implements :
 * - Check if a given name is the name of the command
 * - Retrieve the command name / description
 * - Compare commands together
 */
public abstract class Command {

	private final String name;
	private final String description;

	/**
	 * Executes the command
	 */
	public abstract void execute();

	/**
	 * @param name command name
	 * @param description command description
	 */
	public Command(final String name, final String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * 
	 * @param n command name
	 * @return
	 */
	public boolean hasName(final String n) {
		return (name == null && n == null) || (name != null && n != null && name.compareToIgnoreCase(n) == 0);
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj){
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Command other = (Command) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
