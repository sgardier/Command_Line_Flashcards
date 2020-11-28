package ai.commands;

/**
 * 	@author Simon Gardier - B1 - GR5
 *	
 *	Représente des commandes utilisables par un utilisateur
 *
 *	Fonctionnalités :
 *	-Vérifier si une commande possède un nom précis
 *	-Récupérer le nom d'une commande / sa description
 *	-Comparer objets Command entre eux
 */
public abstract class Command {

	private final String name;
	private final String description;
	
	/**
	 * Méthode abstraite à implémenter dans la classe concrète de la méthode.
	 * Permet d'exécuter une commande
	 */
	public abstract void execute();
	/**Constructeur
	 * @param name : nom de la commande
	 * @param description : description de la commande
	 */
	public Command(final String name, final String description) {
		this.name = name;
		this.description = description;
	}
	/**
	 * 
	 * @param n : Chaine de charactères à comparer avec le nom de l'objet courant
	 * @return
	 */
	public boolean hasName(final String n) {
		return (name == null && n == null) || (name != null && n != null && name.compareToIgnoreCase(n) == 0);
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj){
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Command other = (Command) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
