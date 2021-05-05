/**
 * 
 */
package vue;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;

import modele.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Lance une fenetre de menu, qui va permettre la création la modification ou
 * l'affichage d'une frise.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 */
public class FenetreMenu extends JFrame implements ActionListener, Serializable {

	public static FenetreMenu menu;
	FenetreFrise fenetreFrise = null;

	public static File fichTes;
	int statut;
	public int IndiceCA = 2;
	public int IndiceOuvertureRapide = 4;

	public Date DateDebut = new Date();
	public Date DateFin = new Date();

	public JLabel chLblMsgBienvenue = new JLabel("Bienvenue sur Frise");
	public JLabel chLblCredit = new JLabel("Copyright © 2020");
	public JLabel chLblMsgCreation = new JLabel("Bienvenue sur le menu de création :");
	public JLabel chLblMsgAffichage = new JLabel("Bienvenue sur le menu d'affichage :");
	public JLabel chLblMsgModif = new JLabel("Bienvenue sur le menu de modification :");

	public JLabel chLblFic = new JLabel("Choisissez une Frise :");

	public JLabel chLblMsgNew = new JLabel("Vous êtes sur le point de créer une nouvelle Frise.");
	public JLabel chLblNewTitre = new JLabel("Donnez un titre :");
	public JLabel chLblNewDebut = new JLabel("Date de début :");
	public JLabel chLblNewFin = new JLabel("Date de fin :");

	public JLabel chLblChoix = new JLabel("Il faut choisir un projet.");
	public JLabel chLabelVide = new JLabel("La frise est vide");
	public JLabel chLabelFieldErr = new JLabel("Veuillez compléter tous les champs.");
	public JLabel chLabelExiErr = new JLabel("Ce projet existe dejà, choisissez un autre titre.");

	public JTextField chNewTitreField = new JTextField();

	public JButton btnFra = new JButton();
	public JButton btnEng = new JButton();

	public JButton btnDateDebut = new JButton();
	public JButton btnDateFin = new JButton();

	public JButton btnCreation = new JButton("Création");
	public JButton btnAffichage = new JButton("Affichage");
	public JButton btnNouveau = new JButton("Nouveau");
	public JButton btnModifier = new JButton("Modifier");
	public JButton btnOpenC = new JButton("Ouvrir");
	public JButton btnOpenA = new JButton("Ouvrir");
	public JButton btnBack = new JButton("Retour");
	public JButton btnCreate = new JButton("Créer");

	JComboBox comboFichier = new JComboBox();

	String jour[] = new String[32];
	String mois[] = new String[13];
	String annee[] = new String[2051];

	JComboBox comboJourDebut = new JComboBox();
	JComboBox comboJourFin = new JComboBox();
	JComboBox comboMoisDebut = new JComboBox();
	JComboBox comboMoisFin = new JComboBox();
	JComboBox comboAnneeDebut = new JComboBox();
	JComboBox comboAnneeFin = new JComboBox();

	Image img = Toolkit.getDefaultToolkit().getImage("images" + File.separator + "sky.png");

