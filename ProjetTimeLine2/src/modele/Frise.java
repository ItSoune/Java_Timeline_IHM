package modele;

import java.io.Serializable;
import java.util.*;

/**
 * La classe Agenda contient les �v�nements ajout�s.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */
public class Frise implements Serializable {

	public  TreeMap<Date, Evt> chTreeMap = new TreeMap<Date, Evt>();

	/**
	 * La m�thode toString g�re la forme de l'agenda.
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
	 * La m�thode ajoutMap g�re l'ajout des nouveaux �v�nements � la TreeMap : Elle
	 * classe les �v�nements par leur num�ro de semaine.
	 * 
	 * @param parNvEvenement : Le nouveau �v�nement � ajouter.
	 */
	public void ajoutMap(Evt parNvEvenement) {
		Date date = parNvEvenement.getDate();
			chTreeMap.put(date, parNvEvenement);
		
	}

	/**
     * Retourne l'�v�nement correspondant � la date donn�e en param�tre.
     * 
     * @param parDate Date de l'�v�nement � retourner.
     * @return evt l'�v�nement correspondant � la date en param�tre
     */
	public Evt getEvtDate(Date parDate) {
		Evt evt = chTreeMap.get(parDate);
		return evt;

	}
	
	public TreeMap<Date, Evt> getAllEvt(){
			return chTreeMap;
	}
	

}
