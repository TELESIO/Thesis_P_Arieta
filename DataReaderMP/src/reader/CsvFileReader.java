package reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import data.model.RawData;
import data.model.atomicData.Accelerometer;
import data.model.atomicData.DateTime;
import data.model.atomicData.Geolocalization;
import data.model.atomicData.Gravity;
import data.model.atomicData.Orientation;
import data.model.atomicData.Time;
import data.model.atomicData.Velocity;
import data.model.enums.UnitMeasurementTime;
import data.model.enums.UnitMeasurementVelocity;
import data.model.structure.DataContainer;
import utils.DateUtils;
import utils.NumberUtils;

public class CsvFileReader extends Reader {

    private CSVReader reader;
    private NumberUtils NumberUtils;
    private DateUtils DateUtils;

    public CsvFileReader(final String path_file, final char data_separator) {
	super(path_file, data_separator);
	this.NumberUtils = new NumberUtils();
	this.DateUtils = new DateUtils();
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
		RawData data = new RawData();

		if (NumberUtils.isDoubleNumber(nextLine[0]) && NumberUtils.isDoubleNumber(nextLine[1])
			&& NumberUtils.isDoubleNumber(nextLine[2]))
		    data.setAccelerometer(new Accelerometer(NumberUtils.doubleParser(nextLine[0]),
			    NumberUtils.doubleParser(nextLine[1]), NumberUtils.doubleParser(nextLine[2])));

		if (NumberUtils.isDoubleNumber(nextLine[3]) && NumberUtils.isDoubleNumber(nextLine[4])
			&& NumberUtils.isDoubleNumber(nextLine[5]))
		    data.setGravity(new Gravity(NumberUtils.doubleParser(nextLine[3]),
			    NumberUtils.doubleParser(nextLine[4]), NumberUtils.doubleParser(nextLine[5])));

		if (NumberUtils.isDoubleNumber(nextLine[9]) && NumberUtils.isDoubleNumber(nextLine[10])
			&& NumberUtils.isDoubleNumber(nextLine[11]))
		    data.setOrientation(new Orientation(NumberUtils.doubleParser(nextLine[9]),
			    NumberUtils.doubleParser(nextLine[10]), NumberUtils.doubleParser(nextLine[11])));

		if (NumberUtils.isDoubleNumber(nextLine[13]) && NumberUtils.isDoubleNumber(nextLine[14]))
		    data.setGeolocalization(new Geolocalization(NumberUtils.doubleParser(nextLine[13]),
			    NumberUtils.doubleParser(nextLine[14])));

		if (NumberUtils.isDoubleNumber(nextLine[17]))
		    data.setVelocity(new Velocity(NumberUtils.doubleParser(nextLine[17]),
			    UnitMeasurementVelocity.KILOMETERS_PER_HOURS));

		if (NumberUtils.isDoubleNumber(nextLine[21]))
		    data.setTime(new Time(NumberUtils.doubleParser(nextLine[21]), UnitMeasurementTime.MILLISECOND));

		if (DateUtils.isValidDate(nextLine[22]))
		    data.setDateTime(new DateTime(DateUtils.parseDate(nextLine[22])));

		if (!data.isEmpty())
		    raw_data.put(data);
	    }
	} catch (IOException e) {

	}

    }

    @Override
    public DataContainer getDataFileReaderResult() {
	return raw_data;
    }

}
