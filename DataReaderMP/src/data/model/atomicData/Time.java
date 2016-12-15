package data.model.atomicData;

import data.model.enums.UnitMeasurementTime;

public class Time {
    @Override
    public String toString() {
	return "Time [time=" + time + ", unit=" + unit_of_measurement + "]";
    }

    double time;
    UnitMeasurementTime unit_of_measurement;

    public Time() {
    }

    public Time(double time, UnitMeasurementTime unit) {
	this.time = time;
	this.unit_of_measurement = unit;
    }

    public double getTime() {
	return time;
    }

    public void setTime(double time) {
	this.time = time;
    }

    public UnitMeasurementTime getUnit() {
	return unit_of_measurement;
    }

    public void setUnit(UnitMeasurementTime unit) {
	this.unit_of_measurement = unit;
    }

}
