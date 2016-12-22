package data.manipulation.mp.worker;

import java.util.concurrent.CopyOnWriteArrayList;

import data.manipulation.FilteringDataOperations;
import data.manipulation.mp.OperatorBarrier;
import data.model.RawData;
import data.model.structure.DataContainer;

public class FilterWorker extends Worker {

    private DataContainer data;
    private OperatorBarrier barrier;
    private FilteringDataOperations dataFiltered;

    public FilterWorker(DataContainer data, OperatorBarrier barrier) {
	this.data = data;
	this.barrier = barrier;
	this.dataFiltered = new FilteringDataOperations();
    }

    public FilterWorker() {
	this.dataFiltered = new FilteringDataOperations();
    }

    @Override
    public void run() {

	// dataFiltered.reduceLowVelocity(data);
	// dataFiltered.reduceHighVelocity(data);
	dataFiltered.simpleMovingAverageFilter(data);
	barrier.done();
    }

    @Override
    public CopyOnWriteArrayList<RawData> getResult() {
	return data.getData();
    }

    @Override
    public void set(DataContainer data, OperatorBarrier barrier) {
	this.data = data;
	this.barrier = barrier;
    }
}
