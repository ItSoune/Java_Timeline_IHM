/**
 * 
 */
package vue;

import java.awt.*;
import javax.swing.*;
import modele.*;

/**
 * Class FenetreAgenda, lance l'application en elle même.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 */
public class FenetreFrise extends JFrame {

	static PanelCalendrier pCalendrier;
	static FenetreMenu fMenu;
	static FenetreFrise fFrise;

	public int couleurs = 0;

	JTextField zoneTitre;

	JMenuBar menuBar = new JMenuBar();
	JMenu menu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;

	/**
	 * Méthode FenetreFrise, permet le lancement du programme. Instancie aussi le
	 * menu de l'application.
	 * 
	 * @param parFMenu FenetreMenu donné en paramètre de PanelFrise
	 * @param parTitre Donner un titre à la fenêtre.
	 */
	public FenetreFrise(FenetreMenu parFMenu, String parTitre) {
		super(parTitre); // appel du constructeur de la classe mère JFrame

		fMenu = parFMenu;
		PanelFrise contentPane = new PanelFrise(parFMenu); // le panneau conteneur

		// La barre de menus
		for (int i = 0; i < Data.MENU.length; i++) {
			menu = new JMenu(Data.MENU[i]);
			menu.setMnemonic(Data.MENU[i].charAt(0));
			if (i == 0) {
				for (int j = 0; j < Data.MENUCREATION.length; j++) {
					menuItem = new JMenuItem(Data.MENUCREATION[j]);
					menuItem.setIcon(new ImageIcon(
							new ImageIcon(Data.ICONCREATION[j]).getImage().getScaledInstance(15, 15, 50)));
					menuItem.addActionListener(contentPane);
					menuItem.setMnemonic(Data.MENUCREATION[j].charAt(0));
					menuItem.setActionCommand(Data.MENUCREATION[j]);
					menu.add(menuItem);
				}
			} else if (i == 1) {
				for (int k = 0; k < Data.MENUAFFICHAGE.length; k++) {
					menuItem = new JMenuItem(Data.MENUAFFICHAGE[k]);
					menuItem.setIcon(new ImageIcon(
							new ImageIcon(Data.ICONAFFICHAGE[k]).getImage().getScaledInstance(15, 15, 50)));
					menuItem.addActionListener(contentPane);
					menuItem.setMnemonic(Data.MENUAFFICHAGE[k].charAt(9));
					menuItem.setActionCommand(Data.MENUAFFICHAGE[k]);
					menu.add(menuItem);
				}
			} else if (i == 2) {
				for (int l = 0; l < Data.MENUQUITTER.length; l++) {
					menuItem = new JMenuItem(Data.MENUQUITTER[l]);
					menuItem.setIcon(
							new ImageIcon(new ImageIcon(Data.ICONQUITTER[l]).getImage().getScaledInstance(15, 15, 50)));
					menuItem.addActionListener(contentPane);
					menuItem.setMnemonic(Data.MENUQUITTER[l].charAt(11));
					menuItem.setActionCommand(Data.MENUQUITTER[l]);
					menu.add(menuItem);
					if (l == 1) {
						dispose();
					}
				}
			} else if (i == 3) {
				for (int m = 0; m < Data.MENUAPROPOS.length; m++) {
					menuItem = new JMenuItem(Data.MENUAPROPOS[m]);
					menuItem.setIcon(
							new ImageIcon(new ImageIcon(Data.ICONAPROPOS[m]).getImage().getScaledInstance(15, 15, 50)));
					menuItem.addActionListener(contentPane);
					menuItem.setMnemonic(Data.MENUAPROPOS[m].charAt(0));
					menuItem.setActionCommand(Data.MENUAPROPOS[m]);
					menu.add(menuItem);
				}
				menu.addSeparator();
				ButtonGroup group = new ButtonGroup();
				for (int n = 0; n < Data.MENUCOULEURS.length; n++) {
					rbMenuItem = new JRadioButtonMenuItem(Data.MENUCOULEURS[n]);
					rbMenuItem.addActionListener(contentPane);
					rbMenuItem.setMnemonic(Data.MENUCOULEURS[n].charAt(6));
					rbMenuItem.setActionCommand(Data.MENUCOULEURS[n]);
					group.add(rbMenuItem);
					menu.add(rbMenuItem);
					if (n == 0) {
						couleurs = 0;
						rbMenuItem.setSelected(true);
					}
					if (n == 1) {
						couleurs = 1;
					}
				}
			}
			menuBar.add(menu);
		}
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setJMenuBar(menuBar);

		setContentPane(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	} // FentreMere

	/**
	 * Retourne le int qui permet de cocher le bon JRadioButtonMenuItem.
	 * 
	 * @return int Int allant de 0 à 1 permet la séléction d'un
	 *         JRadioButtonMenuItem.
	 */
	public int getCouleurs() {
		return couleurs;
	}

	/**
	 * Méthode main, donne l'argument en tant que nom de fenêtre.
	 * 
	 * @param args Tableau d'argument.
	 */
	public static void main(String[] args) {
		/*
		 * try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */
		fFrise = new FenetreFrise(fMenu, args[0]); // titre de la fenetre
	} // main
}