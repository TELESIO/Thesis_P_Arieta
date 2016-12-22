package main_test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import com.opencsv.CSVWriter;

import data.model.atomicData.Time;

public class WriterForEuler {
    CopyOnWriteArrayList<Double> eulerResult;
    CopyOnWriteArrayList<Time> time;

    public WriterForEuler(CopyOnWriteArrayList<Double> eulerResult, CopyOnWriteArrayList<Time> time) {
	this.eulerResult = eulerResult;
	this.time = time;
    }

    public void write() {
	CSVWriter writer = null;
	try {
	    writer = new CSVWriter(new FileWriter("file/Euler.csv"), CSVWriter.DEFAULT_SEPARATOR,
		    CSVWriter.NO_QUOTE_CHARACTER);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	String header = "time" + "," + "displacement";
	String[] header_record = header.split(",");
	writer.writeNext(header_record);
	for (int i = 0; i < eulerResult.size(); i++) {

	    Double tim = time.get(i).getTime();
	    Double displacement = eulerResult.get(i);
	    String value_ = tim + "," + displacement;
	    String[] record = value_.split(",");
	    writer.writeNext(record);
	}
	try {
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
