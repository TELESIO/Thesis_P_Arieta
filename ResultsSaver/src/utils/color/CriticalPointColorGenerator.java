package utils.color;

import java.awt.Color;

public class CriticalPointColorGenerator extends ColorGenerator {

	public static int MAX_VALUE = 6;
	@Override
	public String getColor(double value) {
		value = Math.abs(value) / MAX_VALUE;
		float minHue = 60f / 255; // 120° corrisponde al verde, 60° al giallo. 
		float maxHue = 0; // 0° corrsiponde al rosso
		float hue = (float) (value * maxHue + (1 - value) * minHue);
		Color c = new Color(Color.HSBtoRGB(hue, 1f, 0.8f));
		String hex = 	String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());

		// String hex = Integer.toHexString(c.getRGB() & 0xffffff);
		// double val = Math.abs(value);
		// red = (int) (val * (255));
		// green = 255 - red;
		// blue = 0 ;

		// Color color = new Color((int) red, (int) green, (int) blue);

		// while (hex.length() < 6) {
		// hex = "0" + hex;
		// }
		// hex = "#" + hex;

		return hex;
	}

}
