package studyterminal.commands;
import studyterminal.consoles.Console;

/**
 * @author Simon Gardier
 */

/**
 * Exit command
 */
public class ExitCommand extends Command{

	final private Console console;
	
	/**
	 * 
	 * @param console console in which the program is displayed
	 */
	public ExitCommand(final Console console) {
		super("exit", "Exit the system");
		this.console = console;
	}
	
	/**
	 * Execute exit command
	 */
	@Override
	public void execute() {
		exit();
	}
	
	/**
	 * leaves application
	 */
	private void exit() {
		console.print("See you tomorrow!\n");
		console.exit();
	}
}
