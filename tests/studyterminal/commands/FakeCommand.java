package studyterminal.commands;

/**
 * 
 * @author simon
 *	Classe permettant d'effectuer des tests sur la classe abstraite Command
 */
public class FakeCommand extends Command {

	/**
	 * 
	 * @param name : nom de la commande
	 * @param description : desc de la commande
	 */
	public FakeCommand(final String name, final String description) {
		super(name, description);
	}

	@Override
	public void execute(){
	}
}
