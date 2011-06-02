package ch.hsr.gymtastic.server.presentation.frames;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Class ImagePanel is needed to generate an Image which stands as Logo for the Cup.
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image = null;
	private String path = "";

	/**
	 * Instantiates a new image panel.
	 */
	public ImagePanel() {

	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	/**
	 * Generate images an image.
	 * 
	 * @param path
	 *            the path
	 * @return the buffered image
	 */
	public BufferedImage generateImage(String path) {
		this.path = path;
		try {
			image = ImageIO.read(new File(path));
			if (image != null) {
				repaint();
			}
		} catch (IOException ex) {
		}
		return image;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	/**
	 * Checks if is generated.
	 * 
	 * @return true, if is generated
	 */
	public boolean isGenerated() {

		return image != null;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		if (path == null) {
			return;
		}
		this.path = path;
		generateImage(path);
	}

}
