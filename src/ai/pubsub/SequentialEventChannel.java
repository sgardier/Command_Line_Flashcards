package ai.pubsub;
import java.util.ArrayList;
import java.util.List;

import ai.statistic.StatisticEvent;

/**
 * 
 * @author Simon Gardier - B1 - GR5
 * 
 * Class implémentant l'interface EventChannel
 * 
 * --- Choix de collection ---
 *
 *	Implémentation 	-> 		List : 	- Permet de récupérer un élément sur base de son indice 
 *									- Permet d'ajouter un élément dans la collection
 *									- N'ayant pas besoin de fonctionnalité particulière (pas de tri, pas d'association de clés valeurs, de queue / pile)
 *									  Liste propose déjà tout ce dont on a besoin (accéder à un élément pour un indice donné et ajouter un élément à un indice donné)
 *
 *	Classe concrète -> ArrayList : 	- Permet de récupérer très rapidement un élément sur base de son indice -> CTT get() : O(1)
 * 									- Permet de modifier très rapidement un élément sur base de son indice -> CTT set() : O(1)
 *									- Permet d'ajouter un élément dans la collection de façon optimisée pour les 10 premiers éléments
 *
 */
public class SequentialEventChannel implements EventChannel{

	private final List<RevisionStatistic> statistics = new ArrayList<RevisionStatistic>();
	
	@Override
	public void publish(final StatisticEvent revisionEvent) {
		for (final RevisionStatistic revisionStatistic : statistics) {
			revisionStatistic.update(revisionEvent);
		}
	}

	@Override
	public void subscribe(final RevisionStatistic statistic) {
		statistics.add(statistic);
	}
}
