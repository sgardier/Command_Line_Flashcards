package ai.pubsub;

import ai.statistic.StatisticEvent;
/**
 * 
 * @author Simon Gardier - B1 - GR5
 * 
 * 
 * Interface représentant cannal d'évênements auquel des subscribers (statistiques) peuvent s'abonner
 * 
 * Fonctionnalités :
 * 		-publier un évênement(déplacement de carte) auprès des subscribers (statistiques)
 * 		-ajouter un subscriber à sa liste de subscribers voulant profiter des mises à jour de ce canal d'évênementss
 */
public interface EventChannel {
	public void publish(StatisticEvent revisionEvent);
	public void subscribe(RevisionStatistic statistic);
}
