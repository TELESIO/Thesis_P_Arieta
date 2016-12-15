package data.manipulation;

import java.util.HashMap;
import java.util.Map;

import data.model.atomicData.Time;
import data.model.atomicData.Velocity;
import data.model.enums.UnitMeasurementTime;
import data.model.enums.UnitMeasurementVelocity;

public class BasicDataOperations {

	private final int SIGN_CHANGE = -1;
	private final double GRAVITY_MS_VALUE = 9.81;
	private final double VELOCITY_CONSTANT_OF_TRANSFORMATION = 3.6;
	private final double TIME_CONSTANT_OF_TRANSFORMATION = 1000;

	private double changeValueSign(double value) {
		return value * SIGN_CHANGE;

	}

	public void toChangeSign(HashMap<Integer, Double> values) {
		for (Map.Entry<Integer, Double> a : values.entrySet())
			a.setValue(changeValueSign(a.getValue()));
	}

	private double subractGravity(double value) {
		return value - GRAVITY_MS_VALUE;
	}

	public void toSubcractVectorGravityValue(HashMap<Integer, Double> values) {
		for (Map.Entry<Integer, Double> a : values.entrySet())
			a.setValue(subractGravity(a.getValue()));
	}

	public void toChangeVelocityMeasurementUnitAndValue(HashMap<Integer, Velocity> values) {

		for (Map.Entry<Integer, Velocity> a : values.entrySet())
			a.setValue(convertVelocity(a.getValue()));

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

	public void toChangeTimeMeasurementValueAndUnit(HashMap<Integer, Time> values) {
		for (Map.Entry<Integer, Time> a : values.entrySet())
			a.setValue(convertTime(a.getValue()));
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
