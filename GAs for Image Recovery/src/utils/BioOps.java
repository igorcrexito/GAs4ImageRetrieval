/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import bio.Chromossome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.media.jai.PlanarImage;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;

/**
 *
 * @author Igor Pig Essa classe contém as operações a serem realizadas nos
 * cromossomoss
 */
public class BioOps {

    final static int MAX_PIX_VALUE = 255;

    /*
    * Método que realiza o crossover considerando 2 cromossomos de entrada e um ponto de corte.
    * A saída é um vetor contendo os cromossomos filhos resultantes da combinação dos pais.
     */
    public static Chromossome[] performCrossover(Chromossome indivA, Chromossome indivB, int cutoffPoint) {

        int[] pixA = indivA.getGenes().getGeneticInformation();
        int[] pixB = indivB.getGenes().getGeneticInformation();
        int dimension = indivA.getDimension();

        Chromossome sonA = new Chromossome(dimension);
        Chromossome sonB = new Chromossome(dimension);

        //isso representa a reprodução
        for (int i = 0; i < dimension; i++) {
                //boost no crossover. Evita reproduções que afastem da solução
                if (indivA.getGenes().getLockedGenes()[i]==1) {
                    sonA.getGenes().getGeneticInformation()[i] = pixA[i];
                    sonB.getGenes().getGeneticInformation()[i] = pixA[i];
                } else if (indivB.getGenes().getLockedGenes()[i]==1) {
                    sonA.getGenes().getGeneticInformation()[i] = pixB[i];
                    sonB.getGenes().getGeneticInformation()[i] = pixB[i];
                } else {  
                    //faz a mescla de genes
                    if (i < cutoffPoint) {
                        sonA.getGenes().getGeneticInformation()[i] = pixA[i];
                        sonB.getGenes().getGeneticInformation()[i] = pixB[i];
                    } else {
                        sonB.getGenes().getGeneticInformation()[i] = pixA[i];
                        sonA.getGenes().getGeneticInformation()[i] = pixB[i];
                    }
                }
        }

        Chromossome[] output = new Chromossome[2];
        output[0] = sonA;
        output[1] = sonB;

        return output;
    }

    /*
    * Método que realiza a mutação considerando um indivíduo de entrada e o número
    * de mutações a serem realizadas. A saída é um cromossomo com genes
    * alterados.
     */
    public static Chromossome performMutation(Chromossome individual, int numberOfMutations) {

        Random rand = new Random();
        int dimension = individual.getDimension();
        for (int i = 0; i < numberOfMutations; i++) {
            int position = rand.nextInt(dimension);
            int value = rand.nextInt(MAX_PIX_VALUE);
            
            //só faz mutação se o gene for errado
            if (individual.getGenes().getLockedGenes()[position]!=1)
                individual.getGenes().getGeneticInformation()[position] = value;
        }
        return individual;
    }

    /*
    * Método que realiza a seleção dos indivíduos que satisfazem um determinado threshold de fitness
     */
    public static ArrayList<Chromossome> performSelection(ArrayList<Chromossome> population, double fitnessThreshold) {

        for (int i = 0; i < population.size(); i++) {
            if (population.get(i).getFitnessValue() < fitnessThreshold) {
                population.remove(i);
            }
        }

        return population;
    }

    /*
    * Método que realiza a seleção dos indivíduos retornando uma quantidade 'n' de
    * indivíduos mais aptos
     */
    public static ArrayList<Chromossome> performSelection(ArrayList<Chromossome> population, int finalPopulationSize) {

        ArrayList<Chromossome> finalPopulation = new ArrayList<>();
        Collections.sort(population, new ChromossomeComparator());

        for (int i = 0; i < finalPopulationSize; i++) {
            finalPopulation.add(population.get(i));
        }
        return finalPopulation;
    }

    public static double computeFitnessValue(Chromossome basis, Chromossome individual) {

        int dimension = individual.getDimension();
        double fitness = 255*dimension;
        
        for (int i = 0; i < dimension; i++) {
            int difference = (Math.abs(basis.getGenes().getGeneticInformation()[i] - individual.getGenes().getGeneticInformation()[i]));
            fitness = fitness - difference;
            
            //se o valor já está correto, não precisa ser alterado. Isso é um boost
            if (difference==0)
                individual.getGenes().getLockedGenes()[i] = 1;
        }
        
        fitness = (double)fitness/((double)255*(double)dimension);
        
        return fitness;  //normalizar entre 0 e 1 -> depois posso mudar isso
    }

    public static Chromossome getBestIndividual(ArrayList<Chromossome> population) {

        Chromossome selectedChromo = population.get(0);
        double bestFitness = population.get(0).getFitnessValue();
        for (int i = 1; i < population.size(); i++) {
            if (bestFitness < population.get(i).getFitnessValue()) {
                selectedChromo = population.get(i);
            }
        }
        
        return selectedChromo;
    }

    public static ArrayList<Chromossome> startPopulation(Chromossome baseChromo, PlanarImage imagem, int populationSize) {

        ArrayList<Chromossome> population = new ArrayList<>();
        
        //coloca o primeiro indivíduo na população
        Chromossome indiv = convertImage2Chromossome(imagem);
        double fitness = computeFitnessValue(baseChromo, indiv);
        indiv.setFitnessValue(fitness);
        population.add(indiv);
    
        int dimension = indiv.getDimension();
        double mutationCoefficient = (double)(dimension/2)/(double)populationSize;
        
        //inicializa a população com alterações em relação a um indivíduo base
        for (int i = 0; i < populationSize-1; i++) {
            Chromossome newChromo = performMutation(indiv, i*(int)Math.round(mutationCoefficient));
            fitness = computeFitnessValue(baseChromo, newChromo);
            newChromo.setFitnessValue(fitness);
            population.add(newChromo);
        }

        return population;
    }

    public static Chromossome convertImage2Chromossome(PlanarImage imagem) {
        int width = imagem.getWidth();
        int height = imagem.getHeight();
        
        
        Chromossome imagessome = new Chromossome(width * height);
        
        int dimension = 0;
        RandomIter iterator = RandomIterFactory.create(imagem, null);
        int[] pixel = new int[1];

        for (int i = 0; i < imagem.getWidth(); i++) {
            for (int j = 0; j < imagem.getHeight(); j++) {
                iterator.getPixel(i, j, pixel);
                imagessome.getGenes().getGeneticInformation()[dimension] = pixel[0]; //imagens só em grayscale
                dimension++;
            }
        }

        return imagessome;
    }
    
   

}
