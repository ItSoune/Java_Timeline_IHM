/**
 * 
 */
package vue;

import java.awt.*;
import java.io.File;
import javax.swing.*;

import controleur.*;
import modele.*;

/**
 * Classe qui instancie la frise et tous ces composants.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 */
public class PanelTimeLine extends JPanel {

	FenetreMenu menu = new FenetreMenu("Frise - Menu Général");
	Frise frise;

	JTable tableFrise;
	JLabel pTitre = new JLabel();

	ModeleTable modele;
	CelluleRenderer cRenderer = new CelluleRenderer();
	Date DateDebut;
	Date DateFin;

	public static File fichTes;

	/**
	 * Méthode qui met en place la JTable.
	 * @param parFrise Frise utilisée dans le model
	 */
	public PanelTimeLine(Frise parFrise) {

		frise = parFrise;

		DateDebut = menu.getDateDebut();
		DateFin = menu.getDateFin();

		modele = new ModeleTable(DateDebut, DateFin, frise);

		tableFrise = new JTable(modele);
		tableFrise.getTableHeader().setBackground(Data.LeBleu);
		tableFrise.getTableHeader().setForeground(Data.LeBlanc);
		tableFrise.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 15));
		tableFrise.getTableHeader().setResizingAllowed(false);
		tableFrise.getTableHeader().setReorderingAllowed(false);
		tableFrise.setRowHeight(83);
		tableFrise.setDefaultRenderer(String.class, cRenderer);
		tableFrise.setAutoResizeMode(tableFrise.AUTO_RESIZE_OFF);
		tableFrise.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JScrollPane scrollPane = new JScrollPane(tableFrise, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(1400, 372));

		setBackground(Data.LeBlanc);
		this.add(scrollPane);
	}

	/**
	 * La methode enregistreEcouteur met à l'écoute du controleur.
	 * 
	 * @param parControleur Le controleur qui gère les clics.
	 */
	public void enregistreEcouteur(Controleur parControleur) {
		tableFrise.addMouseListener(parControleur);
	}

	/**
	 * Class setModeleTable, recharge l'affichage de la table.
	 * 
	 * @param parFrise Frise qui permet la mise à jour.
	 */
	public void setModeleTable(Frise parFrise) {
		modele = new ModeleTable(DateDebut, DateFin, parFrise);
		tableFrise.setModel(modele);
	}

	/**
	 * Méthode qui lorsque l'on va cliquer sur les boutons du panelEvt, va venir se
	 * déplacer la scrollbar de la frise sur le prochain évènement.
	 * 
	 * @param column La colonne de l'évènement.
	 */
	public void setVue(int column) {
		int b = 0, c = 0, i = 0, j = 0;

		while (c != column) {
			for (i = 0; i < tableFrise.getColumnCount(); i++) {
				for (j = 0; j < 4; j++) {
					if (tableFrise.getValueAt(j, i) != null) {
						if (c == column) {
							b = 0;
							b = i;
							c++;
							break;
						}
						c++;
					}
				}
			}
			break;
		}
		Rectangle cellRect = tableFrise.getCellRect(j, b, false);
		tableFrise.scrollRectToVisible(cellRect);
	}

	/**
	 * Méthode qui met le fond en blanc et le devant en noir.
	 */
	public void getClaire() {
		setBackground(Data.LeBlanc);
		cRenderer.getClaire();
	}

	/**
	 * Méthode qui met le fond en noir et le devant en blanc.
	 */
	public void getSombre() {
		setBackground(Data.LeNoir);
		cRenderer.getSombre();
	}
}
