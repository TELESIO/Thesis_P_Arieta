package data.manipulation.mp.worker;

import java.util.concurrent.CopyOnWriteArrayList;

import data.manipulation.BasicDataOperations;
import data.manipulation.mp.OperatorBarrier;
import data.model.RawData;
import data.model.atomicData.Time;
import data.model.atomicData.Velocity;
import data.model.enums.Axis;
import data.model.structure.DataContainer;

public class BasicOperatorWorker extends Worker {

    private DataContainer data;
    private OperatorBarrier barrier;
    private BasicDataOperations operations;

    public BasicOperatorWorker() {
	this.operations = new BasicDataOperations();
    }

    public BasicOperatorWorker(DataContainer range, OperatorBarrier barrier) {
	this.data = range;
	this.barrier = barrier;
	this.operations = new BasicDataOperations();

    }

    @Override
    public void set(DataContainer range, OperatorBarrier barrier) {
	this.data = range;
	this.barrier = barrier;

    }

    @Override
    public void run() {

	// This BasicOperators do:
	// to subtract the acceleration of gravity (9,81) from the vertical
	// acceleration data.
	// to change the sign of the acceleration along the z axis, in such a
	// way as to have an effective correspondence between accelerated and
	// braked.
	// to change the values ​​and units of the recording time (millisecond
	// -> second).
	// to change the values ​​and the speed unit (km / h -> m / s)

	CopyOnWriteArrayList<Velocity> velocity = data.getVelocityData();
	operations.toChangeVelocityMeasurementUnitAndValue(velocity);
	data.setVelocityData(velocity);

	CopyOnWriteArrayList<Double> verticalAcceleration = data.getAxesAcceleration(Axis.y);
	operations.toSubcractVectorGravityValue(verticalAcceleration);
	data.setAccelerometerAxes(verticalAcceleration, Axis.y);

	CopyOnWriteArrayList<Double> z_acceleration = data.getAxesAcceleration(Axis.z);
	operations.toChangeSign(z_acceleration);
	data.setAccelerometerAxes(z_acceleration, Axis.z);

	CopyOnWriteArrayList<Time> time = data.getTimeData();
	operations.toChangeTimeMeasurementValueAndUnit(time);
	data.setTimeData(time);
	barrier.done();
    }

    @Override
    public CopyOnWriteArrayList<RawData> getResult() {
	return data.getData();
    }
}
