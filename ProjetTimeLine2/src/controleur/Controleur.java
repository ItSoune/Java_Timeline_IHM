package controleur;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

import modele.*;
import outils.LectureEcriture;
import vue.*;

/**
 * La classe controleur permet de faire la liaison entre le modele et la vue.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */
public class Controleur implements ActionListener, MouseListener, Serializable {
	Frise chFrise;
	
	Date chTempdate = new Date();
	Date chAujourdhui = new Date();
	
	PanelCreation chPanelCreation;
	PanelAffichage chPanelAffichage;	
	PanelCalendrier chPanelCalendrier;
	
	BoutonDate chBoutemp = new BoutonDate(chAujourdhui);
	
	/**
	 * Le constructeur prend en paramètre les diffèrentes classes du modele et de la
	 * vue pour les mettre en relation.
	 * 
	 * @param parFrise Le controleur prend en paramètre la Frise correspondante en projet actuel.
	 * @param parPanelCreation Le controleur prend en paramètre PanelCreation qui contient le Formulaire et la TimeLine.
	 * @param parPanelAffichage Le controleur prend en paramètre PanelAffichage qui contient.
	 * @param parPanelCalendrier Le controleur prend en paramètre le PanelCalendrier qui contient le calendrier du mois.
	 */
	public Controleur(Frise parFrise, PanelCreation parPanelCreation, PanelAffichage parPanelAffichage,PanelCalendrier parPanelCalendrier) {
		
		chFrise = parFrise;
		chPanelCalendrier = parPanelCalendrier;
		chPanelCreation = parPanelCreation;
		chPanelAffichage = parPanelAffichage;
		
		chPanelAffichage.enregistreEcouteur(this);
		chPanelCreation.enregistreEcouteur(this);
		chPanelCalendrier.enregistreEcouteur(this);
	}

	/**
	 * Le controleur s'occupe dans un premier temps de l'ajot de l'évènement à la frise et au fichier dans lequel l'évènement est ajouté.
	 * Il est aussi mit à l'écoute des actions sur les boutons de calendrier, il modifie la date de l'évènement de PanelFormulaire.
	 * 
	 * @param parEvt L'action qui déclenche la méthode.
	 */
	public void actionPerformed(ActionEvent parEvt) {
		if (parEvt.getActionCommand().equals("+")) {
			chFrise.ajoutMap(chPanelCreation.getEvenement());
			chPanelCreation.setModeleTable(chFrise);
			LectureEcriture.ecriture(FenetreMenu.fichTes, chFrise);
			
			chPanelCreation.RAZ();
		}
		else {
			BoutonDate bout = (BoutonDate) parEvt.getSource();
			if (chBoutemp != bout) {

				if (chBoutemp.getDate().compareTo(chAujourdhui) == 0) {
					chBoutemp.setBackground(new Color(0, 0, 100));
					chBoutemp.setForeground(Data.LeBlanc);

				} else if (chBoutemp.getDate().getMois() != bout.getDate().getMois()) {
					chBoutemp.setBackground(new Color(175, 175, 175));
					chBoutemp.setForeground(new Color(100, 100, 100));

				} else if (chBoutemp.getDate().getJourSemaine() == 7 || chBoutemp.getDate().getJourSemaine() == 1) {
					chBoutemp.setBackground(new Color(225, 225, 225));
					chBoutemp.setForeground(Data.LeNoir);
				}
				else {
					chBoutemp.setBackground(new Color(200, 200, 200));
					chBoutemp.setForeground(Data.LeNoir);
				}
			}

			if (parEvt.getSource() == bout) {
				Date date = bout.getDate();
				
				chPanelCreation.setDate(date);
				chTempdate = date;
				
				bout.setBackground(Data.LeBlanc);
				bout.setForeground(new Color(0, 0, 100));
				chBoutemp = bout;
			}
		}
	}
	
	/**
	 * Méthode mouseClicked, permet l'affichage de l'évènement sur lequel l'utilisateur clique depuis la frise sur le PanelEvt.
	 * 
	 * @param parEvt L'évènement sur lequel la méthode agit.
	 */
	public void mouseClicked(MouseEvent parEvt) {
		JTable table = (JTable) parEvt.getSource();
		ModeleTable model = (ModeleTable) table.getModel();
		Point point = parEvt.getPoint();

		int rowIndex = table.rowAtPoint(point);
		int colIndex = table.columnAtPoint(point);

		final JFrame frame = new JFrame("Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);

		if (table.getValueAt(rowIndex, colIndex) != null)
			chPanelAffichage.setChoix(model.getValueAt(rowIndex, colIndex).toString());
	}
	
	@Override
	public void mousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	} 
}
