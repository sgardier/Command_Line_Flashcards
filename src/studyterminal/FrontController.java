package studyterminal;
import java.util.Iterator;
import studyterminal.commands.Command;
import studyterminal.commands.CommandMap;
import studyterminal.consoles.Console;

/**
 * @author Simon Gardier
 */

/**
 *	FrontController interacting with the user through the console
 */
public class FrontController {
	
	private final CommandMap commands;
	private final Console console;
	
	/**
	 * 
	 * @param console Console use to interact with the user
	 * @param commands Collection of commands that can be executed by the user
	 */
	public FrontController(final Console console,final CommandMap commands) {
		this.commands = commands;
		this.console = console;
		loop();
	}

	public void welcomeScreen(){
		// font name : Crawford2 (https://patorjk.com/software/taag/#p=display&f=Crawford2&t=Study%20terminal)
		// book ascii art credit : "Bookshelf" by David S. Issel
		console.printLine(
							"  _____ ______  __ __  ___    __ __      ______    ___  ____   ___ ___  ____  ____    ____  _        	   _ _\n" +
							" / ___/|      ||  |  ||   \\  |  |  |    |      |  /  _]|    \\ |   |   ||    ||    \\  /    || |        .-. |C| |\n" +
							"(   \\_ |      ||  |  ||    \\ |  |  |    |      | /  [_ |  D  )| _   _ | |  | |  _  ||  o  || |        | |_|O|L|\n" +
							" \\__  ||_|  |_||  |  ||  D  ||  ~  |    |_|  |_||    _]|    / |  \\_/  | |  | |  |  ||     || |___     |M| |M|O|<\\\n" +
							" /  \\ |  |  |  |  :  ||     ||___, |      |  |  |   [_ |    \\ |   |   | |  | |  |  ||  _  ||     |    |A|A|P|G| \\\\\n" +
							" \\    |  |  |  |     ||     ||     |      |  |  |     ||  .  \\|   |   | |  | |  |  ||  |  ||     |    |T|R|S|I|  \\\\ \n" +
							"  \\___|  |__|   \\__,_||_____||____/       |__|  |_____||__|\\_||___|___||____||__|__||__|__||_____|    |H|T|C|C|   \\> \n" +
							"                                                                                                    \"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"");
	}

	public void loop() {
		while(true){
			console.clear();
			welcomeScreen();
			final String cmd = console.readLine("Enter a command (\"list\" to display all the commands) : ");
			if(cmd == null) {
				break;
			}
			else if("list".equals(cmd)) {
				list();
			}
			else if(find(cmd) != null){
				find(cmd).execute();
			}
			console.readLine("Press Enter to continue...");
		}
	}

	private Command find(final String cmdKey){
		return commands.get(cmdKey);
	}
	
	private void list() {
		Iterator<Command> cmds = commands.iterator();
		int lineLength = 0;
		while(cmds.hasNext()){
			final Command currentCommand = (Command) cmds.next();
			String cmdFormatted = String.format("%-18s | %s", currentCommand.getName(), currentCommand.getDescription());
			if(cmdFormatted.length() > lineLength)
			lineLength = cmdFormatted.length();
		}

		cmds = commands.iterator();
		String cmdFormatted = String.format("%-18s | %s", "Name","Description");
		String underLine = "-".repeat(lineLength);
		console.printLine(underLine);
		console.printLine(cmdFormatted);
		console.printLine(underLine);
		while(cmds.hasNext()){
			final Command currentCommand = (Command) cmds.next();
			cmdFormatted = String.format("%-18s | %s", currentCommand.getName(), currentCommand.getDescription());
			console.printLine(cmdFormatted);
		}
		console.printLine(underLine);
	}
}
