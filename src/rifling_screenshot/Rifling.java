package rifling_screenshot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Rifling {
	private BufferedImage capture;
	private int x;
	private int y;
	private int w;
	private int h;
	private String fileType = "png";

	public Rifling(BufferedImage capture, int x, int y, int w, int h) {
		this.capture = capture;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	private String generateFileName(int w, int h, String postfix, String fileType) {
		StringBuilder widthByPx = new StringBuilder();
		widthByPx.append("");
		widthByPx.append(w);
		String width = widthByPx.toString();
		
		StringBuilder heightByPx = new StringBuilder();
		heightByPx.append("");
		heightByPx.append(h);
		String height = heightByPx.toString();
		
		String fileName = String.join("_", postfix, width);
		fileName = String.join("X", fileName, height);
		fileName = String.join(".", fileName, fileType);
		
		return fileName;
	}
	
	private boolean saveResizedImage(BufferedImage capture, int type, int maxWidth, int maxHeight) {
		BufferedImage resizedImage = ImageUtils.resizeImage(capture, ImageUtils.IMAGE_PNG, maxWidth, maxHeight);
		String fileName = generateFileName(maxWidth, maxHeight, "resizedByMax", fileType);
		return ImageUtils.saveImage(resizedImage, fileName, ImageUtils.IMAGE_PNG);
	} 
	
	public boolean saveTenderloinImage() {
		String fileName = generateFileName(w, h, "origin", fileType);
		BufferedImage tenderloin = capture.getSubimage(x, y, w, h);
		
		try {
			File tend = new File(fileName);
			ImageIO.write(tenderloin, fileType, tend);
		} catch (IOException error) {
			error.printStackTrace();
		}
		
		saveResizedImage(tenderloin, 0, 300, 250);
		saveResizedImage(tenderloin, 0, 120, 600);
		
		return true;
	}

}
