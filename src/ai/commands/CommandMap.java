package ai.commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Simon Gardier - B1 - GR5
 *	Manipule une collection d'objets Command
 *	Fonctionnalités :
 * - Créer une collection de commandes
 * - rechercher un objet command dans une collection via son nom
 */
public class CommandMap implements Iterable<Command>{
	
	/*
	 *->Map :
	 *		Permet d'associer des éléments (nos commandes) à une clé (leur nom). Cela facilite leur recherche dans la collection de 
	 *		commandes
	 *->HashMap : 
	 *		CTT de get() : O(N/k) où n = nombre de commandes comprises dans la HashMap et k = le nombre de listes chaînées comprises dans 
	 *		la HashMap. Dans le cas où il n'y a pas de collisios, la CTT est de O(1);
	 *		Cette CTT rend la HashMap plus perfomante vis à vis d'une autre collection (au niveau de la récupération d'un élément désiré) 
	 *		telle que ArrayList qui doit être parcourue jusqu'à
	 *		trouver l'élément et ensuite le renvoyer (dont la CTT de get() serait : O(N))
	 */
	final private Map <String,Command>commands = new HashMap<String,Command>();
	
	/**
	 * 
	 * @param commands collection d'objets Command
	 */
	public CommandMap(final Command ...commands){
		for (final Command command : commands){
			this.commands.put(command.getName(), command);
		}
	}
	
	/**
	 * Récupère une command dont on connait sa clé (son nom)
	 * @param key : chaine de charactère représentant la clé (nom) d'une valeur (commande) à rechercher dans la collection
	 * @return retourne la commande (valeur) trouvée à l'emplamcement du nom (clé) passé en paramètre et recherché dans la collection commands
	 */
	public Command get(final String key) {
		return commands.get(key);
	}
	@Override
	public Iterator<Command> iterator() {
		return commands.values().iterator();
	}
}
