/**
 * 
 */
package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.net.*;

import modele.*;
import outils.LectureEcriture;
import controleur.*;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * Class qui permet l'affichage de l'application avec les éléments voulut. il
 * instancie la plupart des panels pour pouvoir les utiliser.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 */
public class PanelFrise extends JPanel implements ActionListener {

	CardLayout layout = new CardLayout();
	public static File fichTes;
	Frise chFrise = new Frise();

	PanelCalendrier pCalendrier = new PanelCalendrier();
	FenetreMenu chMenu = new FenetreMenu("Frise");

	FenetreFrise fFrise;

	PanelTimeLine pTLine;
	PanelEvt pEvt;
	PanelFormulaire pForm;
	PanelCreation pCreation;
	PanelAffichage pAffichage;

	int i = chMenu.getIndice();
	int j = chMenu.getIndiceV2();
	int f = 0;

	String chCurrentProject = chMenu.getTitreProjet();

	/**
	 * Lit la Frise sélectionné par l'utilisateur et l'affecte aux panels qui en ont
	 * besoin. il instancie aussi les panels.
	 * 
	 * @param parFMenu La fenetre qui aparaitra après clique dans certains menu de
	 *                 l'application.
	 */
	public PanelFrise(FenetreMenu parFMenu) {

		chMenu = parFMenu;
		if (FenetreMenu.fichTes.length() == 0)
			fichTes = new File(chCurrentProject + File.separator + chCurrentProject + ".ser");
		else {

			LectureEcriture chLecEcr = new LectureEcriture();
			chFrise = (Frise) chLecEcr.lecture(FenetreMenu.fichTes);
		}

		pCreation = new PanelCreation(chFrise, pCalendrier);
		pAffichage = new PanelAffichage(chFrise);
		pCreation.setModeleTable(chFrise);

		Controleur contr = new Controleur(chFrise, pCreation, pAffichage, pCalendrier);

		this.setLayout(layout);

		if (chMenu.getIndice() == 1) {
			this.add(pAffichage, Data.MENU[1]);
			this.add(pCreation, Data.MENU[0]);
		} else if (chMenu.getIndice() == 0) {

			if (j == 0) {
				this.add(pAffichage, Data.MENU[1]);
				this.add(pCreation, Data.MENU[0]);
			} else {
				pCreation.getClaire();
				this.add(pCreation, Data.MENU[0]);
				this.add(pAffichage, Data.MENU[1]);
			}
		}
	}

