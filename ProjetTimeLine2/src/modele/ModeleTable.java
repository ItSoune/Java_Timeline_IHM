package modele;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import vue.FenetreMenu;

/**
 * La Classe ModeleTable gère la table qui contient les événements de la Frise.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */

public class ModeleTable extends DefaultTableModel {
	TreeSet<Evt> chNouvTreeSet = new TreeSet();
	TreeSet<Date> dates = new TreeSet();
	
	Frise chFrise = new Frise();
	FenetreMenu chMenu = new FenetreMenu("Frise - Menu Général" );

	/**
	 * Le constructeur de la classe ModelTable met en place la table (numéro de
	 * colonnes et de lignes) et ajoute les evenements de la case correspondante.
	 * 
	 * @param parDateDebut 		La date de début de la frise.
	 * @param parDateFin 	La date de fin de la frise.
	 * @param parFrise		Frise dans laquelle sont ajoutés les évènements.
	 */
	public ModeleTable(Date parDateDebut ,Date parDateFin, Frise parFrise) {
        chFrise = parFrise;
        dates=getAllDates(parDateDebut,parDateFin);
        
        int J = 0;
        
        setColumnCount(dates.size());
        setRowCount(4);
        
        Date Debut = parDateDebut;
        
        int Jour = Debut.getJour();
        
        String intitulé[] = new String[dates.size()];
        
        for (int i = 0; i < dates.size(); i++) {
        	if (i%4 == 0)
                intitulé[i] =  Jour + "/" + Debut.getMois()+ "/" + Debut.getAnnee();
            else
                intitulé[i] =  " ";
            
        	J++;
            Debut = Debut.dateDuLendemain();
            Jour = Debut.getJour();
        }
        this.setColumnIdentifiers(intitulé);
        
        if (dates == null)
            return;
        
        Iterator<Date> it = dates.iterator();
        int indiceCol = 0;
        while (it.hasNext()) {
            Date date = it.next();
            if (chFrise.getEvtDate(date) != null) {
                Evt evt= chFrise.getEvtDate(date);
                String chCurrentProject = chMenu.getTitreProjet();
                File repertoire = new File (chCurrentProject+"\\ images_"+chCurrentProject);
                String [ ] intitules_images = repertoire.list() ;
                String Titre = evt.getTitre();
                for (int j=0; j<intitules_images.length;j++) {
                    if( intitules_images[j].equals(Titre+".png") ) {
                        setValueAt(Titre, evt.getNiveau(), indiceCol);
                    }
                }
            }
            indiceCol++;
        }
	}

	/**
	 * Ecrit toutes les dates dans une TreeSet pour pouvoir les mettre dans la Table.
	 * 
	 * @param parDateDeb	Date de début.
	 * @param parDateFin	Date de fin.
	 * @return TreeSet		TreeSet de toutes les dates de la Frise.
	 */
	public TreeSet<Date> getAllDates(Date parDateDeb,Date parDateFin){
		Collection<Date> chCollectionDates;

		Date date = parDateDeb;
		Date datefin = parDateFin;
		
		int Mois = date.getMois();
		int Annee = date.getAnnee();
		
		TreeSet<Date> DateTree = new TreeSet();
		
		while (date.compareTo(parDateFin) <= 0){
			CalendrierDuMois calendrier = new CalendrierDuMois(date.getMois(), date.getAnnee());
			chCollectionDates = calendrier.getDates();
			Iterator<Date> it = chCollectionDates.iterator();
			while (it.hasNext()) {
				Date chDate = it.next();
				if(chDate.compareTo(parDateFin) <= 0 && chDate.getMois()== Mois)
					DateTree.add(chDate);
			}
			
			if (Mois == 12) {
				Mois = 1;
				Annee ++;
				date = new Date(datefin.getJour(),Mois,Annee);
			}
			else {
				Mois++;
				date = new Date(datefin.getJour(),Mois,Annee);
			}
		}
		return DateTree;
	}
	
	/**
	 * La méthode getColumnClass retourne la classe du contenu d'une cellule du
	 * tableau.
	 */
	public Class getColumnClass(int indiceColonne) {
		return String.class;
	}
	
	/**
	 * Permet la non modification des cellules de la table.
	 * 
	 * @return boolean Toujours sur False pour ne pas pouvoir modifier les cellules.
	 */
	public boolean isCellEditable (int indiceLigne, int indiceColonne)  {
		return false ;
	}
}
