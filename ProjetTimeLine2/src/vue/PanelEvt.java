package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.*;

import modele.CalendrierDuMois;
import modele.Data;
import modele.Date;
import modele.Evt;
import modele.Frise;

/**
 * La classe permet l'affichage du la partie évènemnent et donc fait partie de
 * l'affichage.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */
public class PanelEvt extends JPanel implements ActionListener, Serializable {

	CardLayout chGestionnaireDeCartes = new CardLayout(5, 5);

	FenetreMenu chMenu = new FenetreMenu("Frise - Menu Général");
	PanelTimeLine PTLine;
	PanelDiaporama evt;

	private final static String[] chIntitulesDesBoutons = { "", "" };
	private final static JButton[] chBoutons = new JButton[2];

	String evtTitres[];

	JPanel chPanelCentre = new JPanel();
	JPanel chPanelWest = new JPanel();
	JPanel chPanelEast = new JPanel();
	JPanel chPanelNord = new JPanel();

	JButton pTitre = new JButton();

	TreeMap<Date, Evt> chTreeMap;

	int j = 0;
	int indiceEvt = 0;
	int indiceTitre = 0;

	GregorianCalendar chAujourdhui = new GregorianCalendar();
	GridBagConstraints contrainte = new GridBagConstraints();
	Image img = Toolkit.getDefaultToolkit().getImage("images" + File.separator + "mask.jpg").getScaledInstance(280, 350, 50);
	Set<Date> cles;

