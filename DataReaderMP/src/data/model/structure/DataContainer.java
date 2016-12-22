package data.model.structure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CopyOnWriteArrayList;

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
    CopyOnWriteArrayList<Accelerometer> accelerometerData;
    CopyOnWriteArrayList<Gravity> gravityData;
    CopyOnWriteArrayList<Geolocalization> geolocalizationData;
    CopyOnWriteArrayList<Orientation> orientationData;
    CopyOnWriteArrayList<Time> timeData;
    CopyOnWriteArrayList<DateTime> dateTimeData;
    CopyOnWriteArrayList<Velocity> velocityData;

    public DataContainer() {
	accelerometerData = new CopyOnWriteArrayList<Accelerometer>();
	gravityData = new CopyOnWriteArrayList<Gravity>();
	geolocalizationData = new CopyOnWriteArrayList<Geolocalization>();
	orientationData = new CopyOnWriteArrayList<Orientation>();
	timeData = new CopyOnWriteArrayList<Time>();
	dateTimeData = new CopyOnWriteArrayList<DateTime>();
	velocityData = new CopyOnWriteArrayList<Velocity>();
	size = 0;

    }

    public boolean isEmpty() {
	return (size > 0) ? true : false;
    }

    public void put(RawData r) {

	accelerometerData.add(r.getAccelerometer());
	gravityData.add(r.getGravity());
	geolocalizationData.add(r.getGeolocalization());
	orientationData.add(r.getOrientation());
	timeData.add(r.getTime());
	velocityData.add(r.getVelocity());
	dateTimeData.add(r.getDateTime());
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
	size = 0;

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

    public CopyOnWriteArrayList<RawData> getData() {
	CopyOnWriteArrayList<RawData> data = new CopyOnWriteArrayList<RawData>();
	for (int i = 0; i < size; i++)
	    data.add(buildRawDataFromIndex(i));
	return data;
    }

    public CopyOnWriteArrayList<Double> getAxesAcceleration(Axis axes) {
	CopyOnWriteArrayList<Double> toReturn = new CopyOnWriteArrayList<Double>();
	for (Accelerometer accelerometer : accelerometerData) {
	    try {
		toReturn.add(
			(Double) accelerometer.getClass().getMethod("getAcceleration_" + axes).invoke(accelerometer));
	    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
		    | NoSuchMethodException | SecurityException e) {
		e.printStackTrace();
	    }
	}
	return toReturn;

    }

    public void setAccelerometerAxes(CopyOnWriteArrayList<Double> value, Axis axes) {

	for (int i = 0; i < value.size(); i++) {
	    Method method = null;
	    try {
		method = Accelerometer.class.getMethod("setAcceleration_" + axes, double.class);
		method.invoke(accelerometerData.get(i), value.get(i));
	    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
		    | InvocationTargetException e1) {
		e1.printStackTrace();
	    }

	}

    }

    public CopyOnWriteArrayList<Double> getAxesGravity(Axis axes) {
	CopyOnWriteArrayList<Double> toReturn = new CopyOnWriteArrayList<Double>();

	for (Gravity gravity : gravityData) {
	    try {
		toReturn.add((Double) gravity.getClass().getMethod("getGravity_" + axes).invoke(gravity));
	    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
		    | NoSuchMethodException | SecurityException e) {
		e.printStackTrace();
	    }
	}
	return toReturn;
    }

    public void setGravityAxes(CopyOnWriteArrayList<Double> value, Axis axes) {

	for (int i = 0; i < gravityData.size(); i++) {
	    Method method = null;
	    try {
		method = Gravity.class.getMethod("setGravity_" + axes, double.class);
		method.invoke(gravityData.get(i), value.get(i));
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

    public CopyOnWriteArrayList<Accelerometer> getAccelerometerData() {
	return accelerometerData;
    }

    public void setAccelerometerData(CopyOnWriteArrayList<Accelerometer> accelerometerData) {
	this.accelerometerData = accelerometerData;
    }

    public CopyOnWriteArrayList<Gravity> getGravityData() {
	return gravityData;
    }

    public void setGravityData(CopyOnWriteArrayList<Gravity> gravityData) {
	this.gravityData = gravityData;
    }

    public CopyOnWriteArrayList<Geolocalization> getGeolocalizationData() {
	return geolocalizationData;
    }

    public void setGeolocalizationData(CopyOnWriteArrayList<Geolocalization> geolocalizationData) {
	this.geolocalizationData = geolocalizationData;
    }

    public CopyOnWriteArrayList<Orientation> getOrientationData() {
	return orientationData;
    }

    public void setOrientationData(CopyOnWriteArrayList<Orientation> orientationData) {
	this.orientationData = orientationData;
    }

    public CopyOnWriteArrayList<Time> getTimeData() {
	return timeData;
    }

    public void setTimeData(CopyOnWriteArrayList<Time> timeData) {
	this.timeData = timeData;
    }

    public CopyOnWriteArrayList<DateTime> getDateTimeData() {
	return dateTimeData;
    }

    public void setDateTimeData(CopyOnWriteArrayList<DateTime> dateTimeData) {
	this.dateTimeData = dateTimeData;
    }

    public CopyOnWriteArrayList<Velocity> getVelocityData() {
	return velocityData;
    }

    public void setVelocityData(CopyOnWriteArrayList<Velocity> velocityData) {
	this.velocityData = velocityData;
    }

    public void remove(int index) {
	accelerometerData.remove(index);
	gravityData.remove(index);
	geolocalizationData.remove(index);
	orientationData.remove(index);
	timeData.remove(index);
	dateTimeData.remove(index);
	velocityData.remove(index);

	size--;

    }

}
