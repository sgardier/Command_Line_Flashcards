package studyterminal.consoles;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Interagit avec l'utilisateur à travers un terminal de saisies de commandes.
 * @see studyterminal.consoles.Console
 * @author HENDRIKX Nicolas
 * */
public class UserConsole implements Console {
	private static final String INTEGER_PATTERN = "^[+-]?\\d+$";
	private final PrintStream output;
	private final Scanner input;

	public UserConsole() {
		this.output = System.out;
		this.input = new Scanner(System.in);
	}

	@Override
	public String readLine(String fmt, Object... args) {
		print(fmt, args);
		return input.nextLine();
	}

	@Override
	public void printLine(String fmt, Object... args) {
		print(fmt, args);
		printLine();
	}

	@Override
	public void print(String fmt, Object... args) {
		output.printf(fmt, args);
	}

	@Override
	public void printLine() {
		output.println();
	}
	
	@Override
	public int readInteger(String fmt, Object... args) {
		String intAsText = null;
		do {
			intAsText = readLine(fmt, args);
		} while(!intAsText.matches(INTEGER_PATTERN));
		
		return Integer.parseInt(intAsText);		
	}
	
	@Override
	public void exit() {
		System.exit(0);
	}

	@Override
	public void clear(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
