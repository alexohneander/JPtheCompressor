package jpthecompressor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
/**
 *
 * @author alexwellnitz
 */
public class JPtheCompressor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
                Scanner user_input = new Scanner( System.in );
                
                //inGoing Filename
                String filename;
                System.out.print("Enter your filename: ");
                filename = user_input.next( );
                
                File imageFile = new File(filename);
                
                //Outgoing Filename
                String filename_output;
                System.out.print("Enter your Output filename: ");
                filename_output = user_input.next( );
                
		File compressedImageFile = new File(filename_output);

		InputStream is = new FileInputStream(imageFile);
		OutputStream os = new FileOutputStream(compressedImageFile);

		float quality = 0.5f;

		// create a BufferedImage as the result of decoding the supplied InputStream
		BufferedImage image = ImageIO.read(is);

		// get all image writers for JPG format
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");

		if (!writers.hasNext())
			throw new IllegalStateException("No writers found");

		ImageWriter writer = (ImageWriter) writers.next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		writer.setOutput(ios);

		ImageWriteParam param = writer.getDefaultWriteParam();

		// compress to a given quality
		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		param.setCompressionQuality(quality);

		// appends a complete image stream containing a single image and
	    //associated stream and image metadata and thumbnails to the output
		writer.write(null, new IIOImage(image, null, null), param);

		// close all streams
		is.close();
		os.close();
		ios.close();
		writer.dispose();
        
    }
    
}
