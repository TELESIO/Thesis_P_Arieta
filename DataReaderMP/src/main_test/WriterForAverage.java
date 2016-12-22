package main_test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import com.opencsv.CSVWriter;

import data.model.atomicData.Time;

public class WriterForAverage {

    private static final String path_file = "file/SMA.csv";
    private CopyOnWriteArrayList<Double> beforeAvg;
    private CopyOnWriteArrayList<Double> afterAvg;
    private CopyOnWriteArrayList<Time> time;

    public WriterForAverage(CopyOnWriteArrayList<Double> beforeAvg, CopyOnWriteArrayList<Double> afterAvg,
	    CopyOnWriteArrayList<Time> time) {
	this.beforeAvg = beforeAvg;
	this.afterAvg = afterAvg;
	this.time = time;
    }

    public void write() {
	CSVWriter writer = null;
	try {
	    writer = new CSVWriter(new FileWriter(path_file), CSVWriter.DEFAULT_SEPARATOR,
		    CSVWriter.NO_QUOTE_CHARACTER);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	String header = "accelerationbefore" + "," + " time" + "," + "accelerationthan";
	String[] header_record = header.split(",");
	writer.writeNext(header_record);
	for (int i = 0; i < beforeAvg.size(); i++) {

	    String value = beforeAvg.get(i).toString() + "," + time.get(i).getTime() + "," + afterAvg.get(i).toString();
	    String[] record = value.split(",");
	    writer.writeNext(record);
	}
	try {
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}