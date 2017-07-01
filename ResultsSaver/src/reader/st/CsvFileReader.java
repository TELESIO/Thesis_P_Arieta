package reader.st;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVReader;

import model.PointOnMap;
import reader.Reader;
import utils.color.ColorGenerator;

public class CsvFileReader extends Reader {

    private CSVReader reader;
    public CsvFileReader(final String path_file, final char data_separator) {
	super(path_file, data_separator);
	

		try {
		    reader = new CSVReader(new FileReader(path_file), data_separator);
		} catch (FileNotFoundException e) {
		    System.out.println("Error while load file, the resource is not available or not exists in: " + path_file);
		}
    }

    int counter = 0;
    @Override
    public void read_file(ColorGenerator color_generator) {
	List<String[]> file_content = null;
	try {
	    file_content = reader.readAll();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	int index = 1;
	while (index < file_content.size()) {
	    String[] nextLine = file_content.get(index);
	    
    	Double displacement = Double.parseDouble(nextLine[0]);
    	Double latitude = Double.parseDouble(nextLine[1]);
    	Double longitude = Double.parseDouble(nextLine[2]);
    	String dates = nextLine[3];

    	DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
    	LocalDateTime dateTime;
    	Date date = null;
    	
    	try {
    		 dateTime = LocalDateTime.parse(dates, formatter);
    		 String dateNew = dateTime.toString().substring(0,10);	
    		 LocalDate l = LocalDate.parse(dateNew);
    		 date = java.sql.Date.valueOf(l);
    	} catch (DateTimeParseException e) {
    		 System.err.println(e);
    	}  
    	
    	String color = color_generator.getColor(displacement);
    	PointOnMap p = new PointOnMap(displacement, latitude, longitude, date, color);
    	
    	container.add(p);
    

	    index++;
	}
	
	

    }

    @Override
    public ArrayList<PointOnMap> getDataFileReaderResult() {
    	return container;
    }

}
