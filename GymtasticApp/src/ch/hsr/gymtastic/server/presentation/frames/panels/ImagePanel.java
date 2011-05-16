package ch.hsr.gymtastic.server.presentation.frames.panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private BufferedImage image = null;
	private String path;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	public ImagePanel() {

	}

	public BufferedImage generateImage(String path) {
		this.path = path;
		try {
			image = ImageIO.read(new File(path));
			if (image != null) {
				repaint();
			}
		} catch (IOException ex) {
			// handle exception...
		}
		return image;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the
										// parameters

	}

	public boolean isGenerated() {

		return image != null;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		generateImage(path);
	}

}
