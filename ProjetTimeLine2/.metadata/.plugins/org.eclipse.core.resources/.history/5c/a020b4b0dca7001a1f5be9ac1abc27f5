package modele;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.table.DefaultTableModel;

/**
 * La Classe ModeleTable gère la table qui contient les événements de l'agenda
 * d'une semaine donnée.
 * 
 * @author BOUCHDI Yassine
 *
 */
public class ModeleTable extends DefaultTableModel {
	TreeSet<Evt> chNouvTreeSet = new TreeSet();
	AgendaV2 chAgenda = new AgendaV2();

	/**
	 * Le constructeur de la classe ModelTable met en place la table (numéro de
	 * colonnes et de lignes) et ajoute les evenements de la semaine.
	 * 
	 * @param parDate : La date choisie dans le calendrier
	 * @param paragenda : L'agenda qui contient les événements.
	 */
	public ModeleTable(Date parDate, AgendaV2 paragenda) {
		chAgenda = paragenda;
		setColumnCount(7);
		setRowCount(24);
		Date Lundi = parDate.datePremierJourSemaine();
		int Jour = Lundi.getJour();
		String intitulé[] = new String[7];
		for (int i = 0; i < Data.JOUR_ABR.length; i++) {
			intitulé[i] = Data.JOUR_ABR[i] + " " + Jour + "/" + Lundi.getMois();
			Lundi = Lundi.dateDuLendemain();
			Jour = Lundi.getJour();
		}
		this.setColumnIdentifiers(intitulé);
		chNouvTreeSet = chAgenda.getEvtSemaine(parDate.getnumdeSemaine());
		if (chNouvTreeSet == null) {
			return;
		}
		Iterator<Evt> it = chNouvTreeSet.iterator();
		while (it.hasNext()) {
			int indiceLigne = 0;
			Evt col = it.next();
			int numcol = col.getDate().getJourSemaine();
			if (numcol == 1) {
				numcol = 8;
			}
			while (getValueAt(indiceLigne, numcol - 2) != null) {
				indiceLigne++;
			}
			setValueAt(col.toStringagenda(), indiceLigne, numcol - 2);

		}
	}

	/**
	 * La méthode getColumnClass retourne la classe du contenu d'une cellule du
	 * tableau.
	 */
	public Class getColumnClass(int indiceColonne) {
		return String.class;
	}
}
