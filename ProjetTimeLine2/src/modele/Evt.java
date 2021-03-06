package modele;

import java.io.Serializable;

/**
 * La classe Evt permet de cr?er un ?v?nement.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */
public class Evt implements Comparable<Evt>, Serializable {
	private Date chDate;
	private String chTitre, chDescription;
	private int chNiveau; 
	private static int chNbEvt = 0;

	/**
	 * M?thode toString g?re la forme de l'?v?nement.
	 */
	public String toString() {

		return chDate.toString() + "\n" + chTitre + "\n" + chDescription+ "\n" +chNiveau;
	}

	/**
	 * Le constructeur de Evt affecte les variables en param?tre aux champs de la
	 * classe pour c?er un evenement.
	 * 
	 * @param parDate : La Date de l'?v?nement.
	 * @param parTitre : Le Titre de l'?v?nement.
	 * @param parDescription : Descrption de l'?v?nement.
	 * @param parNiveau : Le niveau d'importance de l'?v?nement.

	 */
	public Evt(Date parDate, String parTitre, String parDescription, int parNiveau) {
		chDate = parDate;
		chTitre = parTitre;
		chDescription = parDescription;
		chNiveau = parNiveau;
		chNbEvt++;
	}

	/**
	 * La m?thode setDate affecte la date en parametre ? l'?v?nement courant.
	 * 
	 * @param parDate : La nouvelle date de l'?v?nement.
	 */
	public void setDate(Date parDate) {
		chDate = parDate;
	}

	/**
	 * La m?thode getDate renvoie la date de l'?v?nement.
	 * 
	 * @return Date : La date de l'?v?nement.
	 */
	public Date getDate() {
		return chDate;
	}

	

	/**
	 * La methode getTitre renvoie le titre de l'?v?nement.
	 * 
	 * @return String : Titre de l'?v?nement
	 */
	public String getTitre() {
		return chTitre;
	}

	/**
	 * La methode getDescription renvoie la description de l'?v?nement.
	 * 
	 * @return String : Description de l'?v?nement
	 */
	public String getDescription() {
		return chDescription;
	}

	/**
	 * La methode getNbEvt renvoie le nombre d'?v?nements cr??s.
	 * 
	 * @return int : nombre d'?v?nements cr??s.
	 */
	public static int getNbEvt() {
		return chNbEvt;
	}
	
	/**
	 * La methode getNiveau renvoie le niveau d'importance de l'?v?nement.
	 * 
	 * @return int : niveau d'importance.
	 */
	public int getNiveau() {
		return chNiveau;
	}

	
	/**
	 * La m?thode compareTo sert a comparer deux ?v?nements, en se basant sur leurs
	 * dates puis titre si les deux ?v?nements compar?s ont la
	 * m?me date : elle retourne un entier n?gatif si
	 * l'?v?nement en param?tre est plus grand, un entier positif si il est plus
	 * petit, et 0 si les deux ?v?nements sont ?gaux.
	 * 
	 * 
	 */
	public int compareTo(Evt parEvt) {
		int val = this.getDate().compareTo(parEvt.getDate());

		if ((val == 0)) {
			val = parEvt.getNiveau()-this.getNiveau();
		}

		return (val);
	}

}
