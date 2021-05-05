/**
 * 
 */
package modele;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import modele.*;

/**
 * Class CalendrierDuMois, instancie un calendrier.
 * 
 * @author BOUCHDUI Yassine, Cleret Lilian
 */
public class CalendrierDuMois {
    private Collection<Date> dates;

    /**
     * M�thode CalendrierDuMois, rempli le calendrier avec certaines conditions,
     * tels que : la v�rification du mois en cours, ou la v�rification des jours de la
     * semaine qui n'est pas trop �lev�.
     * 
     * @param mois  Permet l'instanciation de la Date.
     * @param annee Permet l'instanciation de la Date.
     */
    public CalendrierDuMois(int mois, int annee) {
        dates = new TreeSet<Date>();
        Date date = new Date(1, mois, annee);
        int indiceJour = date.getJourSemaine() == 1 ? 6 : date.getJourSemaine() - 2;

        for (int indice = indiceJour; indice >= 0; indice--) {
            dates.add(date);
            date = date.dateDeLaVeille();
        }

        date = new Date(2, mois, annee);
        indiceJour = indiceJour + 1 % 7;

        while (date.getMois() == mois) {
            while (indiceJour < 7) {
                dates.add(date);
                date = date.dateDuLendemain();
                indiceJour++;
            }
            indiceJour = 0;
        }
    }

    /**
     * M�thode getDates, return une TreeSet des dates du calendrier.
     * 
     * @return Collection TreeSet instanci�e dans la m�thode CalendierMois.
     */
    public Collection<Date> getDates() {
        return dates;
    }
}
