package utils.color;

import utils.FileType;

public class ColorGeneratorClass {
	
	
	private static ColorGeneratorClass instance;
	
	public static ColorGeneratorClass getInstance(){
		if(instance == null)
			instance = new ColorGeneratorClass();
		return instance;
	}
	
	private ColorGeneratorClass() {
	}
	
	
	public  ColorGenerator getColorGenerator(FileType type){
		switch (type) {
		case IRI:
			return new IRIColorGenerator();
		case CRITICAL:
			return new CriticalPointColorGenerator();
		case SIMPLE:
			return new SimpleColorGenerator();
		}
		return null;
		
	}
	
	
}
