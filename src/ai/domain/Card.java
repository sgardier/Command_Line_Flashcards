package ai.domain;

/**
 * 
 * 	@author Simon Gardier - B1 - GR5
 * 
 * 	Classe représentant des cartes d'une boite de Leitner
 * 
 *	Fonctionnalités :
 *		- Récupérer des info à son propos (Question/Réponse)
 *		- Comparer une carte à un autre objet
 */
public class Card {
	
	private final String question;
	private final String answer;
	
	/**
	 * 
	 * @param question : question liée à la carte
	 * @param answer : réponse lié à la carte
	 */
	public Card(final String question, final String answer) {
		this.question = question;
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public String getAnswer() {
		return answer;
	}
	@Override
	public String toString() {
		return "Carte ( Question = \" "+question+" \", réponse = \" "+answer+" \" ) ";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}
	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (getClass() != other.getClass()) {
			return false;
		}
		final Card otherCard = (Card) other;
		if (answer == null) {
			if (otherCard.answer != null) {
				return false;
			}
		} else if (!answer.equals(otherCard.answer)) {
			return false;
		}
		if (question == null) {
			if (otherCard.question != null) {
				return false;
			}
		} else if (!question.equals(otherCard.question)) {
			return false;
		}
		return true;
	}
}
