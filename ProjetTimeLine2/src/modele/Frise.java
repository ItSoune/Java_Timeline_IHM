package modele;

import java.io.Serializable;
import java.util.*;

/**
 * La classe Agenda contient les événements ajoutés.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */
public class Frise implements Serializable {

	public  TreeMap<Date, Evt> chTreeMap = new TreeMap<Date, Evt>();

	/**
	 * La méthode toString gère la forme de l'agenda.
	 * 
	 * @return String
	 */
	public String toString() {

		String message = "TreeMap : \n";
		Set<Date> cles = chTreeMap.keySet();
		for (Date cle : cles) {
			message += ("Dans la semaine " + cle + "  on a : " + "\n"+chTreeMap.get(cle)+ "\n" );
			
		}
		return message;
	}

	/**
	 * La méthode ajoutMap gère l'ajout des nouveaux événements à la TreeMap : Elle
	 * classe les événements par leur numéro de semaine.
	 * 
	 * @param parNvEvenement : Le nouveau événement à ajouter.
	 */
	public void ajoutMap(Evt parNvEvenement) {
		Date date = parNvEvenement.getDate();
			chTreeMap.put(date, parNvEvenement);
		
	}

	/**
     * Retourne l'évènement correspondant à la date donnée en paramètre.
     * 
     * @param parDate Date de l'évènement à retourner.
     * @return evt l'évènement correspondant à la date en paramètre
     */
	public Evt getEvtDate(Date parDate) {
		Evt evt = chTreeMap.get(parDate);
		return evt;

	}
	
	public TreeMap<Date, Evt> getAllEvt(){
			return chTreeMap;
	}
	

}
