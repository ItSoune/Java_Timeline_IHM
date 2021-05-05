package modele;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

/**
 * Class qui évite la reécriture de certaines variables redondantes.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 */
public class Data {
	//Calendrier
	public static String[] MOIS = { "", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août","Septembre", "Octobre", "Novembre", "Décembre" };
	public static String[] MOISV2 = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août","Septembre", "Octobre", "Novembre", "Décembre" };
	public static String[] JOUR_SEM = { "", "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" };
	public static String[] JOUR_ABR = { "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim" };
	//Calendrier
	
	//MENU
	public static String[] MENU = {"Creation", "Affichage", "Quitter", "?"};
	public static String[] MENUCREATION = {"Nouveau","Modifier"};
	public static String[] ICONCREATION = {"images/window_new.png","images/writing_tool.png"};
	public static String[] MENUAFFICHAGE = {"Charger une frise", "Charger la dernière frise"};
	public static String[] ICONAFFICHAGE = {"images/charger_une_frise.png","images/download_file.png"};
	public static String[] MENUQUITTER = {"Fermer l'application"};
	public static String[] ICONQUITTER = {"images/application_exit.png"};
	public static String[] MENUAPROPOS = {"Documentation","Comment ça marche ?"};
	public static String[] ICONAPROPOS = {"images/book_folder.png","images/how_to.png"};
	public static String[] MENUCOULEURS = {"Mode Clair","Mode Sombre"};
	//MENU
	
	public static String[] NIVIMPORTANCE = {"Très important","Important","Peu important","Pas important"};
	public static Font fontTitre = new Font("Courier New", Font.BOLD, 20);
    public static Font fontText = new Font("Courier New", Font.BOLD, 15);
	
	// PanelEvt
		public final static String INTIT_PRECEDENT = "<";
		public final static String INTIT_SUIVANT = ">";
		public final static String[] INTITULES_BOUTONS = { INTIT_PRECEDENT, INTIT_SUIVANT };
		public final static String[] INTITULES_MOIS = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet","Août", "Septembre", "Octobre", "Novembre", "Décembre" };
		public final static int NB_MOIS = INTITULES_MOIS.length;
	// PanelEvt

	// Couleur
		public final static Color LeBleu = new Color(29, 161, 242);
		public final static Color LeBlanc = new Color(255, 255, 255);
		public final static Color LeNoir = new Color(0, 0, 0);
		public final static Color CouleurBoutons = new Color(215, 215, 215);
		public final static Color LeGris = new Color(175, 175, 175);
		public final static Color LeGB = new Color(220, 220, 220);
		public final static Color LeGrisC = new Color(200, 200, 200);
		public final static Color LeOrange = new Color(255,140,0);
		public final static Color LeRouge = new Color(255,0,0);

	// Couleur

}