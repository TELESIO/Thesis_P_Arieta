package data.model;

import data.model.atomicData.Accelerometer;
import data.model.atomicData.DateTime;
import data.model.atomicData.Geolocalization;
import data.model.atomicData.Gravity;
import data.model.atomicData.Orientation;
import data.model.atomicData.Time;
import data.model.atomicData.Velocity;

public class RawData {

    Accelerometer accelerometer;
    Gravity gravity;
    Geolocalization geolocalization;
    Orientation orientation;
    Time time;
    DateTime dateTime;
    Velocity velocity;

    public RawData() {

    }

    public RawData(Accelerometer accelerometer, Gravity gravity, Geolocalization geolocalization,
	    Orientation orientation, Time time, DateTime dateTime, Velocity velocity) {
	this.accelerometer = accelerometer;
	this.gravity = gravity;
	this.geolocalization = geolocalization;
	this.orientation = orientation;
	this.time = time;
	this.dateTime = dateTime;
	this.velocity = velocity;
    }

    public Accelerometer getAccelerometer() {
	return accelerometer;
    }

    public void setAccelerometer(Accelerometer accelerometer) {
	this.accelerometer = accelerometer;
    }

    public Gravity getGravity() {
	return gravity;
    }

    public void setGravity(Gravity gravity) {
	this.gravity = gravity;
    }

    public Geolocalization getGeolocalization() {
	return geolocalization;
    }

    public void setGeolocalization(Geolocalization geolocalization) {
	this.geolocalization = geolocalization;
    }

    public Orientation getOrientation() {
	return orientation;
    }

    public void setOrientation(Orientation orientation) {
	this.orientation = orientation;
    }

    public Time getTime() {
	return time;
    }

    public void setTime(Time time) {
	this.time = time;
    }

    public DateTime getDateTime() {
	return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
	this.dateTime = dateTime;
    }

    public Velocity getVelocity() {
	return velocity;
    }

    public void setVelocity(Velocity velocity) {
	this.velocity = velocity;
    }

    public boolean isEmpty() {
	if (accelerometer == null && gravity == null && orientation == null && geolocalization == null && time == null
		&& dateTime == null && velocity == null)
	    return true;
	return false;
    }

    @Override
    public String toString() {
	return "RawData [accelerometer=" + accelerometer.toString() + ", gravity=" + gravity.toString()
		+ ", geolocalization=" + geolocalization.toString() + ", orientation=" + orientation.toString()
		+ ", time=" + time.toString() + ", dateTime=" + dateTime.toString() + ", velocity="
		+ velocity.toString() + "]";
    }

}
