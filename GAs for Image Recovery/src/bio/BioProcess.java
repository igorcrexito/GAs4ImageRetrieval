/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio;

import java.awt.image.Raster;
import java.util.ArrayList;
import javax.media.jai.PlanarImage;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;
import utils.BioOps;

/**
 *
 * @author Igor Pig
 */
public class BioProcess {
    
    public void startBioProcess(PlanarImage baseImage, ArrayList<PlanarImage> imgs, int populationSize, int mutationLevel, int crossoverCut) {
        
        Chromossome baseChromo = convertImage2Chromossome(baseImage);
        ArrayList<Chromossome> population = new ArrayList<>();
        int width = baseImage.getWidth();
        int heith = baseImage.getHeight();
        
        //Parei aqui --> isso ainda não tá certo !
        for (int i=0;i<populationSize;i++) {
            population.add(new Chromossome(width*heith));
            population.set(i, convertImage2Chromossome(imgs.get(i)));
            BioOps.computeFitnessValue(baseChromo, population.get(i));
        }
        
    }
    
    private Chromossome convertImage2Chromossome(PlanarImage imagem) {
        int width = imagem.getWidth();
        int height = imagem.getHeight();
        
        Chromossome imagessome = new Chromossome(width*height);
        
        RandomIter iterator = RandomIterFactory.create(imagem, null);
        int[] pixel = new int[1];
        
        for (int i=0;i<imagem.getWidth();i++) {
            for (int j=0;j<imagem.getHeight();j++) {
                iterator.getPixel(i, j, pixel);
                imagessome.getGenes()[i*width+j] = pixel[0]; //imagens ó em grayscale
            }
        }
   
        return imagessome;
    }
}
