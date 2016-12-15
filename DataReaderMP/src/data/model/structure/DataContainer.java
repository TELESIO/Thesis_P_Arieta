package data.model.structure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import data.model.RawData;
import data.model.atomicData.Accelerometer;
import data.model.atomicData.DateTime;
import data.model.atomicData.Geolocalization;
import data.model.atomicData.Gravity;
import data.model.atomicData.Orientation;
import data.model.atomicData.Time;
import data.model.atomicData.Velocity;
import data.model.enums.Axis;

public class DataContainer {

	int size;
	int key;
	HashMap<Integer, Accelerometer> accelerometerData;
	HashMap<Integer, Gravity> gravityData;
	HashMap<Integer, Geolocalization> geolocalizationData;
	HashMap<Integer, Orientation> orientationData;
	HashMap<Integer, Time> timeData;
	HashMap<Integer, DateTime> dateTimeData;
	HashMap<Integer, Velocity> velocityData;

	public DataContainer() {
		accelerometerData = new HashMap<Integer, Accelerometer>();
		gravityData = new HashMap<Integer, Gravity>();
		geolocalizationData = new HashMap<Integer, Geolocalization>();
		orientationData = new HashMap<Integer, Orientation>();
		timeData = new HashMap<Integer, Time>();
		dateTimeData = new HashMap<Integer, DateTime>();
		velocityData = new HashMap<Integer, Velocity>();
		key = 0;
		size = 0;

	}

	public boolean isEmpty() {
		return (size > 0) ? true : false;
	}

	public void put(RawData r) {
		accelerometerData.put(key, r.getAccelerometer());
		gravityData.put(key, r.getGravity());
		geolocalizationData.put(key, r.getGeolocalization());
		orientationData.put(key, r.getOrientation());
		timeData.put(key, r.getTime());
		velocityData.put(key, r.getVelocity());
		dateTimeData.put(key, r.getDateTime());
		key++;
		size++;
	}

	public int size() {
		return size;
	}

	public void clear() {
		accelerometerData.clear();
		gravityData.clear();
		geolocalizationData.clear();
		orientationData.clear();
		timeData.clear();
		dateTimeData.clear();
		velocityData.clear();
	}

	public RawData get(int index) {
		return buildRawDataFromIndex(index);
	}

	private RawData buildRawDataFromIndex(int index) {
		RawData data = new RawData();
		data.setAccelerometer(accelerometerData.get(index));
		data.setGravity(gravityData.get(index));
		data.setGeolocalization(geolocalizationData.get(index));
		data.setOrientation(orientationData.get(index));
		data.setTime(timeData.get(index));
		data.setDateTime(dateTimeData.get(index));
		data.setVelocity(velocityData.get(index));

		return data;

	}

	public HashMap<Integer, RawData> getData() {
		HashMap<Integer, RawData> data = new HashMap<Integer, RawData>();
		for (int i = 0; i < size; i++)
			data.put(i, buildRawDataFromIndex(i));
		return data;
	}

	public HashMap<Integer, Double> getAxesAcceleration(Axis axes) {
		HashMap<Integer, Double> toReturn = new HashMap<Integer, Double>();
		int key = 0;
		for (Accelerometer accelerometer : accelerometerData.values()) {
			try {
				toReturn.put(key,
						(Double) accelerometer.getClass().getMethod("getAcceleration_" + axes).invoke(accelerometer));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			key++;
		}
		return toReturn;

	}

	public void setAccelerometerAxes(HashMap<Integer, Double> value, Axis axes) {

		for (Map.Entry<Integer, Double> a : value.entrySet()) {
			Method method = null;
			try {
				method = accelerometerData.get(a.getKey()).getClass().getMethod("setAcceleration_" + axes,
						double.class);
				method.invoke(accelerometerData.get(a.getKey()), a.getValue());
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}

		}

	}

	public HashMap<Integer, Double> getAxesGravity(Axis axes) {
		HashMap<Integer, Double> toReturn = new HashMap<Integer, Double>();
		int key = 0;
		for (Gravity gravity : gravityData.values()) {
			try {
				toReturn.put(key, (Double) gravity.getClass().getMethod("getGravity_" + axes).invoke(gravity));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			key++;
		}
		return toReturn;

	}

	public void setGravityAxes(HashMap<Integer, Double> value, Axis axes) {

		for (Map.Entry<Integer, Double> a : value.entrySet()) {
			Method method = null;
			try {
				method = gravityData.get(a.getKey()).getClass().getMethod("setGravity_" + axes, double.class);
				method.invoke(gravityData.get(a.getKey()), a.getValue());
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}

		}

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public HashMap<Integer, Accelerometer> getAccelerometerData() {
		return accelerometerData;
	}

	public void setAccelerometerData(HashMap<Integer, Accelerometer> accelerometerData) {
		this.accelerometerData = accelerometerData;
	}

	public HashMap<Integer, Gravity> getGravityData() {
		return gravityData;
	}

	public void setGravityData(HashMap<Integer, Gravity> gravityData) {
		this.gravityData = gravityData;
	}

	public HashMap<Integer, Geolocalization> getGeolocalizationData() {
		return geolocalizationData;
	}

	public void setGeolocalizationData(HashMap<Integer, Geolocalization> geolocalizationData) {
		this.geolocalizationData = geolocalizationData;
	}

	public HashMap<Integer, Orientation> getOrientationData() {
		return orientationData;
	}

	public void setOrientationData(HashMap<Integer, Orientation> orientationData) {
		this.orientationData = orientationData;
	}

	public HashMap<Integer, Time> getTimeData() {
		return timeData;
	}

	public void setTimeData(HashMap<Integer, Time> timeData) {
		this.timeData = timeData;
	}

	public HashMap<Integer, DateTime> getDateTimeData() {
		return dateTimeData;
	}

	public void setDateTimeData(HashMap<Integer, DateTime> dateTimeData) {
		this.dateTimeData = dateTimeData;
	}

	public HashMap<Integer, Velocity> getVelocityData() {
		return velocityData;
	}

	public void setVelocityData(HashMap<Integer, Velocity> velocityData) {
		this.velocityData = velocityData;
	}

}
