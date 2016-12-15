package data.manipulation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import data.model.RawData;

public class FilteringDataOperations {

    public static final double LOW_VELOCITY_CONSTANT = 1.11; // 4 k/h -> 1.1 m/s
    public static final double HIGH_VELOCITY_CONSTANT = 19.44; // 70 k/h ->
							       // 19.44 m/s

    public HashMap<Integer, RawData> reduceLowVelocity(HashMap<Integer, RawData> values) {

	Iterator<Map.Entry<Integer, RawData>> iterator = values.entrySet().iterator();
	while (iterator.hasNext()) {
	    Map.Entry<Integer, RawData> entry = iterator.next();
	    if (entry.getValue().getVelocity().getVelocity() <= LOW_VELOCITY_CONSTANT) {
		iterator.remove();
	    }
	}

	return values;

    }

    public HashMap<Integer, RawData> reduceHighVelocity(HashMap<Integer, RawData> values) {

	Iterator<Map.Entry<Integer, RawData>> iterator = values.entrySet().iterator();
	while (iterator.hasNext()) {
	    Map.Entry<Integer, RawData> entry = iterator.next();
	    if (entry.getValue().getVelocity().getVelocity() >= HIGH_VELOCITY_CONSTANT) {
		iterator.remove();
	    }
	}

	return values;

    }

}
