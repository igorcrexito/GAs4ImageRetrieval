/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Igor Pig
 */
public class ProcessImage {

    public static PlanarImage convertImageToGrayScale(PlanarImage image) {

        PlanarImage source = image;

        if (source.getSampleModel().getNumBands() != 3) {
            return source;
        } else {
            BufferedImageOp op = new ColorConvertOp(
                    ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            return PlanarImage.wrapRenderedImage(op.filter(image.getAsBufferedImage(), null));
        }

    }

    public static ArrayList<PlanarImage> splitImageIntoGrid(PlanarImage image, int numberOfGrids) {
        ArrayList<PlanarImage> fullGrid = new ArrayList<>();
        BufferedImage bufferedImage = image.getAsBufferedImage();

        //imagens são quadradas não precisa tratar dimensões diferentemente
        int newDimension = image.getHeight() / numberOfGrids;

        int initialX = 0;
        int initialY = 0;

        for (int i = 0; i < numberOfGrids * numberOfGrids; i++) {
            fullGrid.add(PlanarImage.wrapRenderedImage(bufferedImage.getSubimage(initialX, initialY, newDimension, newDimension)));
            initialX += newDimension;

            //System.out.println(initialX + " " + i);
            if (initialX == image.getWidth()) {
                initialY += newDimension;
                initialX = 0;
            }
        }

        return fullGrid;
    }

    public static PlanarImage detectaBordas (PlanarImage image){
        image = JAI.create("gradientmagnitude", image ,KernelJAI.GRADIENT_MASK_SOBEL_HORIZONTAL,KernelJAI.GRADIENT_MASK_SOBEL_VERTICAL);
        return image;
    }

}
