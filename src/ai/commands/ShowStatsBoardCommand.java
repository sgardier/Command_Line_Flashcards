package ai.commands;

import java.util.Iterator;

import ai.consoles.Console;
import ai.domain.Card;
import ai.statistic.AverageCardsRevisionCount;
import ai.statistic.CardByLevelCount;
/**
 * @author Simon Gardier - B1 - GR5
 * 
 * Classe implémentant Command. Elle représente une commande d'affichage de toutes les statistiques.
 * 
 * Fonctionnalité :
 * 
 * 		-Afficher les statistiques
 *
 */
public class ShowStatsBoardCommand extends Command{
	private final Console console;
	//Statistiques
	private final AverageCardsRevisionCount averageCardRevision;
	private final CardByLevelCount cardByLevel;
	
	/**
	 * @param console permettant d'intéragier avec l'utilisateur
	 * @param averageCardRevision statistique du nombre moyen de révisions par carte
	 * @param cardByLevel statistique du nombre de fois qu’une carte passe par chaque niveau
	 * 
	 */
	public ShowStatsBoardCommand(final Console console, final AverageCardsRevisionCount averageCardRevision, final CardByLevelCount cardByLevel) {
		super("stats", "Display the statistics");
		this.console = console;
		this.averageCardRevision = averageCardRevision;
		this.cardByLevel = cardByLevel;
	}
	
	@Override
	public void execute() {
		console.printLine("The statistics are :");
		
		
		// Average card revision statistic
		console.printLine("\t"+averageCardRevision.toString());
		
		//Card by level count statistic
		console.printLine("\t", cardByLevel.description());
		final Iterator<Card> cardsHavingData = cardByLevel.iterator();
		while(cardsHavingData.hasNext()){
			final Card currentCard = cardsHavingData.next();
			final String choice = console.readLine("Do you want to [C]onsult the statistics of the card [Question : "+ currentCard.getAnswer()+", Answer : "+ currentCard.getQuestion()+"] ?");
			if("c".compareToIgnoreCase(choice) == 0){
				console.printLine(cardByLevel.toString(currentCard));
			}
		}
		
	}
}
