/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import bio.Chromossome;
import java.util.Comparator;

/**
 *
 * @author Igor Pig Classe de suporte que permite a comparação de um cromossomo
 * com outro
 */
public class ChromossomeComparator implements Comparator<Chromossome> {

    @Override
    public int compare(Chromossome o1, Chromossome o2) {
        if (o1.getFitnessValue() > o2.getFitnessValue()) {
            return -1;
        } else if (o1.getFitnessValue() < o2.getFitnessValue()) {
            return 1;
        }
        return 0;
    }

}
