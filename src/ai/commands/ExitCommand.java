package ai.commands;

import ai.consoles.Console;

/**
 * 
 * @author Simon Gardier - B1 - GR5
 *	Commande permettant de fermer le programme 
 */
public class ExitCommand extends Command{

	final private Console console;
	
	/**
	 * 
	 * @param console : console dans laquelle notre programme est affiché
	 */
	public ExitCommand(final Console console) {
		super("exit", "Exit the system");
		this.console = console;
	}
	
	/**
	 * exécute la command exit()
	 */
	@Override
	public void execute() {
		exit();
	}
	
	/**
	 * quitte l'application
	 */
	private void exit() {
		console.print("See you soon !");
		console.exit();
	}
}
