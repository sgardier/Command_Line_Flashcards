package studyterminal.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandTest {
	Command cmd;
	@BeforeEach
	public void init() {
		cmd = new FakeCommand("fermer","ferme l'application");
	}
	@Test
	public void returnFalseOnHasNameNull() {
		Command cmd = new FakeCommand(null,"lire les cartes");
		assertFalse(cmd.hasName("readCard"));
	}
	@Test
	public void ignoreTheCaseInHasName() {
		Command cmdToCompare = new FakeCommand("FErmeR","ferme l'application");
		assertTrue(cmd.hasName(cmdToCompare.getName()));
	}
	@Test
	public void hasNameReturnTrueIfTheSTwotringsAreNull() {
		Command cmd = new FakeCommand(null,"ferme l'application");
		assertTrue(cmd.hasName(null));
	}
	@Test
	public void hasNameReturnTrueIfTheStringsAreEquals() {
		assertTrue(cmd.hasName("fermer"));
	}
	@Test
	public void hasNameReturnFalseIfTheStringsArentEquals() {
		assertFalse(cmd.hasName("ouvrir"));
	}
	@Test
	public void hasNameReturnFalseIfOneOfTheComparedIsNull() {
		Command cmd = new FakeCommand("Fermer","ferme l'application");
		assertFalse(cmd.hasName(null));
	}
	@Test
	public void getDescriptionReturnTheDescription() {
		assertEquals("ferme l'application", cmd.getDescription());
	}
	@Test
	public void hashCodeIsTheSameForSameInputs() {
		assertEquals(cmd.hashCode(), cmd.hashCode());
	}
	@Test
	public void hashCodeInstTheSameForTwoSimilarObject() {
		Command cmd1 = new FakeCommand("FermerT","ferme l'application");
		assertNotEquals(cmd.hashCode(), cmd1.hashCode());
	}
	@Test
	public void twoSameObjectAreEquals(){
		Command cmd1 = new FakeCommand("fermer","ferme l'application");
		assertTrue(cmd.equals(cmd1));
	}
	@Test
	public void twoDifferentObjectAreNotEquals() {
		Command cmd1 = new FakeCommand("Ouvrir","ouvre l'application");
		assertFalse(cmd.equals(cmd1));
	}
	@Test
	public void twoDifferentObjectWhithOneNullAreNotEquals() {
		Command cmd1 = null;
		assertFalse(cmd.equals(cmd1));
	}
	@Test
	public void twoDifferentObectWithTheComparedHaventNameAreNotEquals() {
		Command cmd1 = new FakeCommand(null,"ferme l'application");
		assertFalse(cmd.equals(cmd1));
	}
	@Test
	public void twoDifferentObjectWithADescriptionNull() {
		Command cmd = new FakeCommand("Exit",null);
		Command cmd1 = new FakeCommand("Exit","ferme l'application");
		assertFalse(cmd.equals(cmd1));
	}
	@Test
	public void twoDifferentObjectWithANameNull() {
		Command cmd = new FakeCommand(null,"ferme l'application");
		Command cmd1 = new FakeCommand("Exit","ferme l'application");
		assertFalse(cmd.equals(cmd1));
	}
	@Test
	public void twoDifferentObjectWithDifferentType() {
		Object cmd = new Object();
		Command cmd1 = new FakeCommand("Exit","ferme l'application");
		assertFalse(cmd.equals(cmd1));
	}
	
}
