package vue;

import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

import controleur.*;
import modele.*;

/**
 * Classe qui affiche la partie Cr�ation de l'application, 
 * c'est-�-dire la Frise et le Formulaire.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 */
public class PanelCreation extends JPanel {

	BorderLayout layout = new BorderLayout();
	
	FenetreMenu chMenu = new FenetreMenu("Frise - Menu G�n�ral");
			
	PanelFormulaire pFormulaire;
	PanelTimeLine pTLine;
	PanelCalendrier chPCalendrier;
	
    JButton pTitre = new JButton();

	Frise chFrise;
	
	/**
     * M�thode qui ajoute les panels Formulaire et TimeLine.
     * Il affiche le panelTimeLine selon depuis o� est-ce qu'il est appel�.
     *
	 * @param parFrise 			Frise utilis� par l'application.
	 * @param parPCalendrier	PanelCalendrier donn� au Fromulaire.
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
	 * appelle la m�thode setModeleTable et lui donne une Frise.
	 * 
	 * @param parFrise Frise utilis� par la m�thode.
	 */
	public void setModeleTable(Frise parFrise) {
        pTLine.setModeleTable(parFrise);
    }
	
	 /**
     * Appelle la m�thode enregistreEcouteur et lui affecte le controleur.
     * 
     * @param parControleur Le controleur qui utilise la m�thode.
     */
    public void enregistreEcouteur(Controleur parControleur) {
        pFormulaire.enregistreEcouteur(parControleur);
    }
    
    /**
     * Remet � z�ro le Fromulaire apr�s envoie de l'�v�nement � la Frise.
     */
    public void RAZ() {
    	pFormulaire.RAZ();
    }
    
    /**
     * Appelle la m�thode getEvenement du PanelFormulaire.
     * 
     * @return Evt l'�v�nement cr�er avec cette m�thode.
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
     * V�rifie que la date choisit par l'utilisateur ne soit pas hors Frise.
     * 
     * @param parDate La Date s�lectionn�e par l'utilisateur.
     */
    public void setDate(Date parDate) {
    	pFormulaire.setDate(parDate);
    }
}
