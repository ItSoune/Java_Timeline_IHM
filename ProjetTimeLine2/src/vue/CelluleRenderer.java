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

	FenetreMenu chMenu = new FenetreMenu("Frise - Menu G�n�ral");
	String chCurrentProject = chMenu.getTitreProjet();

	int couleurs = 0;

	/**
	 * M�thode CelluleRenderer, modifie l'apparence globale du renderer.
	 */
	public CelluleRenderer() {
		super();

		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);

		this.setBackground(Data.LeBlanc);
		this.setForeground(Data.LeNoir);
	}

	/**
	 * M�thode getTableCellRendererCoponent, ajoute des fonctionnalit� sp�ciales et
	 * voulu par l'user.
	 * 
	 * @param table          La JTable concern�e par le changement.
	 * @param valeur         Un Object qui r�cup�re une valeur qui est distribu�e
	 *                       par la suite.
	 * @param estSelectionne Un boolean qui va nous permettre de savoir quelle
	 *                       cellule est s�l�ctionn�e.
	 * @param aLeFocus       Un boolean qui va nous permettre de savoir quelle
	 *                       cellule a le focus.
	 * @param ligne          Un Int qui va nous indiquer quelle ligne est
	 *                       s�lectionn�e.
	 * @param colonne        Un Int qui va nous indiquer quelle colonne est
	 *                       s�lectionn�e.
	 * @return Component This avec toutes les modifications effectu�es.
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
	 * M�thode getClaire, permet la coloration du background en blanc.
	 */
	public void getClaire() {
		couleurs = 0;
		setBackground(Data.LeBlanc);
	}

	/**
	 * M�thode getSombre, permet la coloration du background en noir.
	 */
	public void getSombre() {
		couleurs = 1;
		setBackground(Data.LeNoir);
	}
}
