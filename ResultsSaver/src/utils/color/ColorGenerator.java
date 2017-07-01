package utils.color;

public abstract class ColorGenerator {

	int red = 255;
	int green = 0;
	int blue = 0; 
	
	public abstract String getColor(double value);
}
