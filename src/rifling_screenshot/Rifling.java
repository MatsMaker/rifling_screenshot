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
	private String fileType = "jpg";

	public Rifling(BufferedImage capture, int x, int y, int w, int h) {
		this.capture = capture;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	private String generateFileName() {
		StringBuilder widthByPx = new StringBuilder();
		widthByPx.append("");
		widthByPx.append(w);
		String width = widthByPx.toString();
		
		StringBuilder heightByPx = new StringBuilder();
		heightByPx.append("");
		heightByPx.append(h);
		String height = heightByPx.toString();
		
		String fileName = String.join("_", "tenderloin_", width);
		fileName = String.join("X", fileName, height);
		fileName = String.join(".", fileName, fileType);
		
		return fileName;
	}
	
	private void compressImage() {
		
	} 
	
	public String saveTenderloinImage() {
		// need addd 300x250, 120x600 i t.d.
		String fileName = generateFileName();
		BufferedImage tenderloin = capture.getSubimage(x, y, w, h);
		
		try {
			File tend = new File(fileName);
			ImageIO.write(tenderloin, fileType, tend);
		} catch (IOException error) {
			error.printStackTrace();
		}
		return "Done";
	}

}
