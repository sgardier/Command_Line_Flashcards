package ai.commands;

import org.junit.jupiter.api.Test;

import ai.consoles.FakeConsole;

class ExitCommandTest {

	@Test
	public void exitsOnExecute() {
		FakeConsole console = new FakeConsole();
		ExitCommand cmd = new ExitCommand(console);
		
		cmd.execute();
		
		console.verifyExitCalled();
		console.verifyOutputContains("Au revoir !");
	}
}
