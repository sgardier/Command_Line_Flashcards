package ai;

import java.util.Iterator;

import ai.commands.Command;
import ai.commands.CommandMap;
import ai.consoles.Console;

/**
 * 
 * @author Simon Gardier B1 - GR5
 *	FrontController intéragit avec l'utilisateur à travers une console
 *	Cette classe permet de : 
 *	- recueillir une commande entrée par un utilisateur dans la console et demander son exécution
 */
public class FrontController {
	
	private final CommandMap commands;
	private final Console console;
	
	/**
	 * 
	 * @param console : Console utilisée pour intéragir avec l'utilsateur
	 * @param commands : Collection de commandes utilisables par l'utilisateur
	 */
	public FrontController(final Console console,final CommandMap commands) {
		this.commands = commands;
		this.console = console;
		loop();
	}
	
	/**
	 * boucle dans laquelle s'exécute le programme
	 */
	public void loop() {
		while(true){
			final String cmd = console.readLine("Encode a command (\"list\" to display all the commands) \n");
			if(cmd == null) {
				break;
			}
			else if("list".equals(cmd)) {
				list();
			}
			else if(find(cmd) != null){
				find(cmd).execute();
			}
		}
	}
	
	private Command find(final String cmdKey){
		return commands.get(cmdKey);
	}
	
	private void list() {
		final Iterator<Command> cmds = commands.iterator();
		
		String cmdFormatted = String.format("%-20s %s", "NOM","DESCRIPTION \n");
		console.printLine(cmdFormatted);
		while(cmds.hasNext()){
			final Command currentCommand = (Command) cmds.next();
			cmdFormatted = String.format("%-20s %s", currentCommand.getName(), currentCommand.getDescription());
			console.printLine(cmdFormatted);
		}
	}
}
