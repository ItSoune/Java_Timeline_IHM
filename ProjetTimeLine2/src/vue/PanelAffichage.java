/**
 * 
 */
package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controleur.*;

import java.awt.*;
import java.awt.event.*;

import java.io.*;

import modele.*;


/**
 * Classe qui affiche la partie Affichage de l'application, 
 * c'est-à-dire le Panel contenant les évènements ainsi que la Frise.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 */
public class PanelAffichage extends JPanel  {

    BorderLayout layout = new BorderLayout();

    FenetreMenu chMenu = new FenetreMenu("Frise - Menu Général");

    JLabel pTitre = new JLabel();

    PanelEvt pEvt;
    PanelTimeLine pTLine;

    public static File fichTes;

    /**
     * Méthode qui ajoute les panels Evt et TimeLine.
     * Il affiche le panelTimeLine selon depuis où est-ce qu'il est appelé.
     * 
     * @param parFrise Frise utilisé par l'application.
     */
    public PanelAffichage(Frise parFrise) {
        pTLine = new PanelTimeLine(parFrise);
        pEvt = new PanelEvt(parFrise,pTLine);

        setBackground(new Color(255,255,255));
        
        this.setLayout(new BorderLayout());
        this.add(pTitre, BorderLayout.NORTH);
        this.add(pEvt, BorderLayout.CENTER);
        
        if (chMenu.getIndice() == 1 || chMenu.getIndiceV2() == 0)
            this.add(pTLine, BorderLayout.SOUTH);
    }
    
    /**
     * Appelle la méthode setChoix et lui affecte un titre.
     *  
     * @param parTitre Le titre de l'évènement choisit.
     */
    public void setChoix(String parTitre){
        pEvt.setChoix(parTitre);
    }
    
	public void getClaire() {
		pTLine.getClaire();
		pEvt.getClaire();
		setBackground(Data.LeBlanc);

    }
    
    public void getSombre() {
		pTLine.getSombre();
		pEvt.getSombre();
		setBackground(Data.LeNoir);


    }
    
    /**
     * Appelle la méthode enregistreEcouteur et lui affecte le controleur.
     * 
     * @param parControleur Le controleur qui utilise la méthode.
     */
    public void enregistreEcouteur(Controleur parControleur) {
        pTLine.enregistreEcouteur(parControleur);
    }
}