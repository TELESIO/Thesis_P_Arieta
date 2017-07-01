package utils.color;

import java.awt.Color;

public class SimpleColorGenerator extends ColorGenerator {
	@Override
	public String getColor(double value) {

		if (Math.abs(value) > 1)
			value = 1;
		else
			value = Math.abs(value);
		
		float minHue = 90f / 255; // 120° corrisponde al verde, 60° al giallo.
		float maxHue = 0; // 0° corrsiponde al rosso
		float hue = (float) (value * maxHue + (1 - value) * minHue);
		Color c = new Color(Color.HSBtoRGB(hue, 1f, 0.8f));
		String hex = String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());

		return hex;
	}

}
