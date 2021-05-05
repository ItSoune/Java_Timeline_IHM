package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.io.File;
import java.io.Serializable;
import java.nio.file.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controleur.Controleur;
import modele.*;

/**
 * La classe PanelFormulaire sert à ajouter des événements depuis un formulaire.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */
public class PanelFormulaire extends JPanel implements ActionListener {

	Date chDate = new Date();
	Evt chEvtSaisi;

	public JPanel pnlFormulaire = new JPanel();

	PanelCalendrier pCalendrier;
	FenetreMenu chMenu = new FenetreMenu("Frise - Menu Général");

	Date dateJ1 = chMenu.getDateDebut();

	String chCurrentProject = chMenu.getTitreProjet();
	String filename = new String();

	String niv[] = new String[4];

	JButton btnAdd = new JButton();
	JButton btnImageChoose = new JButton("Charger une image");
	JButton btnDateEvt = new JButton();

	JLabel lblTitre = new JLabel("Titre :", JLabel.LEFT);
	JLabel lblDesc = new JLabel("Description :", JLabel.LEFT);
	JLabel lblNiv = new JLabel("Niveau d'importance :");
	JLabel lblDate = new JLabel("Date :");
	JLabel lblJour = new JLabel(dateJ1.toString());
	JLabel lblImage = new JLabel();
	JLabel lblErr = new JLabel();
	JLabel lblInst = new JLabel();

	static JTextField chZoneTitre = new JTextField();
	JTextField chZoneDateDeb = new JTextField();
	JTextField chZoneDateFin = new JTextField();
	JTextField chImagePath = new JTextField();

	JTextArea chZoneDesc = new JTextArea(8, 8);

	JComboBox chComboNiveau = new JComboBox();

	Border borderBout, borderImg;

