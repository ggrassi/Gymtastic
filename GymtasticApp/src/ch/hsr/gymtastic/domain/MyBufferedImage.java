package ch.hsr.gymtastic.domain;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

import javax.persistence.Entity;

@Entity
public class MyBufferedImage extends BufferedImage {

	public MyBufferedImage(int width, int height, int imageType,
			IndexColorModel cm) {
		super(width, height, imageType, cm);
		// TODO Auto-generated constructor stub
	}

	public MyBufferedImage(int width, int height, int imageType) {
		super(width, height, imageType);
		// TODO Auto-generated constructor stub
	}

	public MyBufferedImage(ColorModel cm, WritableRaster raster,
			boolean isRasterPremultiplied, Hashtable<?, ?> properties) {
		super(cm, raster, isRasterPremultiplied, properties);
		// TODO Auto-generated constructor stub
	}

}
