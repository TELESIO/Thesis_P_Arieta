package reader;

import reader.mt.MPCsvFileReader;
import reader.st.CsvFileReader;

public class ReaderClass {
	
	private static ReaderClass instance;
	
	private ReaderClass(){}
	
	public static ReaderClass getInstance(){
		if(instance == null)
			instance = new ReaderClass();
		return instance;
	}
	
	public Reader getReader(ReaderType type, String path_file, char separator){
		switch (type) {
		case SINGLE_THREAD:
			return new CsvFileReader(path_file, separator);
		case MULTI_THREAD:
			return new MPCsvFileReader(path_file, separator);
		}
		return null;
	}
}
