/**
 * 
 */
package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import modele.Data;

import java.io.*;
import vue.*;

/**
 * Class CelluleRenderer, permet la modification du Renderer, comme l'user le
 * souhaite.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 */
public class CelluleRenderer extends JLabel implements TableCellRenderer {

	FenetreMenu chMenu = new FenetreMenu("Frise - Menu Général");
	String chCurrentProject = chMenu.getTitreProjet();

	int couleurs = 0;

	/**
	 * Méthode CelluleRenderer, modifie l'apparence globale du renderer.
	 */
	public CelluleRenderer() {
		super();

		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);

		this.setBackground(Data.LeBlanc);
		this.setForeground(Data.LeNoir);
	}

	/**
	 * Méthode getTableCellRendererCoponent, ajoute des fonctionnalité spéciales et
	 * voulu par l'user.
	 * 
	 * @param table          La JTable concernée par le changement.
	 * @param valeur         Un Object qui récupère une valeur qui est distribuée
	 *                       par la suite.
	 * @param estSelectionne Un boolean qui va nous permettre de savoir quelle
	 *                       cellule est séléctionnée.
	 * @param aLeFocus       Un boolean qui va nous permettre de savoir quelle
	 *                       cellule a le focus.
	 * @param ligne          Un Int qui va nous indiquer quelle ligne est
	 *                       sélectionnée.
	 * @param colonne        Un Int qui va nous indiquer quelle colonne est
	 *                       sélectionnée.
	 * @return Component This avec toutes les modifications effectuées.
	 */
	public Component getTableCellRendererComponent(JTable table, Object valeur, boolean estSelectionne,
			boolean aLeFocus, int ligne, int colonne) {
		String valeurS = (String) valeur;
		String Chemin = chCurrentProject + "\\ images_" + chCurrentProject + File.separator + valeurS + ".png";

		setIcon(new ImageIcon(new ImageIcon(Chemin).getImage().getScaledInstance(70, 70, 50)));

		int ligneCell = 0;
		int colonneCell = 0;

		if (aLeFocus) {
			this.setBackground(Data.LeBleu);
			this.setForeground(Data.LeBlanc);
		} else if (couleurs == 0) {
			this.setBackground(Data.LeBlanc);
			this.setForeground(Data.LeNoir);
		} else if (couleurs == 1) {
			this.setBackground(Data.LeNoir);
			this.setForeground(Data.LeBlanc);
		}

		table.isCellEditable(ligne, colonne);

		setFont(new Font("Calibri", Font.BOLD, 15));
		setToolTipText("Cliquer pour voir le chemin de l'image.");

		return this;
	}

	/**
	 * Méthode getClaire, permet la coloration du background en blanc.
	 */
	public void getClaire() {
		couleurs = 0;
		setBackground(Data.LeBlanc);
	}

	/**
	 * Méthode getSombre, permet la coloration du background en noir.
	 */
	public void getSombre() {
		couleurs = 1;
		setBackground(Data.LeNoir);
	}
}
