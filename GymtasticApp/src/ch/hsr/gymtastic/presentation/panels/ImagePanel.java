package ch.hsr.gymtastic.presentation.panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
      
    }

	public void generateImage(String path) {
		try {                
		      image = ImageIO.read(new File(path));
		      if ( image != null ){
		          repaint();
		      }
		   } catch (IOException ex) {
		        // handle exception...
		   }
	}

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters

    }

}
