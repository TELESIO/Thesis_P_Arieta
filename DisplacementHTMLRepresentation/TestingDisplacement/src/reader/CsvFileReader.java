package reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import data.Data;
import utils.NumberUtils;

public class CsvFileReader extends Reader {

    private CSVReader reader;
    public CsvFileReader(final String path_file, final char data_separator) {
	super(path_file, data_separator);
	
	new NumberUtils();
	try {
	    reader = new CSVReader(new FileReader(path_file), data_separator);

	} catch (FileNotFoundException e) {
	    System.out.println("Error while load file, the resource is not available or not exists in: " + path_file);
	}
    }

    @Override
    public void read_file() {
	String[] nextLine;
	try {

		
	    while ((nextLine = reader.readNext()) != null) {
	    	Double disp = Double.parseDouble(nextLine[0]);
	    	Double lat = Double.parseDouble(nextLine[1]);
	    	Double longitu = Double.parseDouble(nextLine[2]);
	    	Data d = new Data(disp,lat,longitu);	
			container.add(d);
		}	
	} catch (IOException e) {
	}

    }

    @Override
    public ArrayList<Data> getDataFileReaderResult() {
    	return container;
    }

}
