package data.manipulation;

import java.util.HashMap;
import java.util.Map;

import data.model.RawData;
import data.model.ResultData;

public class ResultantDataOperations {

    private HashMap<Integer, ResultData> result;
    // private static final double HIGH_VERTICAL_ACCELERATION_COMFORT_THRESHOLD
    // = 0.3;
    private static final double EULER_NUMBER = 2.72;

    public ResultantDataOperations() {
	this.result = new HashMap<Integer, ResultData>();
    }

    public void calculateResult(HashMap<Integer, RawData> values) {

	for (Map.Entry<Integer, RawData> data : values.entrySet()) {

	    ResultData e = new ResultData(data.getValue().getGeolocalization(),
		    1 / Math.pow(EULER_NUMBER, data.getValue().getAccelerometer().getAcceleration_y()));
	    result.put(data.getKey(), e);
	}
    }

    public HashMap<Integer, ResultData> getResult() {
	return result;
    }

}
