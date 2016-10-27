/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import javax.media.jai.PlanarImage;

/**
 *
 * @author Igor Pig
 */
public class ProcessImage {
    
    public static PlanarImage convertImageToGrayScale(PlanarImage image) {

        PlanarImage source= image;

        if(source.getSampleModel().getNumBands() != 3)
        {
            return source;
        }
        else
        {
                BufferedImageOp op = new ColorConvertOp(
                ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
                return PlanarImage.wrapRenderedImage(op.filter(image.getAsBufferedImage(), null));
        }

    }
    
}
