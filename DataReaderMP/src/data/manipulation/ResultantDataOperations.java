package data.manipulation;

import java.util.concurrent.CopyOnWriteArrayList;

import data.model.RawData;
import data.model.ResultData;

public class ResultantDataOperations {

    private CopyOnWriteArrayList<ResultData> result;
    // private static final double HIGH_VERTICAL_ACCELERATION_COMFORT_THRESHOLD
    // = 0.3;
    private static final double EULER_NUMBER = 2.72;

    public ResultantDataOperations() {
	this.result = new CopyOnWriteArrayList<ResultData>();
    }

    public void calculateResult(CopyOnWriteArrayList<RawData> values) {

	for (RawData res : values) {
	    ResultData e = new ResultData(res.getGeolocalization(),
		    1 / Math.pow(EULER_NUMBER, res.getAccelerometer().getAcceleration_y()), res.getDateTime());
	    result.add(e);
	}
    }

    public CopyOnWriteArrayList<ResultData> getResult() {
	return result;
    }

}
