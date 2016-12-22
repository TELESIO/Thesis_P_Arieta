package data.manipulation;

import java.util.ArrayList;

import data.model.structure.DataContainer;

public class FilteringDataOperations {

    public static final double LOW_VELOCITY_CONSTANT = 1.11; // 4 k/h -> 1.1 m/s
    public static final double HIGH_VELOCITY_CONSTANT = 19.44; // 70 k/h ->
							       // 19.44 m/s
    public static final int SMA_WINDOW_SIZE = 5;

    public void reduceLowVelocity(DataContainer values) {

	for (int i = 0; i < values.size(); i++)
	    if (values.get(i).getVelocity().getVelocity() <= LOW_VELOCITY_CONSTANT)
		values.remove(i);

    }

    public void reduceHighVelocity(DataContainer values) {
	for (int i = 0; i < values.size(); i++)
	    if (values.get(i).getVelocity().getVelocity() >= HIGH_VELOCITY_CONSTANT)
		values.remove(i);
    }

    public DataContainer simpleMovingAverageFilter(DataContainer data) {

	ArrayList<Double> subset = new ArrayList<Double>(SMA_WINDOW_SIZE);

	for (int y = 0; y < SMA_WINDOW_SIZE; y++)
	    subset.add(data.get(y).getAccelerometer().getAcceleration_y());

	for (int i = 0; i < data.size() - SMA_WINDOW_SIZE; i++) {
	    double average = getSubSetAverage(subset);
	    subset.remove(0);
	    subset.add(data.get(i + SMA_WINDOW_SIZE).getAccelerometer().getAcceleration_y());
	    data.get(i).getAccelerometer().setAcceleration_y(average);
	}

	return data;
    }

    private double getSubSetAverage(ArrayList<Double> subset) {
	double average = 0.0;

	for (int i = 0; i < subset.size(); i++)
	    average += subset.get(i);

	return average / SMA_WINDOW_SIZE;
    }
}
