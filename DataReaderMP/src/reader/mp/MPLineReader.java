package data.reader.mp;

import java.util.ArrayList;
import java.util.List;

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
import utils.DateUtils;
import utils.NumberUtils;

public class MPLineReader extends Thread {

    private List<String[]> slice_toRead;
    private ReaderBarrier barrier;
    private List<RawData> result;
    private int t_id;

    private NumberUtils NumberUtils;
    private DateUtils DateUtils;

    public MPLineReader(List<String[]> slice_toRead, ReaderBarrier barrier, int t_id) {
	this.slice_toRead = slice_toRead;
	this.barrier = barrier;
	this.result = new ArrayList<RawData>();
	this.t_id = t_id;
	this.NumberUtils = new NumberUtils();
	this.DateUtils = new DateUtils();
    }

    public List<RawData> getResult() {
	return result;
    }

    public int get_T_Id() {
	return t_id;
    }

    private void read() {
	int index = 0;
	while (index < slice_toRead.size()) {
	    String[] nextLine = slice_toRead.get(index);
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

	    if (DateUtils.isValidDate(nextLine[22])) {
		data.setDateTime(new DateTime(DateUtils.parseDate(nextLine[22])));
	    }

	    if (!data.isEmpty())
		result.add(data);

	    index++;
	}

    }

    @Override
    public void run() {
	read();
	barrier.done();
    }

}