	/**
	 * Instancie les différentes parties de la classe et les ajoutes.
	 * 
	 * @param parFrise  Frise sur laquelle la classe travail.
	 * @param parPTLine Récupérer par un PanelTimeLine pour être utilisé.
	 */
	public PanelEvt(Frise parFrise, PanelTimeLine parPTLine) {

		this.setLayout(new BorderLayout(9, 9));
		PTLine = parPTLine;

		// Nord
		pTitre.setText(chMenu.getTitreProjet());
		pTitre.setBorderPainted(false);
		pTitre.setBackground(Data.LeBlanc);
		pTitre.setContentAreaFilled(false);
		pTitre.setFont(new Font("Times New Roman", Font.BOLD, 20));

		// West
		chPanelWest.setLayout(new BorderLayout(200, 200));
		chBoutons[0] = new JButton(chIntitulesDesBoutons[0]);
		chBoutons[0].setOpaque(true);
		chBoutons[0].addActionListener(this);
		chBoutons[0].setBackground(Data.LeBlanc);
		chBoutons[0].setBorderPainted(false);
		chBoutons[0].setIcon(
				new ImageIcon(new ImageIcon("images/back.jpg").getImage().getScaledInstance(70, 73, 50)));
		chBoutons[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chPanelWest.add(chBoutons[0]);

		// East
		chPanelEast.setLayout(new BorderLayout(200, 200));
		chBoutons[1] = new JButton(chIntitulesDesBoutons[1]);
		chBoutons[1].setOpaque(true);
		chBoutons[1].addActionListener(this);
		chBoutons[1].setBackground(Data.LeBlanc);
		chBoutons[1].setBorderPainted(false);
		chBoutons[1].setIcon(
				new ImageIcon(new ImageIcon("images/Next.jpg").getImage().getScaledInstance(70, 73, 50)));
		chBoutons[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chPanelEast.add(chBoutons[1]);

		// Centre
		chPanelCentre.setLayout(chGestionnaireDeCartes);
		chTreeMap = parFrise.getAllEvt();
		evtTitres = new String[chTreeMap.size()];
		cles = chTreeMap.keySet();

		for (Date cle : cles) {
			Evt evenement = chTreeMap.get(cle);
			evt = new PanelDiaporama(evenement){
				@Override
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(img, 0, 0, this);
				}
			};
			evt.setBackground(Data.LeBlanc);
			chPanelCentre.add(evt, evenement.getTitre());
			evtTitres[j] = evenement.getTitre();
			j++;
		}

		chPanelCentre.setBackground(Data.LeBleu);
		chGestionnaireDeCartes.show(chPanelCentre, "0");

		// Global
		add(chPanelWest, BorderLayout.WEST);
		add(chPanelCentre, BorderLayout.CENTER);
		add(chPanelEast, BorderLayout.EAST);
		add(pTitre, BorderLayout.NORTH);
		setBackground(new Color(255, 255, 255));
	}

	/**
	 * Affiche l'évènement sur lequel on clique de la frise sur le PanelDiaporama.
	 * 
	 * @param parTitre Titre de l'évènemnet correspondant à l'évènement afficher.
	 */
	public void setChoix(String parTitre) {
		chGestionnaireDeCartes.show(chPanelCentre, parTitre);

		for (int i = 0; i < evtTitres.length; i++) {
			if (evtTitres[i].equals(parTitre))
				indiceTitre = i;
		}

		PTLine.setVue(indiceTitre);
	}

	/**
	 * Cette méthode permet de gérer les événements sur PanelCalendrier.
	 * 
	 * @param parEvt Evènement qui déclenche l'action.
	 */
	public void actionPerformed(ActionEvent parEvt) {
		if (parEvt.getSource() == chBoutons[0]) {
			chGestionnaireDeCartes.previous(chPanelCentre);

			if (indiceTitre == 0)
				indiceTitre = chTreeMap.size() - 1;
			else
				--indiceTitre;

			PTLine.setVue(indiceTitre);
		} else if (parEvt.getSource() == chBoutons[1]) {
			chGestionnaireDeCartes.next(chPanelCentre);

			if (indiceTitre == chTreeMap.size() - 1)
				indiceTitre = 0;
			else
				++indiceTitre;

			PTLine.setVue(indiceTitre);
		}
	}

	/**
	 * Met le background en blanc et le Foreground en noir.
	 */
	public void getClaire() {
		chBoutons[0].setIcon(
				new ImageIcon(new ImageIcon("images/back.jpg").getImage().getScaledInstance(70, 73, 50)));
		chBoutons[1].setIcon(
				new ImageIcon(new ImageIcon("images/Next.jpg").getImage().getScaledInstance(70, 73, 50)));

		setBackground(Data.LeBlanc);
		chBoutons[0].setBackground(Data.LeBlanc);
		chBoutons[1].setBackground(Data.LeBlanc);
		chPanelWest.setBackground(Data.LeBlanc);
		chPanelEast.setBackground(Data.LeBlanc);
		pTitre.setBackground(Data.LeBlanc);

		chPanelWest.setForeground(Data.LeNoir);
		chPanelCentre.setForeground(Data.LeNoir);
		chPanelEast.setForeground(Data.LeNoir);
		pTitre.setForeground(Data.LeNoir);
	}

	/**
	 * Met le background en noir et le Foreground en blanc.
	 */
	public void getSombre() {
		chBoutons[0].setIcon(
				new ImageIcon(new ImageIcon("images/arrprev.png").getImage().getScaledInstance(70, 73, 50)));
		chBoutons[1].setIcon(
				new ImageIcon(new ImageIcon("images/arrnex.png").getImage().getScaledInstance(70, 73, 50)));

		setBackground(Data.LeNoir);
		chBoutons[1].setBackground(Data.LeNoir);
		chBoutons[0].setBackground(Data.LeNoir);
		chPanelWest.setBackground(Data.LeNoir);
		chPanelEast.setBackground(Data.LeNoir);
		pTitre.setBackground(Data.LeNoir);

		chPanelWest.setForeground(Data.LeBlanc);
		chPanelCentre.setForeground(Data.LeBlanc);
		chPanelEast.setForeground(Data.LeBlanc);
		pTitre.setForeground(Data.LeBlanc);
	}
}