	private JPanel chMenuGeneral = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this);
		}
	};

	Date chTempdate = new Date();
	Date chAujourdhui = new Date();
	BoutonDate chBoutemp = new BoutonDate(chAujourdhui);

	/**
	 * Métode main, donne l'argument en tant que nom de fenêtre et appel la fenêtre.
	 * 
	 * @param args Tableau d'argument.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					menu = new FenetreMenu("Frise - Menu Général");
					menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Instancie, et met en forme toutes les variables nécessaires au menu.
	 * 
	 * @param parTitre Titre de la fenetre.
	 */
	public FenetreMenu(String parTitre) {
		super(parTitre);

		GridBagConstraints contrainte = new GridBagConstraints();
		setLayout(new GridBagLayout());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);

		chMenuGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(chMenuGeneral);
		chMenuGeneral.setLayout(null);

		comboJourDebut.addItem("0" + 1);
		for (int i = 1; i <= 9; i++) {
			jour[i] = "0" + Integer.toString(i);
			if (!jour[i].equals("")) {
				comboJourFin.addItem(jour[i]);
			}
		}
		for (int i = 10; i <= 31; i++) {
			jour[i] = Integer.toString(i);
			if (!jour[i].equals("")) {	
				comboJourFin.addItem(jour[i]);
			}
		}
		for (int i = 1; i <= 9; i++) {
			mois[i] = "0" + Integer.toString(i);
			if (!mois[i].equals("")) {
				comboMoisDebut.addItem(mois[i]);
				comboMoisFin.addItem(mois[i]);
			}
		}
		for (int i = 10; i <= 12; i++) {
			mois[i] = Integer.toString(i);
			if (!mois[i].equals("")) {
				comboMoisDebut.addItem(mois[i]);
				comboMoisFin.addItem(mois[i]);
			}
		}
		for (int i = 1583; i <= 2050; i++) {
			annee[i] = Integer.toString(i);
			if (!annee[i].equals("")) {
				comboAnneeDebut.addItem(annee[i]);
				comboAnneeFin.addItem(annee[i]);
			}
		}

		// Mnémonique
		// Mnémonique

		// Esthétique
		chLblMsgBienvenue.setFont(Data.fontTitre);
        btnCreation.setFont(Data.fontText);
        btnAffichage.setFont(Data.fontText);

        chLblMsgCreation.setFont(Data.fontTitre);
        btnNouveau.setFont(Data.fontText);
        btnModifier.setFont(Data.fontText);

        chLblMsgModif.setFont(Data.fontTitre);
        chLblMsgAffichage.setFont(Data.fontTitre);
        chLblFic.setFont(Data.fontText);
        comboFichier.setFont(Data.fontText);

        btnOpenC.setFont(Data.fontText);
        btnOpenA.setFont(Data.fontText);
        btnBack.setFont(Data.fontText);
        chLblCredit.setFont(new Font("Courier New", Font.BOLD, 11));

        chLblMsgNew.setFont(Data.fontText);
        chLblNewTitre.setFont(Data.fontText);
        chLblNewDebut.setFont(Data.fontText);
        chLblNewFin.setFont(Data.fontText);

        chLabelFieldErr.setFont(Data.fontText);
        chLabelExiErr.setFont(Data.fontText);


		btnFra.setIcon(
				new ImageIcon(new ImageIcon("images/drapeau_francais.jpg").getImage().getScaledInstance(30, 18, 50)));
		btnEng.setIcon(
				new ImageIcon(new ImageIcon("images/drapeau_anglais.gif").getImage().getScaledInstance(30, 18, 50)));

		btnDateDebut
				.setIcon(new ImageIcon(new ImageIcon("images/calendar.png").getImage().getScaledInstance(30, 18, 50)));
		btnDateFin.setIcon(new ImageIcon(new ImageIcon("images/calendar.png").getImage().getScaledInstance(30, 18, 50)));

		btnCreation.setOpaque(false);
		btnAffichage.setOpaque(false);
		btnNouveau.setOpaque(false);
		btnModifier.setOpaque(false);
		btnOpenC.setOpaque(false);
		btnOpenA.setOpaque(false);
		btnBack.setOpaque(false);
		btnCreate.setOpaque(false);
		btnDateDebut.setOpaque(false);
		btnDateFin.setOpaque(false);

		chLblMsgBienvenue.setForeground(Data.LeBlanc);
		chLblMsgCreation.setForeground(Data.LeBlanc);
		chLblMsgAffichage.setForeground(Data.LeBlanc);
		chLblMsgModif.setForeground(Data.LeBlanc);
		chLblFic.setForeground(Data.LeBlanc);
		chLblMsgNew.setForeground(Data.LeBlanc);
		chLblNewTitre.setForeground(Data.LeBlanc);
		chLblNewDebut.setForeground(Data.LeBlanc);
		chLblNewFin.setForeground(Data.LeBlanc);
		btnCreation.setForeground(Data.LeBlanc);
		btnAffichage.setForeground(Data.LeBlanc);
		btnNouveau.setForeground(Data.LeBlanc);
		btnModifier.setForeground(Data.LeBlanc);
		btnOpenC.setForeground(Data.LeBlanc);
		btnOpenA.setForeground(Data.LeBlanc);
		btnBack.setForeground(Data.LeBlanc);
		btnCreate.setForeground(Data.LeBlanc);
		chLabelFieldErr.setForeground(Data.LeRouge);
        chLabelExiErr.setForeground(Data.LeRouge);
        
		btnCreation.setBackground(Data.CouleurBoutons);
		btnAffichage.setBackground(Data.CouleurBoutons);
		btnNouveau.setBackground(Data.CouleurBoutons);
		btnModifier.setBackground(Data.CouleurBoutons);
		btnOpenC.setBackground(Data.CouleurBoutons);
		btnOpenA.setBackground(Data.CouleurBoutons);
		btnBack.setBackground(Data.CouleurBoutons);
		btnCreate.setBackground(Data.CouleurBoutons);
		btnDateDebut.setBackground(Data.CouleurBoutons);
		btnDateFin.setBackground(Data.CouleurBoutons);
		comboFichier.setBackground(Data.LeBlanc);
		comboJourDebut.setBackground(Data.LeBlanc);
		comboJourFin.setBackground(Data.LeBlanc);
		comboMoisDebut.setBackground(Data.LeBlanc);
		comboMoisFin.setBackground(Data.LeBlanc);
		comboAnneeDebut.setBackground(Data.LeBlanc);
		comboAnneeFin.setBackground(Data.LeBlanc);
		// Esthétique

		// add
		chMenuGeneral.add(btnFra);
		chMenuGeneral.add(btnEng);

		chMenuGeneral.add(chLblMsgBienvenue);
		chMenuGeneral.add(btnCreation);
		chMenuGeneral.add(btnAffichage);

		chMenuGeneral.add(chLblMsgCreation);
		chMenuGeneral.add(btnNouveau);
		chMenuGeneral.add(btnModifier);

		chMenuGeneral.add(chLblMsgModif);
		chMenuGeneral.add(chLblMsgAffichage);
		chMenuGeneral.add(chLblFic);
		chMenuGeneral.add(comboFichier);

		chMenuGeneral.add(btnOpenC);
		chMenuGeneral.add(btnOpenA);
		chMenuGeneral.add(btnBack);
		chMenuGeneral.add(chLblCredit);

		chMenuGeneral.add(chLblMsgNew);
		chMenuGeneral.add(chLblNewTitre);
		chMenuGeneral.add(chNewTitreField);
		chMenuGeneral.add(chLblNewDebut);
		chMenuGeneral.add(comboJourDebut);
		chMenuGeneral.add(comboMoisDebut);
		chMenuGeneral.add(comboAnneeDebut);
		chMenuGeneral.add(chLblNewFin);
		chMenuGeneral.add(comboJourFin);
		chMenuGeneral.add(comboMoisFin);
		chMenuGeneral.add(comboAnneeFin);
		chMenuGeneral.add(btnCreate);

		chMenuGeneral.add(btnDateDebut);
		chMenuGeneral.add(btnDateFin);

		chMenuGeneral.add(chLabelVide);
		chMenuGeneral.add(chLblChoix);
		chMenuGeneral.add(chLabelFieldErr);
		chMenuGeneral.add(chLabelExiErr);
		// add

		// setBounds
		btnFra.setBounds(5, 5, 30, 20);
		btnEng.setBounds(45, 5, 30, 20);

		chLblMsgBienvenue.setBounds(120, 40, 250, 30);
		btnCreation.setBounds(165, 100, 140, 30);
		btnAffichage.setBounds(165, 150, 140, 30);

		chLblMsgCreation.setBounds(35, 40, 450, 30);
		btnNouveau.setBounds(165, 100, 140, 30);
		btnModifier.setBounds(165, 150, 140, 30);

		chLblMsgAffichage.setBounds(35, 40, 450, 30);
		chLblMsgModif.setBounds(10, 40, 480, 30);
		chLblFic.setBounds(140, 100, 200, 30);
		comboFichier.setBounds(165, 150, 140, 30);

		btnOpenC.setBounds(165, 200, 140, 30);
		btnOpenA.setBounds(100, 200, 140, 30);
		btnBack.setBounds(250, 200, 140, 30);
		chLblCredit.setBounds(183, 320, 150, 15);

		chLblMsgNew.setBounds(15, 50, 500, 30);
		chLblNewTitre.setBounds(70, 100, 200, 30);
		chNewTitreField.setBounds(250, 100, 145, 30);

		chLblNewDebut.setBounds(70, 150, 140, 30);
		comboJourDebut.setBounds(250, 150, 40, 30);
		comboMoisDebut.setBounds(295, 150, 40, 30);
		comboAnneeDebut.setBounds(340, 150, 55, 30);

		chLblNewFin.setBounds(70, 200, 140, 30);
		comboJourFin.setBounds(250, 200, 40, 30);
		comboMoisFin.setBounds(295, 200, 40, 30);
		comboAnneeFin.setBounds(340, 200, 55, 30);

		btnDateDebut.setBounds(210, 150, 30, 30);
		btnDateFin.setBounds(210, 200, 30, 30);

		chLabelFieldErr.setBounds(80, 250, 500, 30);
		chLabelExiErr.setBounds(30, 250, 500, 30);
		chLblChoix.setBounds(30, 250, 500, 30);
		btnCreate.setBounds(165, 290, 140, 30);
		chLabelVide.setBounds(165, 300, 140, 30);
		// setBounds

		// setVisible
		btnFra.setVisible(true);
		btnEng.setVisible(true);
		// setVisible MenuGeneral
		chLblMsgBienvenue.setVisible(true);
		// btnCreation.setVisible(true);
		btnAffichage.setVisible(true);
		chLblCredit.setVisible(true);
		// setVisible MenuGeneral

		// setVisible Creation
		chLblMsgCreation.setVisible(false);
		btnNouveau.setVisible(false);
		btnModifier.setVisible(false);
		// setVisible Creation

		// setVisible Affichage
		chLblMsgAffichage.setVisible(false);
		chLblMsgModif.setVisible(false);
		chLblFic.setVisible(false);
		comboFichier.setVisible(false);
		btnOpenC.setVisible(false);
		btnOpenA.setVisible(false);
		btnBack.setVisible(false);
		// setVisible Affichage

		// setVisible Nouveau
		chLblMsgNew.setVisible(false);
		chLblNewTitre.setVisible(false);
		chNewTitreField.setVisible(false);

		chLblNewDebut.setVisible(false);
		comboJourDebut.setVisible(false);
		comboMoisDebut.setVisible(false);
		comboAnneeDebut.setVisible(false);

		chLblNewFin.setVisible(false);
		comboJourFin.setVisible(false);
		comboMoisFin.setVisible(false);
		comboAnneeFin.setVisible(false);

		btnCreate.setVisible(false);
		chLabelFieldErr.setVisible(false);
		chLabelExiErr.setVisible(false);
		chLblChoix.setVisible(false);
		chLabelVide.setVisible(false);

		btnDateDebut.setVisible(false);
		btnDateFin.setVisible(false);

		// setVisible Nouveau
		// setVisible

		// ActionListener
		btnFra.addActionListener(this);
		btnEng.addActionListener(this);
		btnCreation.addActionListener(this);
		btnAffichage.addActionListener(this);
		btnOpenC.addActionListener(this);
		btnOpenA.addActionListener(this);
		btnBack.addActionListener(this);
		btnNouveau.addActionListener(this);
		btnModifier.addActionListener(this);
		btnCreate.addActionListener(this);
		btnDateDebut.addActionListener(this);
		btnDateFin.addActionListener(this);
		// ActionListener
	}

	/**
	 * Gère les différentes options lié aux boutons de la fenetre.
	 * 
	 * @param parEvt Evènement qui déclenche l'actionPerformed.
	 */
	@Override
	public void actionPerformed(ActionEvent parEvt) {
		if (parEvt.getSource() == btnFra)
			Fr();
		if (parEvt.getSource() == btnEng)
			Eng();
		if (parEvt.getSource() == btnCreation)
			Creation();
		if (parEvt.getSource() == btnNouveau)
			Nouveau();
		if (parEvt.getSource() == btnDateDebut)
			setDateDebut();
		if (parEvt.getSource() == btnDateFin)
			setDateFin();
		if (parEvt.getSource() == btnCreate)
			Create();
		if (parEvt.getSource() == btnModifier)
			Modifier();
		if (parEvt.getSource() == btnAffichage)
			Affichage();
		if (parEvt.getSource() == btnOpenC) {
			menu.IndiceCA = 0;
			menu.IndiceOuvertureRapide = 4;
		}
		if (parEvt.getSource() == btnOpenA) {
			menu.IndiceCA = 1;
			menu.IndiceOuvertureRapide = 4;
		}
		if (parEvt.getSource() == btnOpenC || parEvt.getSource() == btnOpenA) {
			menu.statut = 2;
			Open();
		}
		if (parEvt.getSource() == btnBack)
			setBack();
	}

	/**
	 * Setteur, il met la langue en Français.
	 */
	public void Fr() {
		chLblMsgBienvenue.setText("Bienvenue sur FRISE");
		chLblMsgCreation.setText("Bienvenue sur le menu de création :");
		chLblMsgAffichage.setText("Bienvenue sur le menu d'affichage :");
		chLblMsgModif.setText("Bienvenue sur le menu de modification :");
		chLblFic.setText("Choisissez une frise :");
		chLblMsgNew.setText("Vous êtes sur le point de créer une nouvelle Frise.");
		chLblNewTitre.setText("Donnez un titre :");
		chLblNewDebut.setText("Date de début :");
		chLblNewFin.setText("Date de fin :");

		chLblChoix.setText("Il faut choisir un projet.");
		chLabelVide.setText("La frise est vide.");
		chLabelFieldErr.setText("Veuillez compléter tous les champs.");
		chLabelExiErr.setText("Ce projet existe dejà, choisissez un autre titre.");

		btnOpenC.setText("Ouvrir");
		btnOpenA.setText("Ouvrir");
		btnCreate.setText("Créer");
		btnCreation.setText("Création");
		btnAffichage.setText("Affichage");
		btnNouveau.setText("Nouveau");
		btnModifier.setText("Modifier");
		btnBack.setText("Retour");

		// setBounds
		chLblMsgBienvenue.setBounds(120, 40, 250, 30);
		chLblMsgCreation.setBounds(35, 40, 450, 30);
		chLblMsgAffichage.setBounds(35, 40, 450, 30);
		chLblFic.setBounds(140, 100, 200, 30);
		chLblMsgNew.setBounds(15, 50, 500, 30);
		chLabelFieldErr.setBounds(80, 250, 500, 30);
		chLabelExiErr.setBounds(30, 250, 500, 30);
		chLblChoix.setBounds(30, 250, 500, 30);
		chLabelVide.setBounds(165, 300, 140, 30);
		// setBounds
	}

	/**
	 * Setteur, il met la langue en Anglais.
	 */
	public void Eng() {
		chLblMsgBienvenue.setText("Welcome to FRISE");
		chLblMsgCreation.setText("Welcome to the creation menu :");
		chLblMsgAffichage.setText("Welcome to the display menu :");
		chLblMsgModif.setText("Welcome to the modification menu :");
		chLblFic.setText("Choose a Timeline :");
		chLblMsgNew.setText("You are about to create a new Timeline");
		chLblNewTitre.setText("Give a title :");
		chLblNewDebut.setText("Start date :");
		chLblNewFin.setText("End date:");

		chLblChoix.setText("You have to choose a project");
		chLabelVide.setText("Empty Timeline.");
		chLabelFieldErr.setText("Complete all the fields.");
		chLabelExiErr.setText("This project already exist, choose another name.");

		btnOpenC.setText("Open");
		btnOpenA.setText("Open");
		btnCreate.setText("Create");
		btnCreation.setText("Creation");
		btnAffichage.setText("Display");
		btnNouveau.setText("New");
		btnModifier.setText("Modify");
		btnBack.setText("Back");

		// setBounds
		chLblMsgBienvenue.setBounds(130, 40, 250, 30);
		chLblMsgCreation.setBounds(100, 40, 450, 30);
		chLblMsgAffichage.setBounds(100, 40, 450, 30);
		chLblFic.setBounds(160, 100, 200, 30);
		chLblMsgNew.setBounds(60, 50, 500, 30);
		chLabelFieldErr.setBounds(130, 250, 500, 30);
		chLabelExiErr.setBounds(30, 250, 500, 30);
		chLblChoix.setBounds(30, 250, 500, 30);
		chLabelVide.setBounds(165, 300, 140, 30);
		// setBounds
	}

	/**
	 * Appelle la méthode setCreation() et met IndiceCa à 0.
	 */
	public void Creation() {
		menu.IndiceCA = 0;
		setCreation();
	}

	/**
	 * Setteur, il affiche le menu Création.
	 */
	public void setCreation() {
		btnBack.setBounds(165, 200, 140, 30);

		// setVisible
		// setVisible MenuGeneral
		chLblMsgBienvenue.setVisible(false);
		btnCreation.setVisible(false);
		btnAffichage.setVisible(false);
		// setVisible MenuGeneral

		// setVisible Creation
		chLblMsgCreation.setVisible(true);
		btnNouveau.setVisible(true);
		btnModifier.setVisible(true);
		btnBack.setVisible(true);
		// setVisible Creation

		// setVisible Nouveau
		chLblMsgNew.setVisible(false);
		chLblNewTitre.setVisible(false);
		chNewTitreField.setVisible(false);

		chLblNewDebut.setVisible(false);
		comboJourDebut.setVisible(false);
		comboMoisDebut.setVisible(false);
		comboAnneeDebut.setVisible(false);

		chLblNewFin.setVisible(false);
		comboJourFin.setVisible(false);
		comboMoisFin.setVisible(false);
		comboAnneeFin.setVisible(false);

		btnCreate.setVisible(false);
		chLabelFieldErr.setVisible(false);
		chLabelExiErr.setVisible(false);
		// setVisible Nouveau
		// setVisible
	}

	/**
	 * Appelle la méthode setNouveau() et met le statut à 1.
	 */
	public void Nouveau() {
		menu.statut = 1;
		setNouveau();
	}

	/**
	 * Setteur, il affiche le menu interactif Nouveau.
	 */
	public void setNouveau() {
		// setVisible
		// setVisible Creationedr
		chLblMsgCreation.setVisible(false);
		btnNouveau.setVisible(false);
		btnModifier.setVisible(false);
		btnBack.setVisible(false);
		// setVisible Creation

		// setVisible Nouveau
		chLblMsgNew.setVisible(true);
		chLblNewTitre.setVisible(true);
		chNewTitreField.setVisible(true);
		chLblNewDebut.setVisible(true);
		btnDateDebut.setVisible(true);
		// setVisible Nouveau
		// setVisible
	}

	/**
	 * Setteur, il affiche les boutons correspondants à la date de début.
	 */
	public void setDateDebut() {
		btnDateFin.setVisible(true);
		chLblNewFin.setVisible(true);
		comboJourDebut.setVisible(true);
		comboMoisDebut.setVisible(true);
		comboAnneeDebut.setVisible(true);
	}

	/**
	 * Setteur, il affiche les boutons correspondants à la date de fin.
	 */
	public void setDateFin() {
		comboJourFin.setVisible(true);
		comboMoisFin.setVisible(true);
		comboAnneeFin.setVisible(true);
		btnCreate.setVisible(true);
	}

	/**
	 * Crée un dossier qui comportera le fichier et le dossier des images. La
	 * méthode récupère les items des différentes comboBox du menu Nouveau et créer
	 * deux dates à partir de celles-ci.
	 */
	public void Create() {
		menu.statut = 1;
		menu.IndiceCA = 0;
		menu.IndiceOuvertureRapide = 4;

		FileReader reader = null;

		String TitreProjet = chNewTitreField.getText().trim();

		String JourDebutStr = comboJourDebut.getSelectedItem().toString();
		String MoisDebutStr = comboMoisDebut.getSelectedItem().toString();
		String AnneeDebutStr = comboAnneeDebut.getSelectedItem().toString();

		int JourDebut = Integer.parseInt(JourDebutStr);
		int MoisDebut = Integer.parseInt(MoisDebutStr);
		int AnneeDebut = Integer.parseInt(AnneeDebutStr);

		String JourFinStr = comboJourFin.getSelectedItem().toString();
		String MoisFinStr = comboMoisFin.getSelectedItem().toString();
		String AnneeFinStr = comboAnneeFin.getSelectedItem().toString();

		int JourFin = Integer.parseInt(JourFinStr);
		int MoisFin = Integer.parseInt(MoisFinStr);
		int AnneeFin = Integer.parseInt(AnneeFinStr);

		menu.DateDebut = new Date(JourDebut, MoisDebut, AnneeDebut);
		menu.DateFin = new Date(JourFin, MoisFin, AnneeFin);
		try {

			if ("".equals(String.valueOf(TitreProjet).trim()) || "".equals(String.valueOf(JourDebutStr).trim())
					|| "".equals(String.valueOf(MoisDebutStr).trim()) || "".equals(String.valueOf(AnneeDebutStr).trim())
					|| "".equals(String.valueOf(JourFinStr).trim()) || "".equals(String.valueOf(MoisFinStr).trim())
					|| "".equals(String.valueOf(AnneeFinStr).trim())) {
				chLabelExiErr.setVisible(false);
				chLabelFieldErr.setVisible(false);
				chLabelFieldErr.setVisible(true);
				return;
			}

			reader = new FileReader("fich.Titres" + File.separator + "Titres.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(reader);

		String line;
		String Titres = "";

		try {
			while ((line = bufferedReader.readLine()) != null) {

				Titres += line + "\r\n";
				String unpd[] = line.split(";");
				for (int i = 0; i <= 6; i++)
					unpd[i] = unpd[i].trim();

				if (TitreProjet.equals(unpd[0])) {
					chLabelExiErr.setVisible(false);
					chLabelExiErr.setVisible(true);
					return;
				}
			}

			Titres += TitreProjet + ";" + JourDebut + ";" + MoisDebut + ";" + AnneeDebut + ";" + JourFin + ";" + MoisFin
					+ ";" + AnneeFin;

			File files = new File(TitreProjet + "\\ images_" + TitreProjet);

			if (!files.exists()) {
				if (files.mkdirs())
					System.out.println("Multiple directories are created!");
				else
					System.out.println("Failed to create multiple directories!");
			}

			FileWriter writer = new FileWriter("fich.Titres" + File.separator + "Titres.txt");
			fichTes = new File(TitreProjet + File.separator + TitreProjet + ".ser");
			writer.write(Titres);
			writer.close();
			reader.close();

			chLabelFieldErr.setVisible(false);
			chLabelExiErr.setVisible(false);
			fenetreFrise = new FenetreFrise(menu, "Frise - Nouvelle Frise");
			fenetreFrise.setVisible(true);
			menu.dispose();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		setDateCreate();
	}

	/**
	 * Setteur, il efface tout les menus pour que l'affichage depuis l'application
	 * soit sans problème.
	 */
	public void setDateCreate() {

		// setVisible
		btnFra.setVisible(false);
		btnEng.setVisible(false);

		// setVisible Affichage
		chLblMsgAffichage.setVisible(false);
		chLblMsgModif.setVisible(false);
		chLblFic.setVisible(false);
		comboFichier.setVisible(false);
		btnOpenC.setVisible(false);
		btnOpenA.setVisible(false);
		btnBack.setVisible(false);
		// setVisible Affichage

		// setVisible Nouveau
		chLblMsgNew.setVisible(false);
		chLblNewTitre.setVisible(false);
		chNewTitreField.setVisible(false);

		chLblNewDebut.setVisible(false);
		comboJourDebut.setVisible(false);
		comboMoisDebut.setVisible(false);
		comboAnneeDebut.setVisible(false);

		chLblNewFin.setVisible(false);
		comboJourFin.setVisible(false);
		comboMoisFin.setVisible(false);
		comboAnneeFin.setVisible(false);

		btnCreate.setVisible(false);
		chLabelFieldErr.setVisible(false);
		chLabelExiErr.setVisible(false);
		chLblChoix.setVisible(false);
		chLabelVide.setVisible(false);

		btnDateDebut.setVisible(false);
		btnDateFin.setVisible(false);

		// setVisible Nouveau
		// setVisible
	}

	/**
	 * Appelle la méthode setModifier et rempli la comboBox pour que l'utilisateur
	 * puisse choisir la frise à ouvrir.
	 */
	public void Modifier() {
		menu.statut = 2;

		setModifier();

		FileReader reader = null;
		try {
			reader = new FileReader("fich.Titres" + File.separator + "Titres.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(reader);

		String line;
		String Titres = "";

		try {
			while ((line = bufferedReader.readLine()) != null) {

				Titres += line + "\r\n";
				String unpd[] = line.split(";");
				for (int i = 0; i <= 6; i++)
					unpd[i] = unpd[i].trim();

				comboFichier.addItem(unpd[0]);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Setteur, il affiche le menu Modifier.
	 */
	public void setModifier() {
		btnOpenC.setBounds(165, 200, 140, 30);

		// setVisible
		// setVisible Creation
		chLblMsgCreation.setVisible(false);
		btnNouveau.setVisible(false);
		btnModifier.setVisible(false);
		btnBack.setVisible(false);
		// setVisible Creation

		// setVisible Affichage
		chLblMsgModif.setVisible(true);
		chLblFic.setVisible(true);
		comboFichier.setVisible(true);
		btnOpenC.setVisible(true);
		// setVisible Affichage
		// setVisible
	}

	/**
	 * Appelle la méthode setAffichage et rempli la comboBox pour que l'utilisateur
	 * puisse choisir la frise à ouvrir.
	 */
	public void Affichage() {
		menu.IndiceCA = 1;

		setAffichage();

		FileReader reader = null;
		try {
			reader = new FileReader("fich.Titres" + File.separator + "Titres.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(reader);

		String line;
		String Titres = "";

		try {
			while ((line = bufferedReader.readLine()) != null) {

				Titres += line + "\r\n";
				String unpd[] = line.split(";");
				for (int i = 0; i <= 6; i++)
					unpd[i] = unpd[i].trim();

				comboFichier.addItem(unpd[0]);
			}

		} catch (IOException ex) {

			ex.printStackTrace();
		}
	}

	/**
	 * Setteur, il affiche le menu Affichage.
	 */
	public void setAffichage() {
		btnBack.setBounds(250, 200, 140, 30);

		// setVisible
		// setVisible MenuGeneral
		chLblMsgBienvenue.setVisible(false);
		btnCreation.setVisible(false);
		btnAffichage.setVisible(false);
		// setVisible MenuGeneral

		// setVisible Affichage
		chLblMsgAffichage.setVisible(true);
		chLblFic.setVisible(true);
		comboFichier.setVisible(true);
		btnOpenA.setVisible(true);
		btnBack.setVisible(true);
		// setVisible Affichage
		// setVisible

	}

	/**
	 * Permet l'ouverture du fichier séléctionné dans la JComboBox se trouvant dans
	 * le menu Création ou Affichage.
	 */
	public void Open() {
		try {
			boolean flag = true;

			String TitreProjet = comboFichier.getSelectedItem().toString();
			if ("".equals(String.valueOf(TitreProjet).trim())) {
				chLblChoix.setVisible(true);
				return;
			}

			FileReader reader = new FileReader("fich.Titres" + File.separator + "Titres.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				String unpd[] = line.split(";");
				for (int i = 0; i <= 6; i++)
					unpd[i] = unpd[i].trim();

				if (unpd[0].equals(TitreProjet)) {
					menu.DateDebut = new Date(Integer.parseInt(unpd[1]), Integer.parseInt(unpd[2]),
							Integer.parseInt(unpd[3]));
					menu.DateFin = new Date(Integer.parseInt(unpd[4]), Integer.parseInt(unpd[5]),
							Integer.parseInt(unpd[6]));

					flag = false;
					break;
				}
			}
			reader.close();
			fichTes = new File(comboFichier.getSelectedItem().toString().trim() + File.separator
					+ comboFichier.getSelectedItem().toString().trim() + ".ser");

			FenetreFrise a = null;

			if (flag)
				chLabelVide.setVisible(true);
			else {
				a = new FenetreFrise(menu, "Frise - Modifier Frise");
				a.setVisible(true);
				menu.dispose();
			}
		} catch (java.lang.Exception ex) {
			// Si erreur du pgm Java
			ex.printStackTrace();
		} // catch Java

		setOpen();
	}

	/**
	 * Setteur, il efface tout les menus pour que l'affichage depuis l'application
	 * soit sans problème.
	 */
	public void setOpen() {

		// setVisible
		btnFra.setVisible(false);
		btnEng.setVisible(false);

		// setVisible Affichage
		chLblMsgAffichage.setVisible(false);
		chLblMsgModif.setVisible(false);
		chLblFic.setVisible(false);
		comboFichier.setVisible(false);
		btnOpenC.setVisible(false);
		btnOpenA.setVisible(false);
		btnBack.setVisible(false);
		// setVisible Affichage

		// setVisible Nouveau
		chLblMsgNew.setVisible(false);
		chLblNewTitre.setVisible(false);
		chNewTitreField.setVisible(false);

		chLblNewDebut.setVisible(false);
		comboJourDebut.setVisible(false);
		comboMoisDebut.setVisible(false);
		comboAnneeDebut.setVisible(false);

		chLblNewFin.setVisible(false);
		comboJourFin.setVisible(false);
		comboMoisFin.setVisible(false);
		comboAnneeFin.setVisible(false);

		btnCreate.setVisible(false);
		chLabelFieldErr.setVisible(false);
		chLabelExiErr.setVisible(false);
		chLblChoix.setVisible(false);
		chLabelVide.setVisible(false);

		btnDateDebut.setVisible(false);
		btnDateFin.setVisible(false);

		// setVisible Nouveau
		// setVisible
	}

	/**
	 * Affiche le premier menu de la fenetre.
	 */
	public void setBack() {
		comboFichier.removeAllItems();

		// setVisible
		// setVisible MenuGeneral
		chLblMsgBienvenue.setVisible(true);
		btnCreation.setVisible(true);
		btnAffichage.setVisible(true);
		// setVisible MenuGeneral

		// setVisible Creation
		chLblMsgCreation.setVisible(false);
		btnNouveau.setVisible(false);
		btnModifier.setVisible(false);
		btnOpenC.setVisible(false);
		btnOpenA.setVisible(false);
		btnBack.setVisible(false);
		// setVisible Creation

		// setVisible Affichage
		chLblMsgAffichage.setVisible(false);
		chLblMsgModif.setVisible(false);
		chLblFic.setVisible(false);
		comboFichier.setVisible(false);
		// setVisible Affichage

		// setVisible Nouveau
		chLblMsgNew.setVisible(false);
		chLblNewTitre.setVisible(false);
		chNewTitreField.setVisible(false);

		chLblNewDebut.setVisible(false);
		comboJourDebut.setVisible(false);
		comboMoisDebut.setVisible(false);
		comboAnneeDebut.setVisible(false);

		chLblNewFin.setVisible(false);
		comboJourFin.setVisible(false);
		comboMoisFin.setVisible(false);
		comboAnneeFin.setVisible(false);
		// setVisible Nouveau
		// setVisible
	}

	public String getTitreProjet() {
		if (menu.statut == 1)
			return menu.chNewTitreField.getText().trim();
		else
			return menu.comboFichier.getSelectedItem().toString();
	}

	/**
	 * Retourne un indice qui va déterminer l'affichage de l'application.
	 * 
	 * @return int L'indice qui va permettre d'afficher soit la partie Création soit
	 *         la partie Affichage.
	 */
	public int getIndice() {
		return menu.IndiceCA;
	}

	/**
	 * Retourne un indice qui va permettre l'ouverture de l'affichage dans le menu
	 * de l'application.
	 * 
	 * @return int L'indice qui va permettre d'afficher la Frise depuis l'appli.
	 */
	public int getIndiceV2() {
		menu.IndiceOuvertureRapide /= 2;
		return menu.IndiceOuvertureRapide;
	}

	/**
	 * Retourne la date de début de la Frise.
	 * 
	 * @return Date Date de début.
	 */
	public Date getDateDebut() {
		return menu.DateDebut;
	}

	/**
	 * Retourne la date de fin de la Frise.
	 * 
	 * @return Date Date de fin.
	 */
	public Date getDateFin() {
		return menu.DateFin;
	}
}