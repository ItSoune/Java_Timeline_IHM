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
 * c'est-�-dire le Panel contenant les �v�nements ainsi que la Frise.
 * 
 * @author BOUCHDI Yassine, Cleret Lilian
 */
public class PanelAffichage extends JPanel  {

    BorderLayout layout = new BorderLayout();

    FenetreMenu chMenu = new FenetreMenu("Frise - Menu G�n�ral");

    JLabel pTitre = new JLabel();

    PanelEvt pEvt;
    PanelTimeLine pTLine;

    public static File fichTes;

    /**
     * M�thode qui ajoute les panels Evt et TimeLine.
     * Il affiche le panelTimeLine selon depuis o� est-ce qu'il est appel�.
     * 
     * @param parFrise Frise utilis� par l'application.
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
     * Appelle la m�thode setChoix et lui affecte un titre.
     *  
     * @param parTitre Le titre de l'�v�nement choisit.
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
     * Appelle la m�thode enregistreEcouteur et lui affecte le controleur.
     * 
     * @param parControleur Le controleur qui utilise la m�thode.
     */
    public void enregistreEcouteur(Controleur parControleur) {
        pTLine.enregistreEcouteur(parControleur);
    }
}