/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio;

/**
 *
 * @author Igor Pig
 */
public class Genes {
    
    private int[] geneticInformation;
    private int[] lockedGenes;

    public Genes(int dimension) {
        this.geneticInformation = new int[dimension];
        this.lockedGenes = new int[dimension];
    }
    
    public int[] getGeneticInformation() {
        return geneticInformation;
    }

    public void setGeneticInformation(int[] geneticInformation) {
        this.geneticInformation = geneticInformation;
    }

    public int[] getLockedGenes() {
        return lockedGenes;
    }

    public void setLockedGenes(int[] lockedGenes) {
        this.lockedGenes = lockedGenes;
    }
    
    
    
}
