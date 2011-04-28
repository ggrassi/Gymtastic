package ch.hsr.gymtastic.presentation.panels.server;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ch.hsr.gymtastic.domain.MyBufferedImage;

public class ImagePanel extends JPanel{
	
    private BufferedImage image = null;
	private String path;

    public BufferedImage getImage() {
		return image;
	}

	public void setImage(MyBufferedImage image) {
		this.image = image;
		repaint();
	}

	public ImagePanel() {
      
    }

	public BufferedImage generateImage(String path) {
		this.path = path;
		try {                
		      image = ImageIO.read(new File(path));
		      this.image = image;
		      if ( image != null ){
		          repaint();
		      }
		   } catch (IOException ex) {
		        // handle exception...
		   }
		return image;
	}

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters

    }

	public boolean isGenerated() {
	
		return !(image.equals(null));
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPath(String path) {
		this.path = path;
		generateImage(path);
	}

}
