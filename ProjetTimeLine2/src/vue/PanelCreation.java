package vue;

import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

import controleur.*;
import modele.*;

/**
 * Classe qui affiche la partie Création de l'application, 
 * c'est-à-dire la Frise et le Formulaire.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 */
public class PanelCreation extends JPanel {

	BorderLayout layout = new BorderLayout();
	
	FenetreMenu chMenu = new FenetreMenu("Frise - Menu Général");
			
	PanelFormulaire pFormulaire;
	PanelTimeLine pTLine;
	PanelCalendrier chPCalendrier;
	
    JButton pTitre = new JButton();

	Frise chFrise;
	
	/**
     * Méthode qui ajoute les panels Formulaire et TimeLine.
     * Il affiche le panelTimeLine selon depuis où est-ce qu'il est appelé.
     *
	 * @param parFrise 			Frise utilisé par l'application.
	 * @param parPCalendrier	PanelCalendrier donné au Fromulaire.
	 */
	public PanelCreation(Frise parFrise, PanelCalendrier parPCalendrier) {

		pFormulaire = new PanelFormulaire(parPCalendrier);
		pTLine = new PanelTimeLine(parFrise);
		pTitre.setContentAreaFilled(false);
		pTitre.setText(chMenu.getTitreProjet());
        pTitre.setBorderPainted(false);
        pTitre.setBackground(Data.LeBlanc);
        pTitre.setFont(new Font("Times New Roman", Font.BOLD, 20));
        
		this.setLayout(layout);
		this.add(pFormulaire, BorderLayout.SOUTH);
		this.add(pTitre, BorderLayout.CENTER);
		
		if (chMenu.getIndice() == 0)
			this.add(pTLine, BorderLayout.NORTH);
	}

	/**
	 * appelle la méthode setModeleTable et lui donne une Frise.
	 * 
	 * @param parFrise Frise utilisé par la méthode.
	 */
	public void setModeleTable(Frise parFrise) {
        pTLine.setModeleTable(parFrise);
    }
	
	 /**
     * Appelle la méthode enregistreEcouteur et lui affecte le controleur.
     * 
     * @param parControleur Le controleur qui utilise la méthode.
     */
    public void enregistreEcouteur(Controleur parControleur) {
        pFormulaire.enregistreEcouteur(parControleur);
    }
    
    /**
     * Remet à zéro le Fromulaire après envoie de l'évènement à la Frise.
     */
    public void RAZ() {
    	pFormulaire.RAZ();
    }
    
    /**
     * Appelle la méthode getEvenement du PanelFormulaire.
     * 
     * @return Evt l'évènement créer avec cette méthode.
     */
    public Evt getEvenement() {
        return pFormulaire.getEvenement();
    }
    /**
     * Met le fond en blanc et le devant en noir.
     */
	public void getClaire() {
		pTLine.getClaire();
		pFormulaire.getClaire();
		pTitre.setBackground(Data.LeBlanc);
		pTitre.setForeground(Data.LeNoir);
		setBackground(Data.LeBlanc);

    }
	 
	/**
	 * Met le fond en noir et le devant en blanc.
	 */
    public void getSombre() {
		pTLine.getSombre();
		pFormulaire.getSombre();
		pTitre.setBackground(Data.LeNoir);
		pTitre.setForeground(Data.LeBlanc);
		setBackground(Data.LeNoir);


    }
    /**
     * Vérifie que la date choisit par l'utilisateur ne soit pas hors Frise.
     * 
     * @param parDate La Date sélectionnée par l'utilisateur.
     */
    public void setDate(Date parDate) {
    	pFormulaire.setDate(parDate);
    }
}
