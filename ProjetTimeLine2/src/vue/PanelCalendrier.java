package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controleur.*;
import modele.*;

/**
 * La classe PanelCalendrier permet l'affichage et la gestion des dates des mois
 * de l'ann�e.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 *
 */
public class PanelCalendrier extends JPanel implements ActionListener, ChangeListener {

	CardLayout chGestionnaireDeCartes = new CardLayout(5, 5);

	PanelMois cal;
	Controleur Controleur;

	GregorianCalendar chAujourdhui = new GregorianCalendar();
	int chMoisCourant = chAujourdhui.get(Calendar.MONTH);
	int chAnneCourante = chAujourdhui.get(Calendar.YEAR);
	int chIndiceMOIS = chMoisCourant;

	JPanel chPanelCentre = new JPanel();
	JPanel chPanelSud = new JPanel();
	JPanel chPanelNord = new JPanel();

	JLabel chJours[] = new JLabel[7];

	private JComboBox mois = new JComboBox(Data.MOISV2);

	private JSpinner ann�e = new JSpinner(new SpinnerNumberModel(chAnneCourante, 0, 2100, 1));

	GridBagConstraints contrainte = new GridBagConstraints();
	ArrayList<PanelMois> LesPanelsMOis = new ArrayList<PanelMois>();

	/**
	 * Instancie le Calendrier, les boutons, jours, mois, ann�e.
	 */
	public PanelCalendrier() {

		this.setLayout(new BorderLayout(9, 9));

		// sud
		chPanelSud.setLayout(new BorderLayout(20, 20));
		chPanelSud.add(mois, BorderLayout.WEST);
		ann�e.addChangeListener(this);
		chPanelSud.add(ann�e, BorderLayout.EAST);
		chPanelSud.setBackground(new Color(255, 255, 255));
		mois.addActionListener(this);

		// Centre
		chPanelCentre.setLayout(chGestionnaireDeCartes);
		for (int j = 1; j <= Data.MOISV2.length; j++) {
			cal = new PanelMois(j, chAnneCourante);
			chPanelCentre.add(cal, Data.MOISV2[j - 1]);
			LesPanelsMOis.add(cal);
		}
		mois.setSelectedIndex(chMoisCourant);
		chPanelCentre.setBackground(new Color(255, 255, 255));
		chGestionnaireDeCartes.show(chPanelCentre, Data.MOISV2[chMoisCourant]);

		// Nord
		chPanelNord.setLayout(new GridBagLayout());
		for (int i = 0; i < Data.JOUR_ABR.length; i++) {
			chJours[i] = new JLabel(Data.JOUR_ABR[i], JLabel.CENTER);
			contrainte.insets = new Insets(1, 18, 3, 8);
			contrainte.gridx = i;
			contrainte.gridy = 0;
			chPanelNord.add(chJours[i], contrainte);
		}
		chPanelNord.setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));

		// Global
		add(chPanelSud, BorderLayout.SOUTH);
		add(chPanelCentre, BorderLayout.CENTER);
		add(chPanelNord, BorderLayout.NORTH);
	}

	/**
	 * La m�thode enregistreEcouteur met chaque PanelMois de PanelCalendrier en
	 * �coute du controleur.
	 * 
	 * @param parControleur : Le controleur qui g�re les clics.
	 */
	public void enregistreEcouteur(Controleur parControleur) {
		for (int i = 0; i < LesPanelsMOis.size(); i++) {
			LesPanelsMOis.get(i).enregistreEcouteur(parControleur);
		}
		Controleur = parControleur;
	}

	/**
	 * Cette m�thode permet de g�rer les Mois sur PanelCalendrier.
	 */
	public void actionPerformed(ActionEvent parEvt) {
		chGestionnaireDeCartes.show(chPanelCentre, Data.MOISV2[mois.getSelectedIndex()]);
	}

	/**
	 * Lorsque l'utilisateur change l'ann�e � l'aide de la JSpinner le mois change
	 * en cons�quant.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		for (int j = 1; j <= Data.MOISV2.length; j++) {
			cal = new PanelMois(j, (Integer) ann�e.getValue());
			chPanelCentre.add(cal, Data.MOISV2[j - 1]);
			LesPanelsMOis.add(cal);
			enregistreEcouteur(Controleur);
		}
		chGestionnaireDeCartes.show(chPanelCentre, Data.MOISV2[mois.getSelectedIndex()]);
	}

	/**
	 * M�thode qui met le fond en blanc et l'�criture en noir.
	 */
	public void getClaire() {
		chPanelCentre.setBackground(Data.LeBlanc);
		chPanelSud.setBackground(Data.LeBlanc);
		chPanelNord.setBackground(Data.LeBlanc);
		mois.setBackground(Data.LeBlanc);
		ann�e.setBackground(Data.LeBlanc);
		setBackground(Data.LeBlanc);

		chPanelCentre.setForeground(Data.LeNoir);
		chPanelSud.setForeground(Data.LeNoir);
		chPanelNord.setForeground(Data.LeNoir);
		mois.setForeground(Data.LeNoir);
		ann�e.setForeground(Data.LeNoir);
		for (int i = 0; i < Data.JOUR_ABR.length; i++) {
			chJours[i].setForeground(Data.LeNoir);
		}
	}

	/**
	 * M�thode qui met le fond en noir et l'�criture en blanc.
	 */
	public void getSombre() {
		chPanelCentre.setBackground(Data.LeNoir);
		chPanelSud.setBackground(Data.LeNoir);
		chPanelNord.setBackground(Data.LeNoir);
		mois.setBackground(Data.LeNoir);
		ann�e.setBackground(Data.LeNoir);
		setBackground(Data.LeNoir);

		chPanelCentre.setForeground(Data.LeBlanc);
		chPanelSud.setForeground(Data.LeBlanc);
		chPanelNord.setForeground(Data.LeBlanc);
		mois.setForeground(Data.LeBlanc);
		ann�e.setForeground(Data.LeBlanc);
		for (int i = 0; i < Data.JOUR_ABR.length; i++) {
			chJours[i].setForeground(Data.LeBlanc);
		}
	}
}