	/**
	 * Cette méthode permet à l'utilisateur un panel de chose assez grand comme,
	 * l'affichage du menu création/modifier/ou affichage depuis l'application. Il
	 * peut aussi faire afficher la documentation sur un navigateur, afficher la
	 * frise en cours, permet la fermeture de l'application et il propose le
	 * changement de fond avec le mode sombre et claire.
	 * 
	 * @param parEvt L'évènement qui déclenche l'action.
	 */
	public void actionPerformed(ActionEvent parEvt) {
		String actionCommand = parEvt.getActionCommand();

		// MenuCreation
		if (actionCommand.equals(Data.MENUCREATION[0])) {

			chMenu.chLblCredit.setBounds(175, 340, 150, 15);
			// setVisible
			chMenu.btnFra.setVisible(true);
			chMenu.btnEng.setVisible(true);

			chMenu.chLblCredit.setVisible(true);
			// setVisible MenuGeneral
			// setVisible Creation
			chMenu.chLblMsgCreation.setVisible(false);
			chMenu.btnNouveau.setVisible(false);
			chMenu.btnModifier.setVisible(false);
			chMenu.btnBack.setVisible(false);
			// setVisible Creation

			// setVisible Nouveau
			chMenu.chLblMsgNew.setVisible(true);
			chMenu.chLblNewTitre.setVisible(true);
			chMenu.chNewTitreField.setVisible(true);
			chMenu.chLblNewDebut.setVisible(true);
			chMenu.btnDateDebut.setVisible(true);
			// setVisible Nouveau
			// setVisible
			// setVisible

			chMenu.setVisible(true);
		}
		if (actionCommand.equals(Data.MENUCREATION[1])) {

			chMenu.chLblCredit.setBounds(175, 240, 150, 15);

			// setVisible
			chMenu.btnFra.setVisible(true);
			chMenu.btnEng.setVisible(true);

			// setVisible MenuGeneral
			chMenu.chLblMsgBienvenue.setVisible(false);
			chMenu.btnCreation.setVisible(false);
			chMenu.btnAffichage.setVisible(false);
			chMenu.chLblCredit.setVisible(true);
			// setVisible MenuGeneral

			// setVisible Affichage
			chMenu.chLblMsgAffichage.setVisible(true);
			chMenu.chLblFic.setVisible(true);
			chMenu.comboFichier.setVisible(true);
			chMenu.btnOpenC.setVisible(true);
			chMenu.btnBack.setVisible(false);
			// setVisible Affichage
			// setVisible

			chMenu.setVisible(true);
		}

		// MenuAffichage
		if (actionCommand.equals(Data.MENUAFFICHAGE[0])) {
			chMenu.chLblCredit.setBounds(175, 240, 150, 15);
			chMenu.btnBack.setBounds(250, 200, 140, 30);

			// setVisible
			chMenu.btnFra.setVisible(true);
			chMenu.btnEng.setVisible(true);

			// setVisible MenuGeneral
			chMenu.chLblMsgBienvenue.setVisible(false);
			chMenu.btnCreation.setVisible(false);
			chMenu.btnAffichage.setVisible(false);
			chMenu.chLblCredit.setVisible(true);
			// setVisible MenuGeneral

			// setVisible Affichage
			chMenu.chLblMsgAffichage.setVisible(true);
			chMenu.chLblFic.setVisible(true);
			chMenu.comboFichier.setVisible(true);
			chMenu.btnOpenA.setVisible(true);
			chMenu.btnBack.setVisible(true);
			// setVisible Affichage
			// setVisible

			chMenu.setVisible(true);
		}
		if (actionCommand.equals(Data.MENUAFFICHAGE[1])) {

			fichTes = new File(chCurrentProject + File.separator + chCurrentProject + ".ser");
			FenetreFrise a = null;
			a = new FenetreFrise(chMenu, "Frise - Modifier Frise");
			a.setVisible(true);
		}

		// MenuQuitter
		if (actionCommand.equals(Data.MENUQUITTER[0])) {
			int saisi = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir fermer la fenêtre ?",
					"Dialogue de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			switch (saisi) {
			case JOptionPane.CLOSED_OPTION:
				break;
			case JOptionPane.YES_OPTION:
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				break;
			} // switch
		}

		// MenuAPropos
		if (actionCommand.equals(Data.MENUAPROPOS[0])) {
			File fich = new File("doc" + File.separator + "index.html");
 			String url = "file:///" + fich.getAbsolutePath();
			openInBrowser(url);
		}
		if (actionCommand.equals(Data.MENUAPROPOS[1])) {
			// String url = "monchemin";
			File fich = new File("menu" + File.separator + "comment_ca_marche.html");
			String url = "file:///" + fich.getAbsolutePath();
			openInBrowser(url);
		}
		if (actionCommand.equals(Data.MENUCOULEURS[0])) {
			pCreation.getClaire();
			pAffichage.getClaire();
		}
		if (actionCommand.equals(Data.MENUCOULEURS[1])) {
			pCreation.getSombre();
			pAffichage.getSombre();
		}
	}

	/**
	 * Elle ouvre si possible le fichier spécifier dans l'url sur le browser et si
	 * cela n'est pas possible elle affiche un message d'erreur.
	 * 
	 * @param url il peut être sous forme d'adresse web ou en chemin complet jusqu'à
	 *            un fichier html.
	 */
	public static void openInBrowser(String url) {
		try {
			URI uri = new URL(url).toURI();
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
				desktop.browse(uri);
		} catch (Exception e) {
			e.printStackTrace();

			// Copy URL to the clipboard so the user can paste it into their browser
			StringSelection stringSelection = new StringSelection(url);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
			// Notify the user of the failure
			System.out.println(
					"This program just tried to open a webpage." + "\n" + "The URL has been copied to your clipboard,"
							+ "simply paste into your browser to access. Webpage: " + url);
		}
	}
}
