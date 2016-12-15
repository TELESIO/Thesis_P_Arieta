package data.manipulation.mp.worker;

import java.util.HashMap;

import data.manipulation.BasicDataOperations;
import data.manipulation.mp.OperatorBarrier;
import data.model.RawData;
import data.model.atomicData.Velocity;
import data.model.enums.Axis;
import data.model.structure.DataContainer;

public class BasicOperatorWorker extends Thread {

	private DataContainer data;
	private OperatorBarrier barrier;
	private BasicDataOperations operations = new BasicDataOperations();

	public BasicOperatorWorker(DataContainer range, OperatorBarrier barrier) {
		this.data = range;
		this.barrier = barrier;

	}

	@Override
	public void run() {
		HashMap<Integer, Velocity> a = data.getVelocityData();
		operations.toChangeVelocityMeasurementUnitAndValue(a);
		data.setVelocityData(a);

		HashMap<Integer, Double> a2 = data.getAxesAcceleration(Axis.y);
		operations.toSubcractVectorGravityValue(a2);
		data.setAccelerometerAxes(a2, Axis.y);

		HashMap<Integer, Double> a3 = data.getAxesAcceleration(Axis.z);
		operations.toChangeSign(a3);
		data.setAccelerometerAxes(a3, Axis.z);

		barrier.done();
	}

	public HashMap<Integer, RawData> getResult() {

		return data.getData();
	}
}
