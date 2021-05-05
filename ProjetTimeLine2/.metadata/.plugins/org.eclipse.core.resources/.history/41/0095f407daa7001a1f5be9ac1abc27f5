package modele;

import java.io.Serializable;

/**
 * La classe Evt permet de créer un événement.
 * 
 * @author BOUCHDI Yassine
 *
 */
public class Evt implements Comparable<Evt>, Serializable {
	private Date chDate;
	private String chTitre, chLieu, chDescription, chStringHeureDebut, chStringMinDebut, chStringHeureFin,
			chStringMinFin;
	int chHeureDebut, chMinuteDebut, chHeureFin, chMinutefin;
	private static int chNbEvt = 0;

	/**
	 * Méthode toString gère la forme de l'événement.
	 */
	public String toString() {

		return "[" + chDate.toString() + "," + chTitre + "," + chLieu + "," + chDescription + "," + " " + "Dès" + " "
				+ chHeureDebut + ":" + chMinuteDebut + " " + "à" + " " + chHeureFin + ":" + chMinutefin + " "
				+ "nbre des evenements :" + chNbEvt + "]";
	}

	/**
	 * Le constructeur de Evt affecte les variables en paramètre aux champs de la
	 * classe pour céer un evenement.
	 * 
	 * @param parDate : La Date de l'événement.
	 * @param parTitre : Le Titre de l'événement.
	 * @param parLieu : Le lieu de l'événement.
	 * @param parDescription : Descrption de l'événement.
	 * @param parHeureDebut : Heure Début de l'événement.
	 * @param parMinuteDebut : Minute Début de l'événement.
	 * @param parHeureFin : Heure Fin de l'événement.
	 * @param parMinuteFin : Minute fin de l'événement.
	 */
	public Evt(Date parDate, String parTitre, String parLieu, String parDescription, int parHeureDebut,
			int parMinuteDebut, int parHeureFin, int parMinuteFin) {
		chDate = parDate;
		chTitre = parTitre;
		chLieu = parLieu;
		chDescription = parDescription;
		chHeureDebut = parHeureDebut;
		chMinuteDebut = parMinuteDebut;
		chHeureFin = parHeureFin;
		chMinutefin = parMinuteFin;
		chNbEvt++;
	}

	/**
	 * La méthode setDate affecte la date en parametre à l'événement courant.
	 * 
	 * @param parDate : La nouvelle date de l'événement.
	 */
	public void setDate(Date parDate) {
		chDate = parDate;
	}

	/**
	 * La méthode getDate renvoie la date de l'événement.
	 * 
	 * @return Date : La date de l'événement.
	 */
	public Date getDate() {
		return chDate;
	}

	/**
	 * La méthode getHeureDebut renvoie l'heure de début de l'événement.
	 * 
	 * @return int : Heure Début de l'événement.
	 */
	public int getHeureDebut() {
		return chHeureDebut;
	}

	/**
	 * La méthode getMinuteDebut renvoie la minute de début de l'événement.
	 * 
	 * @return int : Minute Début de l'événement
	 */
	public int getMinuteDebut() {
		return chMinuteDebut;
	}

	/**
	 * La méthode getHeureFin renvoie l'heure de début de l'événement.
	 * 
	 * @return int Heure Fin de l'événement.
	 */
	public int getHeureFin() {
		return chHeureFin;
	}

	/**
	 * La méthode getMinutefin renvoie la minute de fin de l'événement.
	 * 
	 * @return int : Minute Fin de l'événement
	 */
	public int getMinutefin() {
		return chMinutefin;
	}

	/**
	 * La methode getTitre renvoie le titre de l'événement.
	 * 
	 * @return String : Titre de l'événement
	 */
	public String getTitre() {
		return chTitre;
	}

	/**
	 * La methode getDescription renvoie la description de l'événement.
	 * 
	 * @return String : Description de l'événement
	 */
	public String getDescription() {
		return chDescription;
	}

	/**
	 * La methode getNbEvt renvoie le nombre d'événements créés.
	 * 
	 * @return int : nombre d'événements créés.
	 */
	public static int getNbEvt() {
		return chNbEvt;
	}

	/**
	 * la méthode toStingagenda ecrit l'événement sous une deuxième forme plus
	 * pratique pour la Jtable de PanelAffichage.
	 * 
	 * @return String
	 */
	public String toStringagenda() {
		if (chHeureDebut < 10) {
			chStringHeureDebut = "0" + chHeureDebut + ":";
		} else {
			chStringHeureDebut = chHeureDebut + ":";
		}
		if (chMinuteDebut < 10) {
			chStringMinDebut = "0" + chMinuteDebut + "";
		} else {
			chStringMinDebut = chMinuteDebut + ":";
		}
		if (chHeureFin < 10) {
			chStringHeureFin = "0" + chHeureFin + ":";
		} else {
			chStringHeureFin = chHeureFin + ":";
		}
		if (chMinutefin < 10) {
			chStringMinFin = "0" + chMinutefin + "";
		} else {
			chStringMinFin = chMinutefin + ":";
		}
		return " " + chTitre + " : \n" + chDescription + "\n" + "\n Lieu : " + chLieu + "\n Début : "
				+ chStringHeureDebut + chStringMinDebut + "\n Fin : " + chStringHeureFin + chStringMinFin;

	}

	/**
	 * La méthode compareTo sert a comparer deux événements, en se basant sur leurs
	 * dates et temps de début puis son titre si les deux événements comparés ont la
	 * même date et le même temps de début: elle retourne un entier négatif si
	 * l'événement en paramètre est plus grande, un entier positif si il est plus
	 * petit, et 0 si les deux événements sont égaux.
	 * 
	 * 
	 */
	public int compareTo(Evt parEvt) {
		int val1 = this.getDate().compareTo(parEvt.getDate());
		int val2 = (parEvt.getHeureDebut() * 100 + parEvt.getMinuteDebut())
				- (this.getHeureDebut() * 100 + this.getMinuteDebut());
		if ((val1 == 0) && (val2 != 0)) {
			val1 = val2;
		} else if ((val1 == 0) && (val2 == 0)) {
			val1 = this.getTitre().compareTo(parEvt.getTitre());
		}

		return (val1);
	}

}
