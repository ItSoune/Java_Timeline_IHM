package modele;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.*;

/**
 * La classe CelluleRenderer sert a améliorer l'afficchage des cellules de la
 * JTable de PanelAffichage.
 * 
 * @author BOUCHDI Yassine
 *
 */
public class CelluleRenderer extends JLabel implements TableCellRenderer {
	/**
	 * Le constructeur de la classe CellureRenderer applique des normes sur les
	 * cellules.
	 */
	public CelluleRenderer() {
		super();
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		this.setBackground(new Color(255, 255, 255));
		this.setForeground(new Color(0, 0, 100));
		setFont(new Font("Calibri", Font.BOLD, 15));

	}

	/**
	 * La méthode getTableCellRendererComponent sert a appliquer des modifications
	 * sur une cellule specifiée par le numéro de sa ligne et de sa colonne qu'elle
	 * prend en paramètre ainsi que les boolean EstSelection et AleFocus pour des
	 * modifications plus dynamiques.
	 * 
	 */
	public Component getTableCellRendererComponent(JTable parTable, Object parValeur, boolean parEstSelectionne,
			boolean parALeFocus, int chLigne, int chColonne) {
		String valeurS = (String) parValeur;

		setText(valeurS);
		if (parALeFocus == true) {
			this.setBackground(new Color(0, 0, 100));
			this.setForeground(new Color(255, 255, 255));

		} else {
			this.setBackground(new Color(255, 255, 255));
			this.setForeground(new Color(0, 0, 100));
		}

		setToolTipText("Cliquez pour plus d'infos");

		return this;
	}
}
