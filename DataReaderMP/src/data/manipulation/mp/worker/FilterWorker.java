package data.manipulation.mp.worker;

import java.util.HashMap;

import data.manipulation.FilteringDataOperations;
import data.manipulation.mp.OperatorBarrier;
import data.model.RawData;

public class FilterWorker extends Thread {

	private HashMap<Integer, RawData> dataToHandle;
	private OperatorBarrier barrier;
	private FilteringDataOperations dataFiltered;

	public FilterWorker(HashMap<Integer, RawData> data, OperatorBarrier barrier) {
		this.dataToHandle = data;
		this.barrier = barrier;
		dataFiltered = new FilteringDataOperations();

	}

	@Override
	public void run() {
		dataFiltered.reduceLowVelocity(dataToHandle);
		dataFiltered.reduceHighVelocity(dataToHandle);
		barrier.done();
	}

	public HashMap<Integer, RawData> getResult() {
		return dataToHandle;
	}

}
