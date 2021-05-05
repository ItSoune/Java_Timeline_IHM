package modele;

import java.io.Serializable;
import java.util.*;

/**
 * La classe Date permet de créer une date.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */
public class Date implements Comparable<Date>, Serializable {
	private int chJour, chMois, chAn, chJourSemaine, chNumdeSemaine;

	/**
	 * Methode toString gère la forme de la date
	 */
	public String toString() {
		return (Data.JOUR_SEM[chJourSemaine] + " " + chJour + " " + Data.MOIS[chMois] + " " + chAn);
	}

	/**
	 * Le premier constructeur de Date n'a pas besoin de paramètres, il crée la date
	 * d'aujourd'hui.
	 */
	public Date() {

		GregorianCalendar aujourdhui = new GregorianCalendar();
		chAn = aujourdhui.get(Calendar.YEAR);
		chMois = aujourdhui.get(Calendar.MONTH) + 1;
		chJour = aujourdhui.get(Calendar.DAY_OF_MONTH);
		chJourSemaine = aujourdhui.get(Calendar.DAY_OF_WEEK);
		chNumdeSemaine = aujourdhui.get(Calendar.WEEK_OF_YEAR);

	}

	/**
	 * Le deuxieme constructeur de Date crée une date avec le jour, le mois et l'année
	 * donneés en paramètre.
	 * 
	 * @param parJour : le jour de la date
	 * @param parMois : le mois de la date
	 * @param parAn : l'année de la date
	 */
	public Date(int parJour, int parMois, int parAn) {
		GregorianCalendar date = new GregorianCalendar(parAn, parMois - 1, parJour);
		chJour = parJour;
		chMois = parMois;
		chAn = parAn;
		chJourSemaine = date.get(Calendar.DAY_OF_WEEK);
		chNumdeSemaine = date.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * La méthode getAnnee renvoie l'année de la date.
	 * 
	 * @return Année (int)
	 */
	public int getAnnee() {
		return chAn;
	}

	/**
	 * La méthode getJour renvoie le jour de la date.
	 * 
	 * @return Jour (int)
	 */
	public int getJour() {
		return chJour;
	}

	/**
	 * La méthode getMois renvoie le mois de la date.
	 * 
	 * @return Mois (int)
	 */
	public int getMois() {
		return chMois;
	}

	/**
	 * La méthode getJourSemaine renvoie le numéro du jour dans la semaine.
	 * 
	 * @return numéro du jour dans la semaine (int)
	 */
	public int getJourSemaine() {
		return chJourSemaine;
	}

	/**
	 * La méthode getnumdeSemaine renvoie le numéro de la semaine de la date.
	 * 
	 * @return numéro de la semaine (int)
	 */
	public int getnumdeSemaine() {
		if (chJourSemaine == 1) {
			chNumdeSemaine--;
		}

		return chNumdeSemaine;
	}

	/**
	 * La méthode dernierJourDuMois renvoie le dernier jour du mois passe en
	 * paramètre dans l'année passée en paramètre.
	 * 
	 * @param parM : Le mois
	 * @param parAn : L'année
	 * @return le dernier jour (int)
	 */
	public int dernierJourDuMois(int parM, int parAn) {
		switch (parM) {
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if ((parAn % 4 == 0 && parAn % 100 != 0) || parAn % 400 == 0)
				return 29;
			else
				return 28;

		default:
			return 31;
		}
	}

	/**
	 * La méthode dateDuLendemain renvoie la date du lendemain en prenant compte du
	 * mois en cours et le mois suivant.
	 * 
	 * @return Date
	 */
	public Date dateDuLendemain() // throws ExceptionDate
	{
		int an = chAn, mois = chMois, jour = chJour;

		if (jour < dernierJourDuMois(mois, an))
			return new Date(jour + 1, mois, an);
		else if (mois < 12)
			return new Date(1, mois + 1, an);
		else
			return new Date(1, 1, an + 1);

	}

	/**
	 * La méthode dateDeLaveille renvoie la date de la veille en prenant compte du
	 * mois en cours et le mois d'avant.
	 * 
	 * @return Date
	 */
	public Date dateDeLaVeille() { // throws ExceptionDate

		int an = chAn, mois = chMois, jour = chJour;

		if (jour > 1)
			return new Date(jour - 1, mois, an);
		else if (mois > 1)
			return new Date(dernierJourDuMois(mois - 1, an), mois - 1, an);
		else
			return new Date(31, 12, an - 1);

	}

	/**
	 * La méthode estValide renvoie un boolean indiquant si une date est valide.
	 * 
	 * @return true ou false (boolean)
	 */
	public boolean estValide() {
		if (chAn < 1583)
			return false;
		if (chMois < 1 || chMois > 12)
			return false;
		if (chJour < 1 || chJour > dernierJourDuMois(chMois, chAn))
			return false;
		return true;

	}

	/**
	 * La méthode datePremierJourSemaine renvoie la date du Lundi de la semaine
	 * d'une date.
	 * 
	 * @return la date du Lundi (Date)
	 */
	public Date datePremierJourSemaine() {
		Date date = new Date(chJour, chMois, chAn);
		while (date.chJourSemaine != 2) {
			date = date.dateDeLaVeille();
		}

		return date;
	}

	/**
	 * La méthode compareTo sert à comparer deux dates: elle retourne un entier
	 * négatif si la date en paramètre est plus grande, un entier positif si elle
	 * est plus petite, et 0 si les deux dates sont égaux.
	 *
	 */
	public int compareTo(Date parDate) {
		int val1 = chAn * 10000 + chMois * 100 + chJour;
		int val2 = parDate.chAn * 10000 + parDate.chMois * 100 + parDate.chJour;
		return (val1 - val2);
	}

}
