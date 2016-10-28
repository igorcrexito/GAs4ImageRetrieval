/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio;

/**
 *
 * @author Igor Pig
 * Representa a estrutura de cromossomo. Como operamos com imagens, os genes só
 * apresentam valores inteiros (pixels)
 */
public class Chromossome {
    
    private Genes genes;
    private int dimension;
    private double fitnessValue;

    public Chromossome(int dimension) {
        this.dimension = dimension;
        this.genes = new Genes(dimension);
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public Genes getGenes() {
        return genes;
    }

    public void setGenes(Genes genes) {
        this.genes = genes;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
}
