package modele;

import javax.swing.*;

import vue.*;

/**
 * La classe BoutonDate permet de cr�er des boutons associ�s � une date.
 * 
 * @author BOUCHDI Yassine, CLERET Lilian
 *
 */
public class BoutonDate extends JButton {
    private Date chDate;

    public BoutonDate(Date parDate) {
        super(Integer.toString(parDate.getJour()));
        chDate = parDate;
    }

    /**
     * La m�thode setDate permet d'associer la date pass�e en param�tre au bouton.
     * 
     * @param date : La date compl�te du jour qu'on va associer au Bouton.
     */
    public void setDate(Date date) {
        this.chDate = date;
    }

    /**
     * La m�thode getDate renvoie la date associ�e au bouton.
     * 
     * @return Date : La Date associ�e au bouton.
     */
    public Date getDate() {
        return chDate;
    }
}