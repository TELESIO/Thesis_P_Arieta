package reader.mt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import model.PointOnMap;
import utils.Barrier;
import utils.color.ColorGenerator;


public class MPLineReader extends Thread {

    private List<String[]> slice_toRead;
    private Barrier barrier;
    private List<PointOnMap> result;
    private int t_id;
    private ColorGenerator color_generator;
    
    public MPLineReader(List<String[]> slice_toRead, Barrier barrier, int t_id, ColorGenerator color_generator) {
	this.slice_toRead = slice_toRead;
	this.barrier = barrier;
	this.result = new ArrayList<PointOnMap>();
	this.t_id = t_id;
	this.color_generator = color_generator;

    }

    public List<PointOnMap> getResult() {
	return result;
    }

    public int get_T_Id() {
	return t_id;
    }

    private void read() {
	int index = 0;
	while (index < slice_toRead.size()) {
	    String[] nextLine = slice_toRead.get(index);
	    
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
    	result.add(p);
    	index++;
	}

    }

    @Override
    public void run() {
    	read();
    	barrier.done();
    }

}
