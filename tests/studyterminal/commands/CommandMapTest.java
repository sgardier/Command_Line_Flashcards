package studyterminal.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.Test;

class CommandMapTest {
	FakeCommand command;
	@Test
	public void getElementInTheCommandMap(){
		command = new FakeCommand("agrandir","agrandir la fenêtre");
		
		final CommandMap commandsMap = new CommandMap(
													command, 
													new FakeCommand("fermer","ferme l'application"),
													new FakeCommand("ouvrir","ouvrir l'application"),
													new FakeCommand("réduire","réduire la fenêtre"));
		
		assertEquals(command, commandsMap.get("agrandir"));
	}
	@Test
	public void returnNullIsDoNotExist(){
		command = new FakeCommand("agrandir","agrandir la fenêtre");
		
		final CommandMap commandsMap = new CommandMap(
													command, 
													new FakeCommand("fermer","ferme l'application"),
													new FakeCommand("ouvrir","ouvrir l'application"),
													new FakeCommand("réduire","réduire la fenêtre"));
		assertNull(commandsMap.get("rafraichir"));
	}
	@Test
	public void iteratorContainsEnoughElements(){
		command = new FakeCommand("agrandir","agrandir la fenêtre");
		
		final CommandMap commandsMap = new CommandMap(
													command, 
													new FakeCommand("fermer","ferme l'application"),
													new FakeCommand("ouvrir","ouvrir l'application"),
													new FakeCommand("réduire","réduire la fenêtre"));
		int qttOfElements = 0;
		for(Command cmd : commandsMap){
			qttOfElements++;
		}
		assertEquals(4, qttOfElements);
	}
}
