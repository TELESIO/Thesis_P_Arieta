package data.manipulation;

import java.awt.Color;
import java.text.DecimalFormat;

public class ColorGenerator {
    public ColorGenerator() {
    }

    public String getColor(double k) {

	DecimalFormat f = new DecimalFormat("##.###");
	String s = f.format(k);
	String a = new String();
	for (char c : s.toCharArray()) {
	    if (c == ',')
		c = '.';
	    a += c;
	}
	double n = Double.parseDouble(a);

	double R = 0;
	double G = 255;
	double B = 0;

	if (n < 1) {
	    double reduction = 0.004;
	    for (double d = 0.999; d >= 0.000; d -= reduction) {
		R++;
		G--;
		if (n >= (d - reduction) && n <= d) {
		    break;
		}

	    }
	}
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
