package modele;

import javax.swing.*;

import vue.*;

/**
 * La classe BoutonDate permet de créer des boutons associés à une date.
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
     * La méthode setDate permet d'associer la date passée en paramètre au bouton.
     * 
     * @param date : La date complète du jour qu'on va associer au Bouton.
     */
    public void setDate(Date date) {
        this.chDate = date;
    }

    /**
     * La méthode getDate renvoie la date associée au bouton.
     * 
     * @return Date : La Date associée au bouton.
     */
    public Date getDate() {
        return chDate;
    }
}