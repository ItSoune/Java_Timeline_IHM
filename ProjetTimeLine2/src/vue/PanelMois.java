package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import controleur.*;
import modele.*;
import modele.Date;

/**
 * La classe PanelMois rassemble les BoutonDate d'un certain mois.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */
public class PanelMois extends JPanel {
	private static final long serialVersionUID = 1L;

	ArrayList<BoutonDate> chBoutons = new ArrayList<BoutonDate>();
	BoutonDate chBoutjour;
	
	Date chAujourdhui = new Date();
	Date chDate = new Date();
	
	int chMois;
	
	private Collection<Date> chCollectionDates;

	public PanelMois(int parMois,int parAnnee) {
		setLayout(new GridLayout(0, 7));
	
		CalendrierDuMois calendrier = new CalendrierDuMois(parMois, parAnnee);
		
		chCollectionDates = calendrier.getDates();
		
		Iterator<Date> it = chCollectionDates.iterator();
		
		chMois = parMois;
		
		while (it.hasNext()) {
			Date chDate = it.next();
			if (chDate.compareTo(chAujourdhui) == 0) {
				chBoutjour = new BoutonDate(chDate);
				chBoutjour.setBackground(new Color(0, 0, 100));
				chBoutjour.setForeground(new Color(255, 255, 255));
				chBoutjour.setDate(chDate);
				this.add(chBoutjour);
				chBoutons.add(chBoutjour);
			} else if (parMois != chDate.getMois()) {
				chBoutjour = new BoutonDate(chDate);
				chBoutjour.setDate(chDate);
				chBoutjour.setBackground(new Color(175, 175, 175));
				chBoutjour.setForeground(new Color(100, 100, 100));
				this.add(chBoutjour);
				chBoutons.add(chBoutjour);
				chBoutons.remove(chBoutjour);
			}
			else if (chDate.getJourSemaine() == 7 || chDate.getJourSemaine() == 1) {
				chBoutjour = new BoutonDate(chDate);
				chBoutjour.setDate(chDate);
				chBoutjour.setBackground(new Color(225, 225, 225));
				chBoutjour.setForeground(new Color(0, 0, 0));
				this.add(chBoutjour);
				chBoutons.add(chBoutjour);
			}
			else {
				chBoutjour = new BoutonDate(chDate);
				chBoutjour.setDate(chDate);
				chBoutjour.setBackground(new Color(200, 200, 200));
				this.add(chBoutjour);
				chBoutons.add(chBoutjour);
			}
		}

	}

	/**
	 * La methode enregistreEcouteur met tous les BoutonDate du mois en ecoute du
	 * controleur.
	 * 
	 * @param parControleur Le controleur qui gère les clics.
	 */
	public void enregistreEcouteur(Controleur parControleur) {
		for (int i = 0; i < chBoutons.size(); i++) {
			chBoutons.get(i).addActionListener(parControleur);

		}
	}
}
