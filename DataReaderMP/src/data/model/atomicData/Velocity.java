package data.model.atomicData;

import data.model.enums.UnitMeasurementVelocity;

public class Velocity {
    @Override
    public String toString() {
	return "Velocity [velocity=" + velocity + ", unit_of_measurement=" + unit_of_measurement + "]";
    }

    double velocity;
    UnitMeasurementVelocity unit_of_measurement;

    public Velocity(double velocity, UnitMeasurementVelocity unit_of_measurement) {
	this.velocity = velocity;
	this.unit_of_measurement = unit_of_measurement;
    }

    public double getVelocity() {
	return velocity;
    }

    public void setVelocity(double velocity) {
	this.velocity = velocity;
    }

    public UnitMeasurementVelocity getUnit_of_measurement() {
	return unit_of_measurement;
    }

    public void setUnit_of_measurement(UnitMeasurementVelocity unit_of_measurement) {
	this.unit_of_measurement = unit_of_measurement;
    }
}
