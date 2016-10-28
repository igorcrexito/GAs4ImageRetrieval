/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio;

import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.Random;
import javax.media.jai.PlanarImage;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;
import utils.BioOps;

/**
 *
 * @author Igor Pig
 */
public class BioProcess {

    public void startBioProcess(PlanarImage baseImage, ArrayList<PlanarImage> imgs, int populationSize, int mutationLevel, int reproductionLevel, int crossoverCut , double fitnessThreshold) {

        Chromossome baseChromo = BioOps.convertImage2Chromossome(baseImage);
        ArrayList<Chromossome> population;
        int width = baseImage.getWidth();
        int height = baseImage.getHeight();
 
        int iterations = 0;
        Random rand = new Random();
        //para cada imagem da galeria
        for (int z = 0; z < imgs.size(); z++) {
            //inicializo uma população de tamanho 50
            population = BioOps.startPopulation(baseChromo, imgs.get(z), populationSize);
            
            //computo o fitness do melhor indivíduo da população
            double fitness = BioOps.computeFitnessValue(baseChromo, BioOps.getBestIndividual(population));
            System.out.println("fitness value: "+ fitness);
            while (fitness <= fitnessThreshold) {
                
                for (int i=0;i<reproductionLevel;i++) {
                       Chromossome[] chromos = BioOps.performCrossover(population.get(rand.nextInt(populationSize-1)), population.get(rand.nextInt(populationSize-1)), width*height);
                        //ajusta o valor de fitness
                        for (int c=0;c<chromos.length;c++) {
                            chromos[c].setFitnessValue(BioOps.computeFitnessValue(baseChromo, chromos[c]));
                            population.add(chromos[c]);
                        }  
                }
                //faz a mutação nos indivíduos
                for (int i=0;i<populationSize;i++)
                    population.set(i, BioOps.performMutation(population.get(i), i));
                
                population = BioOps.performSelection(population, populationSize);
                fitness = BioOps.computeFitnessValue(baseChromo, BioOps.getBestIndividual(population));
                iterations++;
                System.out.println("fitness value: "+ fitness);
            }
            
        }
        
        System.out.println("número de iterações é: "+iterations);

    }

}
