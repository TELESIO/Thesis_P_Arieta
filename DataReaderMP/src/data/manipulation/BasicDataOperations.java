package data.manipulation;

import java.util.concurrent.CopyOnWriteArrayList;

import data.model.atomicData.Time;
import data.model.atomicData.Velocity;
import data.model.enums.UnitMeasurementTime;
import data.model.enums.UnitMeasurementVelocity;

public class BasicDataOperations {

    private final int SIGN_CHANGE = -1;
    private final double GRAVITY_MS_VALUE = 9.81d;
    private final double VELOCITY_CONSTANT_OF_TRANSFORMATION = 3.6d;
    private final double TIME_CONSTANT_OF_TRANSFORMATION = 1000d;

    private double changeValueSign(double value) {
	return value * SIGN_CHANGE;

    }

    public void toChangeSign(CopyOnWriteArrayList<Double> values) {
	for (int i = 0; i < values.size(); i++)
	    values.set(i, changeValueSign(values.get(i)));
    }

    private double subractGravity(double value) {
	return value - GRAVITY_MS_VALUE;
    }

    public void toSubcractVectorGravityValue(CopyOnWriteArrayList<Double> values) {
	for (int i = 0; i < values.size(); i++)
	    values.set(i, subractGravity(values.get(i)));
    }

    public void toChangeVelocityMeasurementUnitAndValue(CopyOnWriteArrayList<Velocity> values) {

	for (int i = 0; i < values.size(); i++)
	    values.set(i, convertVelocity(values.get(i)));

    }

    private Velocity convertVelocity(Velocity velocity) {
	if (velocity.getUnit_of_measurement() == UnitMeasurementVelocity.KILOMETERS_PER_HOURS) {
	    velocity.setVelocity(velocity.getVelocity() / VELOCITY_CONSTANT_OF_TRANSFORMATION);
	    velocity.setUnit_of_measurement(UnitMeasurementVelocity.METERS_PER_SECOND);
	}

	else {
	    velocity.setVelocity(velocity.getVelocity() * VELOCITY_CONSTANT_OF_TRANSFORMATION);
	    velocity.setUnit_of_measurement(UnitMeasurementVelocity.KILOMETERS_PER_HOURS);
	}

	return velocity;
    }

    public void toChangeTimeMeasurementValueAndUnit(CopyOnWriteArrayList<Time> values) {
	for (int i = 0; i < values.size(); i++)
	    values.set(i, convertTime(values.get(i)));
    }

    private Time convertTime(Time time) {
	if (time.getUnit() == UnitMeasurementTime.MILLISECOND) {
	    time.setTime(time.getTime() / TIME_CONSTANT_OF_TRANSFORMATION);
	    time.setUnit(UnitMeasurementTime.SECOND);
	} else {
	    time.setTime(time.getTime() * TIME_CONSTANT_OF_TRANSFORMATION);
	    time.setUnit(UnitMeasurementTime.MILLISECOND);
	}

	return time;
    }
}
