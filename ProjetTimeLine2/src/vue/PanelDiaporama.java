package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.*;
import javax.swing.*;
import modele.*;
import modele.Date;

/**
 * La classe PanelMois rassemble les BoutonDate d'un certain mois.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 *
 */
public class PanelDiaporama extends JPanel {

	FenetreMenu chMenu = new FenetreMenu("Frise - Menu G�n�ral");
	String chCurrentProject = chMenu.getTitreProjet();
	File repertoire = new File(chCurrentProject + "\\ images_" + chCurrentProject);

	CardLayout gestionnaireDeCartes = new CardLayout(5, 5);

	String[] intitules_images = repertoire.list();
	String titre, Description;
	String htmlText;
	String intitule;

	Date date;

	JPanel panelImage = new JPanel();
	JLabel etiquette = new JLabel();
	JTextPane jTextPane = new JTextPane();

	/**
	 * Instancie et ajoute les Images, le titre, la date et la description
	 * correspondante � l'�v�nement s�lectionn�.
	 * 
	 * @param parEvt Evenement sur lequel la classe travail.
	 */
	public PanelDiaporama(Evt parEvt) {

		titre = parEvt.getTitre().trim();
		date = parEvt.getDate();
		Description = getTokens(parEvt.getDescription());

		htmlText = new String(
				"<html>" + "<h2 style=\"font-size:1.3em\">" + titre + "</h2>" + "<h4 style=\"font-size:1.1em\"><i>"
						+ date + "</i></h4>" + "<p style=\"font-size:1.1em\">" + Description + "</p></html>");

		jTextPane.setContentType("text/html");
		jTextPane.setText(htmlText);
		jTextPane.setOpaque(false);

		// image
		panelImage.setLayout(gestionnaireDeCartes);
		for (int j = 0; j < intitules_images.length; j++) {
			if (intitules_images[j].equals(titre + ".PNG") || intitules_images[j].equals(titre + ".png")) {
				intitule = intitules_images[j];
				etiquette = new JLabel(
						new ImageIcon(chCurrentProject + "\\ images_" + chCurrentProject + File.separator + intitule));
				panelImage.add(etiquette, intitule);
			}
		}
		gestionnaireDeCartes.show(panelImage, intitule);

		etiquette.setIcon(new ImageIcon(
				new ImageIcon(chCurrentProject + "\\ images_" + chCurrentProject + File.separator + intitule).getImage()
						.getScaledInstance(250, 250, 50)));

		setBackground(Data.LeBlanc);

		add(panelImage, BorderLayout.WEST);
		add(jTextPane, BorderLayout.EAST);
	}

	/**
	 * M�thode permettant de revenir � la ligne lors de l'affichage apr�s un nombre
	 * de mots donn�.
	 * 
	 * @param str String donn� en param�tre d'un StringTokenizer.
	 * @return String Le text html coup� tout les x mots.
	 */
	public String getTokens(String str) {
		String Description = new String();
		StringTokenizer tokenizer = new StringTokenizer(str);

		int c = 0;

		while (tokenizer.hasMoreElements()) {
			if (c == 12) {
				Description += "<br>";
				c = 0;
			}
			Description += tokenizer.nextToken() + " ";
			c++;
		}
		return Description;
	}
	 /**
	 * Met le fond en blanc et le devant en noir.
	 */
	 
	public void getClaire() {
        setBackground(Data.LeBlanc);
        panelImage.setBackground(Data.LeBlanc);
        jTextPane.setBackground(Data.LeBlanc);
        
        panelImage.setForeground(Data.LeNoir);
        jTextPane.setForeground(Data.LeNoir);
    }
	 
	/**
	 * Met le fond en noir et le devant en blanc.
	 */
    public void getSombre() {
        setBackground(Data.LeNoir);
        panelImage.setBackground(Data.LeNoir);
        jTextPane.setBackground(Data.LeNoir);

        panelImage.setForeground(Data.LeBlanc);
        jTextPane.setForeground(Data.LeBlanc);
    }
}
