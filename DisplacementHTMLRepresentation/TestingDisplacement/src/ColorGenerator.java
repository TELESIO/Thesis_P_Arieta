

import java.awt.Color;

public class ColorGenerator {
    public ColorGenerator() {
    }

    
    public String getColor(double val) {
	int R = 255;
	int G = 0;
	int B = 0;
	double value = Math.abs(val);
	

		R = (int) (value * (255));
		G = 255 - R;
		B = 0 ;
	
	Color color = new Color((int) R, (int) G, (int) B);
	String hex = Integer.toHexString(color.getRGB() & 0xffffff);
	
	while (hex.length() < 6) {
	    hex = "0" + hex;
	}
	hex = "#" + hex;
	// System.out.println(hex);
	return hex;
    }
}