	/**
	 * Instancie et place toutes les objets se trouvant sur le formulaire.
	 * 
	 * @param parPanelCalendrier Le calendrier utiliser dans le formulaire.
	 */
	public PanelFormulaire(PanelCalendrier parPanelCalendrier) {

		GridBagConstraints contrainte = new GridBagConstraints();
		setLayout(new GridBagLayout());

		pCalendrier = parPanelCalendrier;

		pnlFormulaire.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlFormulaire.setLayout(null);

		// Mnémonique
		lblTitre.setDisplayedMnemonic('T');
		lblDesc.setDisplayedMnemonic('D');
		lblNiv.setDisplayedMnemonic('N');
		lblImage.setDisplayedMnemonic('I');

		lblTitre.setLabelFor(chZoneTitre);
		lblDesc.setLabelFor(chZoneDesc);
		lblNiv.setLabelFor(chComboNiveau);
		lblImage.setLabelFor(chImagePath);
		// Mnémonique

		// Esthétique
		borderBout = BorderFactory.createLineBorder(Data.LeBleu, 1);
		borderImg = BorderFactory.createLineBorder(Data.LeBleu, 2);
		
		lblTitre.setFont(Data.fontTitre);
		lblDesc.setFont(Data.fontText);
		lblNiv.setFont(Data.fontText);
		lblDate.setFont(Data.fontText);
		lblImage.setFont(Data.fontText);

		btnAdd.setBackground(Data.LeBlanc);
		btnImageChoose.setBackground(Data.LeBlanc);
		btnDateEvt.setBackground(Data.LeBlanc);
		chComboNiveau.setBackground(Data.CouleurBoutons);

		btnAdd.setIcon(
				new ImageIcon(new ImageIcon("images/send_formulaire.png").getImage().getScaledInstance(25, 25, 50)));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBorderPainted(false);

		btnDateEvt
				.setIcon(new ImageIcon(new ImageIcon("images/calendar.png").getImage().getScaledInstance(50, 50, 50)));
		btnDateEvt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDateEvt.setBorderPainted(false);

		btnImageChoose.setIcon(
				new ImageIcon(new ImageIcon("images/pictures_folder.png").getImage().getScaledInstance(15, 15, 50)));
		btnImageChoose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		chComboNiveau.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		setBackground(Data.LeBlanc);
		// Esthétique

		// Combo
		for (int i = 0; i < 4; i += 1)
			niv[i] = Data.NIVIMPORTANCE[i];

		chComboNiveau = new JComboBox(niv);
		// Combo

		// bouton
		contrainte.gridx = 23;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(0, 35, 0, 35);
		this.add(btnAdd, contrainte);
		// bouton

		// titre
		contrainte.gridx = 1;
		contrainte.gridy = 0;
		contrainte.gridwidth = 2;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		this.add(lblTitre, contrainte);
		contrainte.gridx = 4;
		contrainte.gridy = 0;
		contrainte.gridwidth = 8;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		this.add(chZoneTitre, contrainte);
		chZoneTitre.setBorder(borderBout);
		// titre

		// Niveau
		contrainte.gridx = 1;
		contrainte.gridy = 1;
		contrainte.gridwidth = 2;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		this.add(lblNiv, contrainte);
		contrainte.gridx = 4;
		contrainte.gridy = 1;
		contrainte.gridwidth = 8;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(20, 10, 20, 10);
		this.add(chComboNiveau, contrainte);
		chComboNiveau.setSelectedItem(3);
		chComboNiveau.setBorder(borderBout);
		// Niveau

		// Date
		contrainte.gridx = 0;
		contrainte.gridy = 1;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 3;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 100, 100);
		this.add(pCalendrier, contrainte);
		pCalendrier.setVisible(false);
		contrainte.gridx = 1;
		contrainte.gridy = 2;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		this.add(lblDate, contrainte);
		contrainte.gridx = 2;
		contrainte.gridy = 2;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 30, 10, 30);
		this.add(btnDateEvt, contrainte);
		btnDateEvt.addActionListener(this);
		contrainte.gridx = 4;
		contrainte.gridy = 2;
		contrainte.gridwidth = 8;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(20, 10, 20, 10);
		this.add(lblJour, contrainte);
		contrainte.gridx = 0; contrainte.gridy = 3; contrainte.gridwidth = 1; contrainte.gridheight = 1; contrainte.fill = GridBagConstraints.BOTH; contrainte.insets = new Insets(50, 10, 10, 10);
        this.add(lblInst, contrainte);
		// Date

		// desc
		contrainte.gridx = 1;
		contrainte.gridy = 3;
		contrainte.gridwidth = 2;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		this.add(lblDesc, contrainte);
		contrainte.gridx = 4;
		contrainte.gridy = 3;
		contrainte.gridwidth = 20;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		chZoneDesc.setLineWrap(true);
		this.add(chZoneDesc, contrainte);
		chZoneDesc.setBorder(borderBout);
		// desc

		// image
		contrainte.gridx = 23;
		contrainte.gridy = 1;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 2;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		this.add(lblImage, contrainte);
		lblImage.setBorder(borderImg);
		contrainte.gridx = 22;
		contrainte.gridy = 2;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(30, 50, 30, 10);
		this.add(btnImageChoose, contrainte);
		btnImageChoose.addActionListener(this);
		// image

		// Erreur
		contrainte.gridx = 0;
		contrainte.gridy = 0;
		contrainte.gridwidth = 1;
		contrainte.gridheight = 1;
		contrainte.fill = GridBagConstraints.BOTH;
		contrainte.insets = new Insets(10, 10, 10, 10);
		this.add(lblErr, contrainte);
		lblErr.setVisible(false);
		// Erreur

		if (FenetreMenu.fichTes.length() == 0) {
			chDate = dateJ1;
			btnDateEvt.setVisible(false);
		}
	}

	/**
	 * La methode enregistreEcouteur met les boutons de PanelFormulaire en Ecoute du
	 * controleur.
	 * 
	 * @param parControleur Le controleur qui gère les clics.
	 */
	public void enregistreEcouteur(Controleur parControleur) {
		btnAdd.addActionListener(parControleur);
		btnAdd.setActionCommand("+");
	}

	/**
	 * La méthode renvoie l'événement saisi.
	 * 
	 * @return Evt L'événement saisi.
	 */
	public Evt getEvenement() {
		int Niveau = 0;
		String Titre = chZoneTitre.getText();
		String Description = chZoneDesc.getText();
		String NiveauStr = chComboNiveau.getSelectedItem().toString();
		for (int i = 0; i < Data.NIVIMPORTANCE.length; i++)
			if (NiveauStr.equals(Data.NIVIMPORTANCE[i]))
				Niveau = i;

		chEvtSaisi = new Evt(chDate, Titre, Description, Niveau);

		btnDateEvt.setVisible(true);

		return chEvtSaisi;
	}

	/**
	 * Méthode de remise à zéro, permet de remplir à nouveau le formulaire après une
	 * utilisation.
	 */
	public void RAZ() {
		chZoneTitre.setText("");
		chZoneDesc.setText("");
		chComboNiveau.setSelectedItem(3);
		chZoneTitre.requestFocus();
		lblImage.setIcon(null);
		lblJour.setText(dateJ1.toString());
	}

	/**
	 * La méthode getzoneTitre renvoie le contenu de la zone de titre de
	 * PanelFormulaire.
	 * 
	 * @return JtextField Le titre de l'évènement.
	 */
	public static JTextField getzoneTitre() {
		return chZoneTitre;
	}

	/**
	 * La méthode permet dans un premier temps d'afficher le calendrier pour choisir
	 * une date. Elle sert aussi à l'ajout d'une image depuis un JFileChooser,
	 * l'image sera en changer en png et le nom sera celui de l'évènement.
	 * 
	 * @param parEvt L'évènement qui déclenche l'action.
	 */
	public void actionPerformed(ActionEvent parEvt) {
		if (parEvt.getSource() == btnDateEvt) {
			pCalendrier.setVisible(true);
			lblInst.setText("Chosir dans l'ordre : année, mois, jour");
			lblInst.setFont(new Font("Courier New", Font.ITALIC, 15));
			lblInst.setForeground(Data.LeOrange);
			lblInst.setVisible(true);
		}
		if (parEvt.getSource() == btnImageChoose) {
			if (chZoneTitre.getText().equals("")) {
				lblErr.setText("Veuillez d'abord choisir un titre.");
				lblErr.setFont(new Font("Courier New", Font.ITALIC, 15));
				lblErr.setForeground(Data.LeRouge);
				lblErr.setVisible(true);
			} else {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);

				File f = chooser.getSelectedFile();
				filename = f.getAbsolutePath();

				chImagePath.setText(filename);
				ImageIcon icon = new ImageIcon(filename);
				lblImage.setIcon(new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(100, 125, 200)));

				Path temp;

				String source = filename;
				String destination = chCurrentProject + "\\ images_" + chCurrentProject + File.separator
						+ chZoneTitre.getText().trim() + ".png";

				try {
					temp = Files.move(Paths.get(source), Paths.get(destination));

					if (temp != null)
						System.out.println("File renamed and moved successfully");
					else
						System.out.println("Failed to move the file");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				btnImageChoose.remove(this);
				btnAdd.setVisible(true);
				btnAdd.setBorder(borderBout);
				lblErr.setVisible(false);
			}
		}
	}

	/**
	 * La méthode permet la récupération du chemin de l'image.
	 * 
	 * @return String Le chemin complet de l'image
	 */
	public String getFileName() {
		return filename;
	}

	/**
	 * La méthode permet de récupérer le titre de l'évènement.
	 * 
	 * @return String Le titre de l'évènement.
	 */
	public String getTitreEvt() {
		return chZoneTitre.getText();
	}

	/**
	 * Cette méthode vérifie que la date sélectionné par l'utilisateur et bien situé
	 * dans la frise, en comparant la date donné en paramètre avec la date de début
	 * et de fin de la frise.
	 * 
	 * @param parDate La date à comparer.
	 */
	public void setDate(Date parDate) {
		Date DateDebut = chMenu.getDateDebut();
		Date DateFin = chMenu.getDateFin();

		if (parDate.compareTo(DateDebut) >= 0 && parDate.compareTo(DateFin) <= 0) {
			lblErr.setVisible(false);
			chDate = parDate;
			lblJour.setText(parDate.toString());
			lblJour.setFont(new Font("Courier New", Font.BOLD, 15));
			lblJour.setForeground(Data.LeNoir);
			pCalendrier.setVisible(false);
			lblInst.setVisible(false);
		} else {
			lblErr.setText("La date choisie n'appartient pas à la frise");
			lblErr.setFont(new Font("Courier New", Font.ITALIC, 15));
			lblErr.setForeground(new Color(255, 0, 0));
			lblErr.setVisible(true);
		}
	}

	/**
	 * Méthode getClaire, permet la coloration du background en blanc.
	 */
	public void getClaire() {
		lblTitre.setForeground(Data.LeNoir);
		lblDesc.setForeground(Data.LeNoir);
		lblNiv.setForeground(Data.LeNoir);
		lblDate.setForeground(Data.LeNoir);
		lblJour.setForeground(Data.LeNoir);

		chZoneTitre.setForeground(Data.LeNoir);
		chZoneDesc.setForeground(Data.LeNoir);
		btnAdd.setForeground(Data.LeNoir);
		btnImageChoose.setForeground(Data.LeNoir);
		btnDateEvt.setForeground(Data.LeNoir);
		chComboNiveau.setForeground(Data.LeNoir);

		chZoneTitre.setBackground(Data.LeBlanc);
		chZoneDesc.setBackground(Data.LeBlanc);
		btnAdd.setBackground(Data.LeBlanc);
		btnImageChoose.setBackground(Data.LeBlanc);
		btnDateEvt.setBackground(Data.LeBlanc);
		chComboNiveau.setBackground(Data.LeBlanc);
		pCalendrier.getClaire();

		setBackground(Data.LeBlanc);
	}

	/**
	 * Méthode getSombre, permet la coloration du background en noir.
	 */
	public void getSombre() {
		lblTitre.setForeground(Data.LeBlanc);
		lblDesc.setForeground(Data.LeBlanc);
		lblNiv.setForeground(Data.LeBlanc);
		lblDate.setForeground(Data.LeBlanc);
		lblJour.setForeground(Data.LeBlanc);

		chZoneTitre.setForeground(Data.LeBlanc);
		chZoneDesc.setForeground(Data.LeBlanc);
		btnAdd.setForeground(Data.LeBlanc);
		btnImageChoose.setForeground(Data.LeBlanc);
		btnDateEvt.setForeground(Data.LeBlanc);
		chComboNiveau.setForeground(Data.LeBlanc);

		chZoneTitre.setBackground(Data.LeNoir);
		chZoneDesc.setBackground(Data.LeNoir);
		btnAdd.setBackground(Data.LeNoir);
		btnImageChoose.setBackground(Data.LeNoir);
		btnDateEvt.setBackground(Data.LeNoir);
		chComboNiveau.setBackground(Data.LeNoir);
		pCalendrier.getSombre();

		setBackground(Data.LeNoir);
	}
}